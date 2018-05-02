package geometry;

import utility.Color;
import utility.Origin;
import utility.Ray;

public class Intersection
{
	public double			t;
	public double			tmpT;
	public GeometricObject	obj;
	
	public Intersection()
	{
		t = 0.0;
		tmpT = 0.0;
		obj = new Sphere(new Origin(0.0, 0.0, 0.0),
				60.0, new Color(1.0f, 0.0f, 1.0f));;
	}
	
	public double closestHit(GeometricObject[] objArr, Ray ray)
	{
		int i;
		
		i = 0;
		while (i < objArr.length)
		{
			tmpT = objArr[i].hit(ray);
			if (tmpT != 0.0)
			{
				obj = objArr[i];
				t = tmpT;
			}
			i++;
		}
		return (t);
	}
}
