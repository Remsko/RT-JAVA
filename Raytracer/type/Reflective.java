package type;

public class Reflective extends ObjectType
{
	public Reflective(double coefficient)
	{
		this.coefficient = coefficient;
	}
	
	public String getType()
	{
		return (new String("reflective"));
	}
}
