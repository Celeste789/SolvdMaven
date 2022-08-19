package company.human;

import company.exceptions.IncorrectSendMessageException;

public abstract class Human {

    private int id;
    private String name;
    private int antiquity;

    public boolean isClient() {
        return isClient;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    private boolean isClient;
    private boolean isEmployee;

    public Human(int id, String name, int antiquity, boolean isClient, boolean isEmployee) {
        this.id = id;
        this.name = name;
        this.antiquity = antiquity;
        this.isClient = isClient;
        this.isEmployee = isEmployee;
    }

    public Human(String name, int id, int antiquity) {
        this.antiquity = antiquity;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAntiquity() {
        return antiquity;
    }

    public String receiveMessage(String message) {
        return ("I received the message saying " + message);
    }

    public abstract String sendMessage(Human receiver, String message) throws IncorrectSendMessageException;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Human human = (Human) obj;
        return human.id == id && human.name == name && human.isClient == isClient && human.isEmployee == isEmployee;
    }

    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }

    public int hashCode() {
        return id;
    }


}
