package hostelmanagementsystem.lib;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Color;
import java.awt.Paint;
import java.awt.LinearGradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Dimension;
public class myScrollBarUI extends BasicScrollBarUI {
    private int scrollerWidth=0,scrollerX=0,scrollerY=0,scrollHldrWidth=0,scrollHldrHeight=40,scrollerRad=5;
    
    myScrollBarUI(int scW,int scH,int scHldrW,int rad){
        this(scW,scH,scHldrW);
        this.scrollerRad=rad;
    }
    
    myScrollBarUI(int scW,int scHldrH,int scHldrW){
        this.trackColor=new Color(0,0,0);
        this.scrollerWidth=scW;
        this.scrollHldrHeight=scHldrH;
        this.scrollHldrWidth=scHldrW;
    }
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(229,229,229));
        g2d.fillRect(0,trackBounds.y,this.scrollerWidth,trackBounds.height);
        
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        c.setOpaque(false);
        g2d.translate(thumbBounds.x, thumbBounds.y);
        g2d.setColor(new Color(67,67,67));
        g2d.fillRoundRect(1, 1,this.scrollerWidth-2,thumbBounds.height-2,this.scrollerRad,this.scrollerRad);
        g2d.translate( -thumbBounds.x, -thumbBounds.y );
    }
    @Override
    protected JButton createDecreaseButton(int orientation) {
       return createInvisibleButton();
    }

    @Override    
    protected JButton createIncreaseButton(int orientation) {
        return createInvisibleButton();
    }
        
    private JButton createInvisibleButton() {
      JButton btn = new JButton();
      btn.setPreferredSize(new Dimension(0, 0));
      btn.setMinimumSize(new Dimension(0, 0));
      btn.setMaximumSize(new Dimension(0, 0));
      return btn;
    }
}
