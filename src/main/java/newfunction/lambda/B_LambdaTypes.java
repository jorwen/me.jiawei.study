package newfunction.lambda;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * 各种lambda类型
 */
public class B_LambdaTypes {
    //无参
    Runnable noArguments = () -> System.out.println("Hello world");

    //一个参数
    ActionListener oneArgument = event -> System.out.println("Hello world");

    //代码块
    Runnable multiStatment = () -> {
        System.out.println("Hello");
        System.out.println(" world");
    };

    //多参
    BinaryOperator<Long> add = (x, y) -> x + y;

    //指定参数类型
    BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
}
