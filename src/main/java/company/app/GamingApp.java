package company.app;

import company.exceptions.GamingAppInvalidBooleansException;

public class GamingApp extends App implements IOnline {
    private boolean isMiniGame;
    private boolean isTwoDimensions;

    private boolean isThreeDimensions;


    public boolean isMiniGame() {
        return isMiniGame;
    }

    public boolean isTwoDimensions() {
        return isTwoDimensions;
    }

    public boolean isThreeDimensions() {
        return isThreeDimensions;
    }


    public GamingApp(String name, String typeOfApp, boolean isMiniGame, boolean isTwoDimensions, boolean isThreeDimensions) throws GamingAppInvalidBooleansException {
        super(name, typeOfApp);
        this.isMiniGame = isMiniGame;
        this.isTwoDimensions = isTwoDimensions;
        this.isThreeDimensions = isThreeDimensions;

        if ((isMiniGame && isTwoDimensions) || (isMiniGame && isThreeDimensions) || (isTwoDimensions && isThreeDimensions)) {
            throw new GamingAppInvalidBooleansException("You can't have two trues in the same app");
        }

        if (!isMiniGame && !isTwoDimensions && !isThreeDimensions) {
            throw new GamingAppInvalidBooleansException("You can't have 3 falses and no true");
        }

    }


    @Override
    public double getRealCost() {
        double basicCost = getBasicCost();
        if (isTwoDimensions) {
            basicCost += 1500;
        } else if (isThreeDimensions) {
            basicCost += 2500;
        }
        return basicCost;
    }

    @Override
    public void connect() {
        System.out.println("Who do you want to play wth?");
    }

    public void validateBools() throws GamingAppInvalidBooleansException {
        if (isMiniGame && (isTwoDimensions || isThreeDimensions)) {
            throw new GamingAppInvalidBooleansException("You can't have two trues in the same app");
        }
        if (!isThreeDimensions && !isTwoDimensions && !isMiniGame) {
            throw new GamingAppInvalidBooleansException("You can't have three falses in a same app");
        }
    }

}
