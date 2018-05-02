package utility;

public class Origin
{
	public double x, y, z;
	
	public Origin()
	{
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}
	
	public Origin(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Origin(Origin ori)
	{
		x = ori.x;
		y = ori.y;
		z = ori.z;
	}
	
	public Origin add(Origin ori)
	{
		return new Origin(x + ori.x, y + ori.y, z + ori.z);
	}
	
	public Origin sub(Origin ori)
	{
		return new Origin(x - ori.x, y - ori.y, z - ori.z);
	}
	
	public double dot(Origin ori)
	{
		return (x * ori.x + y * ori.y + z * ori.z);
	}
	
	public double dot(Direction dir)
	{
		return (x * dir.x + y * dir.y + z * dir.z);
	}
	
	public double dot(Normal nor)
	{
		return (x * nor.x + y * nor.y + z * nor.z);
	}
}
