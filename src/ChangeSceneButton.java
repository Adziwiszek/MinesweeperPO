import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class ChangeSceneButton extends JButton{
    private String newSceneName;
    private SceneManager SM;

    public ChangeSceneButton(String buttonText, String newSceneName_, 
    SceneManager SM_){
        super(buttonText);
        this.setBackground(SceneManager.DEFAULT_BUTTON_COLOR);
        this.newSceneName = newSceneName_;
        this.SM = SM_;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SM.changeScene(newSceneName);
            }
        });        
    }
}
