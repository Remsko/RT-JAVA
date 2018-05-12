package main;

import geometry.Sphere;
import scene.World;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Main
{
	public static World world;
	public static Image image;
	
	public static void main(String [] args)
	{
		long start = System.nanoTime();

		world = new World(1600, 900);
		image = new Image("Image.png");
		
		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, 0.0), new Color(1.0, 0.0, 0.0), 60.0);
		
		for (int y = 0; y < world.viewplane.height; y++)
		{
			for (int x = 0; x < world.viewplane.width; x++)
			{
				Ray ray = new Ray(new Point3D(x - world.viewplane.width / 2 + 0.5,
						world.viewplane.height / 2 - y + 0.5, 70),
						new Vector3D(0.0, 0.0, -1.0));
				if (sphere.hit(ray) != 0.0)
					image.buffer.setRGB(x, y, sphere.color.toInteger());
				else
				{
					image.buffer.setRGB(x, y, 0);
				}
			}
		}
		image.write("PNG");
		
		long end = System.nanoTime();
		System.out.println("Loop Time: " + (end - start) / 10.0E8f);
	}
}