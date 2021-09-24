package newfunction.basic;

/**
 * @author jiawei.fjw
 * @version 1.0
 * @since 2021/9/24
 */
public class DemoInstanceof {
    public static void main(String[] args) {
        Object obj = "程序新视界";
        if(obj instanceof String str){
            System.out.println("关注公众号：" + str);
        }
    }
}
