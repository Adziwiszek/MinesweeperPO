import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class ChangeSceneButton extends JButton{
    // private JButton button;
    private String newSceneName;
    private SceneManager SM;

    public ChangeSceneButton(String buttonText, String newSceneName_, 
    SceneManager SM_){
        // button = new JButton(buttonText);
        super(buttonText);
        this.newSceneName = newSceneName_;
        this.SM = SM_;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SM.changeScene(newSceneName);
            }
        });        
    }
}
