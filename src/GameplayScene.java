import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class GameplayScene  extends MyScene{
    //private GameLogic gameManager;
    JPanel gamePanel = new JPanel();

    public GameplayScene(SceneManager SM_){
        super("Gameplay", Color.green, SM_);
        //gameManager = new GameLogic(this);
        // SM.gameManager.setSettings(8, 8);

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
        upperRow.add(new JLabel("Graj:))"));
        upperRow.add(reset);
        
        this.panel.add(upperRow, BorderLayout.NORTH); 
        
        this.panel.add(gamePanel, BorderLayout.CENTER);
        // this.setGamePanel();
    }

    public void setGamePanel(){
        // this.panel.remove(panel);
        // TODO: remove old panel before adding a new one
        this.gamePanel.removeAll();
        this.gamePanel.add(SM.gameManager.getPanel());
    }

    
}
