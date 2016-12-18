package newfunction.generic;

import java.util.*;

/**
 * 有限制的通配符
 *
 * @author jw.fang
 * @version 1.0
 */
public class C_BoundedWildcards
{
    public static void main(String[] args)
    {
        List<Shape> list = new ArrayList<>();
        list.add(new Circle());
        list.add(new Rectangle());
        new Canvas().drawAll(list);
        new Canvas().drawAll2(list);

        List<Circle> list2 = new ArrayList<>();
        new Canvas().drawAll(list2);
        //        下面代码编译不通过
        //        new Canvas().drawAll2(list2);

    }

    static abstract class Shape
    {
        public void draw(Canvas c)
        {
            System.out.println("Shape.draw");
        }
    }

    static class Circle extends Shape
    {
        private int x, y, radius;

        public void draw(Canvas c)
        {
            System.out.println("Circle.draw");
        }
    }

    static class Rectangle extends Shape
    {
        private int x, y, width, height;

        public void draw(Canvas c)
        {
            System.out.println("Rectangle.draw");
        }
    }

    static class Canvas
    {
        public void draw(Shape s)
        {
            s.draw(this);
        }

        //可以接受任何Shape的子类的List，所以我们可以对List<Circle>进行调用。
        public void drawAll(List<? extends Shape> shapes)
        {
            System.out.println("Canvas.drawAll");

            for (Shape s : shapes)
            {
                s.draw(this);
            }
        }

        public void drawAll2(List<Shape> shapes)
        {
            System.out.println("Canvas.drawAll2");

            for (Shape s : shapes)
            {
                s.draw(this);
            }
        }
    }
}
