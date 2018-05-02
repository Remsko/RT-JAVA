package geometry;

import utility.Color;
import utility.Origin;
import utility.Ray;

public class Cone extends GeometricObject
{
	public Origin center;
	public double radius;
	
	public Cone(Origin center,double radius,Color color)
	{
		this.center = new Origin(center);
		this.radius = radius;
		this.color = new Color(color);
		this.str = "cone";
	}
	
	public double hit(Ray ray)
	{
		Origin ori = ray.ori.sub(center);
		
		double rad = Math.sin(Math.toRadians(radius)) * Math.sin(Math.toRadians(radius));
		double a = ray.dir.x * ray.dir.x - ray.dir.y * ray.dir.y * rad + ray.dir.z * ray.dir.z;
		double b = 2.0 * (ray.dir.x * ori.x - ray.dir.y * ori.y * rad + ray.dir.z * ori.z);
		double c = ori.x * ori.x - ori.y * ori.y * rad + ori.z * ori.z;
		Quadratic equation = new Quadratic(a, b ,c);
		
		return (equation.getPositiveS());
	}
}
