package hostelmanagementsystem.lib;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Paint;
import java.awt.LinearGradientPaint;
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Insets;
public class ComboArrowButton extends JButton{
        private int shadowSize=5,btnW=20,btnH=20,shadowOffset,brdRad;
        private boolean clicked=false,hover=false;
        ComboArrowButton ref;
        LinearGradientPaint painter1,painter2;
        int[] traingleX={4,9,14};
        int[] traingleY={7,14,7};
        public ComboArrowButton(){
                super();
          
        painter1=new LinearGradientPaint(0.0f, 0.0f, 0.0f,getPreferredSize().height,
          new float[] { 0.0f,0.5f,1.0f },
          new Color[] { new Color(92,92,92),
                        new Color(0,0,0),
                        new Color(0,0,0)});   
        painter2=new LinearGradientPaint(0.0f, 0.0f, 0.0f,getPreferredSize().height,
          new float[] { 0.0f,0.5f,1.0f },
          new Color[] { new Color(122,122,122),
                        new Color(22,22,22),
                        new Color(22,22,22)});
                ref=this;
                setFocusPainted(false);
                setContentAreaFilled(false);
                addMouseListener(new btnMsListener());
        }
        private class btnMsListener extends MouseAdapter{
                @Override
                public void mouseEntered(MouseEvent mev){
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    hover=true;
                    ref.repaint();
                    repaint();
                }
                @Override
                public void mouseExited(MouseEvent mev){
                    hover=false;
                    ref.repaint();
                    repaint();
                }
                @Override
                public void mousePressed(MouseEvent mev){
                    if(mev.getButton() == MouseEvent.BUTTON1){
                        clicked=true;
                        ref.repaint();
                        repaint();
                    }
                }
                @Override
                public void mouseReleased(MouseEvent mev){
                    if(mev.getButton() == MouseEvent.BUTTON1){
                        clicked=false;
                        ref.repaint();
                        repaint();
                    }
                }
        }
        
        @Override
        public void paintComponent(Graphics g){
             Graphics2D g2d=(Graphics2D)g;
             g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
             Paint oldPaint=g2d.getPaint();
             Color oldColor=g2d.getColor();
             if(!hover)
                g2d.setPaint(painter1);
             else
                g2d.setPaint(painter2); 
             g2d.fillRect(0,0,btnW,btnH);
             g2d.setPaint(oldPaint);
             if(hover)
                g2d.setColor(new Color(150,150,150));
             else
                g2d.setColor(new Color(250,250,250));
             g2d.fillPolygon(traingleX,traingleY,3);
             g2d.setColor(oldColor);
        }
        @Override
        public void paintBorder(Graphics g){
            if(clicked){
                Insets inset=getInsets();
                Graphics2D g2d=(Graphics2D)g;
                Color oldColor=g2d.getColor();
                g2d.setColor(new Color(0.2f,0.4f,0.6f,0.4f));
                g2d.fillRect(0,0,getWidth(),1);
                g2d.fillRect(0,getHeight()-1,getWidth(),1);
                g2d.fillRect(0,1,1,getHeight()-2);
                g2d.fillRect(getWidth()-1,1,1,getHeight()-2);
                g2d.setColor(oldColor);
                g2d.dispose();
            }
        }
        @Override
        public Insets getInsets(){
            return new Insets(0,0,0,0);
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(btnW,btnH);
        }
        @Override
        public Dimension getMinimumSize() {
            return new Dimension(btnW,btnH);
        }
        @Override
        public Dimension getMaximumSize() {
           return new Dimension(btnW,btnH);
        }
 
   
}
