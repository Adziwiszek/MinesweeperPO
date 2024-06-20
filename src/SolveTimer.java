import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/* Class that is responsible for timing and displaying users time in game. */
public class SolveTimer implements ActionListener {
    private final Timer timer;
    private final JLabel timerLabel;
    private int timeElapsed = 0;

    public SolveTimer(){
        timer = new Timer(1000, this);
        timerLabel = new JLabel("0");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeElapsed++;
        timerLabel.setText("" + timeElapsed);
    }

    public void startTimer(){
        timer.start();
    }

    public void stopTimer(){
        timer.stop();
    }

    public void resetTimer(){
        stopTimer();
        timeElapsed = 0;
        timerLabel.setText("0");
    }

    public int getTime(){ return timeElapsed; }
    public JLabel getLabel() { return timerLabel; }
}
