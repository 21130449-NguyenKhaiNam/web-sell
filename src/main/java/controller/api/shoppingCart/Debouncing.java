package controller.api.shoppingCart;

import java.util.Timer;
import java.util.TimerTask;

public class Debouncing {
    private final long delay;
    private Timer timer;

    public Debouncing(long delay) {
        this.delay = delay;
    }

    public synchronized void debounce(final Runnable action) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                action.run();
            }
        }, delay);
    }

    // Hàm dừng hoạt động
    public synchronized void cancel() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
