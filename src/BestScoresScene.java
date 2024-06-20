import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class BestScoresScene extends MyScene{
    private ScoreManager scoreManager = ScoreManager.getInstance();

    public BestScoresScene(SceneManager SM){
        super(SceneManager.SCORES_SCENE_NAME, SM);
        super.setLayout(new GridBagLayout());

        super.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[0]));
        super.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[1]));
        super.add(scoreManager.getScoresTable(SceneManager.DIFFICULTY_NAMES[2]));

        super.add(new ChangeSceneButton(
            "Back to menu",
            SceneManager.MENU_SCENE_NAME,
            SM
        ));
    }
}