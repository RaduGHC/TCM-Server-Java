import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/** Metoda Main a aplicatiei
 * Stabileste conectiunea la serverul TCP */
public class Main {
    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
        int serverPort = 12345;

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Server started. Port: " + serverPort);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    String folderPath = (String) in.readObject();

                    File[] files = getImageFiles(folderPath);

                    out.writeObject(files);
                    out.flush();

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File[] getImageFiles(String folderPath) {
        File folder = new File(folderPath);
        List<File> imageFiles = new ArrayList<>();

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (isImageFile(file)) {
                        imageFiles.add(file);
                    }
                }
            }
        }

        return imageFiles.toArray(new File[0]);
    }
    private static boolean isImageFile(File file) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};
        for (String ext : imageExtensions) {
            if (file.getName().toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}