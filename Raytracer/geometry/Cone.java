package geometry;

import intersection.Intersection;
import intersection.Quadratic;
import main.Main;
import type.ObjectType;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Cone extends GeometricObject
{
	public Point3D center;
	public Point3D rotation;
	public double radius;
	public double powRadius;
	private Vector3D normal;
	private Point3D relative;
	private Vector3D rayDirection;
	
	public Cone(Point3D center, Color color, double radius, Point3D rotation, ObjectType type)
	{
		this.center = center;
		this.rotation = rotation;
		this.radius = Math.toRadians(radius);
		this.powRadius = this.radius * this.radius;
		this.color = color.divide_ret(255.0);
		this.type = type;
		this.isplane = false;
		this.quadra = new Quadratic();
	}
	
	public double hit(Ray ray)
	{
		relative = ray.origin.sub(center);
		Main.rotation.rotate(relative, rotation);
		
		rayDirection = new Vector3D(ray.direction);
		Main.rotation.rotate(rayDirection, rotation);
		
		double a = rayDirection.x * rayDirection.x - rayDirection.y * rayDirection.y * powRadius + rayDirection.z * rayDirection.z;
		double b = 2.0 * (rayDirection.x * relative.x - rayDirection.y * relative.y * powRadius + rayDirection.z * relative.z);
		double c = relative.x * relative.x - relative.y * relative.y * powRadius + relative.z * relative.z;
		
		quadra = new Quadratic(a, b, c);
		if (quadra.isanswer = false)
			return (0.0);
		else
			return (quadra.smallest > 0.0 ? quadra.smallest : 0.0);
	}
	
	public Vector3D getNormal(Intersection intersection)
	{
		normal = intersection.position.sub_vec(center);
		
		Main.rotation.rotate(normal, rotation);
		//normal.y = -1;
		normal.y *= -Math.tan(powRadius);
		
		Main.rotation.reverseRotate(normal, rotation);
		normal.normalize();
		
		return (normal);
	}
}
