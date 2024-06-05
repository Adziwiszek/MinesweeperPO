import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A simple demonstration of Swing's CardLayout.
 *
 * @author seanh
 */
class CLDemo {

    private JFrame frame = new JFrame("Swing Refresh Bug?");
    private Container contentPane = frame.getContentPane();
    private JPanel cardPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private Component currentComponent;
    private JButton next;

    CLDemo() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        cardPanel.setLayout(cardLayout);
        cardPanel.add(new JLabel("One"),"One");
        cardPanel.add(new JLabel("Two"),"Two");
        cardPanel.add(new JLabel("Three"),"Three");

        next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cardPanel);
                //cardLayout.show(cardPanel, "Two");    
            }
        });

        contentPane.setLayout(new BorderLayout());
        contentPane.add(cardPanel,BorderLayout.CENTER);
        contentPane.add(next,BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        CLDemo demo = new CLDemo();
    }
    
}