import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class ScoreTable extends JPanel{
    private ScoreManager scoreManager;
    private ArrayList<Score> scores;
    private DefaultTableModel tabModel;
    private JScrollPane scrollPane;
    private JTable table;
    private String difficulty;

    public ScoreTable(String difficulty){
        this.setLayout(new BorderLayout());
        scoreManager = ScoreManager.getInstance();
        scores = new ArrayList<>(scoreManager.getScores(difficulty));
        this.difficulty = difficulty;
        
        String[] columnNames = {"Name", "Time"};

        // Convert scores to Object[][] for DefaultTableModel
        Object[][] data = new Object[scores.size()][2];
        for (int i = 0; i < scores.size(); i++) {
            data[i][0] = scores.get(i).getPlayerName();
            data[i][1] = scores.get(i).getTimeScore();
        }
        
        tabModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tabModel);
        scrollPane = new JScrollPane(table);

        JLabel diffLabel = new JLabel(difficulty);
        diffLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(diffLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }   

    public void updateStats(){
        scores = new ArrayList<>(scoreManager.getScores(difficulty));
        
        tabModel.setRowCount(0);
        
        for (Score score : scores) {
            tabModel.addRow(new Object[]{score.getPlayerName(), score.getTimeScore()});
        }
    }
    
    
}
