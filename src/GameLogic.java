import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameLogic {
    private Block[][] mineField;
    private JPanel panel;
    private boolean gameStarted;
    private int gameStatus;
    private int sizeX;
    private int sizeY;
    private int bombs;
    private int coveredFields;
    private ArrayList<Position> mapPositions;

    public void initGame(int sizeX_, int sizeY_, int bombs_, int diff){
        this.sizeX = sizeX_;
        this.sizeY = sizeY_;
        this.bombs = bombs_;
        this.mineField = new Block[this.sizeY][this.sizeX];
        this.panel = new JPanel(new GridLayout(this.sizeY, this.sizeX));
        this.mapPositions = new ArrayList<>();

        Block block;
        for(int j = 0; j < sizeY; j++){
            for(int i = 0; i < sizeX; i++){
                block = new Block(this, new Position(i, j), diff);
                mapPositions.add(new Position(i, j));
                mineField[j][i] = block;
                panel.add(block);
            }
        }
    }

    public void resetGame(){
        this.gameStarted = false;
        this.coveredFields = sizeX*sizeY - bombs;
        this.gameStatus = 0;
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                mineField[j][i].reset();
            }
        }
    }

    public void onClick(int state, boolean uncovered, Position pos){
        if(!gameStarted){
            gameStarted = true;
            this.populateField(this.bombs, pos);
        }

        if(!this.mineField[pos.y][pos.x].getUncovered()){
            Queue<Position> toUncover = new LinkedList<>();
            toUncover.add(pos);
            while (!toUncover.isEmpty() && this.gameStatus == 0) { 
                Position current = toUncover.poll();
                Block curBlock = this.mineField[current.y][current.x];
                this.onClickAction(curBlock.getState(), current);

                if(curBlock.getState() == 0){
                    for(int x = -1; x <= 1; x++){
                        for(int y = -1; y <= 1; y++){
                            int newY = current.y + y;
                            int newX = current.x + x;
                            if(newX < 0 || newX >= this.sizeX || 
                               newY < 0 || newY >= this.sizeY ||
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

    private void onClickAction(int state, Position pos){
        Block curBlock = this.mineField[pos.y][pos.x];
        if(this.gameStatus == 0 && !curBlock.getFlagged()){         
            if(state < 0){
                this.uncoverAllBombs();
                JOptionPane.showMessageDialog(null, 
                "You Lost!!!");
                this.gameStatus = -1;
            }
            
            if(!curBlock.getUncovered()){                 
                this.coveredFields--; 
                curBlock.uncover();
            }

            if(this.coveredFields <= 0){
                JOptionPane.showMessageDialog(null, 
                "You Won!!!");
                this.unflagAllFields();
                this.uncoverAllBombs();
                this.gameStatus = 1;
            }
        }
    }

    private void populateField(int bombs, Position startingPos){
        ArrayList<Position> availablePos = new ArrayList<>(mapPositions);
        availablePos = this.secureStartingPos(availablePos, startingPos);
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
                        if(checkIfValidPosition(pos, x, y)){
                            mineField[pos.y + y][pos.x + x].incrementState(1);
                        }
                        // int newY = pos.y + y;
                        // int newX = pos.x + x;
                        // if(newX < 0 || newX >= this.sizeX || 
                        //    newY < 0 || newY >= this.sizeY){
                        //     continue;
                        // }
                        // mineField[newY][newX].incrementState(1);    
                    }
                }   
            }
        }
    } 

    /* Takes as an input list of available positions and starting position and
     * returns list of all positions that are viable for having a bomb.
     */
    private ArrayList<Position> secureStartingPos(ArrayList<Position> arr,
                                                Position pos){    
        boolean safePos[][] = new boolean[sizeY][sizeX];
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(checkIfValidPosition(pos, i, j)){
                    safePos[pos.y + j][pos.x + i] = true;
                }
            }
        }

        ArrayList<Position> result = new ArrayList<>();
        for (Position position : arr) {
            if(!safePos[position.y][position.x]){
                result.add(position);
            }
        }
        return result;
    }

    private void uncoverAllBombs(){
        unflagAllFields();
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                if(mineField[j][i].getState() < 0){
                    mineField[j][i].uncover();
                }
            }
        }
    }

    public void unflagAllFields(){
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                this.mineField[j][i].setFlagged(false);
            }
        }
    }

    public void flagField(Position pos, boolean f){
        if(!this.mineField[pos.y][pos.x].getUncovered() || f){
            this.mineField[pos.y][pos.x].setFlagged(!f);
            if(f){
                this.mineField[pos.y][pos.x].cover();
            }
            else this.mineField[pos.y][pos.x].uncover();
        }  
    }

    private boolean checkIfValidPosition(Position pos, int offX, int offY){
        int newY = pos.y + offY;
        int newX = pos.x + offX;
        if(newX < 0 || newX >= this.sizeX ||
           newY < 0 || newY >= this.sizeY){
            return false;
        }
        return true;
    }

    public JPanel getPanel(){
        return this.panel;
    }
}
