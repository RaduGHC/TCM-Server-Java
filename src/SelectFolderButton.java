import javax.swing.*;
import java.awt.*;
/** Clasa butonului Select Folder din cadrul aplicatiei */
public class SelectFolderButton extends JButton {
    SelectFolderButton() {
        this.setText("Select Folder");
        this.setBackground(Colors.SlidePurple);
        this.setFont(new Font("Comis Sans MS", Font.PLAIN, 25));
        this.setFocusPainted(false);
    }
}
