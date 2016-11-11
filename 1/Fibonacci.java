public class Fibonacci {
	public static void main(String[] args) {
		int a = 1, b = 1;
		int t, n;
		System.out.print(a + " " + b);
		for(n = 2; n < 40; n++) {
			t = a + b;
			a = b;
			b = t;
			System.out.print(" " + b);
		}
		System.out.println();
		double fib = (double)a / (double)b;
		String f = Double.toString(fib);
		System.out.println(f);
		System.out.println(
			"(" + n + ", " 
			+ f.substring(f.lastIndexOf(".")+ 1).length()
			+  ")"
		);
	}
}
