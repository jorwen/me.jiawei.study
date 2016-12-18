package concurrent.blockingqueue;

import java.util.HashMap;

/**
 *  原始统计字数类，单线程，获取一个计算一个
 */
public class OldWordCount {
    private static final HashMap<String, Integer> counts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Iterable<Page> pages = new Pages(100000, "enwiki.xml").getPages();
        for (Page page : pages) {
            Iterable<String> words = new Words(page.getText()).getWords();
            for (String word : words)
                countWord(word);
        }
    }

    private static void countWord(String word) {
        Integer currentCount = counts.get(word);
        if (currentCount == null) counts.put(word, 1);
        else counts.put(word, currentCount + 1);
    }
}
