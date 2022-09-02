package company.human;

import company.enums.DepartmentName;
import company.exceptions.IncorrectSendMessageException;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;

import java.util.Map;
import java.util.function.IntUnaryOperator;

public class HumanResources extends Employee {
    private static double basicSalary = 200;
    private Map<Employee, Boolean> isWorkingMap;
    public DepartmentName departmentName = DepartmentName.HUMAN_RESOURCES;

    public HumanResources(int antiquity, String name, int id, double salary, Map<Employee, Boolean> isWorkingMap) throws NegativeAntiquityException, NotClientNorEmployeeException {
        super(antiquity, name, id, salary, "Human Resources");
        this.isWorkingMap = isWorkingMap;
    }

    public void beginLeave(Employee employee) {
        if (isWorkingMap.get(employee)) {
            isWorkingMap.computeIfPresent(employee, (k, v) -> v = false);
        }
    }

    public int timeOfLeave(Employee employee, boolean extendsLeave, IntUnaryOperator operator, int minimumDaysAdded) {
        int standarLeave = 10;
        if (extendsLeave && this.isWorkingMap.get(employee)) {
            int extension = operator.applyAsInt(minimumDaysAdded);
            standarLeave += extension;
        }
        return standarLeave;
    }

    public void finishLeave(Employee employee) {
        if (!(isWorkingMap.get(employee))) {
            isWorkingMap.computeIfPresent(employee, (k, v) -> v = true);
        }
    }


    @Override
    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (receiver.getClass().getName() == "company.human.Client") {
            throw new IncorrectSendMessageException();
        }
    }
}
