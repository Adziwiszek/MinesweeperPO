import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SceneManager { 
    private JFrame frame = new JFrame("Minesweeper!");
    private ArrayList<MyScene> scenes;

    private Container contentPane = frame.getContentPane();
    private JPanel cardPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private Component currentComponent;

    private GameplayScene gameplayScene;
    public GameLogic gameManager;
    private int difficulty = 0;
    public final int MAXDIFFICULTY = 3;
    public final int DEFAULT_SIZE = 8;
    public final int DEFAULT_BOMBS = 8;
    public final int EASY_DIFFICULTY[] = {8, 8, 10};
    public final int MEDIUM_DIFFICULTY[] = {16, 16, 40};
    public final int HARD_DIFFICULTY[] = {30, 16, 99};
    public final int DIFFICULTIES[][] = 
        {EASY_DIFFICULTY, MEDIUM_DIFFICULTY, HARD_DIFFICULTY};
    public final String DIFFICULTY_NAMES[] = {"Easy", "Medium", "Hard"};
    public final int DEFAULT_DIFFICULTY = 0;
    public final Integer[] SIZE_SETTINGS = {6, 7, 8, 9 ,10, 11, 12};
    public final Integer[] BOMB_SETTINGS = {6, 7, 8, 9, 10, 11, 12,
                                    13, 14, 15, 16, 17, 18};
    
    public SceneManager(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
                
        cardPanel.setLayout(cardLayout);

        gameplayScene = new GameplayScene(this);
        gameManager = new GameLogic(gameplayScene);

        scenes = new ArrayList();
        scenes.add(new MainMenuScene(this));
        scenes.add(gameplayScene);
        scenes.add(new DifficultyScene(this));
        for (MyScene scene : scenes) {
            cardPanel.add(scene.getPanel(), scene.getName());
        }
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(cardPanel,BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void changeScene(String newSceneName){
        this.cardLayout.show(this.cardPanel, newSceneName);
    }

    public static void main(String[] args) {
        SceneManager man = new SceneManager();
    }

    public void startGame(int sizeX, int sizeY, int bombs, int diff){
        this.gameManager.initGame(sizeX, sizeY, bombs, diff);
        this.gameManager.resetGame();
        this.gameplayScene.setGamePanel();
    }

    public void setDifficulty(int dif) { this.difficulty = dif % MAXDIFFICULTY; }

    public int getDifficulty() { return this.difficulty; }
}
