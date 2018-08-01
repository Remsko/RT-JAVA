package scene;


import java.util.ArrayList;

import geometry.Cone;
import geometry.Cylinder;
import geometry.GeometricObject;
import geometry.Hyperboloid;
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
		ambientIntensity = 0.3;
		alpha = 100.0;
		maxRayRebound = 20;
		
		objects = new ArrayList<GeometricObject>();
		objects.add(new Sphere(new Point3D(-10.0, 0.0, 0.0), new Color(46.0, 148.0, 185.0), 60, new Fresnel(1.0, 1.8)));
		objects.add(new Sphere(new Point3D(100.0, 50.0, 50.0), new Color(255.0, 253.0, 192.0), 70, new Fresnel(1.0, 2.2)));
		objects.add(new Sphere(new Point3D(200.0, -20.0, 0.0), new Color(240.0, 183.0, 117.0), 50, new Fresnel(1.0, 1.9)));
		//objects.add(new Sphere(new Point3D(-100.0, 0.0, 0.0), new Color(240.0, 133.0, 147.0), 50, new Fresnel(1.0, 1.6)));

		//objects.add(new Cylinder(new Point3D(102.0, 0.0, 255.0), new Color(45.0, 247.0, 250.0), 60, new Point3D(0.0, 0.0, 30.0), new Fresnel(1.0, 2.5)));
		
		objects.add(new Hyperboloid(new Point3D(-200.0, -50.0, -100.0), new Color(45.0, 247.0, 250.0), 30, new Point3D(0.0, 0.0, 0.0), new None()));
		
		//objects.add(new Cone(new Point3D(300.0, 0.0, 0.0), new Color(46.0, 148.0, 225.0), 30, new Point3D(0.0, 10.0, 10.0), new Reflective(0.5)));
		//objects.add(new Cone(new Point3D(-200.0, -50.0, 100.0), new Color(255.0, 253.0, 142.0), 40, new Point3D(30.0, 0.0, 0.0), new Reflective(0.5)));
		
		objects.add(new Plane(new Point3D(0.0, -100.0, 0.0), new Color(0.0, 0.0, 0.0), new Vector3D(0.0, 1.0, 0.0), new Reflective(1.0)));
		objects.add(new Plane(new Point3D(0.0, 0.0, 400.0), new Color(0.0, 0.0, 0.0), new Vector3D(0.0, 0.0, -1.0), new Reflective(1.0)));
		//objects.add(new Plane(new Point3D(0.0, 100.0, 0.0), new Color(0.0, 0.0, 0.0), new Vector3D(0.0, -1.0, 0.0), new Reflective(1.0)));
		//objects.add(new Plane(new Point3D(0.0, 0.0, -400.0), new Color(0.0, 0.0, 0.0), new Vector3D(0.0, 0.0, 1.0), new Reflective(1.0)));
		//objects.add(new Plane(new Point3D(400.0, 0.0, 0.0), new Color(0.0, 0.0, 0.0), new Vector3D(-1.0, 0.0, 0.0), new Reflective(1.0)));
		//objects.add(new Plane(new Point3D(-400.0, 0.0, -0.0), new Color(0.0, 0.0, 0.0), new Vector3D(1.0, 0.0, 0.0), new Reflective(1.0)));
		
		lights = new ArrayList<LightObject>();
		lights.add(new PointLight(new Point3D(110.0, 60.0, 10.0), new Color(1.0, 0.0, 1.0), 0.5));
		lights.add(new PointLight(new Point3D(70.0, -70.0, -70.0), new Color(1.0, 1.0, 0.0), 0.3));

		lights.add(new PointLight(new Point3D(300.0, 0.0, -200.0), new Color(0.0, 1.0, 1.0), 0.2));
	}
}