package slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSlf4j {
    private static final Logger LOG1 = LoggerFactory.getLogger("dailyFile");
    private static final Logger LOG2 = LoggerFactory.getLogger(TestSlf4j.class);

    public static void main(String[] args) {
        LOG1.info("hello1");
        LOG2.info("hello2 {}", "jarvis");
    }
}