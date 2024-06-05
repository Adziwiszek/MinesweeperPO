import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class SceneManager { 
    private JFrame frame = new JFrame("Swing Refresh Bug?");
    private ArrayList<MyScene> scenes;

    private Container contentPane = frame.getContentPane();
    private JPanel cardPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private Component currentComponent;
    
    public SceneManager(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
                
        cardPanel.setLayout(cardLayout);

        scenes = new ArrayList();
        scenes.add(new MainMenuScene(this));
        scenes.add(new GameplayScene(this));
        for (MyScene scene : scenes) {
            cardPanel.add(scene.getPanel(), scene.getName());
        }
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(cardPanel,BorderLayout.CENTER);

        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void changeScene(String newSceneName){
        this.cardLayout.show(this.cardPanel, newSceneName);
    }

    public static void main(String[] args) {
        SceneManager man = new SceneManager();
    }
}
