import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LeftBottomPanel extends JPanel
{
    JLabel info;
    LeftBottomPanel(String detail)
    {
        this.setBackground(Color.GRAY);
        this.setLayout(new BorderLayout());

        info = new JLabel("No picture chosen!");
        info.setForeground(Colors.SlideDarkRed);
        info.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.add(info, BorderLayout.CENTER);

    }
    public void changeText(String detail)
    {
        String details;
        details = Objects.requireNonNullElse(detail, "No picture chosen!");
        info.setText(details);
    }
}
