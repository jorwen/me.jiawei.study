package algorithm.sort;

public abstract class AbstractSort
{
    public static int[] arr = new int[]{9,7,2,3,1,5,8,6,4};

    protected static void swap(int a, int b)
    {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    protected static void print()
    {
        System.out.println();
        for(int a : arr)
        {
            System.out.print(a);
        }
    }

    protected static void print(int[] arr)
        {
            System.out.println();
            for(int a : arr)
            {
                System.out.print(a);
            }
        }
}
