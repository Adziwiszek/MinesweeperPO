import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class GameplayScene  extends MyScene{
    private GameLogic gameManager;

    public GameplayScene(SceneManager SM){
        super("Gameplay", Color.green);
        gameManager = new GameLogic();
        this.panel.setLayout(new BorderLayout());   

        JPanel upperRow = new JPanel(new GridLayout(1, 3));
        JButton reset = new JButton("Reset game");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameManager.resetGame();
            }
        });
        upperRow.add(new ChangeSceneButton(
            "Back to Menu",
            "Menu",
            SM
        ));
        upperRow.add(new JLabel("Graj:))"));
        upperRow.add(reset);
        
        this.panel.add(upperRow, BorderLayout.NORTH); 
        this.panel.add(gameManager.getPanel(), BorderLayout.CENTER);
    }
}
