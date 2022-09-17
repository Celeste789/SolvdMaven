package company;

import company.enums.PayingDiscount;
import company.exceptions.IllegalPositionException;
import company.exceptions.SameIDException;
import company.functional_interfaces.ICalculateBonus;
import company.functional_interfaces.IHasBonus;
import company.human.Client;
import company.human.Employee;
import company.human.Seller;
import company.interfaces.ICalculateDiscount;
import company.linkedlist.MyLinkedList;

import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class Company implements ICalculateDiscount {
    public static final Logger LOGGER = Logger.getLogger("Logger.subnivel.info");

    private Set<Employee> employees;
    private static String name;
    private Queue<Client> clients;

    private MyLinkedList<Client> vipClients;

    static {
        LOGGER.log(Level.INFO, "Welcome to " + Company.getName());
    }

    private PayingDiscount payingDiscount;


    public static String getName() {
        return name;
    }


    public Set<Employee> getEmployees() {
        return employees;
    }

    public Company(String name) {
        this.name = name;
    }

    public void hireSomeone(Employee employee) {
        try {
            validateEmployeeID(employee);
            employees.add(employee);
        } catch (SameIDException e) {
            LOGGER.log(Level.WARNING, "This ID is already assigned");
        }
    }

    public void addClient(Client client) {
        try {
            validateClientID(client);
            clients.add(client);
        } catch (SameIDException e) {
            LOGGER.log(Level.WARNING, "This ID is already assigned");
        }
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

    public MyLinkedList<Client> getVipClients() {
        return vipClients;
    }

    public void addVIPClient(Client client) {
        MyLinkedList<Client> list = this.getVipClients();
        list.insert(client);
    }

    public void addVIPClientPriority(Client client, int position) throws IllegalPositionException {
        MyLinkedList<Client> priorityList = this.getVipClients();
        priorityList.insertAt(client, position);
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

    @Override
    public double calculateDiscount(Client client) {
        double discount;
        switch (payingDiscount) {
            case CASH:
                discount = PayingDiscount.CASH.getDiscount();
                break;
            case DEBIT_CARD:
                discount = PayingDiscount.DEBIT_CARD.getDiscount();
                break;
            default:
                discount = PayingDiscount.CREDIT_CARD.getDiscount();
        }
        return discount;
    }

    public static Stream<Client> concatTwoSellers(Seller seller1, Seller seller2) {
        Stream<Client> clientsThis = seller1.getClients().stream();
        Stream<Client> clientsSeller = seller2.getClients().stream();
        Stream<Client> clientStream = Stream.concat(clientsSeller, clientsThis);
        return clientStream;
    }

    public void printVIPClients() {
        vipClients.printLinkedList();
    }

}
