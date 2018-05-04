package geometry;

import utility.Color;
import utility.Normal;
import utility.Origin;
import utility.Ray;

public class Sphere extends GeometricObject
{
	public double radius;
	
	public Sphere(Origin center,double radius,Color ambient, Color diffuse, Color specular)
	{
		this.center = new Origin(center);
		this.radius = radius;
		this.ambient = new Color(ambient);
		this.diffuse = new Color(diffuse);
		this.specular = new Color(specular);
		this.str = "sphere";
	}
	
	public double hit(Ray ray)
	{
		double a = 1.0;
		double b = 2.0 * ray.ori.sub(center).dot(ray.dir);
		double c = ray.ori.sub(center).dot(ray.ori.sub(center)) - radius * radius;
		Quadratic equation = new Quadratic(a, b ,c);
		
		return (equation.getPositiveS());
	}
	
	public Normal getNormal(Intersection inter)
	{
		Normal nor = new Normal(inter.pos.sub(inter.obj.center));
		nor.normalize();
		
		return (nor);
	}
}
