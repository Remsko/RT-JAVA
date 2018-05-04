package utility;

public class Normal
{
	public double x, y, z;
	
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
	
	public Normal(Origin ori)
	{
		x = ori.x;
		y = ori.y;
		z = ori.z;
	}
	
	public Direction mul(double d)
	{
		return new Direction(x * d, y * d, z * d);
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
