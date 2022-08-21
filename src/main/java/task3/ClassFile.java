package task3;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassFile {
    private static final Logger LOGGER = Logger.getLogger("Logger.subnivel.debug");

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/text.txt");
        String data = FileUtils.readFileToString(file, "UTF-8");
        data = data.replaceAll("[^a-zA-Z0-9]", " ");
        data = data.toLowerCase();
        data = data.replaceAll("( +)", " ");
        Set<String> set = new HashSet<String>(Arrays.asList(data.split(" ")));
        int size = set.size();
        String string = "The number of words is " + size;
        File file2 = new File("src/main/resources/text2.txt");
        FileUtils.writeStringToFile(file2, string, "UTF-8");

        //Rest of FileUtils methods
        boolean areEquals = FileUtils.contentEquals(file, file2);
        LOGGER.log(Level.INFO, String.valueOf(areEquals));
        File file3 = new File("src/main/resources/text3.txt");
        FileUtils.copyFile(file, file3);
        String files = String.valueOf(FileUtils.getFile("text.txt", "text2.txt", "text3.txt"));
        LOGGER.log(Level.INFO, files);

        //Rest of StringUtils methods
        String stringToCapitalize = "hello world";
        stringToCapitalize = stringToCapitalize.toUpperCase();
        LOGGER.log(Level.INFO, stringToCapitalize);

        String value = String.valueOf(10);


    }


}
