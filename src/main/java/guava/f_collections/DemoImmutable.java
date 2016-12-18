package guava.f_collections;

import com.google.common.collect.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 不可变集合
 */
public class DemoImmutable {
    public static void main(String[] args) {
        //sample
        ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        ImmutableList.of("a", "b", "c", "a", "d", "b");

        //build
        Set<Color> WEBSAFE_COLORS = new HashSet<>();
        final ImmutableSet<Color> GOOGLE_COLORS = ImmutableSet.<Color>builder()
                .addAll(WEBSAFE_COLORS)
                .add(new Color(0, 191, 255))
                .build();
    }
}
