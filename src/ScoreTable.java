import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*! Java swing table for showing players scores. */
public class ScoreTable extends JPanel{
    private final ScoreManager scoreManager;
    private ArrayList<Score> scores;
    private final DefaultTableModel tabModel;
    private final JScrollPane scrollPane;
    private final JTable table;
    TableRowSorter<TableModel> sorter;
    private final String difficulty;

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
        sorter = new TableRowSorter<>(table.getModel());
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
