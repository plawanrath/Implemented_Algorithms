package Lists;
import java.util.*;

/**
 * Created by Plawan on 5/3/16.
 * Add subtract numbers in list.
 */
public class LargeNumberArith {

    public static void add(List<Integer> x,List<Integer> y,List<Integer> z,int b)
    {
        int index = 0,carry = 0;
        while(index < x.size() && index < y.size())
        {
            int sum = x.get(index) + y.get(index) + carry;
            int res = sum % b;
            carry = sum / b;
            z.add(res);
            index++;
        }
        while(index < x.size())
        {
            int sum = x.get(index) + carry;
            int res = sum % b;
            carry = sum / b;
            z.add(res);
            index++;
        }
        while(index < y.size())
        {
            int sum = y.get(index) + carry;
            int res = sum % b;
            carry = sum / b;
            z.add(res);
            index++;
        }
        if(carry > 0)
            z.add(carry);
    }

    public static void subtract(List<Integer> x,List<Integer> y,List<Integer> z,int b)
    {
        int index = 0,borrow = 0;
        while(index < x.size() && index < y.size())
        {
            int d1 = x.get(index) - borrow;
            int d2 = y.get(index);
            if(d1 < d2)
            {
                d1 = d1 + b;
                borrow = d1 / b;
            }
            int diff = d1 - d2;
            z.add(diff);
            index++;
        }
        while(index < x.size())
        {
            int diff = x.get(index) - borrow;
            borrow = 0;
            z.add(diff);
            index++;
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> x = new ArrayList<>(Arrays.asList(2,1));
        ArrayList<Integer> y = new ArrayList<>(Arrays.asList(3,1));
        ArrayList<Integer> z = new ArrayList<>();
        add(x,y,z,10);
        SetOperations.printList(z);
        z.clear();
        subtract(y,x,z,10);
        SetOperations.printList(z);

    }
}
