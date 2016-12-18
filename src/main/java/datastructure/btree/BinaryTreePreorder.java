package datastructure.btree;

/**
 * todo:
 *
 * @author jw.fang
 * @version 1.0
 */
public class BinaryTreePreorder
{
    public static void preOrder(BTree root)
    {  //先根遍历
        if (root != null)
        {
            System.out.print(root.data + "-");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void inOrder(BTree root)
    {     //中根遍历

        if (root != null)
        {
            inOrder(root.left);
            System.out.print(root.data + "--");
            inOrder(root.right);
        }
    }

    public static void postOrder(BTree root)
    {    //后根遍历

        if (root != null)
        {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + "---");
        }
    }

    public static void main(String[] str)
    {
        int[] array = {12, 76, 35, 22, 16, 48, 90, 46, 9, 40};
        BTree root = new BTree(array[0]);   //创建二叉树
        for (int i = 1; i < array.length; i++)
        {
            root.insert(root, array[i]);       //向二叉树中插入数据
        }
        System.out.println("先根遍历：");
        preOrder(root);
        System.out.println();

        System.out.println("中根遍历：");
        inOrder(root);
        System.out.println();

        System.out.println("后根遍历：");
        postOrder(root);
    }
}
