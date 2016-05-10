/*
 * @author Plawan, Girish
 * This class performs the various arithmetic operations on large integers represented by the BigInt class.
 * 
 */
import java.util.Iterator;

public class BigIntOperations {

	/*
	 * Helper function to get the next digit of the big integer
	 */
	public static Integer next(Iterator<Integer> it) {
		return it.hasNext() ? it.next() : null;
	}
	
	/*
	 * Utility function to perform additions of two numbers represented by a and b.
	 * @param 1: BigInt a (first big integer)
	 * @param 2: BigInt b (second big integer)
	 * Returns value of type BigInt.
	 */
	private static BigInt addUtil(BigInt a, BigInt b) {

		BigInt ans = new BigInt();
		ans.negative = a.negative;
		Integer carry = 0;
		Integer sum = 0;
		Integer modValue;
		Iterator<Integer> itr1 = a.list.iterator();
		Iterator<Integer> itr2 = b.list.iterator();		
		Integer x = next(itr1);
		Integer y = next(itr2);
		while (x != null && y != null) {
			sum = x + y + carry;
			modValue = sum % BigInt.base;
			ans.list.add(modValue);
			carry = sum / BigInt.base;
			x = next(itr1);
			y = next(itr2);
		}
		while (x != null) {
			sum = x + carry;
			modValue = sum % BigInt.base;
			ans.list.add(modValue);
			carry = sum / BigInt.base;
			x = next(itr1);
		}
		while (y != null) {
			sum = y + carry;
			modValue = sum % BigInt.base;
			ans.list.add(modValue);
			carry = sum / BigInt.base;
			y = next(itr2);
		}
		if (carry > 0)
			ans.list.add(carry);

		return ans;

	}

	/*
	 * Function to perform addition of two Big Integers represented by a and b.
	 * @param 1: BigInt a(first number)
	 * @param 2: BigInt b(Second number)
	 * Returns the addition of two BigInt as another BigInt type. (a+b)
	 */
	public static BigInt add(BigInt a, BigInt b) {

		a.removeLeadingZeros();
		b.removeLeadingZeros();

		BigInt result;
		if (a.negative != b.negative) {
			if (a.compareTo(b) < 0) {
				result = subtractUtil(b, a);
				result.negative = b.negative;
				return result;
			} else {
				result = subtractUtil(a, b);
				result.negative = a.negative;
				return result;
			}
		}

		result = addUtil(a, b);
		result.negative = a.negative;
		return result;
	}

	/*
	 * Utility function to perform subtraction of two numbers represented by a and b.
	 * @param 1: BigInt a (first big integer)
	 * @param 2: BigInt b (second big integer)
	 * Returns value of type BigInt.
	 */
	private static BigInt subtractUtil(BigInt a, BigInt b) {

		BigInt ans = new BigInt();
		Integer borrow = 0;
		Integer sub = 0;
		Iterator<Integer> itr1 = a.list.iterator();
		Iterator<Integer> itr2 = b.list.iterator();
		Integer x = next(itr1);
		Integer y = next(itr2);

		while (x != null && y != null) {
			x = x - borrow;

			if (x < y) {
				x = x + BigInt.base;
				borrow = 1;
			} else {
				borrow = 0;
			}

			sub = (x - y) % BigInt.base;
			ans.list.add(sub);
			x = next(itr1);
			y = next(itr2);
		}
		while (x != null) {
			if (x < borrow) {
				x = x + BigInt.base - borrow;
				borrow = 1;
			} else {
				x = x - borrow;
				borrow = 0;
			}
			ans.list.add(x % BigInt.base);
			x = next(itr1);
		}
		ans.removeLeadingZeros();
		return ans;

	}

	/*
	 * Function to perform subtraction of two Big Integers represented by a and b.
	 * @param 1: BigInt a(first number)
	 * @param 2: BigInt b(Second number)
	 * Returns the subtraction of two BigInt as another BigInt type. (a-b)
	 */
	public static BigInt subtract(BigInt a, BigInt b) {

		if (a.negative != b.negative) {
			BigInt l3 = addUtil(b, a);
			l3.negative = a.negative;
			return l3;
		} else if (a.compareTo(b) < 0) {
			BigInt l3 = subtractUtil(b, a);
			l3.negative = !a.negative;
			return l3;
		} else {
			BigInt l3 = subtractUtil(a, b);
			l3.negative = a.negative;
			return l3;
		}

	}
	
	/*
	 * Function to perform product of two Big Integers represented by a and b.
	 * @param 1: BigInt a(first number)
	 * @param 2: BigInt b(Second number)
	 * Returns the product of two BigInt as another BigInt type. (a*b)
	 */
	public static BigInt product(BigInt a, BigInt b) {

		if (a.list.size() == 1 && a.list.get(0) == 0) {
			return a;
		}
		if (b.list.size() == 1 && b.list.get(0) == 0) {
			return b;
		}

		BigInt ans = new BigInt();
		if (a.negative == b.negative)
			ans.negative = false;
		else
			ans.negative = true;

		Integer carry = 0;
		Integer prod = 0;

		for (int i = 0; i < a.list.size() + b.list.size(); i++)
			ans.list.add(0);

		for (int i = 0; i < a.list.size(); i++) {
			for (int j = 0; j < b.list.size(); j++) {
				prod = ans.list.get(i + j) + (a.list.get(i) * b.list.get(j));
				ans.list.set(i + j, prod);

			}
		}

		for (int i = 0; i < ans.list.size(); i++) {
			prod = ans.list.get(i) + carry;
			carry = prod / BigInt.base;
			ans.list.set(i, prod % BigInt.base);
		}
		if (carry > 0)
			ans.list.add(carry);

		ans.removeLeadingZeros();
		return ans;
	}

