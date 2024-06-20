import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SceneManager { 
    /* Block settings. */
    public static final Color CLICKED_COLOR = new Color(168, 185, 243);
    public static final Color UNCLICKED_COLOR = new Color(27, 16, 104);
    public static final Color FLAG_COLOR = new Color(255, 0, 0);
    public static final Color BOMB_COLOR = new Color(0, 0, 0);
    public static final Color[] NUMBER_COLORS = {
        new Color(0, 80, 255), // 1 - blue
        new Color(0, 102, 0), // 2 - green
        new Color(204, 0, 0), // 3 - red
        new Color(0, 20, 80), // 4 - dark blue
        new Color(142, 51, 0), // 5 - brown
        new Color(0, 220, 220), // 6 - cyan
        new Color(0, 0, 0), // 7 - black
        new Color(128, 128, 128) // 8 - grey
    };
    /* These are sizes for special characters that symbolize bombs and flags. */
    public static final int[] PIC_SIZES = {20, 14, 13};
    public static final int[] TEXT_SIZES = {23, 18, 17};

    /* Difficulty settings
     * {map width, map height, number of bombs}
     */
    public static final int EASY_DIFFICULTY[] = {8, 8, 10};
    public static final int MEDIUM_DIFFICULTY[] = {16, 16, 40};
    public static final int HARD_DIFFICULTY[] = {30, 16, 99};
    public static final int DIFFICULTIES[][] = 
        {EASY_DIFFICULTY, MEDIUM_DIFFICULTY, HARD_DIFFICULTY};
    public static final String DIFFICULTY_NAMES[] = {"Easy", "Medium", "Hard"};

    public static final String INTRO_TEXT = 
        "To uncover board click on with left mouse button. \n" +
        "Numbers indicate how many bombs are around each tile. \n" +
        "Click on tiles with right mouse button to flag them, where you suspect the bombs might be";

    /* SCENE NAMES */
    /* If some button changes active scene to this it closes the program. */
    public static final String EXIT_NAME = "Exit"; 
    public static final String MENU_SCENE_NAME = "Menu";
    public static final String DIFFICULTY_SCENE_NAME = "Difficulty";
    public static final String GAMEPLAY_SCENE_NAME = "Gameplay";
    public static final Color DEFAULT_SCENE_BACKGROUND_COLOR = new Color(27, 16, 104);
    public static final Color DEFAULT_BUTTON_COLOR = new Color(168, 185, 243);

    /* Main window of the program. */
    private JFrame frame = new JFrame("Minesweeper!");

    /* Used for switching between different scenes */
    private ArrayList<MyScene> scenes;
    private Container contentPane = frame.getContentPane();
    private JPanel cardPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    /*  */
    private GameplayScene gameplayScene;
    public GameLogic gameManager;
    public SolveTimer gameTimer = new SolveTimer();
    private ScoreManager scoreManager = new ScoreManager();
    public static final String SAVEFILE_NAME = "save.ser";

    private static GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private static GraphicsDevice ev = env.getDefaultScreenDevice();

    public SceneManager(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
                
        cardPanel.setLayout(cardLayout);

        gameplayScene = new GameplayScene(this);
        gameManager = new GameLogic(this);
        scoreManager.loadScoreFromFile();

        scenes = new ArrayList();
        scenes.add(new MainMenuScene(this));
        scenes.add(new DifficultyScene(this));
        scenes.add(gameplayScene);

        for (MyScene scene : scenes) {
            cardPanel.add(scene, scene.getName());
        }
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(cardPanel,BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setVisible(true);
    }
    

    public void changeScene(String newSceneName){
        if(newSceneName.equals(EXIT_NAME)){ 
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        } 
        this.cardLayout.show(this.cardPanel, newSceneName);
    }

    /* Initalizes new game board with given size, number of bombs and font size.
     * fontSize - this selects one of the 3 premade size settings, based on the
     *      chosen difficulty. If this number is not in {0, 1, 2} it takes 
     *      (fontSize % 3).
     */
    public void startGame(int sizeX, int sizeY, int bombs, int fontSize){
        this.gameManager.initGame(sizeX, sizeY, bombs, fontSize);
        this.gameManager.resetGame();
        this.gameplayScene.resetGamePanel();
        this.gameTimer.resetTimer();
    }

    public void endGame(int gameResult){
        this.gameTimer.stopTimer();
        if(gameResult < 0){
            JOptionPane.showMessageDialog(this.frame, 
                "You Lost!!!");
        }
        else{
            int finalTime = this.gameTimer.getTime();
            var name = JOptionPane.showInputDialog(frame, "You Won, give us your name!!! Your time: "+finalTime+" seconds");
            // JOptionPane.showMessageDialog(this.frame, 
            //     "You Won!!! Your time: "+finalTime+" seconds");
            System.out.println(name);
        }
    }

    public JFrame getFrame(){
        return this.frame;
    }

    public static void main(String[] args) {
        SceneManager man = new SceneManager();
    } 
}
