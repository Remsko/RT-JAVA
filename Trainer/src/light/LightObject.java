package light;

import utility.Color;
import utility.Origin;

public class LightObject
{
	public Origin	ori;
	public Color	c;
	public float	i;
	
	public LightObject()
	{
		this.ori = null;
		this.c = null;
		this.i = 0.0f;
	}
	
	public LightObject(Origin ori, Color c, float i)
	{
		this.ori = ori;
		this.c = c;
		this.i = i;
	}
	
	public Color setAmbientLight()
	{
		Color c;
		
		/* add Object.ambientC */
		c = new Color(0.0f, 0.0f, 0.0f);
		return (c);
	}
	
	public Color Process(LightObject[] LightArr)
	{
		Color c;
		
		c = setAmbientLight();
		for (int i = 0; i < LightArr.length; i++)
		{
			/* if (lightvector + inter with lightvector) */
				/* diffuse process */
			/* specular process */
		}
		return (c);
	}
}
