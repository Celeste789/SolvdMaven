package company.app;

public abstract class App {
    private String name;
    private String typeOfApp;
    private double basicCost;

    public String getName() {
        return name;
    }

    public String getTypeOfApp() {
        return typeOfApp;
    }

    public App(String name, String typeOfApp) {
        this.name = name;
        this.typeOfApp = typeOfApp;
    }

    public double getBasicCost() {
        typeOfApp = typeOfApp.toLowerCase();
        if (typeOfApp.equals("gaming") || typeOfApp.equals("streaming")) {
            basicCost = 5000;
        } else if (typeOfApp.equals("social media") || typeOfApp.equals("chatting") || typeOfApp.equals("dating")) {
            basicCost = 4000;
        } else {
            basicCost = 3000;
        }
        return basicCost;
    }

    public abstract double getRealCost();

    public String toString() {
        return ("The name of the app is " + name + " and is a " + typeOfApp + " app");
    }
}
