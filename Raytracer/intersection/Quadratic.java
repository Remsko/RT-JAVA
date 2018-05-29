package intersection;

public class Quadratic
{
	public double discriminant;
	public double smallest;
	public double biggest;
	public boolean isanswer;
	
	public Quadratic()
	{
		discriminant = 0.0;
		smallest = 0.0;
		biggest = 0.0;
		isanswer = false;
	}
	
	public Quadratic(double a, double b, double c)
	{
		discriminant = b * b - 4 * a * c;
		if (discriminant > 0.0)
		{
			/* need to change for more rapidity */
			smallest = Math.min(
					(-b + Math.sqrt(discriminant)) / (2.0 * a),
					(-b - Math.sqrt(discriminant)) / (2.0 * a));
			biggest = Math.max(
					(-b + Math.sqrt(discriminant)) / (2.0 * a),
					(-b - Math.sqrt(discriminant)) / (2.0 * a));
			isanswer = true;
		}
		else
			isanswer = false;
	}
}