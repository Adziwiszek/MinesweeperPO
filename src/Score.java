import java.io.*;

/*! Score of players, difficulty of completed level and 
    time that it took to finish the game. */
public class Score implements Serializable{
    private final String difficultyName;
    private final String playerName;
    private final int timeScore;

    public Score(String difficultyName, String playerName, int timeScore){
        this.difficultyName = difficultyName;
        this.timeScore = timeScore;
        this.playerName = playerName;
    }

    public int getTimeScore() { return timeScore; }
    public String getDifficultyName() { return difficultyName; }
    public String getPlayerName() { return playerName; }
}
