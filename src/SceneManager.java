import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;


public class SceneManager { 
    /* Block settings. */
    public static final Color CLICKED_COLOR = new Color(209, 186, 255);
    public static final Color UNCLICKED_COLOR = new Color(115, 88, 88);
    public static final Color FLAG_COLOR = new Color(255, 0, 0);
    // These are sizes for special characters that symbolize bombs and flags.
    public static final int[] PIC_SIZES = {20, 14, 13};
    public static final int[] TEXT_SIZES = {23, 18, 17};

    private JFrame frame = new JFrame("Minesweeper!");

    /* Used for switching between different scenes */
    private ArrayList<MyScene> scenes;
    private Container contentPane = frame.getContentPane();
    private JPanel cardPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    /*  */
    private GameplayScene gameplayScene;
    public GameLogic gameManager;

    /* Difficulty settings
     * {width of map, height of map, number of bombs}
     */
    public final int EASY_DIFFICULTY[] = {8, 8, 10};
    public final int MEDIUM_DIFFICULTY[] = {16, 16, 40};
    public final int HARD_DIFFICULTY[] = {30, 16, 99};
    public final int DIFFICULTIES[][] = 
        {EASY_DIFFICULTY, MEDIUM_DIFFICULTY, HARD_DIFFICULTY};
    public final String DIFFICULTY_NAMES[] = {"Easy", "Medium", "Hard"};

    public SceneManager(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
                
        cardPanel.setLayout(cardLayout);

        gameplayScene = new GameplayScene(this);
        gameManager = new GameLogic();

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

    public void startGame(int sizeX, int sizeY, int bombs, int fontSize){
        this.gameManager.initGame(sizeX, sizeY, bombs, fontSize);
        this.gameManager.resetGame();
        this.gameplayScene.setGamePanel();
    }
}
