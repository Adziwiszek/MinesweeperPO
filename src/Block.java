import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class Block extends JButton {
    private int state = 0;
    private boolean uncovered = false; 
    private boolean flagged = false;
    private Position position;
    private JButton button;
    private GameLogic parent;    

    public Block(GameLogic parent_, Position pos_){
        super();
        this.parent = parent_;
        this.position = pos_;
        this.setBackground(this.parent.UNCLICKED_COLOR);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    parent.flagField(position, flagged);
                }
                if (SwingUtilities.isLeftMouseButton(e)){
                    parent.onClick(state, uncovered, position);
                }
            }
        });
    }

    public void reset(){
        this.setText("");
        this.setBackground(this.parent.UNCLICKED_COLOR);
        this.uncovered = false;
        this.flagged = false;
        this.state = 0;
    }

    public void uncover(){
        if(this.flagged){
            this.setText("☂");
            this.setFont(new Font("Ariel", Font.PLAIN, 20));
        }
        else{
            if(this.state < 0){
                this.setFont(new Font("Ariel", Font.PLAIN, 20));
                this.setText("㋛");
            }
            else{
                this.setText((this.state > 0 ? this.state : "")+"");
            }
        }
        this.setBackground(parent.CLICKED_COLOR);
        this.setUncovered(true);
    }

    public void cover(){
        this.setText("");
        this.setBackground(this.parent.UNCLICKED_COLOR);
        this.setUncovered(false);
    }

    public JButton getButton(){ return this.button; }

    public void setUncovered(boolean unc){ this.uncovered = unc; }

    public boolean getUncovered() { return this.uncovered; }

    public void setState(int state_){ this.state = state_; }

    public int getState(){ return this.state; }

    public void incrementState(int inc){ this.state += inc; }

    public boolean getFlagged() { return this.flagged; }

    public void setFlagged(boolean f) { this.flagged = f; }
}
