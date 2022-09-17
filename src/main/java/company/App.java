package company;

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

    public double getCost() {
        String typesOfApp = typeOfApp;
        double basicCost = 0;
        switch (typesOfApp) {
            case "Banking App":
                basicCost = 1000;
                break;
            case "Buying App":
                basicCost = 1500;
                break;
            case "Communication App":
                basicCost = 2000;
                break;
            case "Gaming App":
                basicCost = 3000;
                break;
            case "Lives stream App":
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
