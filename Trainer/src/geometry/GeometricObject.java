package geometry;

import utility.Color;
import utility.Normal;
import utility.Origin;
import utility.Ray;

public abstract class GeometricObject
{
	public Origin center;
	public String str;
	public Color ambient;
	public Color diffuse;
	public Color specular;
	protected double t;
	
	public abstract double hit(Ray ray);
	
	public abstract Normal getNormal(Intersection inter);
	
}
