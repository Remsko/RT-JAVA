package main;

import intersection.Intersection;
import utility.Color;
import utility.Point2D;
import utility.Ray;

public class Tracer
{	
	public void trace(int x, int y)
	{
		Point2D	point;
		Ray ray;
		Intersection intersection;
		Color tmpColor;
		Color color = new Color(0.0, 0.0, 0.0);
		
		for (int row = 0; row < Main.sampler.samples; row++)
		{
			for (int col = 0; col < Main.sampler.samples; col++)
			{
				point = Main.sampler.sample(row, col, x, y);
				ray = Main.projection.createRay(point);
				intersection = new Intersection(ray);
				if (intersection.hit == true)
					tmpColor = Main.lightning.PhongShading(intersection, ray);
				else
					tmpColor = Main.world.background;
				color.add(tmpColor);
			}
		}
		color.divide(Main.sampler.samples * Main.sampler.samples);
		Main.image.buffer.setRGB(x, y, color.toInteger());
	}
}
