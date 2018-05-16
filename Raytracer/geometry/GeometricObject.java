package geometry;

import utility.Color;
import utility.Ray;
import utility.Vector3D;

public abstract class GeometricObject
{
	public Color color;
	
	public abstract double hit(Ray ray);
	
	public abstract Vector3D getNormal();
}
