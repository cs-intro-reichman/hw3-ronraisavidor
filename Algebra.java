// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		// Replace the following statement with your code
		while (0<x2) {
			x1 += 1;
			x2 -= 1;
		}
		return x1 + x2;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		// Replace the following statement with your code
		while (x2>0) {
			x1 -= 1;
			x2 -= 1;
		}
		return x1 - x2;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
        // Replace the following statement with your code
		int sum = 0;
		for (int i=0; i<x2; i++) {
			sum += plus(x1, 0);
		}
		return sum;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		// Replace the following statement with your code
		int sum = 1;
		for (int i=0; i<n; i++) {
			sum *= times(x, 1);
		}
		return sum;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
        // Replace the following statement with your code
		int count = 0;
		while (x1 >= x2) {
			x1 = minus(x1, x2);
			count++;
		}

		return count;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
        // Replace the following statement with your code
		
		while (x1 >= x2) {
			x1 = minus(x1, x2);
		}
		return x1;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
        // Replace the following statement with your code
		int result = 0;

		while (true) {
			// Compute (result + 1)^2 using pow and times
			int nextResult = plus(result, 1); // result + 1
			int square = times(nextResult, nextResult); // (result + 1)^2
	
			// If (result + 1)^2 > x, stop
			if (minus(x, square) < 0) {
				break;
			}
	
			result = nextResult; // Update result to result + 1
		}
		return result;
	}	  	  
}