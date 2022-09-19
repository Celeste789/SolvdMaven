package company;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Logger;

public class Reflection {
    private static final Logger LOGGER = Logger.getLogger("Logger.reflection");

    public static void main(String[] args) {

        Class<App> reflectedClass = App.class;
        String name = reflectedClass.getName();
        LOGGER.info(name);

        Method[] methods = reflectedClass.getDeclaredMethods();
        for (Method method : methods) {
            LOGGER.info(method.getName());
        }

        int classModifiers = reflectedClass.getModifiers();
        LOGGER.info(String.valueOf(Modifier.isPrivate(classModifiers)));

        Constructor[] constructors = reflectedClass.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            LOGGER.info(constructor.getName());
        }

        Constructor constructor = null;
        App app = null;

        try {
            constructor = reflectedClass.getConstructor(String.class, String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        String appName = "Game";
        String typeOfApp = "Gaming App";

        try {
            app = (App) constructor.newInstance(appName, typeOfApp);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        String methodName = app.getName();

        LOGGER.info(methodName);
    }

}
