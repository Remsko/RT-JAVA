package utility;

public class Ray
{
	public Origin ori;
	public Direction dir;
	
	public Ray(Origin ori, Direction dir)
	{
		this.ori = new Origin(ori);
		this.dir = new Direction(dir);
	}
}
