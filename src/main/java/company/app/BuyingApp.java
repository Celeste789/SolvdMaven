package company.app;

import company.Company;
import company.exceptions.SendMoneyException;

public class BuyingApp extends App implements IMoneyApp {
    private double balance;

    public BuyingApp(String name, String typeOfApp) {
        super(name, typeOfApp);
    }

    @Override
    public double getRealCost() {
        return getBasicCost();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public double sendMoney(double amount, Company company) throws SendMoneyException {
        try {
            this.validateBalance(amount);
        } catch (SendMoneyException e) {
            throw new SendMoneyException("You can't send this amount of money");
        }
        this.setBalance(balance - amount);
        return balance;
    }

    public void validateBalance(double amount) throws SendMoneyException {
        if (balance < amount) {
            throw new SendMoneyException("You can't send this amount of money");
        }
    }

}
