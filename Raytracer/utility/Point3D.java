package utility;

public class Point3D
{
	public double x, y, z;
	
	public Point3D()
	{
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}
	
	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D(Point3D point)
	{
		x = point.x;
		y = point.y;
		z = point.z;
	}

	public Point3D add(Point3D point)
	{
		return (new Point3D(x + point.x, y + point.y, z + point.z));
	}
	
	public Point3D sub(Point3D point)
	{
		return (new Point3D(x - point.x, y - point.y, z - point.z));
	}

	public Point3D mul(Point3D point)
	{
		return (new Point3D(x * point.x, y * point.y, z * point.z));
	}
	
	public double dot(Vector3D vector)
	{
		return (x * vector.x + y * vector.y + z * vector.z);
	}
	
	public double dot(Point3D point)
	{
		return (x * point.x + y * point.y + z * point.z);
	}
}