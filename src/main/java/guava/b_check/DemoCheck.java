package guava.b_check;

import static com.google.common.base.Preconditions.*;

public class DemoCheck {
    public static void main(String[] args) {
        checkArgument(1 == 1, "参数不正确");//IllegalArgumentException
        checkNotNull(1L);//NullPointerException
        checkState(1 == 1);//IllegalStateException
        checkElementIndex(10, 11);//IndexOutOfBoundsException
    }
}
