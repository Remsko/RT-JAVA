package intersection;

import geometry.GeometricObject;
import main.Main;
import utility.Point3D;
import utility.Ray;

public class Intersection
{
	public double t;
	public double tmp;
	public GeometricObject object;
	public Point3D position;
	public Point3D closestPosition;
	public Point3D farthestPosition;
	
	public Intersection(Ray ray)
	{
		tmp = 0.0;
		t = Double.MAX_VALUE;
		object = null;
		closest(ray);
		position = ray.origin.add(ray.direction.mul(t));
		if (object != null && object.isplane == false)
		{
			closestPosition = ray.origin.add(ray.direction.mul(object.quadra.smallest));
			farthestPosition = ray.origin.add(ray.direction.mul(object.quadra.biggest));
		}
	}
	
	public void closest(Ray ray)
	{
		for (int i = 0; i < Main.world.objects.size(); i++)
		{
			tmp = Main.world.objects.get(i).hit(ray);
			if (tmp != 0.0 && tmp < t)
			{
				t = tmp;
				object = Main.world.objects.get(i);
			}
		}
	}
}
