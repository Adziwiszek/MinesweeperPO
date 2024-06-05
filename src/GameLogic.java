import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class GameLogic {
    // TODO: move  to color class in future
    public Color CLICKED_COLOR = new Color(209, 186, 186);

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
        String newText = "";
        if(state > 0){
            newText = ""+state;
        }
        else if(state < 0){
            newText = "O";
            System.out.println("PRZEGRANA!");
        }
        Block curBlock = this.mineField[x][y];
        curBlock.setText(newText);
        curBlock.setBackground(CLICKED_COLOR);
        curBlock.setUncovered(true);
    }

    private void uncoverBlocks(int x, int y){

    }

    private void populateField(int bombs){
        
        for(int i = 1; i < bombs; i ++){
            
        }
    }

    public JPanel getPanel(){
        return this.panel;
    }
}
