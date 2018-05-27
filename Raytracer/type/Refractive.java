package type;

public class Refractive extends ObjectType
{
	public Refractive(double coefficient, double IOR)
	{
		this.coefficient = coefficient;
		this.IOR = IOR;
	}
	
	public String getType()
	{
		return (new String("refractive"));
	}
}
