package company.human;

import company.exceptions.IncorrectSendMessageException;

public class Developer extends Employee {
    public static double basicSalary = 300;
    private String githubLink;

    public Developer(String name, int id, int antiquity) {
        super(name, id, antiquity);
    }


    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (!(receiver.getClass().getName().equals("company.human.Accountant") || receiver.getClass().getName().equals("company.human.Developer"))) {
            throw new IncorrectSendMessageException("You can't send a message to this person");
        }
    }


    @Override
    public String sendMessage(Human receiver, String message) throws IncorrectSendMessageException {
        try {
            this.validateMessageReceiver(receiver);
        } catch (IncorrectSendMessageException e) {
            throw new IncorrectSendMessageException("You can't send a message to someone other than accountants and other developers");
        }
        return receiver.receiveMessage(message);
    }

    public String code(){
        return ("My code is in this Github link " + githubLink);
    }
}
