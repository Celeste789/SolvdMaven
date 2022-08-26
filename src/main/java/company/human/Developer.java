package company.human;

import company.enums.DepartmentName;
import company.exceptions.IncorrectSendMessageException;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Developer extends Employee {
    private final Logger LOGGER = Logger.getLogger("Logger.warning");

    public DepartmentName departmentName = DepartmentName.DEVELOPER;

    public static double basicSalary = 300;
    private String githubLink;


    public Developer(int antiquity, String name, int id, double salary, String githubLink) throws NegativeAntiquityException, NotClientNorEmployeeException {
        super(antiquity, name, id, salary, "Developers");
        this.githubLink = githubLink;
    }

    @Override
    public void validateMessageReceiver(Human receiver) throws IncorrectSendMessageException {
        if (!(receiver.getClass().getName().equals("company.human.Accountant") || receiver.getClass().getName().equals("company.human.Developer"))) {
            throw new IncorrectSendMessageException();
        }
    }


    @Override
    public String sendMessage(Human receiver, String message) throws IncorrectSendMessageException {
        try {
            this.validateMessageReceiver(receiver);
        } catch (IncorrectSendMessageException e) {
            LOGGER.log(Level.WARNING, "You can't send message to this person");
        }
        return receiver.receiveMessage(message);
    }

    public String code() {
        return ("My code is in this Github link " + githubLink);
    }
}
