package company.human;

import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;
import company.functional_interfaces.IAddVacationDays;
import company.interfaces.IManageMoney;

import java.util.logging.Logger;

public class Accountant extends Employee implements IManageMoney {
    private final Logger LOGGER = Logger.getLogger("Logger.warning");


    public static double basicSalary = 350;


    public Accountant(String name, int id, int antiquity) throws NotClientNorEmployeeException, NegativeAntiquityException {
        super(antiquity, name, id, basicSalary, "Accountants");
    }

    @Override
    public boolean validateMessageReceiver(Human receiver) {
        return !receiver.getClass().getName().equals("company.human.Client");
    }


    public static int calculateDaysOfVacation(Employee employee, IAddVacationDays addVacationDays) {
        int basicAmount = employee.getBasicAmount();
        if (employee.getAntiquity() > 3) {
            basicAmount += addVacationDays.manageVacations();
        }
        return basicAmount;
    }

    @Override
    public double manageMoney(double total, double ins, double outs) {
        total += ins - outs;
        return total;
    }


}
