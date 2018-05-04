package geometry;

import utility.Color;
import utility.Normal;
import utility.Origin;
import utility.Ray;

public class Cylinder extends GeometricObject
{
	public double radius;
	
	public Cylinder(Origin center,double radius,Color ambient, Color diffuse, Color specular)
	{
		this.center = new Origin(center);
		this.radius = radius;
		this.ambient = new Color(ambient);
		this.diffuse = new Color(diffuse);
		this.specular = new Color(specular);
		this.str = "cylinder";
	}
	
	public double hit(Ray ray)
	{
		Origin oriCyl = ray.ori.sub(center);
		double a = ray.dir.x * ray.dir.x + ray.dir.z * ray.dir.z;
		double b = 2.0 * (ray.dir.x * oriCyl.x + ray.dir.z * oriCyl.z);
		double c = oriCyl.x * oriCyl.x + oriCyl.z * oriCyl.z - radius * radius;
		Quadratic equation = new Quadratic(a, b ,c);
		
		return (equation.getPositiveS());
	}

	public Normal getNormal(Intersection inter)
	{
		Normal nor = new Normal(inter.pos.sub(inter.obj.center));
		nor.y = 0;
		nor.normalize();
		
		return (nor);
	}
}