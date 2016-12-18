package newfunction.generic;

import java.util.Collection;

/**
 * todo:
 *
 * @author jw.fang
 * @version 1.0
 */
public interface E_Super
{
    static interface Sink<T>
    {
        void flush(T t);
    }

    public static <T> T writeAll(Collection<T> coll, Sink<T> snk)
    {
        T last = null;
        for (T t : coll)
        {
            last = t;
            snk.flush(last);
        }
        return last;
    }

    //语法 ? super T 表示T的一个未知的父类（或者是T自己）。这跟我们用? extends T 表示T的一个未知的子类是对应的。
    public static <T> T writeAll2(Collection<T> coll, Sink<? super T> snk)
    {
        T last = null;
        for (T t : coll)
        {
            last = t;
            snk.flush(last);
        }
        return last;
    }

    public static void main(String[] args)
    {
        Sink<Object> s = null;
        Collection<String> cs = null;
//        String str = writeAll(cs, s); // 非法的调用!!
        String str = writeAll2(cs, s); //合法
    }
}
