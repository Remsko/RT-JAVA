package main;

import scene.World;
import utility.Point3D;
import intersection.Intersection;
import lightning.Lightning;
import main.Tracer;
import projection.Camera;
import projection.Perspective;
import projection.Projection;
import sampling.RegularSample;
import sampling.Sampler;

public class Main
{
	public static World world;
	public static Image image;
	public static Tracer tracer;
	public static Sampler sampler;
	public static Projection projection;
	public static Camera camera;
	public static Intersection intersection;
	public static Lightning lightning;
	
	public static void main(String [] args)
	{
		long start = System.nanoTime();

		world = new World(1600, 900);
		image = new Image("Image.png");
		tracer = new Tracer();
		sampler = new RegularSample(1);
		camera = new Camera(new Point3D(0.0, 0.0, -200.0), 60);
		projection = new Perspective();
		intersection = new Intersection();
		lightning = new Lightning();
		
		for (int y = 0; y < world.viewplane.height; y++)
		{
			for (int x = 0; x < world.viewplane.width; x++)
			{
				tracer.trace(x, y);
			}
		}
		image.write("PNG");
		
		long end = System.nanoTime();
		System.out.println("Loop Time: " + (end - start) / 10.0E8f);
	}
}