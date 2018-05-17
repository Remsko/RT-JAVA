package intersection;

import geometry.GeometricObject;
import main.Main;
import utility.Point3D;
import utility.Ray;

public class Intersection
{
	public double t;
	public double tmp;
	public boolean hit;
	public GeometricObject object;
	public Point3D position;
	
	public Intersection(Ray ray)
	{
		tmp = 0.0;
		t = Double.MAX_VALUE;
		hit = false;
		object = null;
		closest(ray);
		position = (hit == true) ? ray.origin.add(ray.direction.mul(t)) : null;
	}
	
	public void closest(Ray ray)
	{
		for (int i = 0; i < Main.world.objects.size(); i++)
		{
			tmp = Main.world.objects.get(i).hit(ray);
			if (tmp != 0.0 && tmp < t)
			{
				t = tmp;
				hit = true;
				object = Main.world.objects.get(i);
			}
		}
	}
}
