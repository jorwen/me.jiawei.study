package algorithm.sort;

/**
 * 选择排序：O(N^2)，比冒泡快略快，因为交换得少
 * 1.从左边第开始，标记大小
 * 2.然后右边最矮的覆盖标记，最矮的和最左边的交换。
 * 3.再下一个，直到最后
 */
public class SelectSort extends AbstractSort
{
    public static void main(String[] args)
    {
        print();
        int n = arr.length;
        for(int out = 0; out < n -1; out++)
        {
            int min = out;
            for(int in = out +1; in < n; in++)
            {
                if(arr[in] < arr[min])
                    min = in;
                swap(out,min);
            }
        }
        print();
    }
}