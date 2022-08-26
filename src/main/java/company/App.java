package company;

import company.enums.TypesOfApp;

public class App {
    private String name;
    private String typeOfApp;

    public String getName() {
        return name;
    }

    public App(String name, String typeOfApp) {
        this.name = name;
        this.typeOfApp = typeOfApp;
    }

    public double getCost(TypesOfApp typesOfApp) {
        double basicCost = 0;
        switch (typesOfApp) {
            case BANKING_APP:
                basicCost = 1000;
                break;
            case BUYING_APP:
                basicCost = 1500;
                break;
            case COMMUNICATION_APP:
                basicCost = 2000;
                break;
            case GAMING_APP:
                basicCost = 3000;
                break;
            case LIVES_STREAM_APP:
                basicCost = 3500;
                break;
            default:
                basicCost = 4000;
        }
        return basicCost;
    }


    public String toString() {
        return ("The name of the app is " + name + " and is a " + typeOfApp + " app");
    }
}
