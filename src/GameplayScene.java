import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*! Scene for the gameplay. */
public class GameplayScene  extends MyScene{
    JPanel gamePanel = new JPanel(new GridLayout());

    public GameplayScene(){
        /*! 
         * Setups gameplay scenes, initializes buttons for reseting game and
         * returning to main menu.
        */
        super(SceneManager.GAMEPLAY_SCENE_NAME);
        this.setLayout(new BorderLayout());   

        JPanel upperRow = new JPanel(new GridLayout(1, 3));
        JButton reset = new JButton("Reset game");
        reset.setBackground(SceneManager.DEFAULT_BUTTON_COLOR);
        reset.addActionListener((ActionEvent e) -> {
            SM.gameManager.resetGame();
            SM.gameTimer.resetTimer();
        });

        JButton backToMenuButton = new ChangeSceneButton(
            "Back to Menu",
            SceneManager.MENU_SCENE_NAME,
            SM
        );
        upperRow.add(backToMenuButton);
        upperRow.add(SM.gameTimer.getLabel());
        upperRow.add(reset);
        
        this.add(upperRow, BorderLayout.NORTH);         
        this.add(gamePanel, BorderLayout.CENTER);
    }

    /*! */
    public void resetGamePanel(){
        this.gamePanel.removeAll();
        this.gamePanel.setBackground(SceneManager.DEFAULT_SCENE_BACKGROUND_COLOR);
        this.gamePanel.add(new JScrollPane(SM.gameManager.getPanel()));
    }

    public void endGame(int gameStatus){

    }

    public void startGame(){
    }
}
