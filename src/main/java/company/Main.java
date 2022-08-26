package company;

import company.app.BankingApp;
import company.app.GamingApp;
import company.app.StreamingApp;
import company.exceptions.GamingAppInvalidBooleansException;
import company.exceptions.IncorrectSendMessageException;
import company.exceptions.SameIDException;
import company.exceptions.SendMoneyException;
import company.human.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Stream;

public class Main {


    private static final Logger LOGGER = Logger.getLogger("Logger.subnivel.debug");


    public static void main(String[] args) throws IncorrectSendMessageException, GamingAppInvalidBooleansException, SendMoneyException, SameIDException, IOException {

        SimpleFormatter simpleFormatter = new SimpleFormatter();
        FileHandler fileHandler = new FileHandler(LocalDateTime.now().toString() + ".log");
        fileHandler.setFormatter(simpleFormatter);
        LOGGER.addHandler(fileHandler);
        Seller sellerJohn = new Seller("John", 1, 11);
        Accountant accountantAnna = new Accountant("Anna", 2, 2);
        Developer developerPeter = new Developer("Peter", 3, 15);
        Client clientCarol = new Client("Carol", 1, "Gaming app", 2);
        ArrayList<Client> clients = new ArrayList<Client>(Arrays.asList(clientCarol));
        Vector<Employee> employees = new Vector<Employee>(Arrays.asList(sellerJohn, developerPeter, accountantAnna));
        Company itCompany = new Company("IT Company", clients, employees);
        Seller sellerPaul = new Seller("Paul", 3, 1);
        Client clientSophie = new Client("Sophie", 2, "Gaming app", 12);
        //GamingApp sudoku = new GamingApp("Sudoku", "Gaming", false, false, false);
        GamingApp terraria = new GamingApp("Terraria", "Gaming", false, true, false);

        itCompany.addClient(clientSophie);
        //itCompany.hireSomeone(sellerPaul);
        LOGGER.log(Level.INFO, "The antiquity of Paul is " + sellerPaul.getAntiquity());
        LOGGER.log(Level.INFO, itCompany.paySalary(sellerPaul));

        LOGGER.log(Level.INFO, "This is Paul's salary " + Accountant.calculateSalary(sellerPaul));
        LOGGER.log(Level.INFO, "This is Peter's salary " + Accountant.calculateSalary(developerPeter));

        //LOGGER.log(Level.INFO, "This is the cost for Sophie " + Accountant.calculateCost(sudoku, clientSophie));
        //LOGGER.log(Level.INFO, "This is the cost for Carol " + Accountant.calculateCost(sudoku, clientCarol));

        LOGGER.log(Level.INFO, "This is the cost for Sophie " + Accountant.calculateCostForClient(clientCarol, terraria));
        LOGGER.log(Level.INFO, "This is the cost for Carol " + Accountant.calculateCostForClient(clientSophie, terraria));

        LOGGER.log(Level.INFO, developerPeter.sendMessage(accountantAnna, "I want my salary"));
        //developerPeter.sendMessage(sellerJohn, "Hi");

        BankingApp nationalBankApp = new BankingApp("National Bank app", "Banking", 100);
        //nationalBankApp.sendMoney(1000, itCompany);

        StreamingApp netflix = new StreamingApp("Netflix", "Streaming app", 100, 100);


        UnaryOperator<StreamingApp> addMovie = app -> app.newSetMovies(app);

        Consumer<Client> welcome = client -> LOGGER.log(Level.INFO, "Welcome " + client.getName());

        Predicate<Client> hasDiscount = client -> (client.getAntiquity() > 10);


        HashMap<Employee, Double> employeeDoubleHashMap = new HashMap<Employee, Double>();
        employeeDoubleHashMap.put(accountantAnna, Accountant.calculateSalary(accountantAnna));
        employeeDoubleHashMap.put(sellerJohn, Accountant.calculateSalary(sellerPaul));
        employeeDoubleHashMap.put(sellerPaul, Accountant.calculateSalary(sellerPaul));
        //employeeDoubleHashMap.put(developerPeter, Accountant.calculateSalary(developerPeter));
        //accountantAnna.addEmployee(developerPeter);

        employeeDoubleHashMap.computeIfPresent(sellerJohn, (key, value) -> value + 100);

        int amountOfDaysDeveloperPeter = Accountant.calculateDaysOfVacation(developerPeter, () -> developerPeter.getBasicAmount() + 5);

        //accountantAnna.setEmployeeSalaryMap(employeeDoubleHashMap);

        LOGGER.log(Level.INFO, String.valueOf((Accountant.calculateSalary(accountantAnna))));

        clients.stream().filter(client -> client.getName() == "Carol");


        Set<Client> clients2 = new HashSet<>();
        clients2.add(clientSophie);
        clients2.add(clientCarol);
        sellerJohn.setClients(clients2);

        Set<Client> clientsSellerJohn = sellerJohn.getClients();

        LOGGER.log(Level.INFO, "The amount of John's clients is " + clientsSellerJohn.stream().count());

        Vector<Employee> employeeVector = new Vector<Employee>();
        employeeVector.add(sellerJohn);
        employeeVector.add(sellerPaul);

        Vector<Employee> employeeVector1 = new Vector<Employee>();
        employeeVector1.add(accountantAnna);
        employeeVector1.add(developerPeter);

        Stream.concat(employeeVector.stream(), employeeVector1.stream());

    }
}