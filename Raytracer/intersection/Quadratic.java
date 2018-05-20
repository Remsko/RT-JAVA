package intersection;

public class Quadratic
{
	public double solver(double a, double b, double c)
	{
		double discriminant = b * b - 4 * a * c;
		if (discriminant < 0.0)
			return (0.0);
		double answer = Math.min(
			(-b + Math.sqrt(discriminant)) / (2.0 * a),
			(-b - Math.sqrt(discriminant)) / (2.0 * a));
		return (answer > 0.0 ? answer : 0.0);
	}
}
