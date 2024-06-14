import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class GameplayScene  extends MyScene{
    JPanel gamePanel = new JPanel(new GridLayout());
    JScrollPane scrollPane; // used to fix the board scaling

    public GameplayScene(SceneManager SM_){
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
        upperRow.add(new JLabel("Graj:))"));
        upperRow.add(reset);
        
        this.panel.add(upperRow, BorderLayout.NORTH);         
        this.panel.add(gamePanel, BorderLayout.CENTER);
    }

    public void setGamePanel(){
        this.gamePanel.removeAll();
        this.scrollPane = new JScrollPane(SM.gameManager.getPanel());
        this.gamePanel.add(scrollPane);
        // this.gamePanel.add(SM.gameManager.getPanel());
    }
}
