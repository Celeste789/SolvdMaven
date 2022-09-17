package company.human;

import company.enums.DiscountPerDay;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;
import company.interfaces.ICalculateDiscount;
import company.interfaces.IManageMoney;
import company.linkedlist.MyLinkedList;

import java.util.ArrayList;

public class Seller extends Employee implements ICalculateDiscount, IManageMoney {
    public static double basicSalary = 200;
    private ArrayList<Client> clients = new ArrayList<Client>();
    private MyLinkedList<Client> vipClients = new MyLinkedList<Client>();
    private DiscountPerDay discountPerDay;

    public MyLinkedList<Client> getVipClients() {
        return vipClients;
    }

    public Seller(String name, int id, int antiquity) throws NotClientNorEmployeeException, NegativeAntiquityException {
        super(antiquity, name, id, "Sellers", basicSalary);
    }


    public void addAClient(Client client) {
        clients.add(client);
    }


    @Override
    public double manageMoney(double total, double ins, double outs) {
        return total += ins;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    @Override
    public boolean validateMessageReceiver(Human receiver) {
        return receiver.getClass().getName().equals("company.Human.HumanResources") || receiver.getClass().getName().equals("company.Human.Accountant") || receiver.getClass().getName().equals("company.Human.Clients");
    }


    @Override
    public double calculateDiscount(Client client) {
        double discount = 0;
        switch (discountPerDay) {
            case MONDAY:
                discount = DiscountPerDay.MONDAY.getDiscount();
                break;
            case TUESDAY:
                discount = DiscountPerDay.TUESDAY.getDiscount();
                break;
            case WEDNESDAY:
                discount = DiscountPerDay.WEDNESDAY.getDiscount();
                break;
            case THURSDAY:
                discount = DiscountPerDay.THURSDAY.getDiscount();
                break;
            case FRIDAY:
                discount = DiscountPerDay.FRIDAY.getDiscount();
                break;
            default:
                discount = DiscountPerDay.WEEKEND.getDiscount();
        }
        return discount;
    }
}
