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
	double tmpLightDistance;
	double cosθ;
	double cosΩ;
	
	public Color specularConstribution()
	{
		Color specular = new Color(0.7, 0.7, 0.7);
		
		specular.mul(tmpLight.intensity);
		specular.mul(cosΩ);
		
		return (specular);
	}
	
	public Color diffuseConstribution()
	{
		Color diffuse = new Color(0.7, 0.7, 0.7);
		
		diffuse.mul(object.color);
		diffuse.mul(tmpLight.color);
		diffuse.mul(tmpLight.intensity);
		diffuse.mul(cosθ);
		
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
		Intersection intersection = new Intersection(tmpLightRay);
		
		return (intersection.t < tmpLightDistance ? true : false);
	}
	
	public Color PhongShading(Intersection intersection, Ray ray)
	{
		this.object = intersection.object;
		Color color = ambientContribution();
		Vector3D normal;
		Vector3D refracted;
		Vector3D vision;
		
		for (int i = 0; i < Main.world.lights.size(); i++)
		{
			tmpLight = Main.world.lights.get(i);
			tmpLightRay = tmpLight.getLightRay(intersection);
			tmpLightDistance = tmpLightRay.direction.len();
			tmpLightRay.direction.normalize();
			if (shadow() == false)
			{
				normal = object.getNormal(intersection);
				cosθ = tmpLightRay.direction.dot(normal);
				if (cosθ > 0.0)
					color.add(diffuseConstribution());
				refracted = normal.mul(2.0 * cosθ).sub(tmpLightRay.direction);
				refracted.normalize();
				vision = ray.origin.sub_vec(intersection.position);
				vision.normalize();
				cosΩ = Math.pow(Math.max(refracted.dot(vision), 0.0), Main.world.alpha);
				if (cosΩ > 0.0)
					color.add(specularConstribution());
			}
		}
		return (color);
	}
}