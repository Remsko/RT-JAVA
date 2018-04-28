package main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Driver
{
	private static final int width = 1600;
	private static final int height = 900;
	
	public static void main(String[] args)
	{
		long	start;
		long	end;	
		File image = new File("Image.png");
		BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		start = System.nanoTime();
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				buffer.setRGB(x, y, 19489);
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
		
		end = System.nanoTime();
		System.out.println("Loop time: " + (end - start) / 1000000000.0F);
	}
}
