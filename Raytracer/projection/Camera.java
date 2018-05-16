package projection;

import main.Main;
import utility.Point3D;

public class Camera
{
	Point3D	position;
	double	distance;
	
	public Camera(Point3D position, double FOV)
	{
		this.position = position;
		this.distance = Main.world.viewplane.height / 2 / Math.tan(Math.toRadians(FOV));
	}
}