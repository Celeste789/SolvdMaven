package company;

import company.human.Employee;

import java.util.LinkedList;

public class Department {
    public Department(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }


    public static void addEmployee(LinkedList<Employee> linkedList, Employee employee) {
        linkedList.add(employee);
    }


    public LinkedList<Employee> returnDepartmentsEmployees(Company company) {
        LinkedList<Employee> result = new LinkedList<>();
        for (Employee employee : company.getEmployees()) {
            if (this.getName() == employee.getDepartmentsName()) {
                result.add(employee);
            }
        }
        return result;
    }
}
