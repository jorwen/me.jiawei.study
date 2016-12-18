package concurrent.blockingqueue;

import java.util.ArrayList;
import java.util.List;

public class Pages {
    private List<Page> pages;

    public Pages(int num, String url) {
        pages = new ArrayList<>();
    }

    public List<Page> getPages() {
        return pages;
    }
}
