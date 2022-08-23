package company.app;

import company.Company;
import company.exceptions.SendMoneyException;

public interface IMoneyApp {
    double sendMoney(double amount, Company company) throws SendMoneyException;

    void receiveMoney(double amount);
    //What should I do regarding copy-paste?
}
