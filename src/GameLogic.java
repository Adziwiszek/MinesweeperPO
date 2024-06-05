import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class GameLogic {
    private Block[][] mineField;
    private JPanel panel;
    private boolean gameStarted;
    private int gameStatus;

    public GameLogic(){
        int size = 8;
        mineField = new Block[size][size];
        panel = new JPanel(new GridLayout(size, size));
        
        Block block;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                block = new Block(this, j, i);
                // coordinates - x, y
                mineField[j][i] = block;
                panel.add(block);
            }
        }
    }    

    public void onClick(int state, boolean uncovered, int x, int y){
        System.out.println("CLICKED BLOCK: "+x+","+y+
            " | uncovered = "+uncovered + " | state = "+state);
        this.mineField[x][y].setName("Click!");;
    }

    public JPanel getPanel(){
        return this.panel;
    }
}
