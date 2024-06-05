import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class Block extends JButton {
    private int state = 0;
    private boolean uncovered = false; 
    private boolean flagged = false;
    // private Pair<int, int> positionOnMap;
    private int x;
    private int y;
    private JButton button;
    private GameLogic parent;    

    public Block(GameLogic parent_, int x, int y){
        super(x+";"+y);
        this.parent = parent_;
        this.x = x;
        this.y = y;
        this.button = new JButton(x+";"+y);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.onClick(state, uncovered, x, y);
            }
        });  
    }

    public JButton getButton(){
        return this.button;
    }
}
