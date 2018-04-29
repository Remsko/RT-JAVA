package utility;

public class Color
{
	protected float r, g ,b;
	
	public Color()
	{
		r = 0.0f;
		g = 0.0f;
		b = 0.0f;
	}
	
	public Color(float r, float g, float b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Color(Color c)
	{
		r = c.r;
		g = c.g;
		b = c.b;
	}
	
	public void add(Color c)
	{
		r += c.r;
		g += c.g;
		b += c.b;
	}
	
	public void divide(float scalar)
	{
		r /= scalar;
		g /= scalar;
		b /= scalar;
	}
	
	public int toInteger()
	{
		return ((int)(r * 255) << 16 | (int)(g * 255) << 8 | (int)(b * 255));
	}
}
