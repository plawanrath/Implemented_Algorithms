/*
 * @author Plawan
 * This is a driver program, which takes commands from console and
 * performs the necessary actions based on the conventions specified.
 * 
 */
import java.util.*;
import java.util.Scanner;

public class OperationDrivers {

	static HashMap<String,String> variableValueMap = new HashMap<String,String>(); //Maps variable to value
	static Queue<String> results = new LinkedList<String>(); //stores all the results in a queue
	static ArrayList<String> lines = new ArrayList<String>(); //Stores all the lines of commands that the user entered
	static HashMap<Integer,Integer> indexListMap = new HashMap<Integer,Integer>(); //Maps index of commands in the lines list to the line numbers entered by the users
	
	static int index = 0;
	
	/*
	 * Function to parse a line of command
	 * @param 1: The string representing the command entered by the end user.
	 * It will perform the necessary action for the command given in the statement.
	 */
	public static void readInput(String value)
	{
		String res = null;
		boolean foundEquality = false;
		if(value.contains("="))
			foundEquality = true;
		String[] assignmentSplit = value.split("="); //Splits the string between assignment and operation 
		if(assignmentSplit.length == 1) //if no arithmetic operation
		{
			if(assignmentSplit[0].contains("?")) //if ternary operation
			{
				String[] operationSplit = assignmentSplit[0].split("\\?");
				if(operationSplit.length <= 1) //ternary operator should at least have one argument after ?
					throw new IllegalStateException("Please check the expression!!");
				if(operationSplit[1].contains(":"))
				{
					String[] colonSplit = operationSplit[1].split(":");
					String startLinenum = colonSplit[0];
					Integer ln = Integer.parseInt(startLinenum);
					String afterZeroLine = colonSplit[1];
					Integer afterZero = Integer.parseInt(afterZeroLine);
					String itrVal = variableValueMap.get(operationSplit[0]);
					BigInt itr = new BigInt(itrVal);
					if(itr.compareTo(new BigInt(0)) != 0)
						index = indexListMap.get(ln);
					else
						index = indexListMap.get(afterZero);
				}
				else
				{
					String itrVal = variableValueMap.get(operationSplit[0]);
					BigInt itr = new BigInt(itrVal);
					String startLineNum = operationSplit[1];
					Integer ln = Integer.parseInt(startLineNum);
					if(itr.compareTo(new BigInt(0)) != 0)
						index = indexListMap.get(ln);
				}	
			}
			else if(assignmentSplit[0].contains(")")) //Condition to call printList
			{
				String[] bracketSplit = assignmentSplit[0].split("\\)");
				if(bracketSplit.length > 1)
					throw new IllegalStateException("Please check the expression!!");
				else {
					String valueA = variableValueMap.get(bracketSplit[0]) != null
							? variableValueMap.get(bracketSplit[0])  : bracketSplit[0];
					BigInt a = new BigInt(valueA);
					a.printList();
				}
			}
			else //If no = then we print the value of the variable
			{
				if(foundEquality)
					throw new IllegalStateException("Please check the expression!!");
				String printJob = assignmentSplit[0];
				String val = variableValueMap.get(printJob);
				if(val != null)
					results.add(val);
				else
					throw new IllegalStateException("Undeclared variable!!");
			}
		}
		else //COndition for arithmetic operations
		{
			if(assignmentSplit[1].contains("+")) //perform addition
			{
				String[] operationSplit = assignmentSplit[1].split("\\+");
				if(operationSplit.length == 2)
				{
					String valueA = variableValueMap.get(operationSplit[0]) != null
							? variableValueMap.get(operationSplit[0])  : operationSplit[0];
					String valueB = variableValueMap.get(operationSplit[1]) != null
							? variableValueMap.get(operationSplit[1])  : operationSplit[1];
					BigInt a = new BigInt(valueA);
					BigInt b = new BigInt(valueB);
					res = BigIntOperations.add(a, b).toString();
				}
				else
					throw new IllegalStateException("Please check the expression!!");
				variableValueMap.put(assignmentSplit[0],res);
			}
			else if(assignmentSplit[1].contains("-")) //perform subtraction
			{
				String[] operationSplit = assignmentSplit[1].split("-");
				if(operationSplit.length == 2)
				{
					String valueA = variableValueMap.get(operationSplit[0]) != null
							? variableValueMap.get(operationSplit[0])  : operationSplit[0];
					String valueB = variableValueMap.get(operationSplit[1]) != null
							? variableValueMap.get(operationSplit[1])  : operationSplit[1];
					BigInt a = new BigInt(valueA);
					BigInt b = new BigInt(valueB);
					res = BigIntOperations.subtract(a, b).toString();
				}
				else
					throw new IllegalStateException("Please check the expression!!");
				variableValueMap.put(assignmentSplit[0],res);
			}
			else if(assignmentSplit[1].contains("*")) //perform multiplication
			{
				String[] operationSplit = assignmentSplit[1].split("\\*");
				if(operationSplit.length == 2)
				{
					String valueA = variableValueMap.get(operationSplit[0]) != null
							? variableValueMap.get(operationSplit[0])  : operationSplit[0];
					String valueB = variableValueMap.get(operationSplit[1]) != null
							? variableValueMap.get(operationSplit[1])  : operationSplit[1];
					BigInt a = new BigInt(valueA);
					BigInt b = new BigInt(valueB);
					res = BigIntOperations.product(a, b).toString();
				}
				else
					throw new IllegalStateException("Please check the expression!!");
				variableValueMap.put(assignmentSplit[0],res);
			}
			else if(assignmentSplit[1].contains("/")) //perform division
			{
				String[] operationSplit = assignmentSplit[1].split("/"); 
				if(operationSplit.length == 2)
				{
					String valueA = variableValueMap.get(operationSplit[0]) != null
							? variableValueMap.get(operationSplit[0])  : operationSplit[0];
					String valueB = variableValueMap.get(operationSplit[1]) != null
							? variableValueMap.get(operationSplit[1])  : operationSplit[1];
					BigInt a = new BigInt(valueA);
					BigInt b = new BigInt(valueB);
					res = BigIntOperations.division(a, b).toString();
				}
				else
					throw new IllegalStateException("Please check the expression!!");
				variableValueMap.put(assignmentSplit[0],res);
			}
			else if(assignmentSplit[1].contains("%")) //perform modulo
			{
				String[] operationSplit = assignmentSplit[1].split("%"); 
				if(operationSplit.length == 2)
				{
					String valueA = variableValueMap.get(operationSplit[0]) != null
							? variableValueMap.get(operationSplit[0])  : operationSplit[0];
					String valueB = variableValueMap.get(operationSplit[1]) != null
							? variableValueMap.get(operationSplit[1])  : operationSplit[1];
					BigInt a = new BigInt(valueA);
					BigInt b = new BigInt(valueB);
					res = BigIntOperations.mod(a, b).toString();
				}
				else
					throw new IllegalStateException("Please check the expression!!");
				variableValueMap.put(assignmentSplit[0],res);
			}
			else if(assignmentSplit[1].contains("^")) //perform exponent
			{
				String[] operationSplit = assignmentSplit[1].split("\\^");
				if(operationSplit.length == 2)
				{
					String valueA = variableValueMap.get(operationSplit[0]) != null
							? variableValueMap.get(operationSplit[0])  : operationSplit[0];
					String valueB = variableValueMap.get(operationSplit[1]) != null
							? variableValueMap.get(operationSplit[1])  : operationSplit[1];
					BigInt a = new BigInt(valueA);
					BigInt b = new BigInt(valueB);
					res = BigIntOperations.power(a, b).toString();
				}
				else
					throw new IllegalStateException("Please check the expression!!");				
				variableValueMap.put(assignmentSplit[0],res);
			}
			else if(assignmentSplit[1].contains("~")) //perform square root
			{
				String[] operationSplit = assignmentSplit[1].split("~");
				if(operationSplit.length == 1)
				{
					String valueA = variableValueMap.get(operationSplit[0]) != null
							? variableValueMap.get(operationSplit[0])  : operationSplit[0];
					BigInt a = new BigInt(valueA);
					res = BigIntOperations.squareRoot(a).toString();
				}
				else
					throw new IllegalStateException("Please check the expression!!");
				variableValueMap.put(assignmentSplit[0],res);
			}
			else //If no operator then it is just assignment
			{
				variableValueMap.put(assignmentSplit[0],assignmentSplit[1]);
			}
		}
	}
	
	/*
	 * Function to print results of all the operations
	 */
	public static void printAll()
	{
		while(!results.isEmpty())
		{
			System.out.println(results.poll());
		}
	}
	
	/*
	 * The main function
	 */
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String line = null;
		while(!(line = sc.nextLine()).isEmpty())
		{
			String[] LineAndOperation = line.split(" ");
			if(LineAndOperation.length <= 1)
				throw new IllegalStateException("Please make sure line number is present!!");
			int lineNo = Integer.parseInt(LineAndOperation[0]);
			String operation = LineAndOperation[1];
			lines.add(operation);
//			index++;
			indexListMap.put(lineNo, lines.size()-1);
//			readInput(operation);
		}
		for(int i = 0;i<lines.size();i++)
		{
			i = index++;
			readInput(lines.get(i));
//			index++;
		}
		sc.close();
		printAll();
	}
}
