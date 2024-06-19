import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.Arrays;

public class DifficultyScene extends MyScene {
    public DifficultyScene(SceneManager SM_){
        super(SceneManager.DIFFICULTY_SCENE_NAME, Color.green, SM_);
        this.setLayout(new GridBagLayout());   

        ChangeSceneButton backToMenuButton = new ChangeSceneButton(
            "Back to Menu",
            SceneManager.MENU_SCENE_NAME,
            SM
        );
        JPanel settingsPanel = new JPanel(new GridBagLayout());

        ChangeSceneButton diffButton;
        for(int i = 0; i < 3; i++){
            diffButton = new ChangeSceneButton(
                SceneManager.DIFFICULTY_NAMES[i],
                SceneManager.GAMEPLAY_SCENE_NAME,
                SM
            );
            final int k = i;
            final int[] gameSettings = Arrays.copyOf(SceneManager.DIFFICULTIES[i], 3);

            diffButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SM.startGame(gameSettings[0], 
                                gameSettings[1], 
                                gameSettings[2], 
                                k);
                }
            });
            settingsPanel.add(diffButton);
        }
        
        this.add(backToMenuButton); 
        this.add(settingsPanel);
    }
}
