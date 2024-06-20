import java.awt.Color;
import java.awt.GridBagLayout;

public class MainMenuScene extends MyScene {
    private ScoreManager scoreManager = ScoreManager.getInstance();

    public MainMenuScene(SceneManager SM){
        super(SceneManager.MENU_SCENE_NAME, SM);
        super.setLayout(new GridBagLayout());

        super.add(scoreManager.getEasyScoresTable());

        super.add(new ChangeSceneButton(
            "Play",
            SceneManager.DIFFICULTY_SCENE_NAME,
            SM
        ));
        super.add(new ChangeSceneButton(
            "Exit",
            SceneManager.EXIT_NAME,
            SM
        ));
    }
}
