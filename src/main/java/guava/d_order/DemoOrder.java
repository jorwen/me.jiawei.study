package guava.d_order;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.List;

public class DemoOrder {
    public static void main(String[] args) {
        //ordering
        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");

        System.out.println("list:"+ list);

        Ordering<String> naturalOrdering = Ordering.natural(); //对可排序类型做自然排序，如数字按大小，日期按先后排序
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();//按对象的字符串形式做字典排序
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();//返回一个所有对象的任意顺序

        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));
        System.out.println("usingToStringOrdering:"+ usingToStringOrdering.sortedCopy(list));
        System.out.println("arbitraryOrdering:"+ arbitraryOrdering.sortedCopy(list));

        //list:[peida, jerry, harry, eva, jhon, neron]
        //naturalOrdering:[eva, harry, jerry, jhon, neron, peida]
        //usingToStringOrdering:[eva, harry, jerry, jhon, neron, peida]
        //arbitraryOrdering:[neron, harry, eva, jerry, peida, jhon]

        //reverse(): 返回与当前Ordering相反的排序:
        //nullsFirst(): 返回一个将null放在non-null元素之前的Ordering，其他的和原始的Ordering一样；
        //nullsLast()：返回一个将null放在non-null元素之后的Ordering，其他的和原始的Ordering一样；
        //compound(Comparator)：返回一个使用Comparator的Ordering，Comparator作为第二排序元素，例如对bug列表进行排序，先根据bug的级别，再根据优先级进行排序；
        //lexicographical()：返回一个按照字典元素迭代的Ordering；
        //onResultOf(Function)：将function应用在各个元素上之后, 在使用原始ordering进行排序；
        //greatestOf(Iterable iterable, int k)：返回指定的第k个可迭代的最大的元素，按照这个从最大到最小的顺序。是不稳定的。
        //leastOf(Iterable<E> iterable,int k)：返回指定的第k个可迭代的最小的元素，按照这个从最小到最大的顺序。是不稳定的。
        //isOrdered(Iterable)：是否有序，Iterable不能少于2个元素。
        //isStrictlyOrdered(Iterable)：是否严格有序。请注意，Iterable不能少于两个元素。
        //sortedCopy(Iterable)：返回指定的元素作为一个列表的排序副本。

        //自定义排序器
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
    }
}
