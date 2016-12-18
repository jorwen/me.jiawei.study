package guava.e_throwable;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.sql.SQLException;

public class DemoThrowable {
    public static void main(String[] args) throws IOException, SQLException {
        try {
            //略
        }
        catch (Exception e) {
            //自己处理
        }
        catch (Throwable t) {
            //Throwable类型为X才抛出
            Throwables.propagateIfInstanceOf(t, IOException.class);
            //Throwable类型为X, Error或RuntimeException才抛出
            Throwables.propagateIfPossible(t, SQLException.class);
            //如果Throwable是Error或RuntimeException，直接抛出；否则把Throwable包装成RuntimeException抛出。返回类型是RuntimeException
            throw Throwables.propagate(t);
        }
    }
}
