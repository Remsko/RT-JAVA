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
	
	public Vector3D getReflected(Vector3D incident, Vector3D normal)
	{
		Vector3D reflected = incident.sub(normal.mul(2.0 * normal.dot(incident)));
		
		reflected.normalize();
		return (reflected);
	}
	
	public Vector3D getRefracted(Vector3D incident, Vector3D normal, double IOR)
	{
		Vector3D refracted = new Vector3D();
		double c1 = normal.dot(incident);
		double eta1;
		double eta2;
		
		if (c1 < 0.0)
		{
			c1 = -c1;

			eta1 = 1.0;
			eta2 = IOR;
		}
		else
		{
			normal = normal.mul(-1.0);
		
			eta2 = 1.0;
			eta1 = IOR;	
		}
		double eta = eta1 / eta2;
		double c2 = 1.0 - eta * eta * (1 - c1 * c1);
		if (c2 > 0.0)
		{
			refracted = incident.mul(eta).add(normal.mul(eta * c1 - Math.sqrt(c2)));
			refracted.normalize();
		}
		return (refracted);
	}
}
