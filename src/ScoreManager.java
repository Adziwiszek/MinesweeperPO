import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class Score implements Serializable{
    private String difficultyName;
    private String playerName;
    private int timeScore;

    public Score(String difficultyName, String playerName, int timeScore){
        this.difficultyName = difficultyName;
        this.timeScore = timeScore;
        this.playerName = playerName;
    }

    public int getTimeScore() { return timeScore; }
    public String getDifficultyName() { return difficultyName; }
    public String getPlayerName() { return playerName; }
}

public class ScoreManager {
    private ArrayList<Score> scores = new ArrayList<>();;
    
    public ScoreManager(){

    }

    /* TODO:
     * add saving and reading from file - DONE
     * add saving persons name after winning 
     * add debug writing scores
     * add score tables to main menu
     * ADD DOCUMENTATION
     * and that's it!!! 
     */

    public void addScore(int diff, String playerName, int timeScore){
        String diffName = SceneManager.DIFFICULTY_NAMES[diff];
        scores.add(new Score(diffName, playerName, timeScore));
        saveScoreToFile();
    }

    public void debugPrintScores(){
        for (Score score : scores) {
            System.out.println("dif: " + score.getDifficultyName() + 
             ", player: " + score.getPlayerName() + ", time: "+ score.getTimeScore());
        }
    }

    public void saveScoreToFile(){
        String filePath = "src/resources/"+SceneManager.SAVEFILE_NAME;
        File fileTest = new File(filePath);

        fileTest.getParentFile().mkdirs();

        try{ 
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(scores);
            out.flush();
            out.close();
            file.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void loadScoreFromFile(){
        String filePath = "src/resources/"+SceneManager.SAVEFILE_NAME;
        File fileTest = new File(filePath);
        if(!fileTest.exists()){
            System.out.println("NO FILE!!!!!!!");
            try{
                fileTest.createNewFile();
                System.out.println("created not exisitng file");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            ArrayList<Score> scoresFromFile = null;
            if(fileTest.length() == 0){
                System.out.println("source file is empty");
            }
            else {
                try{
                    FileInputStream file = new FileInputStream(filePath);
                    ObjectInputStream in = new ObjectInputStream(file);
                    scoresFromFile = (ArrayList<Score>)in.readObject();
                    in.close();
                    file.close();
                    scores = new ArrayList<>(scoresFromFile);
                }
                catch (ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }   
        }
    }
}
