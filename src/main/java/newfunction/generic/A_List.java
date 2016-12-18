package newfunction.generic;

import java.util.Iterator;

/**
 * 定义简单的泛型
 *
 * @author jw.fang
 * @version 1.0
 */
public interface A_List<E>
{
    void add(E x);
    Iterator<E> iterator();
}