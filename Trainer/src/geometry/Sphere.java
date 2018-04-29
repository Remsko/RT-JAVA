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
	}
	
	public double hit(Ray ray)
	{
		double a = 1.0;
		double b = 2.0 * ray.ori.sub(center).dot(ray.dir);
		double c = ray.ori.sub(center).dot(ray.ori.sub(center));
		c -= radius * radius;
		Quadratic equation = new Quadratic(a, b ,c);
		
		if (equation.hasSolution() == false)
			t = 0.0;
		else if (equation.getHighestS() > 10E-9)
			t = equation.getHighestS();
		return (t);
	}
}