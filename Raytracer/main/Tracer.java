package main;

import geometry.Fresnel;
import intersection.Intersection;
import utility.Color;
import utility.Point2D;
import utility.Ray;
import utility.Vector3D;

public class Tracer
{
	public final double bias = 0.0001;
	public final Vector3D vector = new Vector3D();

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
				Ray reboundedRay = new Ray();
				Vector3D normal = intersection.object.getNormal(intersection);
				boolean outside = normal.dot(ray.direction) < 0.0 ? true : false;
				Vector3D bias = normal.mul(this.bias);
				
				reboundedRay.origin = outside == true ? intersection.position.add(bias) : intersection.position.sub(bias);
				reboundedRay.direction = vector.getReflected(ray.direction, normal);
				
				/* use reflective coefficient */
				tmpColor.mul(1 - intersection.object.type.coefficient);
				tmpColor.add(recursiveThrowRay(reboundedRay, rebound - 1).mul_ret(intersection.object.type.coefficient));
			
				/* add to the color directly */
				//tmpColor.add(recursiveThrowRay(reboundedRay, rebound - 1).mul_ret(0.8));
			}
			if (intersection.object.type.getType().equals("refractive") == true && rebound > 0)
			{/*
				double absorbDist = intersection.farthestPosition.sub_vec(intersection.closestPosition).len();

				Color absorbCoef = new Color(0.01, 0.02, 0.06);
				Color absorb = new Color((1.0 - Math.exp(-absorbCoef.r * (absorbDist))),
										(1.0 - Math.exp(-absorbCoef.g * (absorbDist))),
										(1.0 - Math.exp(-absorbCoef.b * (absorbDist))));
				tmpColor.mul(absorb);*/
				
				Ray transmittedRay = new Ray();
				
				Vector3D normal = intersection.object.getNormal(intersection);
				boolean outside = normal.dot(ray.direction) < 0.0 ? true : false;
				Vector3D bias = normal.mul(this.bias);
				
				if (outside == true)
					transmittedRay.origin = intersection.position.sub(bias);
				else
					transmittedRay.origin = intersection.position.add(bias);
				transmittedRay.direction = vector.getRefracted(ray.direction, normal, intersection.object.type.IOR);
				
				/* use refractive coefficient */
				//tmpColor.mul(1 - intersection.object.type.coefficient);
				tmpColor.add(recursiveThrowRay(transmittedRay, rebound - 1).mul_ret(intersection.object.type.coefficient));
			
				/* add to the color directly */
				//tmpColor.add(recursiveThrowRay(transmittedRay, rebound - 1).mul_ret(0.8));
			}
			if (intersection.object.type.getType().equals("fresnel") == true && rebound > 0)
			{/*
				double absorbDist = intersection.farthestPosition.sub_vec(intersection.closestPosition).len();

				Color absorbCoef = new Color(0.01, 0.02, 0.06);
				Color absorb = new Color((1.0 - Math.exp(-absorbCoef.r * (absorbDist))),
										(1.0 - Math.exp(-absorbCoef.g * (absorbDist))),
										(1.0 - Math.exp(-absorbCoef.b * (absorbDist))));
				tmpColor.mul(absorb);*/
				Vector3D normal = intersection.object.getNormal(intersection);
				boolean outside = normal.dot(ray.direction) < 0.0 ? true : false;
				Vector3D bias = normal.mul(this.bias);
				
				Color refractionColor = new Color();
				
				double f = new Fresnel().getCoefficient(ray.direction, normal, intersection.object.type.IOR);
				
				if (f < 1.0)
				{
					Ray refractedRay = new Ray();
					refractedRay.origin = outside == true ? intersection.position.sub(bias) : intersection.position.add(bias);
					refractedRay.direction = vector.getRefracted(ray.direction, normal, intersection.object.type.IOR);
					
					refractionColor = recursiveThrowRay(refractedRay, rebound - 1);
				}
				
				Ray reflectedRay = new Ray();
				reflectedRay.origin = outside == true ? intersection.position.add(bias) : intersection.position.sub(bias);
				reflectedRay.direction = vector.getReflected(ray.direction, normal);
				
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
