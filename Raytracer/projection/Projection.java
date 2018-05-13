package projection;

import utility.Point2D;
import utility.Ray;

public abstract class Projection
{
	public abstract Ray createRay(Point2D point);
}
