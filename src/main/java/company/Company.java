package company;

import company.exceptions.SameIDException;
import company.human.Client;
import company.human.Employee;

import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class Company {
    public static final Logger LOGGER = Logger.getLogger("Logger.subnivel.info");

    private Set<Employee> employees;
    private static String name;
    private Queue<Client> clients;

    static {
        LOGGER.log(Level.INFO, "Welcome to " + name);
    }


    public String getName() {
        return name;
    }

    public Queue<Client> getClients() {
        return clients;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

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
        return ("Here's your salary " + employee.getNAME());
    }

    public Company(String name, Queue<Client> clients, Set<Employee> employees) {
        this.name = name;
        this.clients = clients;
        this.employees = employees;
    }

    public void validateEmployeeID(Employee employeeToCheck) throws SameIDException {
        for (Employee employee : employees) {
            if (employeeToCheck.hashCode() == employee.hashCode()) {
                throw new SameIDException("This ID is already assigned");
            }
        }
    }

    public void validateClientID(Client clientToCheck) throws SameIDException {
        for (Client client : this.clients) {
            if (client.hashCode() == clientToCheck.hashCode()) {
                throw new SameIDException("This ID is already assigned");
            }
        }
    }

    public void fireEmployee(Employee employee) {
        employees.remove(employee);
    }


    public long amountOfClients() {
        long result = clients.stream().count();
        return result;
    }

    public Stream<Employee> filterByName(String name) {
        Stream<Employee> employeeStream = employees.stream().filter(employee -> employee.getNAME() == name);
        return employeeStream;
    }

    public void givePresentToFirstClient() {
        Stream<Client> clientStream = clients.stream();
        Optional<Client> client = clientStream.findFirst();
        LOGGER.log(Level.INFO, "Congratulations " + client + " you won a price!");
    }

    public void printEmployees() {
        Stream<Employee> employeeStream = employees.stream();
        employeeStream.forEach(employee -> LOGGER.log(Level.INFO, employee.getNAME()));
    }

}
