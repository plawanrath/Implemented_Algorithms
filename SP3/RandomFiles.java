import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;

public class RandomFiles {

	public int[] randomUniqueGenerator(int min,int max,int size)
	{
		HashSet<Integer> used = new HashSet<Integer>();
		int[] res = new int[size];
		int x;
		for(int i=0;i<size;i++)
		{
			do
			{
				Random r = new Random();
				x = r.nextInt((max - min) + 1) + min;
			} while(used.contains(x));
			res[i] = x;
			used.add(x);
		}		
		return res;
	}

	public int[] randomNonUnique(int min,int max,int size)
	{
		int[] res = new int[size];
		for(int i=0;i<size;i++)
		{
			Random r = new Random();
			res[i] = r.nextInt((max - min) + 1) + min;
		}
		return res;
	}
	
	public void putIntoFile(String filePath,int[] arr) throws FileNotFoundException
	{
		File file1 = new File(filePath);
		FileOutputStream fs1 = new FileOutputStream(file1);
		PrintStream out1 = new PrintStream(fs1);
		System.setOut(out1);
		System.out.println(arr.length);
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i] + " ");
		System.setOut(System.out);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		RandomFiles rf = new RandomFiles();
//		int[] arr = rf.randomUniqueGenerator(0, 10000000, 10000000);
//		rf.putIntoFile("10000000-values", arr);
		int[] arr = rf.randomNonUnique(0, 100000, 100000);
		rf.putIntoFile("100000-NonDistinctvalues", arr);
	}

}
