package company.human;

import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;

import java.lang.reflect.Method;
import java.util.logging.Level;

public abstract class Employee extends Human {
    private double basicSalary;

    private int basicAmountOfVacations = 15;

    private String departmentsName;


    public int getBasicAmountOfVacations() {
        return basicAmountOfVacations;
    }


    public double getBasicSalary() {
        double basicSalary = this.basicSalary;
        if (this.getClass().getName().equals("company.human.Seller")) {
            return Seller.basicSalary;
        } else if (this.getClass().getName().equals("company.human.Accountant")) {
            return Accountant.basicSalary;
        } else {
            return Developer.basicSalary;
        }
    }

    public Employee(int antiquity, String name, int id, String departmentsName, double basicSalary) throws NegativeAntiquityException, NotClientNorEmployeeException {
        super(id, name, antiquity, false, true);
        this.departmentsName = departmentsName;
        this.basicSalary = basicSalary;
    }


    public void getMethods(Class employeeClass) {
        for (Method method : employeeClass.getMethods()) {
            LOGGER.log(Level.INFO, "The method is called " + method.getName());
        }
    }


}
