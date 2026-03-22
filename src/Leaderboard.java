import java.util.Arrays;

public class Leaderboard {
    //merge sort algorithm
    public static SortElement[] sort(SortElement[] arr) {
        //recursively split up the array until it is a single element and then merge the sorted elements
        if(arr.length>1) {
            int mid = arr.length/2;
            return merge(sort(Arrays.copyOfRange(arr, 0, mid)), sort(Arrays.copyOfRange(arr, mid, arr.length)));
        }
        else
        {
            return arr;
        }
        
    }
    //go element by element to merge two already sorted arrays
    private static SortElement[] merge(SortElement[] left, SortElement[] right)
    {
        SortElement[] output = new SortElement[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < output.length; i++)
        {
            if (leftIndex < left.length && rightIndex < right.length)
            {
                if (left[leftIndex].score >= right[rightIndex].score)
                {
                    output[i] = left[leftIndex];
                    leftIndex++;
                }
                else
                {
                    output[i] = right[rightIndex];
                    rightIndex++;
                }
            }
            else if (leftIndex >= left.length)
            {
                output[i] = right[rightIndex];
                rightIndex++;
            }
            else
            {
                output[i] = left[leftIndex];
                leftIndex++;
            }
        }
        return output;
    }
    public static void activate(){
        GUI.createWindow("Leaderboard");
        SortElement[] arr = new SortElement[5];
        arr[0] = new SortElement("Alice", 90);
        arr[1] = new SortElement("Bob", 80);
        arr[2] = new SortElement("Charlie", 85);
        arr[3] = new SortElement("David", 95);
        arr[4] = new SortElement("Eve", 70);
        arr = sort(arr);
        for (int i = 0; i < arr.length; i++)
        {
            GUI.addLabel((i+1) + ". " + arr[i].name + ": " + arr[i].score, 20);
        }
    }
}

