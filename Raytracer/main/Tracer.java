package main;

import intersection.Intersection;
import utility.Color;
import utility.Point2D;
import utility.Ray;
import utility.Vector3D;

public class Tracer
{
	public Color recursiveGetColor(Ray ray, int rebound)
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
				
				reboundedRay.origin = intersection.position;
				reboundedRay.direction = ray.direction.sub(normal.mul(2.0 * normal.dot(ray.direction)));
				
				tmpColor.add(recursiveGetColor(reboundedRay, rebound - 1).mul_ret(0.8));
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
				color.add(recursiveGetColor(ray, Main.world.maxRayRebound));
			}
		}
		color.divide(Main.sampler.samples * Main.sampler.samples);
		Main.image.buffer.setRGB(x, y, color.toInteger());
	}
}
