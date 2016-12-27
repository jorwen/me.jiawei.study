package newfunction.generic;

import demooject.Apple;
import demooject.Fruit;
import demooject.Orange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 一个集合，它的元素类型可以匹配任何类型，它被称为通配符。
 *
 * @author jw.fang
 * @version 1.0
 */
public class B_Wildcards
{
    //通配符
    public static void printCollection1(Collection<?> c) {
        //省略
    }

    //上边界限定通配符,存放所指定的类型，或者是此类型的子类
    public static void printCollection2(Collection<? extends Fruit> c) {
        //省略
    }

    //下边界限定通配符,存放所指定的类型，或者是此类型的父类型
    public static void printCollection3(Collection<? super Apple> c) {
        //省略
    }

    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        List<Orange> orangeList = new ArrayList<Orange>();

        printCollection1(appleList);
        printCollection1(orangeList);

        printCollection2(appleList);
        printCollection2(orangeList);

        printCollection3(appleList);
        //printCollection3(orangeList); 编译不通过
    }
}
