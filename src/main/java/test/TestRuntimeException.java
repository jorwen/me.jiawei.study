package test;

/**
 * Created by fangjiawei on 2016/12/17.
 */
public class TestRuntimeException {
    public void test(){
        if(true)
            throw new RuntimeException("asasdas");
    }

    public static void main(String[] args) {
        new TestRuntimeException().test();
    }
}
