package company;

import company.exceptions.SameIDException;
import company.human.Client;
import company.human.Employee;

import java.util.ArrayList;
import java.util.Vector;

public final class Company {
    private String name;
    private ArrayList<Client> clients;


    public String getName() {
        return name;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public Vector<Employee> getEmployees() {
        return employees;
    }

    private Vector<Employee> employees;

    public void hireSomeone(Employee employee) throws SameIDException {
        try {
            this.validateEmployeeID(employee);
        } catch (SameIDException e) {
            throw new SameIDException("This ID is already assigned");
        }
        employees.add(employee);
    }

    public void addClient(Client client) throws SameIDException {
        try {
            this.validateClientID(client);
        } catch (SameIDException e) {
            throw new SameIDException("This ID is already assigned");
        }
    }

    public String paySalary(Employee employee) {
        return ("Here's your salary " + employee.getName());
    }

    public Company(String name, ArrayList<Client> clients, Vector<Employee> employees) {
        this.name = name;
        this.clients = clients;
        this.employees = employees;
    }

    public void validateEmployeeID(Employee employeeToCheck) throws SameIDException {
        for (Employee employee : employees) {
            if (employeeToCheck.getId() == employee.getId()) {
                throw new SameIDException("This ID is already assigned");
            }
        }
    }

    public void validateClientID(Client clientToCheck) throws SameIDException {
        for (Client client : this.clients) {
            if (client.getId() == clientToCheck.getId()) {
                throw new SameIDException("This ID is already assigned");
            }
        }
    }

    public void fireEmployee(Employee employee) {
        employees.remove(employees.indexOf(employee));
    }


}
