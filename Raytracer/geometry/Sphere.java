package geometry;

import intersection.Intersection;
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
	
		return (Main.quadratic.solver(a, b, c));
	}
	
	public Vector3D getNormal(Intersection intersection)
	{
		Vector3D normal = new Vector3D(intersection.position.sub(center));
		
		normal.normalize();
		return (normal);
	}
}
