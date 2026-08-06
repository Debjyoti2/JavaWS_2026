package inter_thread_communication;

import java.util.concurrent.CountDownLatch;

public class Countdownlatch {


    static void main() {

        CountDownLatch latch = new CountDownLatch(3);
        new Thread(()->startService("DBService",latch)).start();
        new Thread(()->startService("CacheService",latch)).start();
        new Thread(()->startService("QueueService",latch)).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("All services are started");

    }
    public static void startService(String serviceName,CountDownLatch latch) {
        System.out.println("Stating Service " + serviceName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Service " + serviceName + " is started");
        latch.countDown();
    }

}