	/*
	 * Function to perform division of two Big Integers represented by a and b.
	 * @param 1: BigInt a(first number)
	 * @param 2: BigInt b(Second number)
	 * Returns the division of two BigInt as another BigInt type. (a/b)
	 */
	public static BigInt division(BigInt a,BigInt b)
	{
		boolean negative = false;
		BigInt res = new BigInt(0);
		if(b.list.size() == 0)
			return null;
		if(a.negative || b.negative)
			negative = true;
		else
			negative = false;
		if(a.negative)
			a.negative = false;
		if(b.negative)
			b.negative = false;
		while(!a.list.isEmpty() && !a.negative && !a.IsZero() && a.compareTo(b) >= 0)
		{
			a = subtract(a,b);
			a.removeLeadingZeros();
			res = add(res, new BigInt(1));
		}
		res.negative = negative;
		return res;
	}

	/*
	 * Function to perform modulo of two Big Integers represented by a and b.
	 * @param 1: BigInt a(first number)
	 * @param 2: BigInt b(Second number)
	 * Returns the modulo of two BigInt as another BigInt type. (a%b)
	 */
	public static BigInt mod(BigInt a,BigInt b)
	{
		boolean negative = false;
		BigInt res = new BigInt(0);
		if(b.list.size() == 0)
			return null;
		if(a.negative || b.negative)
			negative = true;
		else
			negative = false;
		if(a.negative)
			a.negative = false;
		if(b.negative)
			b.negative = false;
		if(a.compareTo(b) < 0)
		{
			res.negative = negative;
			res = a;
			return res;
		}
		while(!a.list.isEmpty() && !a.negative && !a.IsZero() && a.compareTo(b) >= 0)
		{
			a = subtract(a,b);
			a.removeLeadingZeros();
			res = a;
		}
		res.negative = negative;
		return res;		
	}
	
	/*
	 * FUnction to calculate the nth power of the given number.
	 * @param 1: The BigInt number a
	 * @param 2: The power to which it needs to be raised 
	 * Returns the result of @param1^@param2 as a BigInt number
	 * 
	 * https://en.wikipedia.org/wiki/Exponentiation_by_squaring
	 */
	public static BigInt power(BigInt a,long n)
	{
		return auxPower(new BigInt(1),a,n);
	}
	
	/*
	 * This is an auxiliary function to perform a Divide and conquer method to find the power.
	 * It is called by the power function to find the power.
	 * @param 1: an auxiliary BigInt number
	 * @param 2: the BigInt number to find the exponent of
	 * @param 3: The power to which the number is to be raised
	 * Returns the result of @param2^@param3 
	 */
	private static BigInt auxPower(BigInt y, BigInt a,long n)
	{
		if(n == 0)
			return y;
		else if(n == 1)
			return product(a,y);
		else if(n%2 == 0)
			return auxPower(y,product(a,a),n/2);
		else
			return auxPower(product(a,y),product(a,a),(n-1)/2);
	}

	/*
	 * FUnction to calculate the nth power of the given number.
	 * @param 1: The BigInt number a
	 * @param 2: The BigInt power to which it needs to be raised 
	 * Returns the result of @param1^@param2 as a BigInt number
	 * 
	 */
	public static BigInt power(BigInt a, BigInt b)
	{
		return auxPower(new BigInt(1),a,b);
	}
	
	/*
	 * This is an auxiliary function to perform a Divide and conquer method to find the power.
	 * It is called by the power function to find the power.
	 * @param 1: an auxiliary BigInt number
	 * @param 2: the BigInt number to find the exponent of
	 * @param 3: The BigInt power to which the number is to be raised
	 * Returns the result of @param2^@param3 
	 */
	private static BigInt auxPower(BigInt y,BigInt a,BigInt n)
	{
		if(n.IsZero())
			return y;
		else if(n.compareTo(new BigInt(1)) == 0)
			return product(a,y);
		else if(mod(n,new BigInt(2)).compareTo(new BigInt(0)) == 0)
			return auxPower(y,product(a,a),division(n,new BigInt(2)));
		else
			return auxPower(product(a,y),product(a,a),division(subtract(n,new BigInt(1)),new BigInt(2)));
	}
	
	/*
	 * This function uses Binary search technique to perform square root of the given number
	 * @param 1: the BigInt number to find square root of.
	 * Returns a BigInt number which is the square root of @param 1.
	 * 
	 * http://www.ardendertat.com/2012/01/26/programming-interview-questions-27-squareroot-of-a-number/
	 */
	public static BigInt squareRoot(BigInt num)
	{
		BigInt low = new BigInt(0);
		BigInt high = add(num,new BigInt(1));
		BigInt mid;
		while(subtract(high,low).compareTo(new BigInt(1)) > 0)
		{
			mid = division(add(low,high),new BigInt(2));
			if(product(mid,mid).compareTo(num) <= 0)
				low = mid;
			else
				high = mid;
		}
		return low;
	}
	
}
