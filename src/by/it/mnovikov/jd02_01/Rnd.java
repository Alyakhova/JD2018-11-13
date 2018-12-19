package by.it.mnovikov.jd02_01;

import javax.xml.ws.Dispatch;

public class Rnd {

    static int random(int max) {
        return random(0, max);
    }

    static int random(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    static void sleep(int millis) {
        try {
            Thread.sleep(millis / Dispatcher.K_SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void sleepTime(int from, int to) {
        try {
            int pause = Rnd.random(from, to);
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
