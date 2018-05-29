package main;

import scene.World;
import utility.Point3D;
import intersection.Quadratic;
import lightning.Lightning;
import main.Tracer;
import matrice.Rotation;
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
	public static Rotation rotation;
	
	public static void main(String [] args)
	{
		long start = System.nanoTime();

		world = new World(900, 450);
		image = new Image("Image.png");
		tracer = new Tracer();
		sampler = new RegularSample(1);
		camera = new Camera(new Point3D(50.0, 0.0, -300.0), 30, new Point3D(0.0, 0.0, 0.0));
		projection = new Perspective();
		lightning = new Lightning();
		quadratic = new Quadratic();
		rotation = new Rotation();
		
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