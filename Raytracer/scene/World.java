package scene;


import java.util.ArrayList;

import geometry.Cone;
import geometry.Cylinder;
import geometry.GeometricObject;
import geometry.Plane;
import geometry.Sphere;
import lightning.LightObject;
import lightning.PointLight;
import type.Reflective;
import type.Refractive;
import type.Fresnel;
import type.None;
import utility.Color;
import utility.Point3D;
import utility.Vector3D;

public class World
{
	public ViewPlane viewplane;
	public ArrayList<GeometricObject> objects;
	public ArrayList<LightObject> lights;
	public Color background;
	public double ambientIntensity;
	public double alpha;
	public int maxRayRebound;
	
	public World(int width, int height)
	{
		viewplane = new ViewPlane(width, height);
		background = new Color(0.0, 0.0, 0.0);
		ambientIntensity = 0.1;
		alpha = 100.0;
		maxRayRebound = 10;
		
		objects = new ArrayList<GeometricObject>();
		objects.add(new Sphere(new Point3D(-10.0, 0.0, 0.0), new Color(1.0, 0.0, 0.0), 80, new Fresnel(1.0, 1.6)));
		objects.add(new Sphere(new Point3D(100.0, 50.0, 50.0), new Color(1.0, 1.0, 0.0), 70, new Fresnel(1.0, 1.4)));
		objects.add(new Sphere(new Point3D(200.0, -20.0, 0.0), new Color(0.0, 0.0, 1.0), 50, new Fresnel(1.0, 1.7)));

		objects.add(new Cylinder(new Point3D(0.0, 0.0, 300.0), new Color(0.3, 1.0, 0.3), 60, new Point3D(0.0, 0.0, 30.0), new Reflective(0.6)));
		
		objects.add(new Cone(new Point3D(300.0, 0.0, 0.0), new Color(0.0, 1.0, 1.0), 30, new Point3D(0.0, 0.0, 30.0), new Reflective(0.8)));
		objects.add(new Cone(new Point3D(-200.0, -50.0, 100.0), new Color(1.0, 0.5, 1.0), 40, new Point3D(30.0, 0.0, 0.0), new Reflective(0.9)));
		
		objects.add(new Plane(new Point3D(0.0, -100.0, 0.0), new Color(1.0, 1.0, 1.0), new Vector3D(0.0, 1.0, 0.0), new Refractive(0.8, 1.2)));
		objects.add(new Plane(new Point3D(0.0, 0.0, 400.0), new Color(0.5, 0.0, 0.5), new Vector3D(0.0, 0.0, -1.0), new Reflective(0.8)));
		
		lights = new ArrayList<LightObject>();
		lights.add(new PointLight(new Point3D(110.0, 60.0, 10.0), new Color(1.0, 0.7, 0.2), 1.0));
		lights.add(new PointLight(new Point3D(70.0, -70.0, -70.0), new Color(0.7, 0.2, 1.0), 1.0));

		lights.add(new PointLight(new Point3D(300.0, 0.0, -200.0), new Color(0.2, 1.0, 0.7), 1.0));
	}
}