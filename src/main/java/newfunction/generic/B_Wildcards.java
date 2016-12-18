package newfunction.generic;

import java.util.Collection;

/**
 * 一个集合，它的元素类型可以匹配任何类型，它被称为通配符。
 *
 * @author jw.fang
 * @version 1.0
 */
public class B_Wildcards
{
    void printCollection(Collection<?> c)
    {
        for (Object e : c)
        {
            System.out.println(e);
        }
    }
}
