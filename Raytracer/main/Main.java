package main;

import scene.World;
import utility.Point3D;
import intersection.Quadratic;
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
	public static Lightning lightning;
	public static Quadratic quadratic;
	
	public static void main(String [] args)
	{
		long start = System.nanoTime();

		world = new World(3000, 1800);
		image = new Image("Image.png");
		tracer = new Tracer();
		sampler = new RegularSample(16);
		camera = new Camera(new Point3D(100.0, 10.0, -100.0), 60);
		projection = new Perspective();
		lightning = new Lightning();
		quadratic = new Quadratic();
		
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