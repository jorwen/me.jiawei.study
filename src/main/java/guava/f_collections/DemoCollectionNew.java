package guava.f_collections;

import com.google.common.collect.*;

import java.util.*;

/**
 * 新型集合
 */
public class DemoCollectionNew {
    public static void main(String[] args) {
        //1.multiset：可重复元素的set,统计出现数量
        String strWorld="wer|dfd|dd|dfd|dda|de|dr";
        String[] words=strWorld.split("\\|");
        List<String> wordList= new ArrayList<>();
        Collections.addAll(wordList, words);
        Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(wordList);

        for(String key:wordsMultiset.elementSet()){
            System.out.println(key+" count："+wordsMultiset.count(key));
        }

        //2.SortedMultiset是Multiset 接口的变种，它支持高效地获取指定范围的子集
        //略

        //3.Multimap: 很容易地把一个键映射到多个值
        Multimap<String,Integer> scoreMultimap = ArrayListMultimap.create();
        for(int i=10;i<20;i++){
            Integer studentScore=100-i;
            scoreMultimap.put("peida",studentScore);
        }
        System.out.println("scoreMultimap:" + scoreMultimap.size());
        System.out.println("scoreMultimap:" + scoreMultimap.keys());

        //4.BiMap: 双向map
        BiMap<String, Integer> userId = HashBiMap.create();
        userId.put("1", 100);
        System.out.println("BiMap-v:"+userId.get("1"));
        System.out.println("BiMap-k:"+userId.inverse().get(100));

        //5.Table:多级map
        Table<String, Integer, String> aTable = HashBasedTable.create();
        for (char a = 'A'; a <= 'C'; ++a) {
            for (Integer b = 1; b <= 3; ++b) {
                aTable.put(Character.toString(a), b, String.format("%c%d", a, b));
            }
        }

        //6.RangeSet:区间集合
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));
        System.out.println("rangeSet:" + rangeSet.contains(4));

        //7.RangeMap:区间map
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo");
        System.out.println("rangeMap:" + rangeMap.get(3));
    }
}
