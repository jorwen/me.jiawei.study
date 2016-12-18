package algorithm.sort;

/**
 * 归并排序：O(N*logN) - 递归算法
 */
public class MergeSort extends AbstractSort
{
    public static void main(String[] args)
    {
        print();
        int n = arr.length;
        int[] workSpace = new int[n];
        recMergeSort(workSpace,0,n-1);
        print();
    }

    private static void recMergeSort(int[] workSpace, int lower, int upper)
    {
        if(lower != upper)
        {
            int mid = (lower + upper)/2;
            recMergeSort(workSpace, lower, mid);
            recMergeSort(workSpace, mid+1,upper);
            merge(workSpace, lower,mid+1,upper);
        }
    }

    private static void merge(int[] workSpace, int _lower, int _higher, int upper)
    {
        int j = 0;
        int lower = _lower;
        int mid = _higher -1;
        int n = upper - lower + 1;

        while(_lower <= mid && _higher <= upper)
        {
            if(arr[_lower] < arr[_higher])
                workSpace[j++] = arr[_lower++];
            else
                workSpace[j++] = arr[_higher++];
        }

        while(_lower <= mid)
            workSpace[j++] = arr[_lower++];

        while(_higher <= upper)
            workSpace[j++] = arr[_higher++];


        for(j = 0; j < n; j++)
        {
            arr[lower+j] = workSpace[j];
        }
        print();
        System.out.print(" - ");
        print(workSpace);
    }
}