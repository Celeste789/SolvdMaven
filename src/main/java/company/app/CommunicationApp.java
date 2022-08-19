package company.app;

public class CommunicationApp extends App {
    public CommunicationApp(String name, String typeOfApp) {
        super(name, typeOfApp);
    }

    @Override
    public double getRealCost() {
        return 0;
    }

    private boolean supportsAudio;
    private boolean supportsPicture;
    private boolean supportsVideo;

    public boolean isSupportsAudio() {
        return supportsAudio;
    }

    public boolean isSupportsPicture() {
        return supportsPicture;
    }

    public boolean isSupportsVideo() {
        return supportsVideo;
    }
}
