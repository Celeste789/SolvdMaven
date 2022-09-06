package multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Vector;

public class ConnectionPool {
    final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());

    protected int size;
    protected Vector<Connection> connectionVector;

    public ConnectionPool(int size) {
        this.size = size;
        connectionVector = new Vector<>(size) {
        };
    }

    private void beginConnection(Connection connection) {
        connectionVector.add(connection);
    }

    public void finishConnection(Connection connection) {
        connectionVector.remove(connection);
    }

    public synchronized Connection getConnection() {

        Connection connection;

        if (connectionVector.size() < size) {
            connection = new Connection("Thread#" + (connectionVector.size() + 1));
            beginConnection(connection);
            return connection;

        } else {
            logger.info("There are no free connections at the moment");
            int maxAttemptsCount = 10;
            while (maxAttemptsCount-- > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (connectionVector.size() < size) {
                    connection = new Connection("Thread#" + (connectionVector.size() + 1));
                    beginConnection(connection);
                    return connection;
                }
            }
            throw new RuntimeException("No connections available after 10 seconds");

        }
    }
}
