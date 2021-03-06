package geometry;

import intersection.Intersection;
import intersection.Quadratic;
import type.ObjectType;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Plane extends GeometricObject
{
	Point3D center;
	Vector3D normal;
	double t;
	
	public Plane(Point3D center, Color color, Vector3D normal, ObjectType type)
	{
		this.center = center;
		this.color = color.divide_ret(255.0);
		this.normal = normal;
		this.type = type;
		this.isplane = false;
		this.quadra = new Quadratic();
		
		normal.normalize();
	}
	
	public double hit(Ray ray)
	{
		t = center.sub(ray.origin).dot(normal) / ray.direction.dot(normal);
		
		return (t > 10E-9 ? t : 0.0);
	}
	
	public Vector3D getNormal(Intersection intersection)
	{
		return (normal);
	}
}

