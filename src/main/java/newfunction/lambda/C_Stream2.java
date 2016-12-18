package newfunction.lambda;

import demooject.Track;
import org.apache.commons.lang.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
/**
 * 常用流操作
 */
public class C_Stream2 {
    public static void main(String[] args) {
        //1.toList
        List<String> collected = Stream.of("a", "b", "c")
                .collect(toList());

        //2.map
        List<String> collected2 = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(toList());

        //3.filter
        List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
                .filter(value -> StringUtils.isNumeric(value))
                .collect(toList());

        //4.flatMap 用stream替换值，然后将多个Stream连接成一个Stream
        List<Integer> together = Stream.of(asList(1, 2), asList(3,4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());

        //5.min和max
        List<Track> tracks = asList(new Track("A", 524),
                new Track("B", 456),
                new Track("C", 332));

        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getTime()))
                .get();

        //6.reduce
        int count = Stream.of(1,2,3)
                .reduce(0,(acc,element) -> acc + element); //6
    }
}
