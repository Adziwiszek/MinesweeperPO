import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JPanel;

public class GameLogic {
    // TODO: move  to color class in future
    public Color CLICKED_COLOR = new Color(209, 186, 186);

    private Block[][] mineField;
    private JPanel panel;
    private boolean gameStarted = false;
    private int gameStatus;
    private int size = 8;
    private int bombs = 8;

    private ArrayList<Position> mapPositions;

    public GameLogic(){
        
        mineField = new Block[size][size];
        panel = new JPanel(new GridLayout(size, size));
        mapPositions = new ArrayList<>();

        initGame();

        this.populateField(8);
    }    

    private void initGame(){
        Block block;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                block = new Block(this, new Position(i, j));
                mapPositions.add(new Position(i, j));
                // coordinates - x, y
                mineField[j][i] = block;
                panel.add(block);
                
            }
        }
    }

    public void onClick(int state, boolean uncovered, Position pos){
        if(!this.mineField[pos.y][pos.x].getUncovered()){
            Queue<Position> toUncover = new LinkedList<>();
            toUncover.add(pos);
            while (!toUncover.isEmpty()) { 
                Position current = toUncover.poll();
                Block curBlock = this.mineField[current.y][current.x];
                this.onClickAction(curBlock.getState(), true, current);

                if(curBlock.getState() == 0){
                    for(int x = -1; x <= 1; x++){
                        for(int y = -1; y <= 1; y++){
                            int newY = current.y + y;
                            int newX = current.x + x;
                            if(newX < 0 || newX >= this.size || 
                               newY < 0 || newY >= this.size ||
                               (x == 0 && y == 0)){
                                continue;
                            }
                            if(!this.mineField[newY][newX].getUncovered()){
                                toUncover.offer(new Position(newX, newY));
                            }
                        }   
                    }
                }
            }
        }
    }

    private void onClickAction(int state, boolean uncovered, Position pos)
    {
        System.out.println("CLICKED BLOCK: "+pos.x+","+pos.y+
            " | uncovered = "+uncovered + " | state = "+state);
        String newText = "";
        if(state > 0){
            newText = ""+state;
        }
        else if(state < 0){
            newText = "O";
            System.out.println("PRZEGRANA!");
        }
        Block curBlock = this.mineField[pos.y][pos.x];
        curBlock.setText(newText);
        curBlock.setBackground(CLICKED_COLOR);
        curBlock.setUncovered(true);
    }

    private void populateField(int bombs){
        ArrayList<Position> availablePos = new ArrayList<>(mapPositions);
        Random generator = new Random();
        for(int k = 0; k < bombs; k ++){
            int index = generator.nextInt(availablePos.size() - 1);
            Position pos = availablePos.get(index);
            availablePos.remove(index);

            for(int x = -1; x <= 1; x++){
                for(int y = -1; y <= 1; y++){
                    if(x == 0 && y == 0){
                        mineField[pos.y][pos.x].setState(-bombs);
                    }
                    else {
                        int newY = pos.y + y;
                        int newX = pos.x + x;
                        if(newX < 0 || newX >= this.size || 
                           newY < 0 || newY >= this.size){
                            continue;
                        }
                        mineField[newY][newX].incrementState(1);    
                    }
                }   
            }
        }
    }

    public JPanel getPanel(){
        return this.panel;
    }
}
