package type;

public class Fresnel extends ObjectType
{
	public Fresnel(double coefficient, double IOR)
	{
		this.coefficient = coefficient;
		this.IOR = IOR;
	}
	
	public String getType()
	{
		return (new String("fresnel"));
	}
}