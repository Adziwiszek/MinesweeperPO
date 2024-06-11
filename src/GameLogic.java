import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class GameLogic {
    // TODO: move  to color class in future
    public Color CLICKED_COLOR = new Color(209, 186, 186);

    private Block[][] mineField;
    private JPanel panel;
    private boolean gameStarted = false;
    private int gameStatus;
    private int size = 8;
    private int bombs = 8;
    private int coveredFields;

    private ArrayList<Position> mapPositions;

    public GameLogic(){
        mineField = new Block[size][size];
        panel = new JPanel(new GridLayout(size, size));
        mapPositions = new ArrayList<>();
        this.coveredFields = size*size - bombs;
        initGame();
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

    public void resetGame(){
        this.gameStarted = false;
        this.coveredFields = size*size - bombs;
        this.gameStatus = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                mineField[j][i].reset();
            }
        }
    }

    public void onClick(int state, boolean uncovered, Position pos){
        if(!gameStarted){
            System.out.println("STARTING");
            gameStarted = true;
            this.populateField(8, pos);
        }

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

    private void onClickAction(int state, boolean uncovered, Position pos){
        // System.out.println("CLICKED BLOCK: "+pos.x+","+pos.y+
        //     " | uncovered = "+uncovered + " | state = "+state);
        Block curBlock = this.mineField[pos.y][pos.x];
        if(this.gameStatus == 0 || !curBlock.getFlagged()){         
            if(state < 0){
                this.uncoverAllBombs();
                JOptionPane.showMessageDialog(null, 
                "You Lost!!!");
                this.gameStatus = -1;
            }
            curBlock.uncover();
            if(!curBlock.getUncovered()){ this.coveredFields--; }

            if(this.coveredFields <= 0){
                JOptionPane.showMessageDialog(null, 
                "You Won!!!");
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

    private ArrayList<Position> secureStartingPos(ArrayList<Position> arr,
                                                Position pos){
        int posIndex = pos.y + size*pos.x;
        int[] safePositions = new int[9];
        int n = 0;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                safePositions[n] = posIndex + i*size + j;
                System.out.println(safePositions[n]);
                n++;
            }
        }

        ArrayList<Position> result = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            boolean goToNext = false;
            for(int j = 0; j < 9; j++){
                if(safePositions[j] == i) { goToNext = true; break; }
            }
            if(goToNext) { continue; }
            result.add(arr.get(i));
        }
        return result;
    }

    private void uncoverAllBombs(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(mineField[j][i].getState() < 0){
                    mineField[j][i].uncover();
                }
            }
        }
    }

    public void flagField(Position pos, boolean f){
        this.mineField[pos.y][pos.x].setFlagged(!f);
        if(f){
            this.mineField[pos.y][pos.x].cover();
        }
        else this.mineField[pos.y][pos.x].uncover();
    }
}
