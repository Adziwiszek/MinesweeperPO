import java.awt.GridBagLayout;
import javax.swing.*;

/*! Scene for tables with players scores. */
public class BestScoresScene extends MyScene{
    private final ScoreManager scoreManager = ScoreManager.getInstance();

    public BestScoresScene(){
        super(SceneManager.SCORES_SCENE_NAME);
        super.setLayout(new GridBagLayout());
        Box box = Box.createVerticalBox();

        JPanel scorePanel = new JPanel();
        scorePanel.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[0]));
        scorePanel.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[1]));
        scorePanel.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[2]));
        box.add(scorePanel);

        JPanel butPanel = new JPanel();
        butPanel.add(new ChangeSceneButton(
            "Back to menu",
            SceneManager.MENU_SCENE_NAME,
            SM
        ));
        box.add(butPanel);
        this.add(box);
    }
}