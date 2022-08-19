package company.app;

public class LiveStreamApp extends App implements IStreaming, IOnline{
    public LiveStreamApp(String name, String typeOfApp) {
        super(name, typeOfApp);
    }

    private int wantedAmountOfUSersConnecting;
    private int maxAmountOfUSersConnecting;

    @Override
    public void connect() {
        System.out.println("Do you want to enter this livestream?");
    }

    @Override
    public void beginTransmission() {
        System.out.println("Do you want to begin a livestream?");
    }


    @Override
    public double getRealCost() {
        double cost = getBasicCost();
        if (wantedAmountOfUSersConnecting > maxAmountOfUSersConnecting){
            cost += 500 * (maxAmountOfUSersConnecting - wantedAmountOfUSersConnecting);
        }
        return cost;
    }
}
