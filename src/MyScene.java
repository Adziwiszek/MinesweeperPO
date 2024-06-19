import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyScene extends JPanel{
    private String name;
    protected SceneManager SM;

    public MyScene(String name, SceneManager SM_)
    {
        this.SM = SM_;
        this.name = name;
        this.setBackground(SceneManager.DEFAULT_SCENE_BACKGROUND_COLOR);    
    }

    @Override
    public String getName(){
        return this.name;
    }
}