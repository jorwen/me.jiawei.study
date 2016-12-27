package newfunction.generic;

/**
 * 定义简单的泛型
 *
 * @author jw.fang
 * @version 1.0
 */
public interface A_List<T>
{
    void add(T x);
    T get();
}

class MyList<T> implements A_List<T>{

    @Override
    public void add(T x) {

    }

    @Override
    public T get() {
        return null;
    }
}

class Test{
    public static void main(String[] args) {
        A_List<String> myList = new MyList<>();
        myList.add("apple");
        //myList.add(100); 编译不通过
    }
}