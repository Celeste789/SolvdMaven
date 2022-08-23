package company.app;

public class StreamingApp extends App implements IStreaming {
    public StreamingApp(String name, String typeOfApp) {
        super(name, typeOfApp);
    }

    private int wantedMovies;
    private int wantedSeries;
    private int maxMovies;
    private int maxSeries;

    @Override
    public double getRealCost() {
        double cost = getBasicCost();
        if (wantedMovies > maxMovies) {
            cost = 500 * (wantedMovies - maxMovies);
        }
        if (wantedSeries > maxSeries) {
            cost = 1000 * (wantedSeries - maxSeries);
        }
        return cost;
    }

    @Override
    public void beginTransmission() {
        System.out.println("What movie/series do yoy want to watch?");
    }

    public StreamingApp newSetMovies(StreamingApp app) {
        this.wantedSeries++;
        return app;
    }

    public int getWantedMovies() {
        return wantedMovies;
    }

    public int getWantedSeries() {
        return wantedSeries;
    }

    public int getMaxMovies() {
        return maxMovies;
    }

    public int getMaxSeries() {
        return maxSeries;
    }

    public StreamingApp(String name, String typeOfApp, int wantedMovies, int wantedSeries) {
        super(name, typeOfApp);
        this.wantedMovies = wantedMovies;
        this.wantedSeries = wantedSeries;
    }
}
