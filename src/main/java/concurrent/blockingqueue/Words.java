package concurrent.blockingqueue;

import java.util.ArrayList;
import java.util.List;

public class Words {
    List<String> words;
    public Words(String text) {
        words = new ArrayList<>();
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
