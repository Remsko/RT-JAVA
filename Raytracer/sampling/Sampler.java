package sampling;

import utility.Point2D;

public abstract class Sampler
{
	public int samples;
	
	public abstract Point2D sample(int row, int col, int x, int y);
}
