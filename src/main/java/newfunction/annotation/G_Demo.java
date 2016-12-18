package newfunction.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * 一个完整的SQLString范例
 *
 * @author jw.fang
 * @version 1.0
 */
public class G_Demo
{
    //实现创建表
    public static void main(String[] args)
    {
        TableCreator tc = new TableCreator();
        tc.executeCreateDB(Member.class);
    }

    public @interface Constraints
    {
        boolean primaryKey() default false;

        boolean allowNull() default true;

        boolean unique() default false;
    }

    @Target(ElementType.TYPE)//类，接口或enum
    @Retention(RetentionPolicy.RUNTIME)
    //定义表名的注解
    public @interface DBTable
    {
        public String name() default "";
    }

    @Target(ElementType.FIELD)//类，接口或enum
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SQLInteger
    {
        String name() default "";

        //嵌套注解的功能,将column类型的数据库约束信息嵌入其中
        Constraints constraints() default @Constraints;
    }

    @Target(ElementType.FIELD)//类，接口或enum
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SQLString
    {
        int value() default 0;

        String name() default "";

        //注解元素中引用别的注解，
        Constraints constraints() default @Constraints;
    }

    @DBTable(name = "MEMBER")
    public class Member
    {
        //在使用注解过程中，如果有元素是value，并且只有value需要赋值，
        //则只需要在()中将值写入
        @SQLString(30)
        private String firstName;
        @SQLString(50)
        private String lastName;
        @SQLInteger
        private Integer age;
        @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
        private String handle;

        public String getFirstName()
        {
            return firstName;
        }

        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }

        public String getLastName()
        {
            return lastName;
        }

        public void setLastName(String lastName)
        {
            this.lastName = lastName;
        }

        public Integer getAge()
        {
            return age;
        }

        public void setAge(Integer age)
        {
            this.age = age;
        }

        public String getHandle()
        {
            return handle;
        }

        public void setHandle(String handle)
        {
            this.handle = handle;
        }
    }

    public static class TableCreator
    {
        public Connection getConnection()
        {
            String user = "root";
            String password = "";
            String serverUrl = "jdbc:mysql://localhost:3306/carrent?user=root&password=";
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(serverUrl, user, password);
                return con;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        public void executeCreateDB(Class<?> entity)
        {
            String sqlStr = explainAnnotation(entity);
            Connection con = getConnection();
            PreparedStatement psql = null;
            if (con != null && !sqlStr.equals("error"))
            {
                try
                {
                    psql = con.prepareStatement(sqlStr);
                    psql.execute();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (psql != null) psql.close();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        if (psql != null)
                        {
                            psql.close();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            else System.out.println("failure to...");
        }

        // 真正的处理器,Class<?>必须用这个表明
        public String explainAnnotation(Class<?> entity)
        {
            // 获取指定类型的注解
            DBTable dbtable = entity.getAnnotation(DBTable.class);
            if (dbtable == null)
            {
                System.out.println("No DBTable annotation in class" + entity.getName());
                return "error";
            }
            else
            {
                String tableName = dbtable.name();// 获取注解name值，即表名称
                // 当没有设置name值，直接利用类的名作为表名
                if (tableName.length() < 1) tableName = entity.getName().toUpperCase();// 转换大写
                // 准备处理字段注解
                List<String> columnsDefs = new ArrayList<String>();
                // 获取该类的所有字段
                for (Field field : entity.getDeclaredFields())
                {
                    String columnName = null;
                    // 获取该字段所有的注解
                    Annotation[] anns = field.getDeclaredAnnotations();
                    // Annotation[] anns=field.getAnnotations();
                    // 当有注解的时候
                    if (anns.length >= 1)
                    {
                        // 判断注解的类型
                        if (anns[0] instanceof SQLInteger)
                        {
                            SQLInteger sInt = (SQLInteger) anns[0];
                            // 当没有name时候，将字段大写为列名
                            if (sInt.name().length() < 1) columnName = field.getName().toUpperCase();
                            else columnName = sInt.name();
                            columnsDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                        }
                        if (anns[0] instanceof SQLString)
                        {
                            SQLString sString = (SQLString) anns[0];
                            // 当没有name时候，将字段大写为列名
                            if (sString.name().length() < 1) columnName = field.getName().toUpperCase();
                            else columnName = sString.name();
                            columnsDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
                        }
                    }
                }
                StringBuilder createDB = new StringBuilder("CREATE TABLE " + tableName + "(");
                for (String cols : columnsDefs)
                    createDB.append(" " + cols + ",");
                // 移除最后一个，号
                String tableSQL = createDB.substring(0, createDB.length() - 1) + ");";
                // 输出创建表的过程
                System.out.println("Table Creation SQL is:\n" + tableSQL);
                return tableSQL;
            }
        }

        // 返回指定的约束
        public String getConstraints(Constraints con)
        {
            String constras = "";
            if (!con.allowNull()) constras += " NOT NULL";
            if (con.primaryKey()) constras += " PRIMARY KEY";
            if (con.unique()) constras += " UNIQUE";
            return constras;
        }
    }
}
