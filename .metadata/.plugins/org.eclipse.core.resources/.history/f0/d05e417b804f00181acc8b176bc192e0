package utility;

public class Normal
{
	protected double x, y, z;
	
	public Normal()
	{
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}
	
	public Normal(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Normal(Normal nor)
	{
		x = nor.x;
		y = nor.y;
		z = nor.z;
	}
	
	public double dot(Normal nor)
	{
		return (x * nor.x + y * nor.y + z * nor.z);
	}
	
	public void normalize()
	{
		double magnitude;
		
		magnitude = Math.sqrt(x * x + y * y + z * z);
		
		x /= magnitude;
		y /= magnitude;
		z /= magnitude;
	}
}
