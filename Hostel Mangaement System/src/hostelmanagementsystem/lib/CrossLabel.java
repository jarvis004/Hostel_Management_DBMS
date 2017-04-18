
package hostelmanagementsystem.lib;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;
public class CrossLabel extends JLabel{
    private int width,height;
    private Color ovalColor,crossLineColor;
    private boolean hover=false;
    private BufferedImage cross;
    public CrossLabel(int width,Color ovalColor,Color crossLineColor){
        this.width=width;
        this.height=width;
        this.ovalColor=ovalColor;
        this.crossLineColor=crossLineColor;
        try{
            cross=ImageIO.read(getClass().getResource("icons/cross.png"));
        }
        catch(IOException e){
            
        }
        setOpaque(true);
        addMouseListener(new mouseListener());
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(this.width,this.height);
    } 
    @Override
    public Dimension getMinimumSize(){
        return new Dimension(this.width,this.height);
    }
    @Override
    public Dimension getMaximumSize(){
        return new Dimension(this.width,this.height);
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        
        g2d.drawImage(cross,0,0,null);
    }
    
    private class mouseListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent mev){
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        @Override
        public void mouseExited(MouseEvent mev){
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        @Override
        public void mouseClicked(MouseEvent mev){
            JFrame frame = (JFrame)SwingUtilities.getRoot((JComponent)mev.getSource());
            if(frame.getGlassPane().isVisible())
              frame.getGlassPane().setVisible(false);
            else
               Globals.layeredPane.removeAllComponent();
        }
    }
}
