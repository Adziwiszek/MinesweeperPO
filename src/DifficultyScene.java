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

        SM.gameManager.initGame(8, 8);
        SM.gameManager.resetGame();

        this.panel.setLayout(new GridBagLayout());   


        ChangeSceneButton backToMenuButton = new ChangeSceneButton(
            "Back to Menu",
            "Menu",
            SM
        );
        // backToMenuButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         SM.gameManager.resetGame();
        //     }
        // });
        JPanel settingsPanel = new JPanel(new GridBagLayout());
        sizeSetting = new JSpinner(new SpinnerListModel(SM.SIZE_SETTINGS));
        sizeSetting.setValue(SM.DEFAULT_SIZE);
        diffSetting = new JSpinner(new SpinnerListModel(SM.BOMB_SETTINGS));
        diffSetting.setValue(SM.DEFAULT_BOMBS);
        settingsPanel.add(sizeSetting);
        settingsPanel.add(diffSetting);
        
        ChangeSceneButton playbutton = new ChangeSceneButton(
            "Play",
            "Gameplay",
            SM
        );
        playbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("size: "+ getSizeSetting() + " diff: " +  getDiffSetting());
                // SM.gameManager.setSettings(getSizeSetting(), getDiffSetting());
                // SM.gameManager.initGame(getSizeSetting(), getDiffSetting());
                // SM.gameManager.initGame(8, 8);
                // SM.gameManager.resetGame();
                SM.startGame(getSizeSetting(), getDiffSetting());
            }
        });


        this.panel.add(backToMenuButton); 
        this.panel.add(playbutton);
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
