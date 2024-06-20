import java.awt.GridBagLayout;

/*! Scene for main menu. */
public class MainMenuScene extends MyScene {
    public MainMenuScene(){
        super(SceneManager.MENU_SCENE_NAME);
        super.setLayout(new GridBagLayout());

        super.add(new ChangeSceneButton(
            "Play",
            SceneManager.DIFFICULTY_SCENE_NAME,
            SM
        ));
        super.add(new ChangeSceneButton(
            "Best Scores",
            SceneManager.SCORES_SCENE_NAME,
            SM
        ));
        super.add(new ChangeSceneButton(
            "Exit",
            SceneManager.EXIT_NAME,
            SM
        ));
    }
}
