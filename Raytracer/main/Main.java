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

		world = new World(6000, 3600);
		image = new Image("Image.png");
		tracer = new Tracer();
		sampler = new RegularSample(1);
		camera = new Camera(new Point3D(100.0, 0.0, -100.0), 60);
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





/*
object = intersection.object;
//Color color = ambient();
Color color = new Color(0.0, 0.0, 0.0);
Vector3D normal;

for (int i = 0; i < Main.world.lights.size(); i++)
{
	tmpLight = Main.world.lights.get(i);
	tmpLightRay = tmpLight.getLightRay(intersection);
	if (shadow() == false)
	{
		normal = object.getNormal(intersection);
		cosTeta = tmpLightRay.direction.dot(normal);
		//if (cosTeta > 0.0)
			//color.add(diffuse());
		Vector3D vision = ray.origin.sub_vec(intersection.position);
		Vector3D refracted = normal.mul(2.0 * cosTeta).sub(tmpLightRay.direction);
		double cosOmega = Math.pow(Math.max(0.0, refracted.dot(vision)), 300.0);
		if (cosOmega > 0.0)
			color.add(specular());
	}
}
return (color);
*/