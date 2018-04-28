package geometry;

import utility.Color;
import utility.Normal;
import utility.Origin;
import utility.Ray;

public class Plane extends GeometricObject
{
	Origin ori;
	Normal nor;
	
	public Plane(Origin ori, Normal nor, Color c)
	{
		this.ori = new Origin(ori);
		this.nor = new Normal(nor);
		this.color = new Color(c);
	}
	
	public double hit(Ray ray)
	{
		
	}
}
