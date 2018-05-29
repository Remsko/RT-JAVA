package geometry;

import intersection.Intersection;
import intersection.Quadratic;
import type.ObjectType;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Sphere extends GeometricObject
{
	public Point3D center;
	public double powRadius;
	
	public Sphere(Point3D center, Color color, double radius, ObjectType type)
	{
		this.center = center;
		this.powRadius = radius * radius;
		this.color = color;
		this.type = type;
		this.isplane = false;
		this.quadra = new Quadratic();
	}
	
	public double hit(Ray ray)
	{
		Point3D positionRelative = ray.origin.sub(center);
		
		double a = 1.0;
		double b = 2.0 * positionRelative.dot(ray.direction);
		double c = positionRelative.dot(ray.origin.sub(center)) - powRadius;
	
		quadra = new Quadratic(a, b, c);
		if (quadra.isanswer = false)
			return (0.0);
		else
			return (quadra.smallest > 0.0 ? quadra.smallest : 0.0);
	}
	
	public Vector3D getNormal(Intersection intersection)
	{
		Vector3D normal = new Vector3D(intersection.position.sub(center));
		
		normal.normalize();
		return (normal);
	}
}
