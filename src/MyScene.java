import javax.swing.*;

/*! Template scene class. */
public class MyScene extends JPanel{
    private final String name;
    protected SceneManager SM;

    public MyScene(String name)
    {
        this.SM = SceneManager.getInstance();
        this.name = name;
        this.setBackground(SceneManager.DEFAULT_SCENE_BACKGROUND_COLOR);    
    }

    @Override
    public String getName(){
        return this.name;
    }
}