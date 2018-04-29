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
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				Ray ray = new Ray(new Origin(x - width / 2.0 + 0.5, y - height / 2.0 + 0.5, 100),
									new Direction(0.0, 0.0, -1.0));
				if (sphere.hit(ray) != 0.0)
					buffer.setRGB(x, y, sphere.color.toInteger());
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
