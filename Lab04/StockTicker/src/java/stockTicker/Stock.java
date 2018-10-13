package stockTicker;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

public class Stock extends Thread {
    final Random rand = new Random();
    protected int price = -1;
    protected String timeStamp="";

    /**
     * Periodically updates stock price and notifies sevlet threads.
     */
    @Override
    public void run() {
        while (!Thread.interrupted())
            try {
                synchronized (this) {
                    price = 90 + rand.nextInt(20);
                    timeStamp=new SimpleDateFormat("HH:mm:ss").format(new Date());
                    notifyAll();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.getGlobal().log(Level.INFO, "Stock updates terminated!");
                break;
            }
    }
}
