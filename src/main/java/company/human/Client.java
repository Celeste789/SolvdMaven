package company.human;

import company.App;
import company.exceptions.IncorrectSendMessageException;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;

import java.util.logging.Logger;

public class Client extends Human {
    private String typeOfApp;
    private int amountOfApps;

    public int getAmountOfApps() {
        return amountOfApps;
    }

    private static final Logger LOGGER = Logger.getLogger("Logger.subnivel.debug");

    public Client(String name, int id, String typeOfApp, int antiquity, int amountOfApps) throws NegativeAntiquityException, NotClientNorEmployeeException {
        super(id, name, antiquity, true, false);
        this.typeOfApp = typeOfApp;
        this.amountOfApps = amountOfApps;
    }


    @Override
    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (!(receiver.getClass().getName().equals("Seller"))) {
            throw new IncorrectSendMessageException();
        }
    }


    @Override
    public String sendMessage(Human receiver, String message) throws IncorrectSendMessageException {
        try {
            this.validateMessageReceiver(receiver);
        } catch (IncorrectSendMessageException e) {
            throw new IncorrectSendMessageException();
        }
        return receiver.receiveMessage(message);
    }

    public String receiveApp(App app) {
        return ("I received the app " + app.getName());
    }
}
