package utility;

public class Direction
{
	public double x, y, z;
	
	public Direction()
	{
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}
	
	public Direction(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Direction(Direction dir)
	{
		x = dir.x;
		y = dir.y;
		z = dir.z;
	}
	
	public Direction(Origin ori)
	{
		x = ori.x;
		y = ori.y;
		z = ori.z;
	}
	
	public Direction add(Direction dir)
	{
		return new Direction(x + dir.x, y + dir.y, z + dir.z);
	}
	
	public Direction sub(Direction dir)
	{
		return new Direction(x - dir.x, y - dir.y, z - dir.z);
	}
	
	public Direction mul(double d)
	{
		return new Direction(x * d, y * d, z * d);
	}
	
	public double dot(Direction dir)
	{
		return (x * dir.x + y * dir.y + z * dir.z);
	}
	
	public double dot(Origin ori)
	{
		return (x * ori.x + y * ori.y + z * ori.z);
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
