import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** Panel-ul unde se afiseaza imaginile */
public class ViewField extends JPanel {
    private final LeftBottomPanel leftBottomPanel;
    private BufferedImage image;
    private final DisplayLabel errorLabel;
    ViewField(LeftBottomPanel leftBottomPanel)
    {
        this.setBackground(Color.WHITE);
        this.leftBottomPanel = leftBottomPanel;
        this.setSize(new Dimension(730,700));

        this.errorLabel = new DisplayLabel("This file cannot be displayed", "loading.png");
        this.errorLabel.setBounds(0, 230, 275, 200);
        this.add(this.errorLabel);
    }
    /** Metoda ce actualizeaza imaginea din Panel */
    public void updateImage(String path) {
        try {
            File file = new File(path);
            image = ImageIO.read(file);
            String name = file.getName();
            String detail = "<html>" + name + "<br>" + image.getWidth() + "px " + image.getHeight() + "px</html>";
            leftBottomPanel.changeText(detail);
            image = Functions.resizeImage(image, 1000, 710);
            this.errorLabel.setVisible(false);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            repaint();
            this.errorLabel.setVisible(true);
            leftBottomPanel.changeText("N/A");
        }
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
