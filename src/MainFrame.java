import javax.swing.*;
import java.awt.*;

/** MainFrame
 * Fereastra principala a aplicatiei
 * Obiectele aplicatiei sunt adaugate in componenta JFrame,
 * sau adaugate ca membre ale clasei pentru facilitarea operatiilor cu obiectele membre
 */
public class MainFrame extends JFrame
{
    /** Membru al clasei de tip FolderField */
    private FolderField folderField;
    MainFrame()
    {
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setSize(1500, 900);
        this.setTitle("SlideShow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Functions.centerFrame(this);

        MainPanel mainPanel = new MainPanel();

        BottomPanel bottomPanel = new BottomPanel();
        LeftBottomPanel leftBottomPanel = bottomPanel.getLeftPanel();
        ButtonsPanel buttonsPanel = bottomPanel.getButtonsPanel();

        folderField = new FolderField(leftBottomPanel, buttonsPanel, "./images");
        bottomPanel.getRightBottomPanel().setFolderField(folderField);
        bottomPanel.getRightBottomPanel().setMainFrame(this);

        this.add(mainPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(folderField, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }
    public void setFolderField(FolderField folderField) {this.folderField = folderField;}
    public void removeFolderField() {this.remove(folderField);
    }
}
