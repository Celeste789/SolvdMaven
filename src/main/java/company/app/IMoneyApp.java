package company.app;

import company.Company;
import company.exceptions.SendMoneyException;
import company.human.Client;

public interface IMoneyApp {
    double sendMoney(double amount, Company company) throws SendMoneyException;

    default String receiveMoney(double amount, Client client) {
        return ("You received " + amount + " from " + client);
    }
}
