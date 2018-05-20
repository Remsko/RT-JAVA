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
	public Point3D rotation;
	public double radius;
	private Vector3D normal;
	private Point3D relative;
	private Vector3D rayDirection;
	
	public Cylinder(Point3D center, Color color, double radius, Point3D rotation)
	{
		this.center = center;
		this.radius = radius;
		this.color = color;
		this.rotation = rotation;
	}
	
	public double hit(Ray ray)
	{
		relative = ray.origin.sub(center);
		Main.rotation.rotate(relative, rotation);
		
		rayDirection = new Vector3D(ray.direction);
		Main.rotation.rotate(rayDirection, rotation);
		
		double a = rayDirection.x * rayDirection.x + rayDirection.z * rayDirection.z;
		double b = 2.0 * (rayDirection.x * relative.x + rayDirection.z * relative.z);
		double c = relative.x * relative.x + relative.z * relative.z - radius * radius;
	
		return (Main.quadratic.solver(a, b, c));
	}
	
	public Vector3D getNormal(Intersection intersection)
	{
		normal = new Vector3D(intersection.position.sub(center));
		
		Main.rotation.rotate(normal, rotation);
		normal.y = 0;
		
		Main.rotation.reverseRotate(normal, rotation);
		normal.normalize();
		
		return (normal);
	}
}
