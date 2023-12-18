import javax.crypto.Cipher;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class Keyboard extends JComponent {
    static int count = 1;
    static String fileName = "";
    static WordLibrary wb = new WordLibrary();
    static String answer = "";
    static JPanel screen;
    static JTextField textField0 = new JTextField();
    static JTextField textField1 = new JTextField();
    static JTextField textField2 = new JTextField();
    static JTextField textField3 = new JTextField();
    static JTextField textField4 = new JTextField();
    static JTextField textField5 = new JTextField();
    static JTextField textField6 = new JTextField();
    static JTextField[] textFields = {textField0, textField1, textField2, textField3, textField4, textField5, textField6};

    static ArrayList<String> incorrectGuess = new ArrayList<String>();
    static ArrayList<String> correctGuess = new ArrayList<>();
    static ArrayList<JButton> buttonList = new ArrayList<>();

    static JFrame frame = new JFrame();
    static JButton newGame;
    private static void addButton(JPanel jp, String thing) {
        JButton button = new JButton(thing);
        button.setActionCommand(thing);
        buttonList.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonChr = e.getActionCommand();
                Character chr = buttonChr.charAt(0);
                if (!Main.checkLetterInWord(chr, answer)) {

                    incorrectGuess.add(String.valueOf(chr));
                    count += 1;
                    for (String incorrect : incorrectGuess) {
                        for (JButton bt : buttonList) {
                            if (bt.getActionCommand().equals(incorrect)) {
                                bt.setBackground(Color.red);
                            }
                        }
                    }
                    if (!(count < 6)) {
                        for (int i = 0; i < answer.length(); i++) {
                            textFields[i].setText(String.valueOf(answer.charAt(i)).toUpperCase());

                        }
                    }
                } else {
                    correctGuess.add(String.valueOf(chr));
                    for (String incorrect : incorrectGuess) {
                        for (JButton bt : buttonList) {
                            if (bt.getActionCommand().equals(incorrect)) {
                                bt.setBackground(Color.red);
                            }
                        }
                    }
                    for (int i = 0; i < answer.length(); i++) {
                        char newChar = buttonChr.toLowerCase().charAt(0);
                        if (newChar == answer.charAt(i)) {
                            textFields[i].setText(String.valueOf(answer.charAt(i)).toUpperCase());
                        }
                    }
                }
                frame.dispose();
                createGUI();

            }
        });
        jp.add(button);
    }
    static String[] list = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static JPanel keyboard() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3,8));
        for (String character : list) {
            addButton(jp, character);
        }
        return jp;
    }

    public static void createGUI() {
        JLabel image = new JLabel();
        try {
            ImageHandler ih = new ImageHandler();
            image = ih.ImageDisplay(count);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "OOPSIE DAISY! SEEMS LIKE YOU ARE OUT OF TRIES!",
                    "OUT OF TRIES", JOptionPane.ERROR_MESSAGE);

        }
        JButton newGame = new JButton("New Game");
        newGame.setActionCommand("newGame");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incorrectGuess = new ArrayList<>();
                correctGuess = new ArrayList<>();
                count = 1;
                for (JTextField tf : textFields) {
                    textField0.setText("");
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                }
                answer = wb.WordLibrary(fileName);
            }
        });
        frame = new JFrame("Keyboard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel game = new JPanel();
        game.add(newGame);
        JPanel jp =  keyboard();
        frame.add(jp, BorderLayout.SOUTH);
        frame.add(game, BorderLayout.EAST);
        screen = new JPanel();
        screen.setLayout(new GridLayout(0,answer.length()));
        for (int i = 0; i < answer.length(); i++) {
            screen.add(textFields[i]);
        }
        frame.add(screen);
        frame.add(image, BorderLayout.NORTH);
        frame.pack();
        for (String incorrect : incorrectGuess) {
            for (JButton bt : buttonList) {
                if (bt.getActionCommand().equals(incorrect)) {
                    bt.setBackground(Color.red);
                }
            }
        }
        for (String correct : correctGuess) {
            for (JButton bt : buttonList) {
                if (bt.getActionCommand().equals(correct)) {
                    bt.setBackground(Color.green);
                }
            }
        }
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getHomePage();
            }
        });
    }

    public static void getHomePage() {
        JPanel imagePanel = new JPanel();
        JFrame homePage = new JFrame();
        try{
            File file = new File("guess 5.jpg");
            BufferedImage bi = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bi);
            JLabel jLabel = new JLabel();
            jLabel.setSize(50, 200);
            jLabel.setIcon(imageIcon);
            imagePanel.add(jLabel);
            homePage.add(imagePanel, BorderLayout.CENTER);
        } catch(Exception e){
            e.printStackTrace();
        }
        homePage.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        homePage.setSize(500, 500);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(3,8));
        JLabel titleLabel = new JLabel("Welcome to HANGMAN");
        titlePanel.add(titleLabel);
        homePage.add(titlePanel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,8));
        JButton easyButton = new JButton("EASY");
        easyButton.setActionCommand("Easy");
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileName = "Easy.txt";
                answer = wb.WordLibrary("Easy.txt");
                homePage.dispose();
                createGUI();

            }
        });
        buttonPanel.add(easyButton);
        JButton mediumButton = new JButton("MEDIUM");
        mediumButton.setActionCommand("Medium");
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileName = "Medium.txt";
                answer = wb.WordLibrary("Medium.txt");
                homePage.dispose();
                createGUI();

            }
        });
        buttonPanel.add(mediumButton);
        JButton hardButton = new JButton("HARD");
        hardButton.setActionCommand("Hard");
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileName = "Hard.txt";
                answer = wb.WordLibrary("Hard.txt");
                homePage.dispose();
                createGUI();

            }
        });
        buttonPanel.add(hardButton);
        homePage.add(buttonPanel, BorderLayout.SOUTH);
        homePage.setVisible(true);

    }
}
