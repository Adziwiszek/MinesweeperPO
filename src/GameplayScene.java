import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class GameplayScene  extends MyScene{
    //
    JPanel gamePanel = new JPanel(new GridLayout());
    // Used to fix the board scaling
    JScrollPane scrollPane; 

    public GameplayScene(SceneManager SM_){
        /* SM_ - parent class 
         * Setups gameplay scenes, initializes buttons for reseting game and
         * returning to main menu.
        */
        super("Gameplay", Color.green, SM_);
        this.panel.setLayout(new BorderLayout());   

        JPanel upperRow = new JPanel(new GridLayout(1, 3));
        JButton reset = new JButton("Reset game");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SM.gameManager.resetGame();
            }
        });

        JButton backToMenuButton = new ChangeSceneButton(
            "Back to Menu",
            "Menu",
            SM
        );
        backToMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SM.gameManager.resetGame();
            }
        });
        upperRow.add(backToMenuButton);
        upperRow.add(reset);
        
        this.panel.add(upperRow, BorderLayout.NORTH);         
        this.panel.add(gamePanel, BorderLayout.CENTER);
    }

    public void setGamePanel(){
        /* Resets JPanel that stores game tiles. */
        this.gamePanel.removeAll();
        this.scrollPane = new JScrollPane(SM.gameManager.getPanel());
        this.gamePanel.add(scrollPane);
    }
}
