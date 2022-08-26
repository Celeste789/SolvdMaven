package company.human;

import company.exceptions.IncorrectSendMessageException;

import java.util.Set;

public class Seller extends Employee {
    public static double basicSalary = 200;
    private Set<Client> clients;


    public Seller(String name, int id, int antiquity) {
        super(name, id, antiquity);
    }

    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (!(receiver.getClass().getName().equals("company.human.Accountant") || receiver.getClass().getName().equals("company.human.Seller") || receiver.getClass().getName().equals("company.human.Client"))) {
            throw new IncorrectSendMessageException("You can't send a message to this person");
        }
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void addAClient(Client client, String typeOfApp) {
        clients.add(client);
    }


    @Override
    public String sendMessage(Human receiver, String message) throws IncorrectSendMessageException {
        try {
            this.validateMessageReceiver(receiver);
        } catch (IncorrectSendMessageException e) {
            throw new IncorrectSendMessageException("You can't send a message to someone other than accountants, clients and other sellers");
        }
        return receiveMessage(message);
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
