package JavaPractice;

import java.util.concurrent.atomic.AtomicInteger;

public class job {

    private static final AtomicInteger count = new AtomicInteger(0);
    private final int jobID;
    private final String name;

    private boolean isFilled;

    public job(String title) {
        name = title;
        isFilled = false;
        jobID = count.incrementAndGet();
    }
}
