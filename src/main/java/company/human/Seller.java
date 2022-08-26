package company.human;

import company.enums.DepartmentName;
import company.enums.TypesOfDiscount;
import company.exceptions.IncorrectSendMessageException;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;
import company.interfaces.ICalculateDiscount;
import company.interfaces.IManageMoney;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Seller extends Employee implements ICalculateDiscount, IManageMoney {
    public static double basicSalary = 200;
    private ArrayList<Client> clients;
    public DepartmentName departmentName = DepartmentName.SELLERS;

    public Seller(String name, int id, int antiquity) throws NotClientNorEmployeeException, NegativeAntiquityException {
        super(antiquity, name, id, basicSalary, "Sellers");
    }

    @Override
    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (!(receiver.getClass().getName().equals("company.human.Accountant") || receiver.getClass().getName().equals("company.human.Seller") || receiver.getClass().getName().equals("company.human.Client"))) {
            throw new IncorrectSendMessageException();
        }
    }

    public ArrayList<Client> getClients() {
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
            throw new IncorrectSendMessageException();
        }
        return receiveMessage(message);
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    @Override
    public double calculateDiscount(Client client, double discountOfTheDay, TypesOfDiscount typesOfDiscount) {
        return discountOfTheDay;
    }

    @Override
    public double manageMoney(double total, double ins, double outs) {
        return total += ins;
    }

    public static Stream<Client> concatTwoSellers(Seller seller1, Seller seller2) {
        Stream<Client> clientsThis = seller1.clients.stream();
        Stream<Client> clientsSeller = seller2.clients.stream();
        Stream<Client> clientStream = Stream.concat(clientsSeller, clientsThis);
        return clientStream;
    }
}
