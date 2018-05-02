package geometry;

import utility.Color;
import utility.Origin;
import utility.Ray;

public class Sphere extends GeometricObject
{
	public Origin center;
	public double radius;
	
	public Sphere(Origin center,double radius,Color color)
	{
		this.center = new Origin(center);
		this.radius = radius;
		this.color = new Color(color);
		this.str = "sphere";
	}
	
	public double hit(Ray ray)
	{
		double a = 1.0;
		double b = 2.0 * ray.ori.sub(center).dot(ray.dir);
		double c = ray.ori.sub(center).dot(ray.ori.sub(center)) - radius * radius;
		Quadratic equation = new Quadratic(a, b ,c);
		
		return (equation.getPositiveS());
	}
}
