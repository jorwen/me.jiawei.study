package guava.f_collections;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.*;
import java.util.function.Function;

/**
 * 集合工具
 */
public class DemoCollectionUtils {
    public static void main(String[] args) {
        //1.静态工厂
        Set<String> copySet = Sets.newHashSet("aaa");
        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");

        List<String> exactly100 = Lists.newArrayListWithCapacity(100);
        List<String> approx100 = Lists.newArrayListWithExpectedSize(100);
        Set<String> approx100Set = Sets.newHashSetWithExpectedSize(100);

        Multiset<String> multiset = HashMultiset.create();

        //2.Iterables

        //3.Lists
        List countUp = Ints.asList(1, 2, 3, 4, 5);
        List countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
        List<List> parts = Lists.partition(countUp, 2);//{{1,2}, {3,4}, {5}}

        //4.Sets
        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");

        Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
        // {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
        //  {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}

        Set<Set<String>> animalSets = Sets.powerSet(animals);
        // {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}

        //5.Maps
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("a", 0, "b", 2, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        diff.entriesInCommon();
        diff.entriesInCommon();
        diff.entriesOnlyOnLeft();
        diff.entriesOnlyOnRight();

        //6.Multisets

        //7.Multimaps

        //8.Tables
    }
}
