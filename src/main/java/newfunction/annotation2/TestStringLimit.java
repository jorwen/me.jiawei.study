package newfunction.annotation2;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestStringLimit {

    @StringLimit(maxLength = 20)
    private String text;

    @StringLimit(maxLength = 10)
    private String name;

    private String id;

    public String getText() {

        return text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static void main(String[] args) throws Exception {

        TestStringLimit test = new TestStringLimit();
        test.setText("asjdhashdjksahdjksahdjash");
        test.setName("126345372462387");
        annotation(test);
        System.out.println("text:"+test.getText());
        System.out.println("name:"+test.getName());
    }

    private static void annotation(TestStringLimit s) throws Exception {
        //拿到所有变量
        Field[] fields = s.getClass().getDeclaredFields(); //[text,name]的反射对象

        //遍历所有变量
        for (Field field : fields) {
            StringLimit limit = field.getDeclaredAnnotation( StringLimit.class);
            if(limit != null && limit.maxLength() > 0){
                //获取get set 方法
                Method methodGet = BeanUtils.findMethod(s.getClass(),"get"+StringUtils.capitalize(field.getName()));
                Method methodSet = BeanUtils.findMethod(s.getClass(),"set"+StringUtils.capitalize(field.getName()),String.class);
                if(methodGet == null || methodSet == null) continue;

                //获得变量值
                String value = (String)methodGet.invoke(s); //asjdhashdjksahdjksahdjash

                //截断
                value = StringUtils.abbreviate(value, limit.maxLength());//asjdhashdjksah...

                //调用set设置
                methodSet.invoke(s,value);
            }
        }
    }
}
