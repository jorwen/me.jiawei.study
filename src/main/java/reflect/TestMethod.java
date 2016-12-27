package reflect;

import demooject.Track;

import java.lang.reflect.Constructor;

public class TestMethod {
    public static void main(String[] args) throws Exception{
        Class<?> class1 = Class.forName("demooject.Track");
        // 第一种方法，实例化默认构造方法，调用set赋值
        Track track = (Track) class1.newInstance();
        track.setName("周杰伦");
        track.setTime(100);
        System.out.println(track.getName());

        // 第二种方法 取得全部的构造函数 使用构造函数赋值
        Constructor<?> con = class1.getConstructor(String.class, Integer.class);
        track = (Track) con.newInstance("Rollen",100);
        System.out.println(track.getName());
    }
}
