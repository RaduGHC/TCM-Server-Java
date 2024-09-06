import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/** LoadImages este clasa (fara interfata proprie) care incarca/paseaza directorul de imagini
 * de la server catre clasa FolderField */
public class LoadImages extends SwingWorker<DefaultListModel<File>, Void> {
    private final FolderField folderField;
    private final ButtonsPanel buttonsPanel;
    private DisplayLabel loadingLabel;
    private JList<File> fileList;
    private int numberOfImages;
    private String path;

    LoadImages(FolderField folderField, ButtonsPanel buttonsPanel, String path) {
        numberOfImages = 0;
        this.folderField = folderField;
        this.buttonsPanel = buttonsPanel;
        this.path = path;
    }

    /** Metoda de se foloseste de un Thread pentru a incarca directorul */
    protected DefaultListModel<File> doInBackground() {
        DefaultListModel<File> listModel = new DefaultListModel<>();

        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            /** Stabileste lista de imagini de la server */
            out.writeObject(path);
            out.flush();

            /** Lista rezultata */
            Object receivedObject = in.readObject();
            if (receivedObject instanceof File[] files)
            {
                loadingLabel = new DisplayLabel("Loading images!", "loading.png");
                loadingLabel.setVisible(true);
                folderField.add(loadingLabel);
                folderField.revalidate();
                folderField.repaint();

                /** Iterarea in imagini */
                for (File file : files) {
                    if (isImageFile(file)) {
                        numberOfImages++;
                        listModel.addElement(file);
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listModel;
    }
    /** Odata terminata incarcarea, se trece la construirea listei in JScrollPane,
     * si actualizarea folderField */

    @Override
    protected void done() {
        try {
            DefaultListModel<File> listModel = get();
            folderField.remove(loadingLabel);
            folderField.revalidate();
            folderField.repaint();

            if (listModel.isEmpty()) {
                JLabel noFilesLabel = new JLabel("No files found");
                folderField.add(noFilesLabel, BorderLayout.NORTH);
            } else {
                fileList = new JList<>(listModel);
                fileList.setCellRenderer(new FileCellRenderer());
                fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                fileList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            File selectedFile = fileList.getSelectedValue();
                            if (selectedFile != null) {
                                folderField.getViewField().updateImage(selectedFile.getAbsolutePath());
                            }
                        }
                    }
                });

                JScrollPane scrollPane = new JScrollPane(fileList);
                scrollPane.setPreferredSize(new Dimension(480, 0));

                folderField.setFileList(fileList);
                folderField.setScrollPane(scrollPane);
                folderField.add(scrollPane, BorderLayout.EAST);
            }

            buttonsPanel.getLeftButton().setFolderField(folderField);
            buttonsPanel.getRightButton().setFolderField(folderField);
            folderField.revalidate();
            folderField.repaint();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /** Metoda ce asigura ca doar fisierele de tip imagine (cele enumerate in inageExtensions)
     * sunt luate in considerare */
    private boolean isImageFile(File file) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};
        for (String ext : imageExtensions) {
            if (file.getName().toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /** Metoda ce ajuta la construirea unei interfete mai interesante a listei de imagini */
    private class FileCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            File file = (File) value;
            label.setText(file.getName());

            try {
                BufferedImage img = ImageIO.read(file);
                ImageIcon thumbnail = new ImageIcon(Functions.resizeImage(img, 100, 100));
                label.setIcon(thumbnail);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return label;
        }
    }

    public int getNumberOfImages() {
        return numberOfImages;
    }
}
