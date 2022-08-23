package company.app;

import company.Company;
import company.exceptions.SendMoneyException;

public class BankingApp extends App implements IMoneyApp {
    private double balance;


    @Override
    public double getRealCost() {
        return getBasicCost();
    }

    public BankingApp(String name, String typeOfApp, double balance) {
        super(name, typeOfApp);
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public double sendMoney(double amount, Company company) throws SendMoneyException {
        if (balance < amount) {
            throw new SendMoneyException("You can't send this amount of money");
        }
        setBalance(balance - amount);
        return (balance);
    }

    public void validateBalance(double amount) throws SendMoneyException {
        if (amount > balance) {
            throw new SendMoneyException("You can't send this amount of money");
        }
    }

    @Override
    public void receiveMoney(double amount) {
        setBalance(balance + amount);
    }
}
