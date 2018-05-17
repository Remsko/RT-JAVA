package lightning;

import intersection.Intersection;
import utility.Color;
import utility.Point3D;
import utility.Ray;

public abstract class LightObject
{
	public Color color;
	public Point3D position;
	public double intensity;
	
	public abstract Ray getLightRay(Intersection intersection);
}
