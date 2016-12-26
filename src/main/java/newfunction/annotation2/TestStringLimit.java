package newfunction.annotation2;

import org.apache.commons.lang.StringUtils;

public class TestStringLimit {
    @StringLimit(maxLength = 10)
    private String text;

    public String getText() {
        StringLimit limit = null;
        try {
            limit =this.getClass().getDeclaredField("text").getDeclaredAnnotation(StringLimit.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if(limit != null && limit.maxLength() > 0){
            text = StringUtils.abbreviate(text, limit.maxLength());
        }
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static void main(String[] args) {

        TestStringLimit test = new TestStringLimit();
        test.setText("asjdhashdjksahdjksahdjash");

        System.out.println(test.getText());
    }
}
