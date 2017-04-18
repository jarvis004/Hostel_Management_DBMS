package hostelmanagementsystem.lib;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Toolkit;
public class customTextArea extends JTextArea{
    int width=200,height=50,placeholderHeight=0;
    private String placeholder;
    FontMetrics fm=null;
    customTextArea(){
        setOpaque(false);
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(this.width,this.height);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setColor(new Color(230,230,230));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(1, 1, this.width-2, this.height-2, 5, 5);
        fm=Toolkit.getDefaultToolkit().getFontMetrics(g2d.getFont());
        this.placeholderHeight=fm.getHeight();
        g2d.setColor(new Color(190,190,190));
        g2d.drawString(this.placeholder,0,this.placeholderHeight);
        super.paintComponent(g);
    }
}
