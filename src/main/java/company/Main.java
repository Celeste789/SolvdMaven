package company;

import company.enums.TypesOfApp;
import company.enums.TypesOfDiscount;
import company.exceptions.*;
import company.human.*;
import company.linkedlist.MyLinkedList;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Stream;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Logger.subnivel.debug");

    public static void main(String[] args) throws IllegalPositionException, IOException, NotClientNorEmployeeException, NegativeAntiquityException, SameIDException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IncorrectSendMessageException {

        SimpleFormatter simpleFormatter = new SimpleFormatter();
        FileHandler fileHandler = new FileHandler(LocalDateTime.now().toString() + ".log");
        fileHandler.setFormatter(simpleFormatter);
        LOGGER.addHandler(fileHandler);


        App netflix = new App("Netflix", "Streaming App");
        Client clientCarol = new Client("Carol", 1, "Streaming App", 1, 1);
        Developer developerMax = new Developer(10, "Max", 1, Developer.basicSalary, "git.com");
        Accountant accountantAnna = new Accountant("Anna", 2, 10);
        Class<Employee> employeeClass = Employee.class;
        Method getBasicSalary = employeeClass.getMethod("getBasicSalary");
        Parameter[] parameters = getBasicSalary.getParameters();
        Class<Department> departmentClass = Department.class;
        Department accountants = departmentClass.getConstructor(String.class).newInstance("Accountants");
        Field[] employeeFields = employeeClass.getDeclaredFields();
        HashSet<Employee> employees = new HashSet<>();
        employees.add(developerMax);
        employees.add(accountantAnna);
        Queue<Client> clients = new PriorityQueue<>();
        Company company = new Company("Company", clients, employees);
        int departmentConstructorModifiers = departmentClass.getConstructor(String.class).getModifiers();
        HashMap<Employee, Boolean> isWorking = new HashMap<>();
        isWorking.put(developerMax, true);
        isWorking.put(accountantAnna, false);
        HumanResources humanResourcesJack = new HumanResources(10, "Jack", 4, 300, isWorking);
        humanResourcesJack.beginLeave(developerMax);
        humanResourcesJack.timeOfLeave(accountantAnna, true, i -> 3 + i, 2);
        humanResourcesJack.finishLeave(accountantAnna);
        Client clientSophie = new Client("Shophie", 2, "GamingApp", 20, 5);
        Seller sellerJohn = new Seller("John", 5, 3);
        Seller sellerPeter = new Seller("Peter", 6, 2);
        ArrayList<Client> clientsJohn = new ArrayList<>();
        ArrayList<Client> clientsPeter = new ArrayList<>();
        sellerPeter.setClients(clientsPeter);
        sellerJohn.setClients(clientsJohn);
        sellerPeter.addAClient(clientSophie, "GamingApp");
        sellerJohn.addAClient(clientCarol, "Streaming App");
        Stream<Client> concatTwoSellers = Seller.concatTwoSellers(sellerJohn, sellerPeter);
        LinkedList<Employee> employeeLinkedList = new LinkedList<>();
        MyLinkedList<Employee> employeeMyLinkedList = new MyLinkedList<>();


        double discountCarol = accountantAnna.calculateDiscount(clientCarol, 0, TypesOfDiscount.ANTIQUITY_DISCOUNT);
        double bonusMax = Accountant.calculateBonus(() -> developerMax.getAntiquity() > 5, () -> 50);
        int vacationDaysMax = Accountant.calculateDaysOfVacation(developerMax, () -> 3);
        LinkedList<Employee> accountantsEmployees = accountants.returnDepartmentsEmployees(company);
        Accountant.calculateSalary(developerMax, (() -> developerMax.getAntiquity() > 5), () -> 150);
        double costCarol = accountantAnna.calculateCostForClient(clientCarol, netflix, TypesOfApp.STREAMING_APP, 100, TypesOfDiscount.DISCOUNT_PER_APP);
        Stream<Employee> filterByName = company.filterByName("Max");
        employeeMyLinkedList.insert(sellerJohn);
        employeeMyLinkedList.insertAt(sellerPeter, 0);

        LOGGER.log(Level.INFO, "The discount for Carol is " + discountCarol);
        LOGGER.log(Level.INFO, "The bonus for Max is " + bonusMax);
        LOGGER.log(Level.INFO, "Max has " + vacationDaysMax + " days of vacation");
        LOGGER.log(Level.INFO, employeeClass.getName());
        developerMax.getMethods(employeeClass);
        for (Parameter parameter : parameters) {
            LOGGER.log(Level.INFO, parameter.getName());
        }
        for (Employee accountant : accountantsEmployees) {
            LOGGER.log(Level.INFO, accountant.getNAME());
        }
        for (Field field : employeeFields) {
            LOGGER.log(Level.INFO, "The field name is " + field.getName());
        }
        LOGGER.log(Level.INFO, String.valueOf(departmentConstructorModifiers));
        LOGGER.log(Level.INFO, "The cost for Carol is " + costCarol);
        LOGGER.log(Level.INFO, clientCarol.receiveApp(netflix));
        LOGGER.log(Level.INFO, developerMax.code());
        LOGGER.log(Level.INFO, developerMax.sendMessage(accountantAnna, "Thanks for sending me my salary"));
        LOGGER.log(Level.INFO, "The amount of clients of the company " + company.amountOfClients());
        company.givePresentToFirstClient();
        company.printEmployees();
        Department.addEmployee(employeeLinkedList, sellerJohn);
        employeeMyLinkedList.printLinkedList();
    }
}