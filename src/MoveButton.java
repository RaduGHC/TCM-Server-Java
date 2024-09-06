import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** MoveButton este clasa de defineste cele 2 butoate de inainte/inapoi
 * Directia de miscare este data de membrul direction
 * 1 pentru inainte, -1 pentru inapoi*/
public class MoveButton extends JButton implements ActionListener {
    private FolderField folderField;
    private final int direction;
    MoveButton(String filename, int direction)
    {
        this.direction = direction;
        ImageIcon image = new ImageIcon(filename);
        ImageIcon img = Functions.resizeIcon(image, 40, 40);
        this.setIcon(img);
        this.setPreferredSize(new Dimension(70, 50));

        this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent actionEvent)
    {
        try{
            if(actionEvent.getSource() == this) {
                Move();
            }
        }
        catch (IndexOutOfBoundsException e){

        }
    }
    public void Move()
    {
        int selectedIndex = folderField.getFileList().getSelectedIndex();
        if (selectedIndex < folderField.getFileList().getModel().getSize() + direction) {
            folderField.getFileList().setSelectedIndex(selectedIndex + direction);
            folderField.getFileList().ensureIndexIsVisible(selectedIndex + direction);
        }
    }
    public void setFolderField(FolderField folderField) {this.folderField = folderField;}
    public FolderField getFolderField() {return folderField;}
}
