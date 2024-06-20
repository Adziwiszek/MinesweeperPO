import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DifficultyScene extends MyScene {
    public DifficultyScene(SceneManager SM_){
        super(SceneManager.DIFFICULTY_SCENE_NAME, SM_);
        this.setLayout(new GridBagLayout());
        JPanel textPanel = new JPanel();
        Box box = Box.createVerticalBox();

        /* Tutorial text. */
        JLabel lab1 = new JLabel("To win the game all non-mine cells must be opened without opening a mine.");
        lab1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        box.add(lab1);
        JLabel lab2 = new JLabel("Each tile has a number that indicates how many mines are around it.");
        lab2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        box.add(lab2);
        JLabel lab3 = new JLabel("Left click on a tile to uncover it and right click to mark it as a potential mine.");
        lab3.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        box.add(lab3);
        
        ChangeSceneButton backToMenuButton = new ChangeSceneButton(
            "Back to Menu",
            SceneManager.MENU_SCENE_NAME,
            SM
        );
        JPanel settingsPanel = new JPanel(new FlowLayout());
        settingsPanel.add(backToMenuButton); 
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
        box.add(settingsPanel);

        textPanel.add(box);
        this.add(textPanel);        
    }
}
