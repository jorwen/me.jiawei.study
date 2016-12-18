package datastructure.btree;

/**
 * todo:
 *
 * @author jw.fang
 * @version 1.0
 */
public class BTree
{

    int data;      //根节点数据
    BTree left;    //左子树
    BTree right;   //右子树

    public BTree(int data)    //实例化二叉树类
    {
        this.data = data;
        left = null;
        right = null;
    }

    public void insert(BTree root, int data)
    {
        //向二叉树中插入子节点
        if (data > root.data) //二叉树的左节点都比根节点小
        {
            if (root.right == null)
            {
                root.right = new BTree(data);
            }
            else
            {
                this.insert(root.right, data);
            }
        }
        else
        {
            //二叉树的右节点都比根节点大
            if (root.left == null)
            {
                root.left = new BTree(data);
            }
            else
            {
                this.insert(root.left, data);
            }
        }
    }
}
