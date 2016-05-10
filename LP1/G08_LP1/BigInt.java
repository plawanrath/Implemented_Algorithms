/*
 * @author Plawan,Girish
 * The class itself was created by Girish, and refactored and modified by Plawan.
 * This class contains our custom implementation of Big Integer.
 */
import java.util.LinkedList;

public class BigInt implements Comparable<BigInt> {

	LinkedList<Integer> list; //List of digits
	boolean negative; //flag set to true if the number is negative
	public final static int base = 10; //We set the base to 10 by default and all operations assume a base of 10.

	/*
	 * Constructor to create an empty BigInt. An empty BigInt is not same as 0.
	 */
	public BigInt() {
		list = new LinkedList<Integer>();
		negative = false;
	}

	/*
	 * Constructor to create a big integer with an int
	 */
	public BigInt(int i) 
	{
		String s = i + "";
		toBigInt(s);
	}
	
	/*
	 * Constructor to create a Big Integer where the integer is given as a string
	 */
	public BigInt(String s) {
		toBigInt(s);
	}

	public BigInt(long num)
	{
		String s = num + "";
		toBigInt(s);
	}
	
	/*
	 * Constructor to create a Big Integer with a long type 
	 */
	private void toBigInt(String s)
	{
		int len = s.length();
		list = new LinkedList<Integer>();
		if (s.charAt(0) == '-') {
			negative = true;
			for (int i = len - 1; i > 0; i--) {
				list.add(s.charAt(i) - '0');
			}
		} else {
			negative = false;
			for (int i = len - 1; i >= 0; i--) {
				list.add(s.charAt(i) - '0');
			}
		}		
	}
	/**
	 * Function to format the number as a decimal String.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int n : list) {
			s.insert(0, n);
		}
		if (negative == true)
			s.insert(0, '-');
		return s.toString();
	}

	/*
	 * Function to print the contents of the list which stores the Big Integer
	 */
	public void printList() {
		System.out.print("base " + base + ": ");
		if (negative == true)
			System.out.print("-");
		for (Integer n : list)
			System.out.print(n + " ");
		System.out.println();
	}

	/*
	 * Function to remove leading zeros from the Big Integer
	 */
	public void removeLeadingZeros() {
		while (list.size() > 0) {
			if (list.get(list.size() - 1) == 0)
				list.remove(list.size() - 1);
			else
				return;
		}
		if (list.size() == 0)
			list.add((Integer) 0);
	}

	/*
	 * Function checks if the particular number is zero
	 */
	public boolean IsZero()
	{
		removeLeadingZeros();
		for(int val  : list)
		{
			if(val != 0)
				return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Custom compareTo to check if two numbers are greater, less or equal.
	 * Returns o if numbers are equal, Returns -1 if BigInt b is greater than the number, or else
	 * returns 1.
	 * 
	 */
	@Override
	public int compareTo(BigInt b) {
		
		if (list.size() == 1 && b.list.size() == 1
				&& list.get(0) == 0 && b.list.get(0) == 0)
			return 0;

		if (negative && !b.negative)
			return -1;

		if (!negative && b.negative)
			return 1;

		if (!negative && !b.negative) {
			if (list.size() < b.list.size()) {
				return -1;

			} else if (list.size() > b.list.size()) {
				return 1;
			} else {
				int i = list.size() - 1;
				while (list.get(i) == b.list.get(i)) {
					if (i == 0)
						break;
					i--;
				}
				if (i >= 0) {
					if (list.get(i) < b.list.get(i)) {
						return -1;
					}
				}
				if (i == 0 && list.get(i) == b.list.get(i))
					return 0;
			}

			return 1;
		}

		else {
			if (list.size() < b.list.size()) {
				return 1;

			} else if (list.size() > b.list.size()) {
				return -1;
			} else {
				int i = list.size() - 1;
				while (list.get(i) == b.list.get(i)) {
					if (i == 0)
						break;
					i--;
				}
				if (i >= 0) {
					if (list.get(i) < b.list.get(i)) {
						return 1;
					}
				}
				if (i == 0 && list.get(i) == b.list.get(i))
					return 0;
			}

			return -1;
		}
	}
}
