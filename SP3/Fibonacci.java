import java.util.Scanner;
/*
 * @author Girish
 */
public class Fibonacci {

	public static long p;
	
	/**
	 * 
	 * @param number
	 * @param p
	 * @param fib
	 * @return fibonacci number at a given number mod p
	 * Function to implement fibonacci without DAC
	 * This takes linear time as we are calculating each value
	 */
	public long linearFibonacci(long n, long p){
		long zero = 0 % p;
		long first = 1 % p;
		long result = 0 % p;
		for(int i = 2;i <= n;i++){
			result = (zero % p  + first % p) % p;
			zero = first % p;
			first = result % p;
		}
		return result % p;
	}
	
	/**
	 * Function to write fibonacci with recursion
	 * @param args
	 * this takes exponential time. So not advised
	 */
	
	public long fibonacciRecursive(long number){
		if ((int) number == 0){
			return 0 % p;
		}
		else if ((int) number == 1){
			return 1 % p;
		}
		else{
			return fibonacciRecursive(number-1) % p + fibonacciRecursive(number-2) % p; 
		}
	}
	
	/**
	 * Fibonacci O(logn)
	 * @param args
	 */
	public long[][] matrixMultiplication(long[][] matrix1, long[][] matrix2, long p){
		long[][] result = new long[2][2];
		result[0][0] = ((matrix1[0][0] * matrix2[0][0]) % p + (matrix1[0][1] * matrix2[1][0]) % p);
		result[0][1] = ((matrix1[0][0] * matrix2[0][1]) % p + (matrix1[0][1] * matrix2[1][1]) % p);
		result[1][0] = ((matrix1[1][0] * matrix2[0][0]) % p + (matrix1[1][1] * matrix2[0][1]) % p);
		result[1][1] = ((matrix1[1][0] * matrix2[0][1]) % p + (matrix1[1][1] * matrix2[1][1]) % p);
		
		return result;
	}
	public long[][] exponentMatrix(long[][] matrix1, long power, long p){
		long[][] result;
		if (power == 1){
			return matrix1;
		}
		result = exponentMatrix(matrixMultiplication(matrix1, matrix1,p), power/2, p);
		if (power % 2 != 0){
			return matrixMultiplication(result, matrix1, p);
		}
		else{			
			return result;
		}
	}
	
	public long logFibonacci(long n, long p){
		long[][] matrix = {{1, 1}, {1, 0}};
		long[][] result = exponentMatrix(matrix, n-1,p);
		
		return result[0][0] % p;
	}
	
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		/*long number = scan.nextLong();
		p = scan.nextLong();*/
		
	//		long number = 2000000000;
	//		long p = 999953;
		long number = -1,p = -1;
		if(args.length == 0)
		{
			number = scan.nextLong();
			p = scan.nextLong();
		}
		else if(args.length == 2)
		{
			number = Long.parseLong(args[0]);
			p = Long.parseLong(args[1]);
		}
		else
		{
			System.out.println("Please check arguments!!");
			System.exit(-1);
		}
		
		Timer timer = new Timer();
		Fibonacci fb = new Fibonacci();
		System.out.println("Time taken by O(n) method");
		
		timer.start();
		System.out.println("f_n%p = " + fb.linearFibonacci(number, p));
		timer.end();
		System.out.println(timer);
		
		System.out.println();
		
		System.out.println("Time taken by O(logn) method: ");

		timer.start();
		System.out.println("f_n%p = " + fb.logFibonacci(number, p));
		timer.end();
		System.out.println(timer);
		
		scan.close();
	}

}