package utility;

public class Ray
{
	protected Origin ori;
	protected Direction dir;
	
	public Ray(Origin ori, Direction dir)
	{
		this.ori = new Origin(ori);
		this.dir = new Direction(dir);
	}
}
