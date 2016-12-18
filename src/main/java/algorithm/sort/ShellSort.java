package algorithm.sort;

/**
 * 希尔排序：基于插入排序，但复制次数少很多
 * 比如1000个数，先以364为增量，再以121为增量，然后40，13，4，最有1为增量进行插入排序
 */
public class ShellSort extends AbstractSort
{
    public static void main(String[] args)
    {
        print();
        int tmp;
        int n = arr.length;
        int h = 1;
        while (h <= n / 3)//选择是1，4，13，40，121...
            h = h * 3 + 1;

        while (h > 0)
        {
            for (int outer = h; outer < n; outer++)
            {
                //插入排序
                tmp = arr[outer];
                int inner = outer;
                while (inner > h - 1 && arr[inner - h] >= tmp)
                {
                    arr[inner] = arr[inner - h];
                    inner -= h;
                }
                arr[inner] = tmp;
            }
            h = (h - 1) / 3;
        }
        print();
    }

}