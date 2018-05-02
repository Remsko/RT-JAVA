package geometry;

import utility.Color;
import utility.Origin;
import utility.Ray;

public class Cylinder extends GeometricObject
{
	public Origin center;
	public double radius;
	
	public Cylinder(Origin center,double radius,Color color)
	{
		this.center = new Origin(center);
		this.radius = radius;
		this.color = new Color(color);
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
}
