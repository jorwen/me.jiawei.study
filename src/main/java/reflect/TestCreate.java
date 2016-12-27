package reflect;

import demooject.Track;

import java.lang.reflect.Method;

public class TestCreate {
    public static void main(String[] args) throws Exception{
        Class<?> clazz = Class.forName("demooject.Track");
        Method method = clazz.getMethod("setName",String.class);

        Track track = (Track) clazz.newInstance();
        method.invoke(track, "张三");

        Method method2 = clazz.getMethod("getName");
        System.out.println(method2.invoke(track));
    }
}
