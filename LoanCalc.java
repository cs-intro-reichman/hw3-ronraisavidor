// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations

    public static void main(String[] args) {
        // Get the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

		// Loan details
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		System.out.println();

		// Compute the periodical payment using brute force search
		System.out.println("Periodical payment, using brute force: " + (int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
		System.out.println();

		// Compute the periodical payment using bisection search
		System.out.println("Periodical payment, using bi-section search: " + (int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
    }

    // Computes the ending balance of a loan, given the loan amount, annual interest rate,
    // number of periods (n), and the periodical payment.
    private static double endBalance(double loan, double rate, int n, double payment) {
        double annualRate = rate / 100.0;

        // Loop and calculate the remaining balance
        for (int i = 0; i < n; i++) {
            loan = (loan - payment) * (1 + annualRate);
        }

        // Return the final balance after all payments
        return loan;
    }

    // Uses brute force search to compute an approximation of the periodical payment
    // that will bring the ending balance of a loan close to 0.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double payment = loan / n; 
        double step = epsilon;

        // Loop until the ending balance is close to zero
        while (endBalance(loan, rate, n, payment) > 0) {
            payment += step;
            iterationCounter++;
        }

        return payment;
    }

    // Uses bisection search to compute an approximation of the periodical payment
    // that will bring the ending balance of a loan close to 0.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;

        double low = 0;
        double high = loan * (1 + rate / 100.0);

        while ((high - low) > epsilon) {
            iterationCounter++;
            double mid = (low + high) / 2.0;
            double balance = endBalance(loan, rate, n, mid);

            if (balance > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return (low + high) / 2.0;
    }
}