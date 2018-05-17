package lightning;

import geometry.GeometricObject;
import intersection.Intersection;
import main.Main;
import utility.Color;
import utility.Ray;

public class Lightning
{
	GeometricObject object;
	LightObject tmpLight;
	Ray	tmpLightRay;
	double cosTeta;
	
	public Color specular()
	{
		return null;
	}
	
	public Color diffuse()
	{
		Color color = new Color(1.0, 1.0, 1.0);
		
		color.mul(object.color);
		color.mul(tmpLight.color);
		color.mul(tmpLight.intensity);
		color.mul(cosTeta);
		
		return (color);
	}
	
	public Color ambient()
	{
		Color ambient = new Color(object.color);
		
		ambient.mul(Main.world.ambientIntensity);
		return (ambient);
	}
	
	public boolean shadow()
	{
		return (false);
	}
	
	public Color PhongShading(Intersection intersection, Ray ray)
	{
		object = intersection.object;
		Color color = ambient();
		
		for (int i = 0; i < Main.world.lights.size(); i++)
		{
			tmpLight = Main.world.lights.get(i);
			tmpLightRay = tmpLight.getLightRay(intersection);
			if (shadow() == false)
			{
				cosTeta = tmpLightRay.direction.dot(object.getNormal(intersection));
				if (cosTeta > 0.0)
					color.add(diffuse());
			}
		}
		return (color);
	}
}