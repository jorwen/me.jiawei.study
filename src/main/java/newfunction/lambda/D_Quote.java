package newfunction.lambda;

import demooject.Track;

import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * 高级应用
 */
public class D_Quote {
    public static void main(String[] args) {
        List<Track> tracks = asList(new Track("A", 524),
                new Track("B", 456),
                new Track("C", 332));

        //表达式
        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getTime()))
                .get();

        //方法引用
        Track shortestTrack2 = tracks.stream()
                .min(Comparator.comparing(Track::getTime))
                .get();
    }
}
