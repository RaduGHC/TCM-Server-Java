import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RightBottomPanel extends JPanel implements ActionListener {
    private SelectFolderButton selectFolderButton;
    private JLabel message;

    private FolderField folderField;
    private MainFrame mainFrame;
    RightBottomPanel(FolderField folderField) {
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(30, 70));
        this.setLayout(new BorderLayout());
        this.folderField = folderField;

        selectFolderButton = new SelectFolderButton();
        selectFolderButton.addActionListener(this);
        this.add(selectFolderButton, BorderLayout.EAST);

        message = new JLabel("Images");
        message.setForeground(Colors.SlideDarkRed);
        message.setFont(new Font("Cooper Black", Font.PLAIN, 25));
        this.add(message, BorderLayout.WEST);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == selectFolderButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
                File selectedDirectory = fileChooser.getSelectedFile();
                String selectedPath = selectedDirectory.getAbsolutePath();
                mainFrame.removeFolderField();
                message.setText(selectedDirectory.getName());
                folderField = new FolderField(folderField.getLeftBottomPanel(), folderField.getButtonsPanel(), selectedPath);
                mainFrame.setFolderField(folderField);
                mainFrame.add(folderField, BorderLayout.CENTER);
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        }
    }
    public void setFolderField(FolderField folderField) {this.folderField = folderField;}
    public void setMainFrame(MainFrame mainFrame) {this.mainFrame = mainFrame;}
}
