package company.human;

import company.app.App;
import company.exceptions.IncorrectSendMessageException;
import company.functional_interfaces.IAddVacationDays;

import java.util.Map;

public class Accountant extends Employee {
    public static double basicSalary = 350;
    private Map<Employee, Double> employeeSalaryMap;
    //private Set<Map.Entry<Employee, Double>> toSet = employeeSalaryMap.entrySet();


    //public Set<Map.Entry<Employee, Double>> getToSet() {
    //return toSet;
    // }

    /*
    public void setEmployeeSalaryMap(Map<Employee, Double> employeeSalaryMap) {
        this.employeeSalaryMap = employeeSalaryMap;
    }

*/
    public Accountant(String name, int id, int antiquity) {
        super(name, id, antiquity);
    }

    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (receiver.getClass().getName().equals("company.human.Client")) {
            throw new IncorrectSendMessageException("You can't send a message to this person");
        }
    }


    @Override
    public String sendMessage(Human receiver, String message) throws IncorrectSendMessageException {
        try {
            this.validateMessageReceiver(receiver);
        } catch (IncorrectSendMessageException e) {
            throw new IncorrectSendMessageException("You can't send a message to clients");
        }
        return receiver.receiveMessage(message);
    }

    public static final Double calculateSalary(Employee employee) {
        double salary = employee.getBasicSalary();
        if (employee.getAntiquity() >= 10) {
            salary += (employee.getAntiquity() - 10) * 50;
        }
        return salary;
    }

    public static final double calculateDiscount(Client client) {
        double discount = 0;
        if (client.getAntiquity() > 10) {
            discount += 50 * (client.getAntiquity() - 10);
        }
        return discount;
    }

    public void addEmployee(Employee employee) {
        employeeSalaryMap.put(employee, Accountant.calculateSalary(employee));
    }

    public static double calculateCostForClient(Client client, App app) {
        double cost = app.getRealCost() - calculateDiscount(client);
        return cost;
    }


    public static int calculateDaysOfVacation(Employee employee, IAddVacationDays addVacationDays) {
        int basicAmount = employee.getBasicAmount();
        if (employee.getAntiquity() > 3) {
            basicAmount += employee.getAntiquity() - 3;
        }
        return basicAmount;
    }

    public Map<Employee, Double> getEmployeeSalaryMap() {
        return employeeSalaryMap;
    }
}
