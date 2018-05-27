package type;

public class None extends ObjectType
{
	public None()
	{
		this.IOR = 0.0;
	}
	
	public String getType()
	{
		return (new String("none"));
	}
}