package geometry;

import utility.Color;
import utility.Normal;
import utility.Origin;
import utility.Ray;

public class Cone extends GeometricObject
{
	public double radius;
	
	public Cone(Origin center,double radius,Color ambient, Color diffuse, Color specular)
	{
		this.center = new Origin(center);
		this.radius = radius;
		this.ambient = new Color(ambient);
		this.diffuse = new Color(diffuse);
		this.specular = new Color(specular);
		this.str = "cone";
	}
	
	public double hit(Ray ray)
	{
		Origin ori = ray.ori.sub(center);
		
		double rad = Math.sin(Math.toRadians(radius)) * Math.sin(Math.toRadians(radius));
		double a = ray.dir.x * ray.dir.x - ray.dir.y * ray.dir.y * rad + ray.dir.z * ray.dir.z;
		double b = 2.0 * (ray.dir.x * ori.x - ray.dir.y * ori.y * rad + ray.dir.z * ori.z);
		double c = ori.x * ori.x - ori.y * ori.y * rad + ori.z * ori.z;
		Quadratic equation = new Quadratic(a, b ,c);
		
		return (equation.getPositiveS());
	}
	
	public Normal getNormal(Intersection inter)
	{
		Normal nor = new Normal(inter.pos.sub(inter.obj.center));
		nor.y *= -1;
		nor.normalize();
		
		return (nor);
	}
}