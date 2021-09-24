package newfunction.basic;

/**
 * @author jiawei.fjw
 * @version 1.0
 * @since 2021/9/24
 */
public class DemoSwitch {
    private static int i = 2;
    public static void main(String[] args) {
        //jdk 12
        int number = switch (i){
            case 1 -> 4;
            case 2,3 -> 5;
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
        //jdk 13
        int number2 = switch (i){
            case 1:
                yield 4;
            case 2,3:
                yield 5;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        };
    }
}
