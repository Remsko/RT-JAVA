package main;

import intersection.Intersection;
import utility.Color;
import utility.Point2D;
import utility.Ray;
import utility.Vector3D;

public class Tracer
{
	public final double bias = 0.0001;

	public Vector3D getReflected(Vector3D incident, Vector3D normal)
	{
		/*
		 * Reflected vector R formula for I and N, incident and normal vector :
		 * 
		 * R = I - 2 * (I�N) * N
		 * 
		 */
		
		Vector3D reflected = incident.sub(normal.mul(2.0 * normal.dot(incident)));
		
		reflected.normalize();
		return (reflected);
	}
	
	public Vector3D getRefracted(Vector3D incident, Vector3D normal, double IOR)
	{
		/*
		 * Refracted vector T formula for IOR (index of refraction), I and N, incident and normal vector :
		 * 
		 * Snell :
		 * eta = eta1 / eta2
		 * 
		 * c1 = I�N
		 * c2 = sqrt(1 - eta * eta * (1 - c1 * c1))
		 *
		 * T = eta * I + (eta * c1 - c2) * N
		 */	
		Vector3D refracted = new Vector3D();
		double c1 = normal.dot(incident);
		double eta1;
		double eta2;
		
		if (c1 < 0.0)
		{
			c1 = -c1;

			eta1 = 1.0;
			eta2 = IOR;
		}
		else
		{
			normal = normal.mul(-1.0);
		
			eta2 = 1.0;
			eta1 = IOR;	
		}
		double eta = eta1 / eta2;
		double c2 = 1.0 - eta * eta * (1 - c1 * c1);
		if (c2 > 0.0)
		{
			refracted = incident.mul(eta).add(normal.mul(eta * c1 - Math.sqrt(c2)));
			refracted.normalize();
		}
		return (refracted);
	}
	
	public double fresnel(Vector3D incident, Vector3D normal, double IOR)
	{
		double cosI = normal.dot(incident);
		double eta1;
		double eta2;
		
		if (cosI < 0.0)
		{
			eta1 = 1.0;
			eta2 = IOR;
		}
		else
		{
			eta2 = 1.0;
			eta1 = IOR;
		}
		double sinT = (eta1 / eta2) * Math.sqrt(Math.max(0.0, 1 - cosI * cosI));
		if (sinT >= 1.0)
			return (1.0);
		else
		{
			double cosT = Math.sqrt(Math.max(0.0, 1 - sinT * sinT));
			cosI = Math.abs(cosI);
			double Rs = ((eta2 * cosI) - (eta1 * cosT)) / ((eta2 * cosI) + (eta1 * cosT));
			double Rp = ((eta1 * cosI) - (eta2 * cosT)) / ((eta1 * cosI) + (eta2 * cosT));
			return ((Rs * Rs + Rp * Rp) / 2);
		}
	}
	
	public Color recursiveThrowRay(Ray ray, int rebound)
	{
		Intersection intersection;
		Color tmpColor;
		
		intersection = new Intersection(ray);
		
		if (intersection.object != null)
		{
			tmpColor = Main.lightning.PhongShading(intersection, ray);
			/* reflective case */
			if (intersection.object.type.getType().equals("reflective") == true && rebound > 0)
			{
				Ray reboundedRay = new Ray();
				Vector3D normal = intersection.object.getNormal(intersection);
				boolean outside = normal.dot(ray.direction) < 0.0 ? true : false;
				Vector3D bias = normal.mul(this.bias);
				
				reboundedRay.origin = outside == true ? intersection.position.sub(bias) : intersection.position.add(bias);
				reboundedRay.direction = getReflected(ray.direction, normal);
				
				/* use reflective coefficient */
				//tmpColor.mul(1 - intersection.object.type.coefficient);
				//tmpColor.add(recursiveThrowRay(reboundedRay, rebound - 1).mul_ret(intersection.object.type.coefficient));
			
				/* add to the color directly */
				tmpColor.add(recursiveThrowRay(reboundedRay, rebound - 1).mul_ret(0.8));
			}
			/* refractive case */
			if (intersection.object.type.getType().equals("refractive") == true && rebound > 0)
			{
				Ray transmittedRay = new Ray();
				
				Vector3D normal = intersection.object.getNormal(intersection);
				boolean outside = normal.dot(ray.direction) < 0.0 ? true : false;
				Vector3D bias = normal.mul(this.bias);
				
				if (outside == true)
					transmittedRay.origin = intersection.position.sub(bias);
				else
					transmittedRay.origin = intersection.position.add(bias);
				transmittedRay.direction = getRefracted(ray.direction, normal, intersection.object.type.IOR);
				
				/* use refractive coefficient */
				tmpColor.mul(1 - intersection.object.type.coefficient);
				tmpColor.add(recursiveThrowRay(transmittedRay, rebound - 1).mul_ret(intersection.object.type.coefficient));
			
				/* add to the color directly */
				//tmpColor.add(recursiveThrowRay(transmittedRay, rebound - 1).mul_ret(0.8));
			}
			/* reflective and refractive case */
			if (intersection.object.type.getType().equals("fresnel") == true && rebound > 0)
			{
				double absorbDist = intersection.farthestPosition.sub_vec(intersection.closestPosition).len();

				Color absorbCoef = new Color(0.01, 0.02, 0.06);
				Color absorb = new Color(1.0 - Math.exp(-absorbCoef.r * (absorbDist)),
										1.0 - Math.exp(-absorbCoef.g * (absorbDist)),
										1.0 - Math.exp(-absorbCoef.b * (absorbDist)));
				tmpColor.mul(absorb);
				
				Vector3D normal = intersection.object.getNormal(intersection);
				boolean outside = normal.dot(ray.direction) < 0.0 ? true : false;
				Vector3D bias = normal.mul(this.bias);
				
				Color refractionColor = new Color();
				
				double f = fresnel(ray.direction, normal, intersection.object.type.IOR);
				
				if (f < 1.0)
				{
					Ray refractedRay = new Ray();
					refractedRay.origin = outside == true ? intersection.position.sub(bias) : intersection.position.add(bias);
					refractedRay.direction = getRefracted(ray.direction, normal, intersection.object.type.IOR);
					
					refractionColor = recursiveThrowRay(refractedRay, rebound - 1);
				}
				
				Ray reflectedRay = new Ray();
				reflectedRay.origin = outside == true ? intersection.position.sub(bias) : intersection.position.add(bias);
				reflectedRay.direction = getReflected(ray.direction, normal);
				
				Color reflectionColor = recursiveThrowRay(reflectedRay, rebound - 1);
				
				// final color += reflection's color * f + refraction's color * (1 - f)
				tmpColor.add(reflectionColor.mul_ret(f).add_ret(refractionColor.mul_ret(1.0 - f)));
			}
		}
		else
			tmpColor = Main.world.background;
		return (tmpColor);
	}
	
	public void trace(int x, int y)
	{
		Point2D	point;
		Ray ray;
		Color color = new Color(0.0, 0.0, 0.0);
		
		for (int row = 0; row < Main.sampler.samples; row++)
		{
			for (int col = 0; col < Main.sampler.samples; col++)
			{
				point = Main.sampler.sample(row, col, x, y);
				ray = Main.projection.createRay(point);
				color.add(recursiveThrowRay(ray, Main.world.maxRayRebound));
			}
		}
		color.divide(Main.sampler.samples * Main.sampler.samples);
		Main.image.buffer.setRGB(x, y, color.toInteger());
	}
}
