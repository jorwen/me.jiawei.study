package algorithm.sort;

/**
 * 插入排序：O(N^2)，比冒泡快一倍，比选择略快
 * 1.从左边第2个开始，标记
 * 2.把它插入到其左边合适的位置，形成局部有序
 * 3.再标记下一个，直到最后
 */
public class InsertSort extends AbstractSort
{
    public static void main(String[] args)
    {
        print();
        int n = arr.length;
        for(int out = 1; out < n; out++)
        {
            int tmp = arr[out];
            int in = out;
            while(in > 0 && arr[in-1] >=tmp)
            {
                //右移
                arr[in] = arr[in-1];
                --in;
            }
            arr[in] = tmp;
            print();
        }
        print();
    }
}