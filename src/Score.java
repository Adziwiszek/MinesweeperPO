import java.io.*;

public class Score implements Serializable{
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
