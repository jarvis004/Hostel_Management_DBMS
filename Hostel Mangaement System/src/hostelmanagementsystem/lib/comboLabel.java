package hostelmanagementsystem.lib;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class comboLabel extends JLabel{
    private int rad=5;
    boolean clicked=false,hover=false;
    comboLabel(String text,Dimension dim){
        super(text);
        this.setForeground(new Color(255,255,255));
        setPreferredSize(dim);
        setBorder(BorderFactory.createCompoundBorder(getBorder(),BorderFactory.createEmptyBorder(5,10,5,5)));
       addMouseListener(new MouseAdapter(){});
        
    }
    @Override
    protected void paintComponent(Graphics g){
       Graphics2D g2d=(Graphics2D)g;
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(new Color(0,0,0));
       g2d.fillRoundRect(0,0,getWidth(),getHeight(),rad, 0);
       g2d.setColor(Color.WHITE);
       
       super.paintComponent(g);
    }
}
