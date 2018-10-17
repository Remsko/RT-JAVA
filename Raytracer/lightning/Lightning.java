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
	double cosTeta;
	double cosOmega;
	
	public Color specularConstribution()
	{
		Color specular = new Color(0.7, 0.7, 0.7);
		
		specular.mul(tmpLight.intensity);
		specular.mul(cosOmega);
		
		return (specular);
	}
	
	public Color diffuseConstribution()
	{
		Color diffuse = new Color(0.7, 0.7, 0.7);
		
		diffuse.mul(object.color);
		diffuse.mul(tmpLight.color);
		diffuse.mul(tmpLight.intensity);
		diffuse.mul(cosTeta);
		
		return (diffuse);
	}
	
	public Color ambientContribution(Intersection intersection)
	{
		Color ambient = new Color(object.color);
		
		double absorbDist = intersection.farthestPosition.sub_vec(intersection.closestPosition).len();

		Color absorbCoef = new Color(0.01, 0.02, 0.06);
		Color absorb = new Color(1.0 - Math.exp(-absorbCoef.r * (absorbDist)),
								1.0 - Math.exp(-absorbCoef.g * (absorbDist)),
								1.0 - Math.exp(-absorbCoef.b * (absorbDist)));
		//System.out.println(absorb.r);
		ambient.mul(absorb);
		
		ambient.mul(Main.world.ambientIntensity);
		return (ambient);
	}
	
	public boolean shadow()
	{
		Intersection intersection = new Intersection(tmpLightRay);
		
		return (intersection.t < tmpLightDistance ? true : false);
		//return (true);
	}
	
	public Color PhongShading(Intersection intersection, Ray ray)
	{
		this.object = intersection.object;
		Color color = ambientContribution(intersection);
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