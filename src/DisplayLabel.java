import javax.swing.*;
import java.awt.*;

/** Label ce se afiseaza in timp ce directorul de imagini se incarca. */
public class DisplayLabel extends JLabel
{
    DisplayLabel(String message, String filename)
    {
        ImageIcon errorImage = new ImageIcon(filename);
        ImageIcon img = Functions.resizeIcon(errorImage, 100, 100);
        this.setIcon(img);
        this.setText(message);
        this.setHorizontalTextPosition(JLabel.LEFT);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Times New Roman", Font.BOLD, 30));
        this.setVisible(false);
    }
}
