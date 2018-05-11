package light;

import geometry.GeometricObject;
import geometry.Intersection;
import utility.Color;
import utility.Direction;
import utility.Normal;
import utility.Origin;
import utility.Ray;

public class LightObject
{
	public Origin	pos;
	public Color	c;
	public double	i;
	
	public LightObject()
	{
		this.pos = null;
		this.c = null;
		this.i = 0.0f;
	}
	
	public LightObject(Origin pos, Color c, double i)
	{
		this.pos = pos;
		this.c = c;
		this.i = i;
	}
	
	public boolean isShadow()
	{
		return (false);
	}
	
	public Color setAmbientLight(GeometricObject obj)
	{
		return (obj.ambient);
	}
	
	public Color addSpecularLight(Intersection inter, Ray ray, Ray lightRay, Normal nor, Color c, double i)
	{
		Direction	refracted;
		Direction	vision;
		double		cosOmega;
	
		vision = new Direction(ray.ori.sub(inter.pos));
		vision.normalize();
		refracted = nor.mul(2.0 * lightRay.dir.dot(nor)).sub(lightRay.dir);
		refracted.normalize();
		cosOmega = Math.pow(Math.max(0.0, vision.dot(refracted)), 300.0);
		if (cosOmega > 0)
			c.add(inter.obj.specular.mul((float)i).mul((float)cosOmega));
		return (c);
	}
	
	public Color addDiffuseLight(Intersection inter, LightObject light, Color c, double cosAlpha)
	{
		Color tmp = new Color(inter.obj.diffuse).mul(light.c).mul((float)light.i).mul((float)cosAlpha);
		
		tmp.r = tmp.r < 0 ? 0 : tmp.r;
		tmp.g = tmp.g < 0 ? 0 : tmp.g;
		tmp.b = tmp.b < 0 ? 0 : tmp.b;
		c.add(tmp);
		c.r = tmp.r > 1 ? 1 : tmp.r;
		c.g = tmp.g > 1 ? 1 : tmp.g;
		c.b = tmp.b > 1 ? 1 : tmp.b;
		return (c);
	}
	
	public Color Process(LightObject[] LightArr, Intersection inter, Ray ray)
	{
		Color	c = setAmbientLight(inter.obj);
		Normal	nor = inter.obj.getNormal(inter);
		Ray		lightRay = null;
		double	cosAlpha = 0.0;
		
		ray.dir.normalize();
		for (int i = 0; i < LightArr.length; i++)
		{	
			lightRay = new Ray(inter.pos, new Direction(LightArr[i].pos.sub(inter.pos)));
			lightRay.dir.normalize();
			if (isShadow() == false)
			{
				cosAlpha = lightRay.dir.dot(nor);
				if (cosAlpha > 0.0)
					c = addDiffuseLight(inter, LightArr[i], c, cosAlpha);
			}
			//c = addSpecularLight(inter, ray, lightRay, nor, c, LightArr[i].i);
		}
		return (c);
	}
}
