package hostelmanagementsystem.lib;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.RenderingHints;

import java.awt.image.BufferedImage;

public class PicLabel extends JLabel{
    private int width,height,brd=4,rad=4;
    private Color brdCol=new Color(250,250,250);
    private BufferedImage icon;
    PicLabel(BufferedImage icon){
        this.icon=icon;
        this.width=icon.getWidth()+2*brd;
        this.height=icon.getHeight()+2*brd;
        setPreferredSize(new Dimension(width,height));
    }
    @Override
    protected void paintComponent(Graphics g){
       Graphics2D g2d=(Graphics2D)g;
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(brdCol);
       g2d.fillRoundRect(0,0,width,height,rad,rad);
       g2d.drawImage(icon,brd,brd,null);
    }
}
