package utility;

public class Color
{
	public double r, g, b;
	
	public Color()
	{
		r = 0.0;
		g = 0.0;
		b = 0.0;
	}
	
	public Color(double r, double g, double b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void	add(Color color)
	{
		r += color.r;
		g += color.g;
		b += color.b;
	}
	
	public void	mul(Color color)
	{
		r *= color.r;
		g *= color.g;
		b *= color.b;
	}
	
	public void	divide(int scalar)
	{
		r /= scalar;
		g /= scalar;
		b /= scalar;
	}
	
	public int toInteger()
	{
		r = r > 1.0 ? 1.0 : r;
		g = g > 1.0 ? 1.0 : g;
		b = b > 1.0 ? 1.0 : b;
		
		r = r < 0.0 ? 0.0 : r;
		g = g < 0.0 ? 0.0 : g;
		b = b < 0.0 ? 0.0 : b;
		
		return (int)(r *255.0) << 16 | (int)(g *255.0) << 8 | (int)(b *255.0);
	}
}
