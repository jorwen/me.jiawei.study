package algorithm.sort;

/**
 * 冒泡排序法：O(N^2)
 * 1.从最左开始，比较相邻2个
 * 2.如果左边大就交换
 * 3.然后右移，同样方法比较下面两个。
 */
public class BubbleSort extends AbstractSort
{
    public static void main(String[] args)
    {
        int n = arr.length;
        for (int out = n - 1; out > 1; out--)
        {
            for (int in = 0; in < out; in++)
            {
                if (arr[in] > arr[in + 1]) swap(in, in + 1);
            }
        }
        print();
    }
}
