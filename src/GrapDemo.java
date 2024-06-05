import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
 
class MyMouseListener extends MouseAdapter{

    @Override
    public void mouseClicked(MouseEvent e) {
         int  x1 = e.getX();
         int  y1 = e.getY();
         System.out.println(x1);
         System.out.println(y1);
    }
}

class MyButton extends JPanel{
    BufferedImage img;
    int x, y;
    int w, h;

    public MyButton(String imgName){
        try {
            img = ImageIO.read(getClass().getResource(
                "/resources/" + imgName));
            this.setSize(img.getWidth(), img.getHeight());
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setImageSize(int w, int h){
        this.w = w;
        this.h = h;
    }

    void draw(Graphics g){
        g.drawImage(
            img.getScaledInstance(this.w, this.h, Image.SCALE_REPLICATE),
            this.x, 
            this.y, 
            null);
    }
}

public class GrapDemo extends JFrame {
    BufferedImage img; 
    MyButton myBtn;
    public GrapDemo() {
        super("Lines Drawing Demo");
        System.out.println("test...");
        try {
            img = ImageIO.read(getClass().getResource("/resources/bomb.png"));
        } catch (Exception e) { e.printStackTrace(); }
        myBtn = new MyButton("bomb.png");
        myBtn.setImageSize(80, 80);
        myBtn.setPosition(85, 30);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
 
        g2d.drawLine(120, 50, 360, 50);
 
        g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));
 
        g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));
 
    }
 
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(
            img.getScaledInstance(80, 80, Image.SCALE_REPLICATE),
            5, 
            30, 
            null);
        myBtn.draw(g);
        drawLines(g);

    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GrapDemo().setVisible(true);
            }
        });
    }
}