import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

        // TODO: make this better
        JLabel lab1 = new JLabel("To uncover board click on with left mouse button.");
        lab1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        box.add(lab1);
        JLabel lab2 = new JLabel("Numbers indicate how many bombs are around each tile.");
        lab2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        box.add(lab2);
        JLabel lab3 = new JLabel("Click on tiles with right mouse button to flag them, where you suspect the bombs might be");
        lab3.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        box.add(lab3);
        textPanel.add(box);


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
        this.add(textPanel);        
    }
}
