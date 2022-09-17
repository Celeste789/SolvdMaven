package company.human;

import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;

import java.util.logging.Logger;

public class Developer extends Employee {
    private final Logger LOGGER = Logger.getLogger("Logger.warning");

    public static double basicSalary = 300;
    private String githubLink;


    public Developer(int antiquity, String name, int id, double salary, String githubLink) throws NegativeAntiquityException, NotClientNorEmployeeException {
        super(antiquity, name, id, salary, "Developers");
        this.githubLink = githubLink;
    }


    public String code() {
        return ("My code is in this Github link " + githubLink);
    }

    @Override
    public boolean validateMessageReceiver(Human receiver) {
        return receiver.getClass().getName().equals("company.Human.HumanResources") || receiver.getClass().getName().equals("company.Human.Accountant");
    }
}
