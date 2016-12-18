package newfunction.lambda;

import demooject.Track;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

/**
 * 高级应用
 */
public class E_Advance {
    public void test() {
        //1.顺序
        Set<Integer> numbers = new HashSet<>(asList(4,3,2,1));
        List<Integer> sameOrder = numbers.stream()
                .sorted()
                .collect(toList());//1234

        //2.收集器
        //2.1 转换成其它集合
        TreeSet<Integer> treeNumbers = numbers.stream()
                .collect(toCollection(TreeSet::new));

        //2.2 转换成值
        List<Track> tracks = asList(new Track("A", 524),
                new Track("B", 456),
                new Track("C", 332));
        double average = tracks.stream()
                .collect(averagingInt(Track::getTime));

        //2.3 数据分块
        //分成 500长度以上和以下2部分
        Map<Boolean,List<Track>> trackListMap = tracks.stream().collect(partitioningBy(track -> track.getTime() >= 500));

        //2.4 数据分组
        //按类型分组
        Map<String,List<Track>> trackListMap2 = tracks.stream().collect(groupingBy(Track::getType));

        //2.5 字符串
        //最后变成[name1,name2,name3]
        String result = tracks.stream()
                .map(Track::getName)
                .collect(joining(",", "[", "]"));

        //2.6 组合
        //分组计数
        Map<String,Long> trackListMap3 = tracks.stream().collect(groupingBy(Track::getType,counting()));
        //分组名称列表
        Map<String,List<String>> trackListMap4 = tracks.stream().collect(groupingBy(Track::getType,
                mapping(Track::getName,toList())));

        //2.7 重构和定制收集器
        //reduce和StringCombiner 格式化名称
        String result2 = tracks.stream()
                .map(Track::getName)
                .reduce(new StringCombiner(",","[","]"),StringCombiner::add, StringCombiner::merge)
                .toString();

        //reduce和StringCollector 格式化名称，推荐
        String result3 = tracks.stream()
                .map(Track::getName)
                .collect(new StringCollector(",", "[", "]"));

        //2.8 对收集器归一化处理，这种方式很低效
         String result4 = tracks.stream()
                .map(Track::getName)
                .collect(reducing(new StringCombiner(",", "[", "]"), name -> new StringCombiner(",", "[", "]").add(name), StringCombiner::merge))
                .toString();

        //3 computeIfAbsent缓存，值不存在则赋值
        Map<String,Track> cache = new HashMap<>();
        Track track = cache.computeIfAbsent("myKey",this::getTrackFromDB);

        //4 使用内部迭代器遍历map
        cache.forEach((name, myTrack) -> {
            //略
        });
    }

    public Track getTrackFromDB(String name){
        //假设从数据库获取
        return new Track(name,123);
    }
}

class StringCombiner{
    private String delim;
    private String prefix;
    private String suffix;
    private StringBuilder builder;

    public StringCombiner(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
        builder = new StringBuilder();
    }

    public StringCombiner add(String e){
        if(builder.length() == 0){
            builder.append(prefix);
        }else{
            builder.append(delim);
        }
        builder.append(e);
        return this;
    }

    public StringCombiner merge(StringCombiner other){
        builder.append(other.builder);
        return this;
    }
}

//<待手机元素类型，累加器，最终结果类型>
class StringCollector implements Collector<String,StringCombiner,String>{
    private String delim;
    private String prefix;
    private String suffix;

    public StringCollector(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    //创建容器的工厂
    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(delim,prefix,suffix);
    }

    //元素叠加收集器
    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    //合并2个容器
    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    //最终结果
    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}

