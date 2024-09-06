import javax.swing.*;
import java.awt.*;

/** Clasa BottomPanel reprezinta Panel-ul de jos, ce este divizat in 3 panel-uri
 * Fiecare sub-panel are functionalitatea sa proprie. */
public class BottomPanel extends JPanel {
    /** Sub-panelul din stanga */
    private final LeftBottomPanel leftBottomPanel;
    /** Sub-panelul din dreapta */
    private final RightBottomPanel rightBottomPanel;
    /** Sub-panelul din centru */
    private final ButtonsPanel buttonsPanel;
    private FolderField folderField;

    BottomPanel()
    {
        this.setBackground(Colors.SlideGray);
        this.setPreferredSize(new Dimension(100, 70));
        this.setLayout(new GridLayout(1, 3));

        leftBottomPanel = new LeftBottomPanel(null);
        buttonsPanel = new ButtonsPanel();
        rightBottomPanel = new RightBottomPanel(folderField);

        this.add(leftBottomPanel);
        this.add(buttonsPanel);
        this.add(rightBottomPanel);
    }
    public LeftBottomPanel getLeftPanel()
    {
        return leftBottomPanel;
    }
    public RightBottomPanel getRightBottomPanel()
    {
        return rightBottomPanel;
    }
    public ButtonsPanel getButtonsPanel()
    {
        return buttonsPanel;
    }
}
