package scene;

import java.util.ArrayList;

import geometry.GeometricObject;
import geometry.Sphere;
import utility.Color;
import utility.Point3D;

public class World
{
	public ViewPlane viewplane;
	public ArrayList<GeometricObject> objects;
	public Color background;
	
	public World(int width, int height)
	{
		viewplane = new ViewPlane(width, height);
		background = new Color(0.0, 0.0, 0.0);
		
		objects = new ArrayList<GeometricObject>();
		objects.add(new Sphere(new Point3D(0.0, 0.0, 0.0), new Color(1.0, 0.0, 0.0), 50));
		objects.add(new Sphere(new Point3D(-200.0, 0.0, 0.0), new Color(0.0, 1.0, 0.0), 50));
		objects.add(new Sphere(new Point3D(200.0, 0.0, 0.0), new Color(0.0, 0.0, 1.0), 50));
	}
}