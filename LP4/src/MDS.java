//TeamMembers: Girish Tejwani, Plawan Rath
/**
 * Created by Plawan on 3/31/16.
 * Tested and modified by Girish
 */

import java.text.DecimalFormat;
import java.util.*;

public class MDS {

    TreeMap<Long, Item> itemCollection = new TreeMap<>();
    public static HashSet<Long> setOfEqualIds = new HashSet<>();

    // insert a new item
    int insert(long id, double price, long[] description, int size) {
        if(itemCollection.containsKey(id))
        {
            Item value = itemCollection.remove(id);
            if(size > 0)
            {
                value.description = description;
                value.price = price;
                parseSameSame(value);
            }
            else
            {
                value.price = price;
            }
            itemCollection.put(id,value);
            return 0;
        }
        else
        {
            Item val = new Item(id, price, description, size);
            parseSameSame(val);
            itemCollection.put(id, val);
            return 1;
        }
    }

    // return price of item with given id
    double find(long id) {
        Item value = itemCollection.get(id);
        if(value != null)
            return value.price;
        return 0;
    }

    //delete item from storage and returns the sum of description
    long delete(long id) {
        Item value = itemCollection.remove(id);
        if(value != null)
        {
            long sum = 0;
            for(long val : value.description)
                sum += val;
            return sum;
        }
        return 0;
    }
    
    // given a long int n, find items whose description contains n 
    // and return lowest price of those items.
    double findMinPrice(long des) {
        ArrayList<Double> res = getPricesMatchingDescriptions(des);
        return res.size() > 0 ? res.get(0) : 0;
    }

    //given a long int n, find items whose description contains n, and return highest price of those items.
    double findMaxPrice(long des) {
        ArrayList<Double> res = getPricesMatchingDescriptions(des);
        Collections.sort(res,Collections.reverseOrder());
        return res.size() > 0 ? res.get(0) : 0;
    }

    //given a long int n, find the number of items whose description contains n, 
    //and their prices fall within the given range, [low, high].
    int findPriceRange(long des, double lowPrice, double highPrice) {
        return findPricesInRangeGivenDescription(des, lowPrice, highPrice).size();
    }

    // to increase the price of every product between minId and maxId 
    double priceHike(long minid, long maxid, double rate) {
        double sum = 0;
        for(Map.Entry<Long,Item> entry : itemCollection.entrySet())
        {
            long key = entry.getKey();
            Item value = entry.getValue();
            if(key >= minid && key <= maxid)
            {
                double hikedPrice = value.price + ((value.price)*(rate/100));
                DecimalFormat df = new DecimalFormat("#.##");
                hikedPrice = Double.parseDouble(df.format(hikedPrice));

                sum += Double.parseDouble(df.format(hikedPrice  - value.price));
                value.price = hikedPrice;
                itemCollection.put(key, value);
            }
        }
        return sum;
    }

    // counts the number of items whose price is at least "low" and at most "high".
    int range(double lowPrice, double highPrice) {
        int count = 0;
        for(Map.Entry<Long,Item> entry : itemCollection.entrySet())
        {
            Item value = entry.getValue();
            if(value.price >= lowPrice && value.price <= highPrice)
                count++;
        }
        return count;
    }

    // same function
    int samesame() {
        return setOfEqualIds.size();
    }

    // Helper function to find MinPrice and MaxPrice
    private ArrayList<Double> getPricesMatchingDescriptions(long desc)
    {
        ArrayList<Double> res = new ArrayList<>();
        for(Map.Entry<Long,Item> entry : itemCollection.entrySet())
        {
            Item value = entry.getValue();
            if(value.search(desc))
                res.add(value.price);
        }
        Collections.sort(res);
        return res;
    }

    // Helper function to find PriceRange
    private ArrayList<Double> filterPricesOnRange(List<Double> array, double low, double high)
    {
        ArrayList<Double> res = new ArrayList<>();
        for(double val : array)
        {
            if(val >= low && val < high)
                res.add(val);
        }
        return res;
    }
    
    //Helper function to find PriceRange
    private ArrayList<Double> findPricesInRangeGivenDescription(long desc, double low, double high)
    {
        return filterPricesOnRange(getPricesMatchingDescriptions(desc), low, high);
    }
    
    //Helper function to parse the description and find SameSame after insertion.
    private void parseSameSame(Item i)
    {
        for(Map.Entry<Long,Item> entry : itemCollection.entrySet()) {
            long key = entry.getKey();
            Item value = entry.getValue();
            if(Arrays.equals(i.description,value.description) && value.description.length >= 8 && i.description.length >= 8) {
                setOfEqualIds.add(key);
                setOfEqualIds.add(i.id);
            }
        }
    }
}