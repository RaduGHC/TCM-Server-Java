import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    MainPanel()
    {
        this.setBackground(Colors.SlideGray);
        this.setPreferredSize(new Dimension(100, 80));
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel();

        ImageIcon image = new ImageIcon("folder.png");
        ImageIcon img = Functions.resizeIcon(image, 100, 100);

        label.setIcon(img);
        label.setText("Slide Show");
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));

        label.setPreferredSize(new Dimension(420, 360));
        this.add(label, BorderLayout.WEST);
    }
}
