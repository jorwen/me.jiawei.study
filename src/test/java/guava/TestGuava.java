package guava;

import com.google.common.collect.ImmutableSortedSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class TestGuava {
    @Test
    public void ImmutableTest(){
        Set<String> set = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");

        Assert.assertEquals(set.size(),4);
    }
}
