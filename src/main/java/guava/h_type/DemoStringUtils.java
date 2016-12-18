package guava.h_type;

import com.google.common.base.*;

/**
 * 字符串工具
 */
public class DemoStringUtils {
    public static void main(String[] args) {
        //1.Joiner,不可变的线程安全的
        Joiner joiner = Joiner.on("; ").skipNulls();
        String s1 = joiner.join("Harry", null, "Ron", "Hermione");//Harry; Ron; Hermione

        //2.Splitter
        Iterable<String> it1 = Splitter.on(',')
            .trimResults()
            .omitEmptyStrings()
            .split("foo,bar,,   qux");

        //3.CharMatcher 字符匹配器
        String string = "";
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); //移除control字符
        String theDigits = CharMatcher.DIGIT.retainFrom(string); //只保留数字字符
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');//去除两端的空格，并把中间的连续空格替换成单个空格
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); //用*号替换所有数字
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);// 只保留数字和小写字母
    }
}
