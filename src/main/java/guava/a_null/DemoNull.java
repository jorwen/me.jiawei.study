package guava.a_null;

import java.util.Optional;

public class DemoNull {
    public static void main(String[] args) {
        //Optional
        //用Optional<T>表示可能为null的T类型引用
        Optional<Integer> possible = Optional.of(5);
        possible.isPresent(); // returns true
        possible.get(); // returns 5

        possible.orElse(6);//为空则返回6
        possible.orElseGet(() -> {
            //为空则返回
            return null;
        });
    }
}
