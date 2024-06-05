import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyScene {
    protected JPanel panel;
    private ArrayList<JComponent> objects;
    private String name;

    public MyScene(String name, Color backgroundColor)
    {
        this.name = name;
        this.panel = new JPanel();
        this.panel.setBackground(backgroundColor);
        this.objects = new ArrayList();
    }

    public void addComponent(JComponent component){
        this.panel.add(component);    
        this.objects.add(component);
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public ArrayList<JComponent> getObjects(){
        return this.objects;
    }

    public String getName(){
        return this.name;
    }

    
    public static void main(String[] args) {
        System.out.println("asdsd");
    }
}