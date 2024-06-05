import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class GameplayScene  extends MyScene{
    private GameLogic gameManager;

    public GameplayScene(SceneManager SM){
        super("Gameplay", Color.green);
        this.panel.setLayout(new BorderLayout());   

        JPanel upperRow = new JPanel(new GridLayout(1, 2));
        upperRow.add(new ChangeSceneButton(
            "Back to Menu",
            "Menu",
            SM
        ));
        upperRow.add(new JLabel("Graj:))"));
        this.panel.add(upperRow, BorderLayout.NORTH);

        gameManager = new GameLogic();
        this.panel.add(gameManager.getPanel(), BorderLayout.CENTER);
    }
}
