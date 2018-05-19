package scene;

import java.util.ArrayList;

import geometry.GeometricObject;
import geometry.Sphere;
import lightning.LightObject;
import lightning.PointLight;
import utility.Color;
import utility.Point3D;

public class World
{
	public ViewPlane viewplane;
	public ArrayList<GeometricObject> objects;
	public ArrayList<LightObject> lights;
	public Color background;
	public double ambientIntensity;
	public double alpha;
	
	public World(int width, int height)
	{
		viewplane = new ViewPlane(width, height);
		background = new Color(0.0, 0.0, 0.0);
		ambientIntensity = 0.2;
		alpha = 3000.0;
		
		objects = new ArrayList<GeometricObject>();
		objects.add(new Sphere(new Point3D(0.0, 0.0, 0.0), new Color(1.0, 0.0, 0.0), 50));
		objects.add(new Sphere(new Point3D(0.0, 50.0, 50.0), new Color(0.0, 1.0, 0.0), 50));
		objects.add(new Sphere(new Point3D(200.0, 0.0, 0.0), new Color(0.0, 0.0, 1.0), 50));
		
		lights = new ArrayList<LightObject>();
		lights.add(new PointLight(new Point3D(100.0, 50.0, 0.0), new Color(1.0, 1.0, 1.0), 1.0));
		lights.add(new PointLight(new Point3D(60.0, -60.0, -60.0), new Color(1.0, 1.0, 1.0), 1.0));
	}
}