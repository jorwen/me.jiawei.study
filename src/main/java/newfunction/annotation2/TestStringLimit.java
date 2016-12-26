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
        Field[] fields = s.getClass().getDeclaredFields();
        for (Field field : fields) {
            StringLimit limit = field.getDeclaredAnnotation(StringLimit.class);
            if(limit != null && limit.maxLength() > 0){
                Method methodGet = BeanUtils.findMethod(s.getClass(),"get"+StringUtils.capitalize(field.getName()));
                Method methodSet = BeanUtils.findMethod(s.getClass(),"set"+StringUtils.capitalize(field.getName()),String.class);
                if(methodGet == null || methodSet == null) continue;
                String value = (String)methodGet.invoke(s);
                value = StringUtils.abbreviate(value, limit.maxLength());
                methodSet.invoke(s,value);
            }
        }
    }
}
