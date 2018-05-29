package geometry;

import intersection.Intersection;
import intersection.Quadratic;
import type.None;
import type.ObjectType;
import utility.Color;
import utility.Ray;
import utility.Vector3D;

public abstract class GeometricObject
{
	public Color color;
	public ObjectType type = new None();
	public Quadratic quadra;
	public boolean isplane;
	
	public abstract double hit(Ray ray);
	
	public abstract Vector3D getNormal(Intersection intersection);
}
