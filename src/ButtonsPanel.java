import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;


/** ButtonsPanel este un obiect JPanel ce contine:
 * Doua butoate de oprire/pornire a derularii automate
 * Doua butoate de inainte/inapoi pentru imagini
 * Un JTextField unde se introduce numarul de secunde delay intre derulari*/
public class ButtonsPanel extends JPanel implements ActionListener {
    /** Butoanele inainte/inapoi */
    private final MoveButton leftButton, rightButton;
    /** Butoanele oprire/pornire */
    private JButton timerButton, stopButton;
    /** JTextField pentru numarul de secunde */
    private final JTextField value;
    /** Obiect de tip Timer pentru lucrul cu Timer-ul derularii imaginilor */
    private Timer timer;

    ButtonsPanel() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension());
        this.setLayout(new BorderLayout());

        leftButton = new MoveButton("leftarrow.png", -1);
        rightButton = new MoveButton("rightarrow.png", 1);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        value = new JTextField("1");
        value.setPreferredSize(new Dimension(70, 60));
        value.setFont(new Font("Consolas", Font.PLAIN, 20));

        JLabel text = new JLabel("<html>Timer<br>in seconds</html>");
        text.setFont(new Font("Consolas", Font.PLAIN, 20));
        text.setForeground(Color.RED);

        timerButton = new JButton("Start!");
        timerButton.addActionListener(this);
        stopButton = new JButton("Stop!");
        stopButton.addActionListener(this);

        panel.add(value);
        panel.add(text);
        panel.add(timerButton);
        panel.add(stopButton);


        this.add(leftButton, BorderLayout.WEST);
        this.add(panel, BorderLayout.CENTER);
        this.add(rightButton, BorderLayout.EAST);
    }

    public MoveButton getLeftButton() {
        return leftButton;
    }

    public MoveButton getRightButton() {
        return rightButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timerButton)
        {
            timer = new Timer();
            TimerTask task = new TimerTask()
            {
                int counter = 0;
                /** reps reprezinta numarul de derulari (inainte) ce se executa la apasarea butonului Start
                 * este egal cu: numarul de imagini incarcate - indexul imaginii selectate - 1 */
                final int reps = leftButton.getFolderField().getLoadImages().getNumberOfImages() - leftButton.getFolderField().getFileList().getSelectedIndex() - 1;
                /** Metoda Move este o metoda specifica clasei MoveButton */
                @Override
                public void run()
                {
                    rightButton.Move();
                    counter++;
                    if (counter >= reps) {
                        timer.cancel();
                    }
                }
            };
            try
            {
                int period = Integer.parseInt(value.getText());
                period *= 1000;
                timer.scheduleAtFixedRate(task, 0, period);
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Timer must be an integer!");
            }
        }
        else if(e.getSource() == stopButton)
        {
            try{
                timer.cancel();
            }
            catch (NullPointerException exception) {}
        }
    }
}

