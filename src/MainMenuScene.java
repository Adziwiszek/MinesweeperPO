import java.awt.Color;

public class MainMenuScene extends MyScene {
    public MainMenuScene(SceneManager SM){
        super("Menu", Color.blue);

        super.addComponent(new ChangeSceneButton(
            "Play",
            "Gameplay",
            SM
        ));
    }
}
