// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.println("Periodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.println("Periodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	

		double interestRate = rate / 100.0;

		// Loop and calculate the remaining balance
		for (int i = 0; i < n; i++) {
			loan = (loan - payment) * (1 + interestRate);
		}
	
		// Return the final balance after all payments
		return loan;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		// Replace the following statement with your code
		
		double interestRate = rate / 100.0;
		iterationCounter = 0;
		double payment = loan / n; // Initial guess for the payment
		double step = epsilon;

		// Calculate the ending balance for the current payment
		while (endBalance(loan, interestRate, n, payment) > 0) {
			payment += step;
			iterationCounter++;
		}

		return payment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        
		double interestRate = rate / 100.0;
		iterationCounter = 0;

		double low = loan / n; // Minimum payment guess
		double high = (loan * Math.pow(1 + interestRate, n)) / n; // Maximum payment guess

		while (high - low > epsilon) {
			iterationCounter++;
			double mid = (low + high) / 2.0;
			double balance = endBalance(loan, interestRate, n, mid);

			if (balance > 0) {
				low = mid;
			} else {
				high = mid;
			}
		}

		return (low + high) / 2.0;
	}
}