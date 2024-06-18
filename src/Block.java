import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class Block extends JButton {
    public final Color UNCOLOR = new Color(115, 88, 88);
    private final int[] PIC_SIZES = {20, 14, 13};
    private final int[] TEXT_SIZES = {20, 16, 13};

    private int state = 0;
    private boolean uncovered = false; 
    private boolean flagged = false;
    private Position position;
    private JButton button;
    private int pictureSize;
    private int textSize;
    private GameLogic parent;    

    public Block(GameLogic parent_, Position pos_, int diff){
        super();
        this.pictureSize = PIC_SIZES[diff];
        this.textSize = TEXT_SIZES[diff];
        this.parent = parent_;
        this.position = pos_;
        // this.setBackground(this.parent.UNCLICKED_COLOR);
        this.setBackground(this.UNCOLOR);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // System.out.println("clicked: " + position.x + ",  " + position.y);
                if (SwingUtilities.isRightMouseButton(e)) {
                    parent.flagField(position, flagged);
                }
                if (SwingUtilities.isLeftMouseButton(e)){
                    // System.out.println("block clicked");
                    parent.onClick(state, uncovered, position);
                }
            }
        });
    }

    public void reset(){
        this.setText("");
        this.setBackground(this.parent.UNCLICKED_COLOR);
        // this.setBackground(this.UNCOLOR);

        this.uncovered = false;
        this.flagged = false;
        this.state = 0;
    }

    public void uncover(){
        // System.out.println("uncovering block");
        this.setBackground(this.parent.CLICKED_COLOR);

        if(this.flagged){
            this.setText("☂");
            this.setFont(new Font("Ariel", Font.PLAIN, pictureSize));
        }
        else{
            if(this.state < 0){
                this.setFont(new Font("Ariel", Font.PLAIN, pictureSize));
                this.setText("㋛");
            }
            else{
                this.setFont(new Font("Ariel", Font.PLAIN, textSize));
                this.setText((this.state > 0 ? this.state : "")+"");
            }
        }
        this.setBackground(this.parent.CLICKED_COLOR);
        // this.setBackground(this.UNCOLOR);
        this.setUncovered(true);
    }

    public void cover(){
        this.setText("");
        System.out.println("covering block");
        this.setBackground(this.parent.UNCLICKED_COLOR);
        this.setUncovered(false);
    }

    public JButton getButton(){ return this.button; }

    public void setUncovered(boolean unc){ this.uncovered = unc; }

    public boolean getUncovered(){ return this.uncovered; }

    public void setState(int state_){ this.state = state_; }

    public int getState(){ return this.state; }

    public void incrementState(int inc){ this.state += inc; }

    public boolean getFlagged(){ return this.flagged; }

    public void setFlagged(boolean f){ this.flagged = f; }
}
