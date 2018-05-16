package intersection;

import geometry.GeometricObject;
import main.Main;
import utility.Point3D;
import utility.Ray;

public class Intersection
{
	public double t;
	public Point3D position;
	
	public GeometricObject closest(Ray ray)
	{
		GeometricObject tmpObject;
		double min;
		
		tmpObject = null;
		min = Double.MAX_VALUE;
		for (int i = 0; i < Main.world.objects.size(); i++)
		{
			t = Main.world.objects.get(i).hit(ray);
			if (t != 0.0 && t < min)
			{
				min = t;
				tmpObject = Main.world.objects.get(i);
			}
		}
		if (min != Double.MIN_VALUE)
			position = new Point3D(ray.origin.add(ray.direction.mul(t)));
		return (tmpObject);
	}
}