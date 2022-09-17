package company.human;

import company.App;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;

public class Client extends Human {

    public Client(String name, int id, int antiquity) throws NegativeAntiquityException, NotClientNorEmployeeException {
        super(id, name, antiquity, true, false);

    }


    public String receiveApp(App app) {
        return ("I received the app " + app.getName());
    }

    @Override
    public boolean validateMessageReceiver(Human receiver) {
        return receiver.getClass().getName().equals("company.Human");
    }
}
