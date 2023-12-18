import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ImageHandler extends JComponent{

    public ImageHandler() {
    }

    public static void main(String[] args) throws IOException {
        ImageHandler f = new ImageHandler();
        f.ImageDisplay(1);
    }
    public JLabel ImageDisplay(int count) throws IOException {
        File file = null;
        if (count == 1) {
            file = new File("guess 1.jpg");
        } if (count == 2) {
            file = new File("guess 2.jpg");
        } if (count == 3) {
            file = new File("guess 3.jpg");
        } if (count == 4) {
            file = new File("guess 4.jpg");
        } if (count == 5) {
            file = new File("guess 5.jpg");
        }
        BufferedImage bi = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bi);
        JLabel jLabel = new JLabel();
        jLabel.setSize(500, 350);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }


}
