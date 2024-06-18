import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.Arrays;

public class DifficultyScene extends MyScene {
    public DifficultyScene(SceneManager SM_){
        super("Difficulty", Color.green, SM_);
        this.panel.setLayout(new GridBagLayout());   

        ChangeSceneButton backToMenuButton = new ChangeSceneButton(
            "Back to Menu",
            "Menu",
            SM
        );
        JPanel settingsPanel = new JPanel(new GridBagLayout());

        ChangeSceneButton diffButton;
        for(int i = 0; i < 3; i++){
            diffButton = new ChangeSceneButton(
                SM.DIFFICULTY_NAMES[i],
                "Gameplay",
                SM
            );
            final int k = i;
            final int[] gameSettings = Arrays.copyOf(SM.DIFFICULTIES[i], 3);

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
        
        this.panel.add(backToMenuButton); 
        this.panel.add(settingsPanel);
    }
}
