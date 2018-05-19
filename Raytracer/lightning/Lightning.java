package lightning;

import geometry.GeometricObject;
import intersection.Intersection;
import main.Main;
import utility.Color;
import utility.Ray;
import utility.Vector3D;

public class Lightning
{
	GeometricObject object;
	LightObject tmpLight;
	Ray	tmpLightRay;
	double cosTeta;
	double cosOmega;
	
	public Color specular()
	{
		Color color = new Color(1.0, 1.0, 1.0);
		
		color.mul(tmpLight.color);
		color.mul(tmpLight.intensity);
		color.mul(cosTeta);
		
		return (color);
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
		Vector3D normal;
		Vector3D refracted;
		Vector3D vision;
		
		for (int i = 0; i < Main.world.lights.size(); i++)
		{
			tmpLight = Main.world.lights.get(i);
			tmpLightRay = tmpLight.getLightRay(intersection);
			if (shadow() == false)
			{
				normal = object.getNormal(intersection);
				cosTeta = tmpLightRay.direction.dot(normal);
				if (cosTeta > 0.0)
					color.add(diffuse());
				refracted = object.getNormal(intersection).mul(2.0 * tmpLightRay.direction.dot(object.getNormal(intersection)));
				refracted.sub(tmpLightRay.direction);
				refracted.normalize();
				vision = ray.origin.sub_vec(intersection.position);
				vision.normalize();
				cosOmega = Math.pow(Math.max(refracted.dot(vision), 0.0), 500.0);
				if (cosOmega > 0.0)
					color.add(specular());
			}
		}
		return (color);
	}
}