package multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClientsRunner {
    private static Logger logger = LogManager.getLogger(ClientsRunner.class);
    private static ConnectionPool cp = new ConnectionPool(5);

    public static void main(String[] args) throws InterruptedException {

        final int MAX_THREADS = 7;

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(MAX_THREADS);

        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(MAX_THREADS, MAX_THREADS, 3, TimeUnit.SECONDS, queue);
        for (int i = 0; i < MAX_THREADS; i++) {
            Runnable connection = () -> {
                try {
                    Connection connection1 = cp.getConnection();
                    connection1.connect();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    connection1.disconnect();
                    cp.finishConnection(connection1);
                } catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                }
            };
            executor1.execute(connection);
        }
        executor1.shutdown();
        while (!executor1.isTerminated()) {

        }
        logger.info("Finished all threads");
    }
}
