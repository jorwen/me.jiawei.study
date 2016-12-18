package concurrent.noblocking;

import java.util.concurrent.atomic.AtomicReference;

/**
 * push() 方法观察当前最顶的节点，构建一个新节点放在堆栈上，然后，
 * 如果最顶端的节点在初始观察之后没有变化，那么就安装新节点。
 * 如果 CAS 失败，意味着另一个线程已经修改了堆栈，那么过程就会重新开始。
 *
 * @author jw.fang
 * @version 1.0
 */
public class ConcurrentStack<E>
{
    AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();

    public void push(E item)
    {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do
        {
            oldHead = head.get();
            newHead.next = oldHead;
        }
        while (!head.compareAndSet(oldHead, newHead));
    }

    public E pop()
    {
        Node<E> oldHead;
        Node<E> newHead;
        do
        {
            oldHead = head.get();
            if (oldHead == null) return null;
            newHead = oldHead.next;
        }
        while (!head.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    static class Node<E>
    {
        final E item;
        Node<E> next;

        public Node(E item) { this.item = item; }
    }
}
