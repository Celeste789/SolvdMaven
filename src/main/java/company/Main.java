package company;

import company.exceptions.IllegalPositionException;
import company.exceptions.NegativeAntiquityException;
import company.exceptions.NotClientNorEmployeeException;
import company.exceptions.SameIDException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Logger.sub level.debug");

    public static void main(String[] args) throws IllegalPositionException, IOException, NotClientNorEmployeeException, NegativeAntiquityException, SameIDException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        SimpleFormatter simpleFormatter = new SimpleFormatter();
        FileHandler fileHandler = new FileHandler(new SimpleDateFormat("dd-MM-yyyy hh-mm-ss'.tsv'").format(new Date()) + ".log");
        fileHandler.setFormatter(simpleFormatter);
        LOGGER.addHandler(fileHandler);
    }
}