package newfunction.basic;

/**
 * Java 14中记录类型(Record Type)作为预览特性被引入。记录对象允许使用紧凑的语法来声明类，和枚举类型一样，记录也是类的一种受限形式。
 */
public record DemoRecord(int x, int y) {
    public static void main(String[] args) {
        DemoRecord point = new DemoRecord(1,3);
        System.out.println(point.x());
        System.out.println(point.y());
    }
}

class Address{}

record CustUserWithBody(
    String firstName,
    String lastName,
    Address address,
    int age
) {
    public String fullName(){
        return firstName+ lastName;
    }

    public CustUserWithBody{
        if (age < 18) {
            throw new IllegalArgumentException( "男大当婚，女大当嫁，18岁未到，不许出嫁!");
        }
    }
}
