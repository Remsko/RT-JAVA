package sampling;

import java.util.Random;

import main.Main;
import utility.Point2D;

public class JitteredSample extends Sampler
{
	public Random random;
	
	public JitteredSample(int samples)
	{
		this.samples = samples;
		random = new Random();
	}
	
	public Point2D sample(int row, int col, int x, int y)
	{
		Point2D point = new Point2D();
		
		point.x = x - Main.world.viewplane.width / 2 + (col + random.nextDouble()) / samples;
		point.x = x - Main.world.viewplane.width / 2 + (col + random.nextDouble()) / samples;
		
		return (point);
	}
}
