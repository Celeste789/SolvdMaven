package company.human;

import company.App;
import company.enums.DepartmentName;
import company.enums.TypesOfApp;
import company.enums.TypesOfDiscount;
import company.exceptions.IncorrectSendMessageException;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;
import company.functional_interfaces.IAddVacationDays;
import company.functional_interfaces.ICalculateBonus;
import company.functional_interfaces.IHasBonus;
import company.interfaces.ICalculateDiscount;
import company.interfaces.IManageMoney;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Accountant extends Employee implements ICalculateDiscount, IManageMoney {
    private final Logger LOGGER = Logger.getLogger("Logger.warning");

    public DepartmentName departmentName = DepartmentName.ACCOUNTANT;

    public static double basicSalary = 350;


    public Accountant(String name, int id, int antiquity) throws NotClientNorEmployeeException, NegativeAntiquityException {
        super(antiquity, name, id, basicSalary, "Accountants");
    }

    @Override
    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (receiver.getClass().getName().equals("company.human.Client")) {
            throw new IncorrectSendMessageException();
        }
    }


    @Override
    public String sendMessage(Human receiver, String message) throws IncorrectSendMessageException {
        try {
            this.validateMessageReceiver(receiver);
        } catch (IncorrectSendMessageException e) {
            LOGGER.log(Level.WARNING, "You can't send messages to this person");
        }
        return receiver.receiveMessage(message);
    }

    @Override
    public double calculateDiscount(Client client, double discountOfTheDay, TypesOfDiscount typesOfDiscount) {
        double discount = 0;
        switch (typesOfDiscount) {
            case ANTIQUITY_DISCOUNT:
                if (client.getAntiquity() > typesOfDiscount.getCondition()) {
                    discount = 10 * client.getAntiquity() - 10;
                }
                break;
            case DISCOUNT_PER_APP:
                if (client.getAmountOfApps() > typesOfDiscount.getCondition()) {
                    discount = client.getAmountOfApps() * 2;
                }
                break;
            default:
                discount = discountOfTheDay;
        }
        return discount;
    }

    public static final Double calculateSalary(Employee employee, BooleanSupplier booleanSupplier, DoubleSupplier doubleSupplier) {
        double salary = employee.getBasicSalary();
        if (booleanSupplier.getAsBoolean()) {
            salary = doubleSupplier.getAsDouble();
        }
        return salary;
    }

    public static double calculateBonus(IHasBonus hasBonus, ICalculateBonus iCalculateBonus) {
        double bonus = 0;
        if (hasBonus.hasBonus()) {
            bonus = iCalculateBonus.iCalculateBonus();
        }
        return bonus;
    }

    public double calculateCostForClient(Client client, App app, TypesOfApp typesOfApp, double discountOfTheDay, TypesOfDiscount typesOfDiscount) {
        double cost = app.getCost(typesOfApp) - this.calculateDiscount(client, discountOfTheDay, typesOfDiscount);
        return cost;
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
