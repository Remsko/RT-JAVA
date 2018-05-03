package geometry;

import utility.Ray;

public class Intersection
{
	public double			t;
	public double			tmp;
	public GeometricObject	obj;
	
	public Intersection()
	{
		t = 0.0;
		tmp = 0.0;
		obj = null;
	}
	
	public double closestHit(GeometricObject[] objArr, Ray ray)
	{
		for (int i = 0; i < objArr.length; i++)
		{
			tmp = objArr[i].hit(ray);
			if (tmp != 0.0)
			{
				obj = objArr[i];
				t = tmp;
			}
		}
		return (t);
	}
}
