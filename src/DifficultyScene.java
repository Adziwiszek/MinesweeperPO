import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

public class DifficultyScene extends MyScene {
    private JSpinner sizeSetting;
    private JSpinner diffSetting;

    public DifficultyScene(SceneManager SM_){
        super("Difficulty", Color.green, SM_);

        // SM.gameManager.initGame(8, 8, 0);
        // SM.gameManager.resetGame();

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
            final int[] gameSettings = new int[3];
            // for(int j = 0; j < 3; j++) {gameSettings[j] = SM.DIFFICULTIES[i][j];}
            gameSettings[0] = SM.DIFFICULTIES[i][0];
            gameSettings[1] = SM.DIFFICULTIES[i][1];
            gameSettings[2] = SM.DIFFICULTIES[i][2];

            diffButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // chooseDiffStartGame(k);
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

    private int getSizeSetting(){
        try{
            Object result = this.sizeSetting.getValue();
            if(result instanceof Integer){
                return ((Integer) result).intValue();
            }    
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return SM.DEFAULT_SIZE;
    }

    private int getDiffSetting(){
        try{
            Object result = this.diffSetting.getValue();
            if(result instanceof Integer){
                return ((Integer) result).intValue();
            }    
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return SM.DEFAULT_BOMBS;
    }
}
