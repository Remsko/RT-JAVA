package type;

public class None extends ObjectType
{
	public None()
	{
		this.coefficient = 0.0;
	}
	
	public String getType()
	{
		return (new String("none"));
	}
}