package geometry;

import intersection.Intersection;
import main.Main;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Cone extends GeometricObject
{
	public Point3D center;
	public double radius;
	
	public Cone(Point3D center, Color color, double radius)
	{
		this.center = center;
		this.radius = radius;
		this.color = color;
	}
	
	public double hit(Ray ray)
	{
		Point3D relative = ray.origin.sub(center);
		double rad = Math.sin(Math.toRadians(radius)) * Math.sin(Math.toRadians(radius));
		
		double a = ray.direction.x * ray.direction.x - ray.direction.y * ray.direction.y * rad + ray.direction.z * ray.direction.z;
		double b = 2.0 * (ray.direction.x * relative.x - ray.direction.y * relative.y * rad + ray.direction.z * relative.z);
		double c = relative.x * relative.x - relative.y * relative.y * rad + relative.z * relative.z;
	
		return (Main.quadratic.solver(a, b, c));
	}
	
	public Vector3D getNormal(Intersection intersection)
	{
		Vector3D normal = new Vector3D(intersection.position.sub(center));
		
		normal.y = -1;
		normal.normalize();
		return (normal);
	}
}
