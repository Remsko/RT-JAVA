package type;

public class Refractive extends ObjectType
{ 
	public Refractive(double coefficient)
	{
		this.coefficient = coefficient;
	}
	
	public String getType()
	{
		return (new String("refractive"));
	}
}
