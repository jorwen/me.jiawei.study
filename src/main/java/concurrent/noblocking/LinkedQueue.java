package concurrent.noblocking;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞队列算法的插入操作
 *
 * @author jw.fang
 * @version 1.0
 */
public class LinkedQueue<E>
{
    private static class Node<E>
    {
        final E item;
        final AtomicReference<Node<E>> next;

        Node(E item, Node<E> next)
        {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(new Node<E>(null, null));
    private AtomicReference<Node<E>> tail = head;

    public boolean put(E item)
    {
        Node<E> newNode = new Node<E>(item, null);
        while (true)
        {
            Node<E> curTail = tail.get();
            Node<E> residue = curTail.next.get();
            if (curTail == tail.get())
            {
                if (residue == null) /* A */
                {
                    if (curTail.next.compareAndSet(null, newNode)) /* C */
                    {
                        tail.compareAndSet(curTail, newNode) /* D */;
                        return true;
                    }
                }
                else
                {
                    tail.compareAndSet(curTail, residue) /* B */;
                }
            }
        }
    }
}
