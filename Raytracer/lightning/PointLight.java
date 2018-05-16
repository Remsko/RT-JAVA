package lightning;

import main.Main;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class PointLight extends LightObject
{
	public PointLight(Point3D position, Color color, double intensity)
	{
		this.position = position;
		this.color = color;
		this.intensity = intensity;
	}

	public Ray getLightRay()
	{
		Ray lightRay = new Ray(new Point3D(Main.intersection.position),
				new Vector3D(this.position.sub(Main.intersection.position)));
		
		lightRay.direction.normalize();
		return (lightRay);
	}
}