package geometry;

import utility.Vector3D;

public class Fresnel
{
	public double getCoefficient(Vector3D incident, Vector3D normal, double IOR)
	{
		double cosI = normal.dot(incident);
		double eta1;
		double eta2;
		
		if (cosI < 0.0)
		{
			eta1 = 1.0;
			eta2 = IOR;
		}
		else
		{
			eta2 = 1.0;
			eta1 = IOR;
		}
		double sinT = (eta1 / eta2) * Math.sqrt(Math.max(0.0, 1 - cosI * cosI));
		if (sinT >= 1.0)
			return (1.0);
		else
		{
			double cosT = Math.sqrt(Math.max(0.0, 1 - sinT * sinT));
			cosI = Math.abs(cosI);
			double Rs = ((eta2 * cosI) - (eta1 * cosT)) / ((eta2 * cosI) + (eta1 * cosT));
			double Rp = ((eta1 * cosI) - (eta2 * cosT)) / ((eta1 * cosI) + (eta2 * cosT));
			return ((Rs * Rs + Rp * Rp) / 2);
		}
	}
}