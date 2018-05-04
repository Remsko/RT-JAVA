package main;

import geometry.*;
import light.LightObject;

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
		
		GeometricObject[] objArr = new GeometricObject[4];
		
		objArr[0] = new Sphere(new Origin(0.0, 0.0, -50.0),
				10.0, new Color(1.0f, 0.0f, 0.0f), new Color(1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f));
		
		objArr[1] = new Plane(new Origin(0.0, -10.0, 0.0),
				new Normal(0, 1.0, 0.0), new Color(0.0f, 0.7f, 1.0f), new Color(1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f));
				
		objArr[2] = new Cone(new Origin(400.0, 0.0, 0.0),
						10.0, new Color(1.0f, 0.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f));
		
		objArr[3] = new Cylinder(new Origin(200.0, 0.0, 0.0),
				30.0, new Color(1.0f, 1.0f, 0.0f), new Color(1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f));
		
		LightObject[] lightArr = new LightObject[2];
		
		lightArr[0] = new LightObject(new Origin(500.0, 500.0, 500.0), new Color(1.0f, 1.0f, 1.0f), 1.0);
		lightArr[1] = new LightObject(new Origin(-500.0, -500.0, -500.0), new Color(1.0f, 1.0f, 1.0f), 1.0);
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				Ray ray = new Ray(new Origin(x - width / 2.0 + 0.5, height / 2.0 + 0.5 - y, 100),
									new Direction(0.0, 0.0, -1.0));
				Intersection inter = new Intersection();
				if (inter.closestHit(objArr, ray) != 0.0)
				{
					Color c = lightArr[0].Process(lightArr, inter, ray);
					buffer.setRGB(x, y, c.toInteger());
				}
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
