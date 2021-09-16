package newfunction.basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jiawei.fjw
 * @version 1.0
 * @since 2021/9/16
 */
public class DemoTryWithResources {
    public static void main(String[] args) {
        //声明在括号中的资源在方法执行后被自动释放，无需再finally里关闭
        try(BufferedReader reader = new BufferedReader(new FileReader("1.txt"))){

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
