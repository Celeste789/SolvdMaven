package company;

import company.exceptions.*;
import company.human.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Logger.sub level.debug");

    public static void main(String[] args) throws IllegalPositionException, IOException, NotClientNorEmployeeException, NegativeAntiquityException, SameIDException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        SimpleFormatter simpleFormatter = new SimpleFormatter();
        FileHandler fileHandler = new FileHandler(new SimpleDateFormat("dd-MM-yyyy hh-mm-ss'.tsv'").format(new Date()) + ".log");
        fileHandler.setFormatter(simpleFormatter);
        LOGGER.addHandler(fileHandler);


        Company company = new Company("Company");
        Client client1 = new Client("Client1", 1, 0);
        Client client2 = new Client("Client2", 2, 5);
        Accountant accountant1 = new Accountant("Accountant1", 1, 3);
        Accountant accountant2 = new Accountant("Accountant2", 2, 2);
        Developer developer = new Developer(3, "Developer", 3, "developer.com");
        Developer developer2 = new Developer(1, "Developer2", 4, "developer2.com");
        HumanResources humanResources = new HumanResources(6, "Human Resources", 5);
        HumanResources humanResources2 = new HumanResources(5, "Human Resources", 6);
        Seller seller = new Seller("Seller", 7, 8);
        Seller seller2 = new Seller("Seller2", 8, 10);
        App app = new App("App1", "Gaming App");
        App app2 = new App("App2", "Streaming App");

        int sellerVacation = Accountant.calculateDaysOfVacation(seller, (() -> seller.getVipClients().mySize()));
        try {
            seller2.sendMessage(developer2, "Hi");
        } catch (IncorrectSendMessageException e) {
            LOGGER.log(Level.WARNING, "You can't send a message to this person");
        }
        LOGGER.log(Level.INFO, client1.receiveApp(app));
        humanResources2.addEmployeeToWorkingMap(seller, true);
        humanResources2.addEmployeeToWorkingMap(developer2, false);


        humanResources2.beginLeave(seller);
        seller.addAClient(client1);
        for (Client client : seller.getClients()) {
            LOGGER.log(Level.INFO, "This are seller's clients " + client.getNAME());
        }
        LOGGER.log(Level.INFO, "The cost of " + app2.getName() + " is " + app2.getCost());
        LOGGER.log(Level.INFO, "The vacation days of " + seller.getNAME() + " is " + sellerVacation);

        int timeOfLeaveDeveloper = humanResources.timeOfLeave(developer, true, (int leave) -> developer.getBasicAmountOfVacations() + 5);
        LOGGER.log(Level.INFO, "The time of leave for " + developer.getNAME() + " is " + timeOfLeaveDeveloper);


    }
}