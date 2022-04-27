import java.util.Hashtable;

public class main
{
    //main method, declaration of array to be tested
    public static void main(String[]args)
    {
        int[] array=new int[]{1,3,8,4,4,0};

        findSumPair(12, array);
        System.out.println("DistinctValues: "+DistinctValues(array));
    }

    //Finds two integers in array[] that equal target when summed, if the integers exist
    public static void findSumPair(int target, int[] array)
    {
        //sorts the array into a hashtable
        Hashtable <Integer, Boolean> htable = new Hashtable<Integer, Boolean>();
        for(int i=0; i<array.length;i++)
        {
            htable.put(array[i],true);
        }

        //converts the hashtable into a sorted array to make traversal easier
        Integer[] org=new Integer[htable.size()];
        htable.keySet().toArray(org);

        //Sets the high and low index of the array.
        //Due to how the hash table organizes negative numbers, they are sorted before high numbers.
        //To compensate for that, if the array has negative numbers, it will then search for the point where
        //  the lowest negative immediately precedes the highest index
        int high=0;
        int low=org.length-1;
        if(org[0]<0)
        {
            int i=0;
            while(i+1<org.length && org[i]>org[i+1])
            {
                i++;
            }
            high= i+1;
            low= i;
        }

        //Test Code for low and high indices. Prints out org array
//        System.out.println("lowInd:" + low);
//        System.out.println("HighInd:" + high);
//        for(int j=0; j<org.length;j++)
//        {
//            System.out.println(org[j]);
//        }

        //Searches for sum=target while high> low and target sum has not been found
        // If org[high] + org[low] > target, the number less than high is tested
        // If org[high] + org[low] < target, the number greater than low is tested
        //Both methods will wrap around if they reach an end of the list, only relevant for lists with negatives
        while(org[high] > org[low] && org[high]+org[low]!=target)
        {
            if(org[high] + org[low] > target)
            {
                if(high==org.length-1)
                {
                    high=0;
                }
                else
                {
                    high++;
                }
            }
            else
            {
                if(low==0)
                {
                    high=org.length-1;
                }
                else
                {
                    low--;
                }
            }
        }

        //Print statements, if pair is found, then they are stated.
        if(org[high]+org[low] == target)
        {
            System.out.println("Pair found: " + org[high]+", " + org[low]);
        }
        else
        {
            System.out.println("No pair found");
        }
    }

    //Returns the amount of distinct absolute values in array[], based on the size of htable
    public static int DistinctValues(int[] array)
    {
        //Hashtable is not affected by repeat values, so only distinct values are counted.
        //Cycles through array[], adding the values to htable if possible
        Hashtable <Integer, Boolean> htable = new Hashtable<Integer, Boolean>();
        for(int i=0; i<array.length;i++)
        {
            htable.put(Math.abs(array[i]),true);
        }

        return htable.size();
    }
}