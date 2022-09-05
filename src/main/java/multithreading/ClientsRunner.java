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

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(MAX_THREADS);

        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(MAX_THREADS, MAX_THREADS, 4, TimeUnit.SECONDS, queue);
        for (int i = 0; i < MAX_THREADS; i++) {
            Runnable connection = new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection connection = cp.getConnection();
                        connection.connect();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        connection.disconnect();
                        cp.finishConnection(connection);
                    } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                    }
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
