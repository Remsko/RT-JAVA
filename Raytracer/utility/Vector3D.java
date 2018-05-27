package utility;

public class Vector3D extends TripleDouble
{
	public Vector3D()
	{
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}
	
	public Vector3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3D(Vector3D vector)
	{
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}
	
	public Vector3D(Point3D point)
	{
		x = point.x;
		y = point.y;
		z = point.z;
	}
	
	public Vector3D add(Vector3D vector)
	{
		return (new Vector3D(x + vector.x, y + vector.y, z + vector.z));
	}
	
	public Vector3D sub(Vector3D vector)
	{
		return (new Vector3D(x - vector.x, y - vector.y, z - vector.z));
	}
	
	public Vector3D sub(Point3D point)
	{
		return (new Vector3D(x - point.x, y - point.y, z - point.z));
	}


	public Vector3D mul(Vector3D vector)
	{
		return (new Vector3D(x * vector.x, y * vector.y, z * vector.z));
	}
	
	public Vector3D mul(double factor)
	{
		return (new Vector3D(x * factor, y * factor, z * factor));
	}
	
	public Vector3D opposite()
	{
		return (new Vector3D(-x, -y, -z));
	}
	
	public double len()
	{
		return (Math.sqrt(x * x + y * y + z * z));
	}
	
	public double dot(Vector3D vector)
	{
		return (x * vector.x + y * vector.y + z * vector.z);
	}
	
	public double dot(Point3D point)
	{
		return (x * point.x + y * point.y + z * point.z);
	}
	
	public void	normalize()
	{
		double magnitude = Math.sqrt(x * x + y * y + z * z);
		
		x /= magnitude;
		y /= magnitude;
		z /= magnitude;
	}
}
