package main;

import utility.Color;
import utility.Point2D;
import utility.Ray;

public class Tracer
{
	public void trace(int x, int y)
	{
		Ray		ray;
		Color	color = new Color(0.0, 0.0, 0.0);
		Color	tmpColor;
		Point2D	point;
		double	min, tmp;
		
		
		for (int row = 0; row < Main.sampler.samples; row++)
		{
			for (int col = 0; col < Main.sampler.samples; col++)
			{
				point = Main.sampler.sample(row, col, x, y);
				ray = Main.projection.createRay(point);
				
				min = Double.MAX_VALUE;
				tmpColor = Main.world.background;
				
				for (int i = 0; i < Main.world.objects.size(); i++)
				{
					tmp = Main.world.objects.get(i).hit(ray);
					
					if (tmp != 0.0 && tmp < min)
					{
						min = tmp;
						tmpColor = Main.world.objects.get(i).color;
					}
				}
				color.add(tmpColor);
			}
		}
		color.divide(Main.sampler.samples * Main.sampler.samples);
		Main.image.buffer.setRGB(x, y, color.toInteger());
	}
}
