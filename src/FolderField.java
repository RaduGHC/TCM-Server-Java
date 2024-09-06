import javax.swing.*;
import java.awt.*;
import java.io.File;

/** Clasa FolderField este Panelul unde vor fi afisate lista de imagini si imaginea selectata
 * Este punctul de plecare pentru operatiile din aplicatie.
 * Membrii clasei sunt folositi drept referinte pentru operatiile dintre obiecte din aplicatie.
 */

public class FolderField extends JPanel {
    private final ViewField viewField;
    private LoadImages loadImages;
    private JList<File> fileList;
    private JScrollPane scrollPane;
    private ButtonsPanel buttonsPanel;
    private LeftBottomPanel leftBottomPanel;

    /** Constructorul clasei
     * String path reprezinta calea catre directorul selectat
     * Acesta se schimba la selectarea prin butonul SelectFolderButton
     * Directorul Default (selectat la deschiderea aplicatiei) este images*/
    public FolderField(LeftBottomPanel leftBottomPanel, ButtonsPanel buttonsPanel, String path)
    {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1300, 700));
        this.buttonsPanel = buttonsPanel;
        this.leftBottomPanel = leftBottomPanel;
        /** LoadImages este clasa ce contine operatia de incarcare a directorului de imagini.*/
        Runnable loadImagesTask = () -> {
            loadImages = new LoadImages(FolderField.this, buttonsPanel, path);
            loadImages.execute();
        };
        Thread loadImagesThread = new Thread(loadImagesTask);
        loadImagesThread.start();
        /** ViewField este un obiect JPanel unde sunt afisate imaginile. */
        viewField = new ViewField(leftBottomPanel);
        viewField.setPreferredSize(new Dimension(1000, 0));
        this.add(viewField, BorderLayout.WEST);

    }
    public void setFileList(JList<File> list) {
        fileList = list;
    }

    public JList<File> getFileList() {
        return fileList;
    }

    public ViewField getViewField() {
        return viewField;
    }
    public void setScrollPane(JScrollPane scrollpane)
    {
        scrollPane = scrollpane;
    }
    public LoadImages getLoadImages() {return loadImages;}
    public ButtonsPanel getButtonsPanel() {return buttonsPanel;}
    public LeftBottomPanel getLeftBottomPanel() {return leftBottomPanel;}
}