package company.human;

import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntUnaryOperator;

public class HumanResources extends Employee {
    private static double basicSalary = 200;
    private Map<Employee, Boolean> isWorkingMap = new HashMap<Employee, Boolean>();

    public void addEmployeeToWorkingMap(Employee employee, boolean isWorking) {
        Map<Employee, Boolean> workingMap = isWorkingMap;
        workingMap.put(employee, isWorking);
        setIsWorkingMap(workingMap);
    }

    public HumanResources(int antiquity, String name, int id) throws NegativeAntiquityException, NotClientNorEmployeeException {
        super(antiquity, name, id, "Human Resources", basicSalary);
    }

    public void beginLeave(Employee employee) {
        if (isWorkingMap.get(employee)) {
            isWorkingMap.computeIfPresent(employee, (k, v) -> v = false);
        }
    }

    public void setIsWorkingMap(Map<Employee, Boolean> isWorkingMap) {
        this.isWorkingMap = isWorkingMap;
    }

    public int timeOfLeave(Employee employee, boolean extendsLeave, IntUnaryOperator operator) {
        int standardLeave = 10;
        int minimumDaysAdded = 3;
        if (extendsLeave) {
            int extension = operator.applyAsInt(minimumDaysAdded);
            standardLeave += extension;
        }
        return standardLeave;
    }

    public void finishLeave(Employee employee) {
        if (!(isWorkingMap.get(employee))) {
            isWorkingMap.computeIfPresent(employee, (k, v) -> v = true);
        }
    }


    @Override
    public boolean validateMessageReceiver(Human receiver) {
        return !receiver.getClass().getName().equals("company.Human.Clients");
    }
}
