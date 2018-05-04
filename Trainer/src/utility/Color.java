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
	
	public void add(double d)
	{
		r += d;
		g += d;
		b += d;
	}
	
	public Color mul(Color c)
	{
		return new Color(r * c.r, g * c.g, b * c.b);
	}
	
	public Color mul(float d)
	{
		return new Color(r * d, g * d, b * d);
	}
	
	public Color divide(float scalar)
	{
		return new Color(r / scalar, g / scalar, b / scalar);
	}
	
	public int toInteger()
	{
		return ((int)(r * 255) << 16 | (int)(g * 255) << 8 | (int)(b * 255));
	}
}
