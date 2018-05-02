package main;

import geometry.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import utility.*;

public class Driver
{
	private static final int width = 1600;
	private static final int height = 900;
	
	public static void main(String[] args)
	{
		long	start = System.nanoTime();	
		File image = new File("Image.png");
		
		BufferedImage buffer = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		
		Sphere sphere = new Sphere(new Origin(0.0, 0.0, 0.0),
				60.0, new Color(1.0f, 0.0f, 1.0f));
		
		Plane plane = new Plane(new Origin(0.0, -60.0, 0.0),
				new Normal(1.0, 1.0, 0.0), new Color(1.0f, 1.0f, 0.0f));
				
		Cone cone = new Cone(new Origin(400.0, 0.0, 0.0),
						10.0, new Color(0.0f, 1.0f, 1.0f));
		
		Cylinder cylinder = new Cylinder(new Origin(200.0, 0.0, 0.0),
				30.0, new Color(1.0f, 0.0f, 0.0f));
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				Ray ray = new Ray(new Origin(x - width / 2.0 + 0.5, height / 2.0 + 0.5 - y, 100),
									new Direction(0.0, 0.0, -1.0));
				if (sphere.hit(ray) != 0.0)
					buffer.setRGB(x, y, sphere.color.toInteger());
				else if (plane.hit(ray) != 0.0)
					buffer.setRGB(x, y, plane.color.toInteger());
				else if (cylinder.hit(ray) != 0.0)
					buffer.setRGB(x, y, cylinder.color.toInteger());
				else if (cone.hit(ray) != 0.0)
					buffer.setRGB(x, y, cone.color.toInteger());
				else
					buffer.setRGB(x, y, 0);
			}
		}
		try
		{
			ImageIO.write(buffer, "PNG", image);
		}
		catch (Exception e)
		{
			System.out.println("Couldn't write image.");
			System.exit(1);
		}	
		long end = System.nanoTime();
		System.out.println("Loop time: " + (end - start) / 10.0E8f);
	}
}
