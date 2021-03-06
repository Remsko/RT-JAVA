package projection;

import main.Main;
import utility.Point2D;
import utility.Ray;

public class Perspective extends Projection
{
	public Ray createRay(Point2D point)
	{
		Ray ray = new Ray();
		
		ray.origin = Main.camera.position;
		
		ray.direction.x = point.x;
		ray.direction.y = point.y;
		ray.direction.z = Main.camera.distance;
		
		Main.rotation.rotate(ray.direction, Main.camera.rotation);
		ray.direction.normalize();
		return (ray);
	}
}