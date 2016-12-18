package test;

/**
 * Created by fangjiawei on 2016/12/17.
 */
public class TestException {
    public void testChecked() throws Exception {
        if(true) {
            throw new Exception("errpr");
        }
        System.out.println("1123");
    }

    public static void main(String[] args) {
        try {
            new TestException().testChecked();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}