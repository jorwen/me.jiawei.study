package collection;

import java.util.*;

/**
 * 一种排序过的链接，使用了并行链表提高了搜索的速度
 *
 * @author jw.fang
 * @version 1.0
 */
public class NavigableMapDemo
{
    public static void main(String[] args)
    {
        NavigableMap<String, Integer> nav = new TreeMap<String, Integer>();
        nav.put("aaa", 111);
        nav.put("bbb", 222);
        nav.put("eee", 333);
        nav.put("ccc", 555);
        nav.put("ddd", 444);
        System.out.printf("Whole list:%s%n", nav);

        System.out.printf("First key: %s\tFirst entry: %s%n", nav.firstKey(), nav.firstEntry());

        System.out.printf("Last key: %s\tLast entry: %s%n", nav.lastKey(), nav.lastEntry());

        System.out.printf("Key floor before bbb: %s%n", nav.floorKey("bbb"));

        System.out.printf("Key lower before bbb: %s%n", nav.lowerKey("bbb"));

        System.out.printf("Key ceiling after ccc: %s%n", nav.ceilingKey("ccc"));

        System.out.printf("Key higher after ccc: %s%n", nav.higherKey("ccc"));
    }
}
