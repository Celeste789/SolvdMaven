package task3;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ClassFile {


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
    }


}
