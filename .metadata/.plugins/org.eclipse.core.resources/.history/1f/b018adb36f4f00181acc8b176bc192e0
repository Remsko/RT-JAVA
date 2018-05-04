package geometry;

import utility.Color;
import utility.Normal;
import utility.Origin;
import utility.Ray;

public class Plane extends GeometricObject
{
	Origin ori;
	Normal nor;
	
	public Plane(Origin ori, Normal nor, Color c)
	{
		this.ori = new Origin(ori);
		this.nor = new Normal(nor);
		this.color = new Color(c);
		this.str = "plane";
	}
	
	public double hit(Ray ray)
	{
		t = ori.sub(ray.ori).dot(nor) / ray.dir.dot(nor);
		
		return (t > 10E-9 ? t : 0.0);
	}
}
