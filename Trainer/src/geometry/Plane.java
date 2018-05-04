package geometry;

import utility.Color;
import utility.Normal;
import utility.Origin;
import utility.Ray;

public class Plane extends GeometricObject
{
	Normal nor;
	
	public Plane(Origin ori, Normal nor, Color ambient, Color diffuse, Color specular)
	{
		this.center = new Origin(ori);
		this.nor = new Normal(nor);
		this.ambient = new Color(ambient);
		this.diffuse = new Color(diffuse);
		this.specular = new Color(specular);
		this.str = "plane";
	}
	
	public double hit(Ray ray)
	{
		t = center.sub(ray.ori).dot(nor) / ray.dir.dot(nor);
		
		return (t > 10E-9 ? t : 0.0);
	}
	
	public Normal getNormal(Intersection inter)
	{
		nor.normalize();
		
		return (nor);
	}
}
