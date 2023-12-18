import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

    }

    public static boolean checkLetterInWord(char letter, String word) {
        char[] charArray = word.toUpperCase().toCharArray();
        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            charList.add(charArray[i]);

        }
        return charList.contains(letter);
    }
}