import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Score implements Serializable{
    private String difficultyName;
    private int timeScore;

    public Score(String difficultyName, int timeScore){
        this.difficultyName = difficultyName;
        this.timeScore = timeScore;
    }

    public int getTimeScore() { return timeScore; }
    public String getDifficultyName() { return difficultyName; }
}

public class ScoreManager {
    private ArrayList<Score> scores = null;
    
    public ScoreManager(){

    }

    /* TODO:
     * add saving and reading from file
     * add saving persons name after winning 
     * add debug writing scores
     * add score tables to main menu
     * ADD DOCUMENTATION
     * and that's it!!!
     */

    public void saveScoreToFile(){
        try{ 
            FileOutputStream file = new FileOutputStream("resources/"+SceneManager.SAVEFILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
            if(scores != null){
                out.writeObject(scores);
            }
            else{
                scores = new ArrayList<>();
                scores.add(new Score("asd", 123));
            }
            out.flush();
            out.close();
            file.close();
        }
        catch(IOException ex){
            System.out.println("IOException is caught");
        }
    }

    public void loadScoreFromFile(){
        ArrayList<Score> scoresFromFile = null;
        try{
            FileInputStream file = new FileInputStream("resources/"+SceneManager.SAVEFILE_NAME);
            ObjectInputStream in = new ObjectInputStream(file);
            scoresFromFile = (ArrayList<Score>)in.readObject();
            in.close();
            file.close();
        }
        catch (ClassNotFoundException | IOException ex) {
            System.out.println("save file not found");
            ex.printStackTrace();
            saveScoreToFile();
        }
    }
}
