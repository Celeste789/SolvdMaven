package company.human;

import company.exceptions.IncorrectSendMessageException;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Human {

    public final Logger LOGGER = Logger.getLogger("Logger warning");
    final private int ID;
    final private String NAME;
    private int antiquity;

    public boolean isClient() {
        return isClient;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    private boolean isClient;
    private boolean isEmployee;


    public Human(int id, String name, int antiquity, boolean isClient, boolean isEmployee) throws NegativeAntiquityException, NotClientNorEmployeeException {
        if (antiquity < 0) {
            throw new NegativeAntiquityException("The antiquity can't be negative");
        }
        if (!isClient && !isEmployee) {
            throw new NotClientNorEmployeeException("A person has to be a client or a employee");
        }
        this.ID = id;
        this.NAME = name;
        this.antiquity = antiquity;
        this.isClient = isClient;
        this.isEmployee = isEmployee;
    }


    public String getNAME() {
        return NAME;
    }

    public int getAntiquity() {
        return antiquity;
    }

    public String receiveMessage(String message) {
        return ("I received the message saying " + message);
    }

    public String sendMessage(Human receiver, String message) throws IncorrectSendMessageException {
        try {
            this.validateMessageReceiver(receiver);
        } catch (IncorrectSendMessageException e) {
            LOGGER.log(Level.WARNING, "You can't send a message to this person");
        }
        return receiver.receiveMessage(message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Human human = (Human) obj;
        return human.ID == ID && human.NAME == NAME && human.isClient == isClient && human.isEmployee == isEmployee;
    }


    public int hashCode() {
        return ID;
    }

    public abstract void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException;
}
