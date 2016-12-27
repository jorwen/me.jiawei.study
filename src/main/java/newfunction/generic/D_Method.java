package newfunction.generic;

import java.util.Collection;
import java.util.List;

/**
 * 泛型方法
 *
 * @author jw.fang
 * @version 1.0
 */
public abstract class D_Method<E>
{
    abstract <T> boolean containsAll(Collection<T> c);

    abstract <T extends E> boolean addAll(Collection<T> c);

    abstract  <T, S extends T>  void copy(List<T> dest, List<S> src);

    public void fromArrayToCollection(E[] a, Collection<E> c)
    {
        for (E o : a)
        {
            c.add(o); // correct
        }
    }
}
