import java.util.*;
class Main {
	private static long power(long a, long b, long p)
	{
		if (b == 1)
			return a;
		else
			return (((long)Math.pow(a, b)) % p);
		
	}
	public static void main(String[] args)
	{
		Scanner s =new Scanner(System.in);
		long P, G, x, a, y, b, ka, kb;
		System.out.println("Enter value of P:");
		P=s.nextLong();
		
		System.out.println("Enter value of G:");
		G=s.nextLong();
		
		System.out.println("Enter value of a:");
		a=s.nextLong();
		
		x = power(G, a, P);
		System.out.println("Enter value of b:");
		b=s.nextLong();
	
		y = power(G, b, P);
		ka = power(y, a, P);
		kb = power(x, b, P);
		System.out.println("Secret key for the Sender is:" + ka);
		System.out.println("Secret key for the Receiver is:" + kb);
	}
}

// order of input
// 23 - 5 - 4 -3
//op: 13 , 13
