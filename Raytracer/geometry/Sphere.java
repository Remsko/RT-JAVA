package geometry;

import main.Main;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Sphere extends GeometricObject
{
	public Point3D center;
	public double radius;
	
	public Sphere(Point3D center, Color color, double radius)
	{
		this.center = center;
		this.radius = radius;
		this.color = color;
	}
	
	public double hit(Ray ray)
	{
		double a = 1.0;
		double b = 2 * ray.origin.sub(center).dot(ray.direction);
		double c = ray.origin.sub(center).dot(ray.origin.sub(center)) - radius * radius;
		double discriminant = b * b - 4 * a * c; 
	
		if (discriminant < 0.0)
			return (0.0);
		else
		{
			double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
			double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
			double t = t1 < t2 ? t1 : t2;
			
			return (t > 10E-9 ? t : 0.0);
		}
	}
	
	public Vector3D getNormal()
	{
		Vector3D normal = new Vector3D(Main.intersection.position.sub(center));
		
		normal.normalize();
		return (normal);
	}
}