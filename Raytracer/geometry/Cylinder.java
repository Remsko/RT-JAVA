package geometry;

import intersection.Intersection;
import main.Main;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Cylinder extends GeometricObject
{
	public Point3D center;
	public double radius;
	
	public Cylinder(Point3D center, Color color, double radius)
	{
		this.center = center;
		this.radius = radius;
		this.color = color;
	}
	
	public double hit(Ray ray)
	{
		Point3D positionRelative = ray.origin.sub(center);
		
		double a = ray.direction.x * ray.direction.x + ray.direction.z * ray.direction.z;
		double b = 2.0 * (ray.direction.x * positionRelative.x + ray.direction.z * positionRelative.z);
		double c = positionRelative.x * positionRelative.x + positionRelative.z * positionRelative.z - radius * radius;
	
		return (Main.quadratic.solver(a, b, c));
	}
	
	public Vector3D getNormal(Intersection intersection)
	{
		Vector3D normal = new Vector3D(intersection.position.sub(center));
		
		normal.y = 0;
		normal.normalize();
		return (normal);
	}
}
