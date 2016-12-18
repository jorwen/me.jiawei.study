package newfunction.generic;

/**
 * todo:
 *
 * @author jw.fang
 * @version 1.0
 */
public class F_Demo
{
    //老代码
    interface Collection
    {
        public boolean containsAll(Collection c);

        public boolean addAll(Collection c);
    }

    //初级泛型

    /**
     * containsAll() 方法能对所有进来的任意类型的collection工作。它只有在传进来的collection中真正只包含E的实例才成功，但是：
     * 1.传进来的collection的静态类型可能不同，可能是因为调用者不知道传进来的colleciton的精确类型，或者因为它是一个Collection<S>，S是E的子类型。
     * 2.用一个不同类型的collection来调用containsAll()应该是合法的。这个例程应该能够工作，返回false。
     *
     * @param <E>
     */
    interface Collection2<E>
    {
        public boolean containsAll(Collection2<E> c);

        public boolean addAll(Collection2<E> c);
    }

    //好的泛型
    interface Collection3<E>
    {
        public boolean containsAll(Collection3<?> coll);

        public boolean addAll(Collection3<? extends E> coll);
    }
}
