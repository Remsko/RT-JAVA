package main;

import intersection.Intersection;
import utility.Color;
import utility.Point2D;
import utility.Ray;
import utility.Vector3D;

public class Tracer
{
	public final double bias = 0.0001;
	
	public double fresnel(Vector3D incident, Vector3D normal, double IOR)
	{
		double cosI = normal.dot(incident);
		double η1;
		double η2;
		
		if (cosI < 0.0)
		{
			η1 = 1.0;
			η2 = IOR;
		}
		else
		{
			η2 = 1.0;
			η1 = IOR;
		}
		
		double sinT = (η1 / η2) * Math.sqrt(Math.max(0.0, 1 - cosI * cosI));
		
		if (sinT >= 1.0)
			return (1.0);
		else
		{
			double cosT = Math.sqrt(Math.max(0.0, 1 - sinT * sinT));
			cosI = Math.abs(cosI);
			double Rs = ((η2 * cosI) - (η1 * cosT)) / ((η2 * cosI) + (η1 * cosT));
			double Rp = ((η1 * cosI) - (η2 * cosT)) / ((η1 * cosI) + (η2 * cosT));
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
			if (intersection.object.type.getType().equals("reflective") == true && rebound > 0)
			{
				/*
				 * Reflected vector R formula for I and N, incident and normal vector :
				 * 
				 * R = I - 2 * (I.N) * N
				 * 
				 */
				
				Ray reboundedRay = new Ray();
				Vector3D normal = intersection.object.getNormal(intersection);
				
				reboundedRay.origin = intersection.position;
				reboundedRay.direction = ray.direction.sub(normal.mul(2.0 * normal.dot(ray.direction)));
				
				tmpColor.mul(1 - intersection.object.type.coefficient);
				tmpColor.add(recursiveThrowRay(reboundedRay, rebound - 1).mul_ret(intersection.object.type.coefficient));
			}
			if (intersection.object.type.getType().equals("refractive") == true && rebound > 0)
			{
				/*
				 * Refracted vector T formula for IOR (index of refraction), I and N, incident and normal vector :
				 * 
				 * Snell :
				 * η = η1 / η2
				 * 
				 * c1 = I.N
				 * c2 = sqrt(1 - η * η * (1 - c1 * c1))
				 *
				 * T = η * I + (η * c1 - c2) * N
				 * 
				 */
				
				Ray transmittedRay = new Ray();
				
				Vector3D normal = intersection.object.getNormal(intersection);
				Vector3D bias = normal.mul(this.bias);
				
				double c1 = normal.dot(ray.direction);
				double η1;
				double η2;
				boolean outside;
				
				if (c1 < 0.0)
				{
					c1 = -c1;

					outside = true;
					η1 = 1.0;
					η2 = intersection.object.type.IOR;
				}
				else
				{
					normal = normal.mul(-1.0);
				
					outside = false;
					η2 = 1.0;
					η1 = intersection.object.type.IOR;	
				}
				
				double η = η1 / η2;
				double c2 = 1.0 - η * η * (1 - c1 * c1);
				
				if (outside == true)
					transmittedRay.origin = intersection.position.sub(bias);
				else
					transmittedRay.origin = intersection.position.add(bias);
				
				if (c2 > 0.0)
				{
					transmittedRay.direction = ray.direction.mul(η).add(normal.mul(η * c1 - Math.sqrt(c2)));
					transmittedRay.direction.normalize();
				}
				
				tmpColor.mul(1 - intersection.object.type.coefficient);
				tmpColor.add(recursiveThrowRay(transmittedRay, rebound - 1).mul_ret(intersection.object.type.coefficient));
			}
			if (intersection.object.type.getType().equals("fresnel") == true && rebound > 0)
			{
				Vector3D normal = intersection.object.getNormal(intersection);
				boolean outside = normal.dot(ray.direction) < 0.0 ? true : false;
				Vector3D bias = normal.mul(this.bias);
				
				Color refractionColor = new Color();
				
				double f = fresnel(ray.direction, normal, intersection.object.type.IOR);
				
				if (f < 1.0)
				{
					Ray refractedRay = new Ray();
					refractedRay.origin = outside == true ? intersection.position.sub(bias) : intersection.position.add(bias);
					refractedRay.direction = new Vector3D();
					
					refractionColor = recursiveThrowRay(refractedRay, rebound - 1);
				}
				
				Ray reflectedRay = new Ray();
				reflectedRay.origin = outside == true ? intersection.position.sub(bias) : intersection.position.add(bias);
				reflectedRay.direction = new Vector3D();
				
				Color reflectionColor = recursiveThrowRay(reflectedRay, rebound - 1);
				
				/* final color += reflection color * f + refraction color * (1 - f) */
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
