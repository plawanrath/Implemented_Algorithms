//TeamMembers: Girish Tejwani, Plawan Rath
/**
 * Created by Plawan on 3/31/16.
 * Tested and modified by Girish
 */

import java.util.Arrays;

public class Item {
    long id;
    double price;
    long[] description;
    int size;

    public Item(long id, double price, long[] description, int size)
    {
        this.id = id;
        this.price = price;
        long[] updatedDesc = getNewSmallerDescriptionArray(description);
        Arrays.sort(updatedDesc);
        this.description = updatedDesc;
        this.size = size;
    }

    private boolean search(long value, long[] array,int low,int high)
    {
        while(low <= high)
        {
            int mid = low + (high-low)/2;
            if(array[mid] == value)
                return true;
            else if(array[mid] < value)
                low = mid+1;
            else
                high = mid-1;
        }
        return false;
    }

    private long[] getNewSmallerDescriptionArray(long[] array)
    {
        int j = 0;
        for( int i=0;  i<array.length;  i++ )
        {
            if (array[i] != 0)
                array[j++] = array[i];
        }
        long [] newArray = new long[j];
        System.arraycopy( array, 0, newArray, 0, j );
        return newArray;
    }

    @SuppressWarnings("unused")
	private int min(int x, int y) { return (x<=y)? x : y; }

    public boolean search(long value)
    {
        return search(value, this.description, 0, this.description.length-1);
    }
}
