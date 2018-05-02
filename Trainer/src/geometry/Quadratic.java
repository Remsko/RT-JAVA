package geometry;

public class Quadratic
{
	private double x1, x2;
	private double discriminant;

	public Quadratic(double a, double b, double c)
	{
		setDiscriminant(b * b - 4.0 * a * c);
		setX1((-b - Math.sqrt(discriminant)) / (2 * a));
		setX2((-b + Math.sqrt(discriminant)) / (2 * a));
	}
	
	public boolean hasSolution()
	{
		return (discriminant > 0.0 ? true : false);
	}
	
	public double getPositiveS()
	{
		if (hasSolution() == false)
			return (0.0);
		else if (getHighestS() > 10E-9)
			return (getHighestS());
		else
			return (0.0);
	}
	
	public double getHighestS()
	{
		return (x1 > x2 ? x1 : x2);
	}
	
	public double getSmallestS()
	{
		return (x1 < x2 ? x1 : x2);
	}
	
	public double getX1()
	{
		return (x1);
	}

	public void setX1(double x1)
	{
		this.x1 = x1;
	}
	
	public double getX2()
	{
		return (x2);
	}

	public void setX2(double x2)
	{
		this.x2 = x2;
	}

	public double getDiscriminant()
	{
		return (discriminant);
	}

	public void setDiscriminant(double discriminant)
	{
		this.discriminant = discriminant;
	}
}
