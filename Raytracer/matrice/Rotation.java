package matrice;

import utility.TripleDouble;

public class Rotation
{
	private double tmpX;
	private double tmpY;
	
	public void xAxis(TripleDouble target, double angle)
	{
		tmpX = target.z;
		tmpY = target.y;
		target.y = tmpY * Math.cos(angle) - tmpX * Math.sin(angle);
		target.z = tmpY * Math.sin(angle) + tmpX * Math.cos(angle);
	}
	
	public void yAxis(TripleDouble target, double angle)
	{
		tmpX = target.x;
		tmpY = target.z;
		target.z = tmpY *  Math.cos(angle) - tmpX *  Math.sin(angle);
		target.x = tmpY *  Math.sin(angle) + tmpX *  Math.cos(angle);
	}
	
	public void zAxis(TripleDouble target, double angle)
	{
		tmpX = target.x;
		tmpY = target.y;
		target.x = tmpX * Math.cos(angle) - tmpY * Math.sin(angle);
		target.y = tmpX * Math.sin(angle) + tmpY * Math.cos(angle);
	}
	
	public void rotate(TripleDouble target, TripleDouble rotation)
	{
		xAxis(target, Math.toRadians(rotation.x));
		yAxis(target, Math.toRadians(rotation.y));
		zAxis(target, Math.toRadians(rotation.z));
	}
	
	public void reverseRotate(TripleDouble target, TripleDouble rotation)
	{
		xAxis(target, -Math.toRadians(rotation.x));
		yAxis(target, -Math.toRadians(rotation.y));
		zAxis(target, -Math.toRadians(rotation.z));
	}
}
