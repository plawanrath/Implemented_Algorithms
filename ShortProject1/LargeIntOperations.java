/**
 * @author plawanrath
 * This class handles large numbers addition and subtraction, where in large numbers are 
 * stored in integer Lists.
 */

import java.util.*;

public class LargeIntOperations {

	private static Integer next(Iterator<Integer> it)
	{
		return it.hasNext() ? it.next() : null;
	}

	/*This function is the direct implementation of the addition of large integer algorithm
	 * explained in class. It adds the numbers stored in lists x and y and stores the result in list z.
	 * Parameter 1: List of integers x (Input list 1)
	 * Parameter 2: List of integers y (Input list 2)
	 * Parameter 3: List of integers z (Output List)
	 * Parameter 4: Base of the large numbers being added
	 */
	public static void add(List<Integer> x, List<Integer>  y,List<Integer> z, int b)
	{
		Iterator<Integer> itx = x.iterator();
		Iterator<Integer> ity = y.iterator();
		Integer xValue = next(itx);
		Integer yValue = next(ity);
		int carry = 0;
		int sum,modValue;
		while(xValue != null && yValue != null)
		{
			sum = xValue + yValue + carry;
			modValue = sum % b;
			z.add(modValue);
			carry = sum / b;
			xValue = next(itx);
			yValue = next(ity);
		}
		while(xValue != null)
		{
			sum = xValue + carry;
			modValue = sum % b;
			z.add(modValue);
			carry = sum / b;
			xValue = next(itx);
		}
		while(yValue != null)
		{
			sum = yValue + carry;
			modValue = sum % b;
			z.add(modValue);
			carry = sum / b;
			yValue = next(ity);
		}
		if(carry > 0)
			z.add(carry);
	}

	/* This function is for subtraction of large integer numbers.
	 * It subtracts the numbers stored in lists x and y and stores the result in list z.
	 * Parameter 1: List of integers x (Input list 1)
	 * Parameter 2: List of integers y (Input list 2)
	 * Parameter 3: List of integers z (Output List)
	 * Parameter 4: Base of the large numbers being subtracted
	 */
	public static void subtract(List<Integer> x, List<Integer>  y,List<Integer> z, int b)
	{
		Iterator<Integer> itx = x.iterator();
		Iterator<Integer> ity = y.iterator();
		Integer xValue = next(itx);
		Integer yValue = next(ity);
		int borrow = 0;
		int sub;
		while(xValue != null && yValue != null)
		{
			if(xValue >= yValue)  //If xValue < yValue we need to borrow from the next upcoming List entry
			{
				xValue = xValue - borrow;
				sub = xValue - yValue;
			}
			else
			{
				int tempXValue = xValue + b;
				xValue = tempXValue - borrow;
				sub = xValue - yValue;
				borrow = tempXValue / b;
			}
			z.add(sub);
			xValue = next(itx);
			yValue = next(ity);
		}
		while(xValue != null)
		{
			sub = xValue - borrow;
			z.add(sub);
			borrow = xValue / b;
			xValue = next(itx);
		}
		if(yValue != null) // We do not handle subtraction of larger number from smaller number ( which would ideally give negative output 
		{
			System.err.println("Larger Number being subtracted from smaller number isn't handled!!");
			System.exit(-1);
		}
	}
	
	/*
	 * Function to display a List
	 */
	public static void displayList(List<Integer> res)
	{
		Iterator<Integer> it = res.iterator();
		while(it.hasNext())
			System.out.print(it.next() + " ");
		System.out.println();		
	}

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(9, 9));
		ArrayList<Integer> b = new ArrayList<>(Arrays.asList(1, 1));
		ArrayList<Integer> res = new ArrayList<>();
		ArrayList<Integer> resSub = new ArrayList<>();
		LargeIntOperations.add(a, b, res, 10);
		LargeIntOperations.displayList(res);
		LargeIntOperations.subtract(a, b, resSub, 10);
		LargeIntOperations.displayList(resSub);
	}

}
