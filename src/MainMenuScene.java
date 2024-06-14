import java.awt.Color;

public class MainMenuScene extends MyScene {
    public MainMenuScene(SceneManager SM){
        super("Menu", Color.blue, SM);

        super.addComponent(new ChangeSceneButton(
            "Play",
            "Difficulty",
            SM
        ));
    }
}
