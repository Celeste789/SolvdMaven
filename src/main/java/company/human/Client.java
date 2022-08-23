package company.human;

import company.app.App;
import company.exceptions.IncorrectSendMessageException;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Human {
    private String typeOfApp;

    private static final Logger LOGGER = Logger.getLogger("Logger.subnivel.debug");

    public Client(String name, int id, String typeOfApp, int antiquity) {
        super(name, id, antiquity);
        this.typeOfApp = typeOfApp;
    }

    Consumer<Client> buyApp = client -> LOGGER.log(Level.INFO, "Hello, I'd like to buy an app");

    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (!(receiver.getClass().getName().equals("Seller"))) {
            throw new IncorrectSendMessageException("You can't send a message to someone other than sellers");
        }
    }


    @Override
    public String sendMessage(Human receiver, String message) throws IncorrectSendMessageException {
        try {
            this.validateMessageReceiver(receiver);
        } catch (IncorrectSendMessageException e) {
            throw new IncorrectSendMessageException("You can't send a message to someone other than sellers");
        }
        return receiver.receiveMessage(message);
    }

    public String receiveApp(App app) {
        return ("I received the app " + app.getName());
    }
}
