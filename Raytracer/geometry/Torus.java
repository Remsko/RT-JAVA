package geometry;

import intersection.Intersection;
import intersection.Quadratic;
import intersection.Quartic;
import type.ObjectType;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Torus extends GeometricObject
{
	public Point3D	center;
	public double	radiusA;
	public double	radiusB;
	
	public Torus(Point3D center, Color color, double radiusA, double radiusB, ObjectType type)
	{
		this.center = center;
		this.color = color;
		this.radiusA = radiusA;
		this.radiusB = radiusB;
		this.type = type;
		this.isplane = false;
		this.quadra = new Quadratic();
	}
	
	public double hit(Ray ray)
	{
		Vector3D E = ray.direction;
		Point3D D = ray.origin.sub(center);
		
		double T = 4.0 * radiusA * radiusA;
        double G = T * (E.x * E.x + E.y * E.y);
        double H = 2.0 * T * (D.x * E.x + D.y * E.y);
        double I = T * (D.x * D.x + D.y * D.y);
        double J = E.dot(E);
        double K = 2.0 * D.dot(E);
        double L = D.dot(D) + radiusA * radiusA - radiusB * radiusB;
		
		Quartic quartic = new Quartic(J * J,
				2.0 * J * K,
				2.0 * J * L + K * K - G,
				2.0 * K * L - H,
				L * L - I);
		if (quartic.isanswer = false)
			return (0.0);
		else
			return (quartic.smallest > 0.0 ? quartic.smallest : 0.0);
	}
	
	public Vector3D getNormal(Intersection intersection)
	{
		Point3D P = intersection.position;
		
		double a = 1.0 - (radiusA / Math.sqrt(P.x * P.x + P.y * P.y));
		
		Vector3D normal = new Vector3D(a * P.x, a * P.y, P.z);
		
		normal.normalize();
		return (normal);
	}
}