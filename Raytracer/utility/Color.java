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
	
	public Color(Color color)
	{
		this.r = color.r;
		this.g = color.g;
		this.b = color.b;
	}

	public void	add(Color color)
	{
		r += color.r;
		g += color.g;
		b += color.b;
	}
	
	public Color add_ret(double factor)
	{
		return (new Color(r + factor, g + factor, b + factor));
	}
	
	public Color add_ret(Color color)
	{
		return (new Color(r + color.r, g + color.g, b + color.b));
	}
	
	public void	mul(Color color)
	{
		r *= color.r;
		g *= color.g;
		b *= color.b;
	}

	public void mul(double factor)
	{
		r *= factor;
		g *= factor;
		b *= factor;
	}
	
	public Color mul_ret(double factor)
	{
		return (new Color(r * factor, g * factor, b * factor));
	}
	
	public void	divide(int scalar)
	{
		r /= scalar;
		g /= scalar;
		b /= scalar;
	}
	
	public void	divide(Color color)
	{
		r /= color.r;
		g /= color.g;
		b /= color.b;
	}
	
	public Color divide_ret(Color color)
	{
		return (new Color(r /= color.r, g /= color.g, b /= color.b));
	}
	
	public Color divide_ret(double scalar)
	{
		return (new Color(r /= scalar, g /= scalar, b /= scalar));
	}
	
	public int toInteger()
	{
		r = Math.max(r, 0.0);
		g = Math.max(g, 0.0);
		b = Math.max(b, 0.0);
		
		r = Math.min(r, 1.0);
		g = Math.min(g, 1.0);
		b = Math.min(b, 1.0);
		
		return (int)(r *255.0) << 16 | (int)(g *255.0) << 8 | (int)(b *255.0);
	}
}
