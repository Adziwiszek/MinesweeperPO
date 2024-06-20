import java.awt.GridBagLayout;
import javax.swing.*;

public class BestScoresScene extends MyScene{
    private ScoreManager scoreManager = ScoreManager.getInstance();

    public BestScoresScene(SceneManager SM){
        super(SceneManager.SCORES_SCENE_NAME, SM);
        super.setLayout(new GridBagLayout());
        Box box = Box.createVerticalBox();

        JPanel scorePanel = new JPanel();
        scorePanel.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[0]));
        scorePanel.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[1]));
        scorePanel.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[2]));
        box.add(scorePanel);

        box.add(new ChangeSceneButton(
            "Back to menu",
            SceneManager.MENU_SCENE_NAME,
            SM
        ));
        this.add(box);
    }
}