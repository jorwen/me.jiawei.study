package algorithm.sort;

/**
 * 快速排序：O(N*logN)
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort extends AbstractSort
{
    public static void main(String[] args)
    {
        print();
        quickSort(0,arr.length-1);
        print();
    }

    private static void quickSort(int left, int right)
    {
        if(right>left)
        {
            int pivot = arr[right];
            int partition = partitionIt(left, right, pivot);
            quickSort(left,partition-1);
            quickSort(partition+1, right);
        }
    }

    private static int partitionIt(int left, int right, int pivot)
    {
        int leftPtr = left - 1;
        int rightPtr = right;
        while(true)
        {
            while(arr[++leftPtr] < pivot);

            while(rightPtr > 0 && arr[--rightPtr] > pivot);

            if(leftPtr >= rightPtr) break;
            else
                swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right);
        return leftPtr;
    }
}