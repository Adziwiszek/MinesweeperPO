import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;


public class Block extends JButton {
    // TODO: maybe move to my own color class in future
    public Color UNCLICKED_COLOR = new Color(115, 88, 88);

    private int state = 0;
    private boolean uncovered = false; 
    private boolean flagged = false;
    // private Pair<int, int> positionOnMap;
    private int x;
    private int y;
    private JButton button;
    private GameLogic parent;    

    public Block(GameLogic parent_, int x_, int y_){
        super();
        this.parent = parent_;
        this.x = x_;
        this.y = y_;
        this.setBackground(UNCLICKED_COLOR);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.onClick(state, uncovered, x, y);
            }
        });  
    }

    public JButton getButton(){
        return this.button;
    }

    public void setUncovered(boolean unc){
        this.uncovered = unc;
    }

    public void setState(int state_){
        this.state = state_;
    }
}
