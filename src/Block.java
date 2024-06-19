import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class Block extends JButton {
    /* state >= 0 - number of bombs around  
     * state < 0 - this is a bomb
     */
    private int state = 0;
    private boolean uncovered = false; 
    private boolean flagged = false;
    // Position on map
    private Position position;
    // 
    private final int fontSize;
    // Instance of parent class, that handles game logic
    private GameLogic parent;    

    public Block(GameLogic parent_, Position pos_, int fontSize){
        /* parent_ - instance of parent class, that handles game logic
         * pos_ - position of this block on the board
         * fontSize - selects font size for specific difficulty 
         */
        super();
        this.fontSize = fontSize;
        this.parent = parent_;
        this.position = pos_;
        this.setBackground(SceneManager.UNCLICKED_COLOR);
        

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
        this.setBackground(SceneManager.UNCLICKED_COLOR);
        this.uncovered = false;
        this.flagged = false;
        this.state = 0;
    }

    public void uncover(){
        this.setBackground(SceneManager.CLICKED_COLOR);

        if(this.flagged){
            this.setText("@");
            this.setFont(new Font("Ariel", 
                        Font.PLAIN,
                        SceneManager.PIC_SIZES[fontSize] + 2));
            this.setForeground(SceneManager.FLAG_COLOR);
        }
        else{
            if(this.state < 0){
                this.setFont(new Font("Ariel", 
                            Font.PLAIN, 
                            SceneManager.PIC_SIZES[fontSize]));
                this.setText("⬤");
                this.setForeground(SceneManager.BOMB_COLOR);
            }
            else{
                this.setFont(new Font("Ariel", 
                            Font.BOLD, 
                            SceneManager.TEXT_SIZES[fontSize]));
                if(this.state > 0){
                    this.setText(this.state+"");
                    this.setForeground(SceneManager.NUMBER_COLORS[this.state-1]);
                }
                else{
                    this.setText("");
                }
            }
        }
        this.setBackground(SceneManager.CLICKED_COLOR);
        this.setUncovered(true);
    }

    public void cover(){
        this.setText("");
        this.setBackground(SceneManager.UNCLICKED_COLOR);
        this.setUncovered(false);
    }

    public JButton getButton(){ return this; }

    public void setUncovered(boolean unc){ this.uncovered = unc; }

    public boolean getUncovered(){ return this.uncovered; }

    public void setState(int state_){ this.state = state_; }

    public int getState(){ return this.state; }

    public void incrementState(int inc){ this.state += inc; }

    public boolean getFlagged(){ return this.flagged; }

    public void setFlagged(boolean f){ this.flagged = f; }
}
