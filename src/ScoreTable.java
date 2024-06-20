import java.awt.BorderLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.*;

public class ScoreTable extends JPanel{
    private ScoreManager scoreManager;
    private ArrayList<Score> scores;
    private DefaultTableModel tabModel;
    private JScrollPane scrollPane;
    private JTable table;
    TableRowSorter<TableModel> sorter;
    private String difficulty;

    public ScoreTable(String difficulty){
        this.setLayout(new BorderLayout());
        scoreManager = ScoreManager.getInstance();
        scores = new ArrayList<>(scoreManager.getScores(difficulty));
        this.difficulty = difficulty;
        
        String[] columnNames = {"Name", "Time"};

        Object[][] data = new Object[scores.size()][2];
        for (int i = 0; i < scores.size(); i++) {
            data[i][0] = scores.get(i).getPlayerName();
            data[i][1] = scores.get(i).getTimeScore();
        }
        
        tabModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tabModel);
        sorter = new TableRowSorter<TableModel>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);
        scrollPane = new JScrollPane(table);

        JLabel diffLabel = new JLabel(difficulty);
        diffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sorter.sort();

        this.add(diffLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }   

    public void updateStats(){
        scores = new ArrayList<>(scoreManager.getScores(difficulty));
        
        tabModel.setRowCount(0);
        
        for (Score score : scores) {
            tabModel.addRow(new Object[]{score.getPlayerName(), score.getTimeScore()});
        }
        sorter.sort();
    }
    
    
}
