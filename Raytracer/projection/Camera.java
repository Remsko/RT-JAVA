package projection;

import main.Main;
import utility.Point3D;

public class Camera
{
	Point3D	position;
	Point3D rotation;
	double	distance;
	
	public Camera(Point3D position, double FOV, Point3D rotation)
	{
		this.position = position;
		this.rotation = rotation;
		this.distance = Main.world.viewplane.height / 2 / Math.tan(Math.toRadians(FOV));
	}
}
