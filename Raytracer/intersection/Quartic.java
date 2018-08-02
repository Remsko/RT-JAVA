package intersection;

public class Quartic
{
	public static final double TWO_PI_43 = 4.1887902047863909846168;
	public static final double TWO_PI_3 = 2.0943951023931954923084;
	public static final double EPSILON = 1.0e-10;
	public double CubicRoots[] = new double[3];
	public double QuarticRoots[] = new double[4];
	public double smallest = Double.MAX_VALUE;
	public boolean isanswer = false;
	
	public int solveCubic(double a, double b, double c, double d)
	{
		double a0 = a;
		
		double a1 = b / a0;
		double a2 = c / a0;
		double a3 = d / a0;
		
		double A2 = a1 * a1;
		double Q = (A2 - 3.0 * a2) / 9.0;
		double R = (a1 * (A2 - 4.5 * a2) + 13.5 * a3) / 27.0;
		double Q3 = Q * Q * Q;
		double R2 = R * R;
		double D = Q3 - R2;
		double an = a1 / 3.0;
		
		if (D >= 0.0)
		{
			D = R / Math.sqrt (Q3);
		    double theta = Math.acos(d) / 3.0;
		    double sQ = -2.0 * Math.sqrt(Q);

		    CubicRoots[0] = sQ * Math.cos (theta) - an;
		    CubicRoots[1] = sQ * Math.cos (theta + TWO_PI_3) - an;
		    CubicRoots[2] = sQ * Math.cos (theta + TWO_PI_43) - an;
			return (3);
		}
		else
		{
			double sQ = Math.pow(Math.sqrt (R2 - Q3) + Math.abs(R), 1.0 / 3.0);
			
		    if (R < 0)
		    	CubicRoots[0] = (sQ + Q / sQ) - an;
		    else
		    	CubicRoots[0] = -(sQ + Q / sQ) - an;
			return (1);
		}
	}
	
	public int solveQuartic(double a, double b, double c, double d, double e)
	{
		//double c0 = a;
		double c1 = b / a;
        double c2 = c / a;
        double c3 = d / a;
        double c4 = e / a;

        double c12 = c1 * c1;
        double p = -0.375 * c12 + c2;
        double q = 0.125 * c12 * c1 - 0.5 * c1 * c2 + c3;
        double r = -0.01171875 * c12 * c12 + 0.0625 * c12 * c2 - 0.25 * c1 * c3 + c4;
        
        double A = 1.0;
        double B = -0.5 * p;
        double C = -r;
        double D = 0.5 * r * p - 0.125 * q * q;
        
        int i = solveCubic(A, B, C, D);
        
        double z;
        if (i > 0)
        	z = CubicRoots[0];
        else
        	return (0);
        
        double d1 = 2.0 * z - p;
        double d2;
        
        if (d1 < 0.0)
          {
            if (d1 > -EPSILON)
            	d1 = 0.0;
            else
            	return 0;
          }
        if (d1 < EPSILON)
        {
            d2 = z * z - r;
            if (d2 < 0.0)
            	return (0);
            d2 = Math.sqrt(d2);
        }
        else
        {
            d1 = Math.sqrt (d1);
            d2 = 0.5 * q / d1;
        }
        
        double q1 = d1 * d1;
        double q2 = -0.25 * c1;
        i = 0;

        p = q1 - 4.0 * (z - d2);
        if (p == 0)
          QuarticRoots[i++] = -0.5 * d1 - q2;
        else if (p > 0)
        {
            p = Math.sqrt(p);
            QuarticRoots[i++] = -0.5 * (d1 + p) + q2;
            QuarticRoots[i++] = -0.5 * (d1 - p) + q2;
        }
        p = q1 - 4.0 * (z + d2);
        if (p == 0)
        	QuarticRoots[i++] = 0.5 * d1 - q2;
        else if (p > 0)
        {
            p = Math.sqrt(p);
            QuarticRoots[i++] = 0.5 * (d1 + p) + q2;
            QuarticRoots[i++] = 0.5 * (d1 - p) + q2;
        }
        return i;
	}
	
	public Quartic(double a, double b, double c, double d, double e)
	{
		int i = solveQuartic(a, b, c, d, e);
		int j = 0;
		while (j < i)
		{
			if (QuarticRoots[j] > 0.0 && QuarticRoots[j] < smallest)
			{
				smallest = QuarticRoots[j];
				isanswer = true;
			}
			j++;
		}
	}
}
