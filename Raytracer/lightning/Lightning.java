package lightning;

import geometry.GeometricObject;
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
	//	couleur de l'objet * couleur de la lumiere * intensité de la lumière * cosOmega
		
		return (color);
	}
	
	public Color ambient()
	{
		return (object.color);
	}
	
	public boolean shadow()
	{
		return (true);
	}
	
	public Color PhongShading(GeometricObject object, Ray ray)
	{
		this.object = object;
		Color color = ambient();
		
		for (int i = 0; i < Main.world.lights.size(); i++)
		{
			tmpLight = Main.world.lights.get(i);
			tmpLightRay = tmpLight.getLightRay();
			if (shadow() == false)
			{
				cosTeta = tmpLightRay.direction.dot(object.getNormal());
				if (cosTeta > 0.0)
					color.add(diffuse());
			}
		}
		return (color);
	}
}