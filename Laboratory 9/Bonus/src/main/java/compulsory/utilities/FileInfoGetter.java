package compulsory.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInfoGetter {
    public static Boolean get() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/initFile.dat"));
        String text = bufferedReader.readLine();
        return text.equals("USE_JDBC=true");
    }
}
