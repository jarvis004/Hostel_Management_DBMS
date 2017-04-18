package hostelmanagementsystem.lib;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Dimension;
public class showWarning extends JPanel{
    String warning;
    Color color=new Color(250,250,250);
    FontMetrics fm=null;
    Font font=new Font("Verdana",Font.BOLD,12);
    int textWidth=1;
    int textHeight=1;        
    showWarning(String txt,Color col){this(txt,col,null);}
    showWarning(String txt,Font font){this(txt,null,font);}
    showWarning(String txt){this(txt,null,null);}
    showWarning(String txt,Color col,Font font){
        this.warning=txt;
        if(col!=null)
         this.color=col;
        if(font!=null)
         this.font=font;
        setOpaque(false);
        
        fm=Toolkit.getDefaultToolkit().getFontMetrics(this.font);
        textWidth=fm.stringWidth(warning);
        textHeight=fm.getHeight();
        
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(textWidth+2,textHeight+5);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.color);
        if(font!=null)
            g2d.setFont(font);
        g2d.setColor(color);
        g2d.drawString(warning,0,textHeight);
        g2d.dispose();
    }
            
}
