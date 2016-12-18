package newfunction.lambda;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class A_Helloworld {
    public static void main(String[] args) {
        Button button = new Button();
        //传统写法 - 匿名内部类
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button clicked");
            }
        });

        //Lambda写法
        button.addActionListener(event -> System.out.println("button clicked"));

    }
}
