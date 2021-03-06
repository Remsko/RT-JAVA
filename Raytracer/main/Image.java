package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Main;

public class Image
{
	public BufferedImage buffer;
	public File image;
	
	public Image(String filename)
	{
		image = new File(filename);
		buffer = new BufferedImage(Main.world.viewplane.width,
			Main.world.viewplane.height,
			BufferedImage.TYPE_INT_RGB);
	}
	
	public void write(String filetype)
	{
		try
		{
			ImageIO.write(buffer, filetype, image);
		}
		catch (IOException e)
		{
			System.out.println("Couldn't write image.");
			System.exit(1);
		}
	}
}