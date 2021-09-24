package newfunction.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Java 10 局部变量的类型推断 var关键字
 */
public class DemoVar {
    public static void main(String[] args) {
        List a = new ArrayList<String>();
        a = new ArrayList<Integer>();

        var b = new ArrayList<String>();
        //编译失败
        //b = new ArrayList<Integer>();

        for (var x : a) {  }
        try (var reader = new BufferedReader(new FileReader("1.txt"))) { } catch(Exception e){}

    }
}
