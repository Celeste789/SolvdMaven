package company.human;

import company.Company;
import company.exceptions.IncorrectSendMessageException;
import company.exceptions.SameIDException;

public abstract class Employee extends Human {
    private double basicSalary;
    private String team;

    public String getTeam() {
        return team;
    }

    public double getBasicSalary() {
        double basicSalary = this.basicSalary;
        if (this.getClass().getName().equals("company.human.Seller")) {
            Seller seller = (Seller) this;
            return seller.basicSalary;
        } else if (this.getClass().getName().equals("Accountant")) {
            Accountant accountant = (Accountant) this;
            return accountant.basicSalary;
        } else {
            Developer developer = (Developer) this;
            return developer.basicSalary;
        }
    }

    public Employee(int antiquity, String name, int id, double salary, Company company) throws SameIDException {
        super(name, id, antiquity);
        this.basicSalary = salary;
        try {
            for (Employee employee : company.getEmployees()) {
                company.validateEmployeeID(employee);
            }
        } catch (SameIDException e) {
            throw new SameIDException("This ID is already assigned");
        }

    }

    public Employee(String name, int id, int antiquity) {
        super(name, id, antiquity);
    }

    public String receiveSalary() {
        double salary = Accountant.calculateSalary(this);
        return ("I received my salary for the amount of " + salary);
    }

    @Override
    public abstract String sendMessage(Human receiver, String message) throws IncorrectSendMessageException;
}
