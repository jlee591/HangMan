import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
public class WordLibrary {
    private String[] library;
    private Random random;
    public WordLibrary(){

    }

    public String WordLibrary(String fileName) {
        ArrayList<String> k = new ArrayList();

        try {
            FileReader f = new FileReader(fileName);
            BufferedReader br = new BufferedReader(f);
            String line = br.readLine();
            for(line = br.readLine(); line != null; line = br.readLine()) {
                k.add(line);
            }
            this.library = new String[k.size()];
            random = new Random();
            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }
        return k.get(random.nextInt(k.size()));

    }

}
