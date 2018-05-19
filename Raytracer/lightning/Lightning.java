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
	
	public Color specularConstribution()
	{
		Color specular = new Color(1.0, 1.0, 1.0);
		
		specular.mul(tmpLight.intensity);
		specular.mul(cosOmega);
		
		return (specular);
	}
	
	public Color diffuseConstribution()
	{
		Color diffuse = new Color(1.0, 1.0, 1.0);
		
		diffuse.mul(object.color);
		diffuse.mul(tmpLight.color);
		diffuse.mul(tmpLight.intensity);
		diffuse.mul(cosTeta);
		
		return (diffuse);
	}
	
	public Color ambientContribution()
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
		Color color = ambientContribution();
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
					color.add(diffuseConstribution());
				refracted = normal.mul(2.0 * cosTeta).sub(tmpLightRay.direction);
				refracted.normalize();
				vision = ray.origin.sub_vec(intersection.position);
				vision.normalize();
				cosOmega = Math.pow(Math.max(refracted.dot(vision), 0.0), Main.world.alpha);
				if (cosOmega > 0.0)
					color.add(specularConstribution());
			}
		}
		return (color);
	}
}