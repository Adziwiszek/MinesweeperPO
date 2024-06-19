import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class GameplayScene  extends MyScene{
    //
    JPanel gamePanel = new JPanel(new GridLayout());

    public GameplayScene(SceneManager SM_){
        /* SM_ - parent class 
         * Setups gameplay scenes, initializes buttons for reseting game and
         * returning to main menu.
        */
        super(SceneManager.GAMEPLAY_SCENE_NAME, SM_);
        this.setLayout(new BorderLayout());   

        JPanel upperRow = new JPanel(new GridLayout(1, 3));
        JButton reset = new JButton("Reset game");
        reset.setBackground(SceneManager.DEFAULT_BUTTON_COLOR);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SM.gameManager.resetGame();
            }
        });

        JButton backToMenuButton = new ChangeSceneButton(
            "Back to Menu",
            SceneManager.MENU_SCENE_NAME,
            SM
        );
        backToMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SM.gameManager.resetGame();
            }
        });
        upperRow.add(backToMenuButton);
        upperRow.add(reset);
        
        this.add(upperRow, BorderLayout.NORTH);         
        this.add(gamePanel, BorderLayout.CENTER);
    }

    /* */
    public void resetGamePanel(){
        this.gamePanel.removeAll();
        this.gamePanel.setBackground(SceneManager.DEFAULT_SCENE_BACKGROUND_COLOR);
        this.gamePanel.add(new JScrollPane(SM.gameManager.getPanel()));
    }
}
