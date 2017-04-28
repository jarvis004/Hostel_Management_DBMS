
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
import java.awt.GradientPaint;
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Insets;
import java.awt.Font;
public class customButtonPanel extends JPanel {
   private BufferedImage result = null,src=null;
   private int shadowSize=5,btnW,btnH,shadowOffset,brdRad;
   private boolean fastRendering = false,displayShadow=false,btnClicked=false;
   private float shadowOpacity;
   private String labelText;
   public customButton cbt;
   private boolean enabled=true;
   customButtonPanel ref;
   private Font buttonFont;
   public customButtonPanel(String labelText,int width,int height,int rad,int offset){this(labelText,width,height,rad,offset,0.2f);}
   public customButtonPanel(String labelText,int width,int height,int rad){this(labelText,width,height,rad,3,0.2f);}
   public customButtonPanel(String labelText,int width,int height,int rad,Font font){this(labelText,width,height,rad,3,0.2f,font);}
   public customButtonPanel(String labelText,int width,int height){this(labelText,width,height,3,3,0.2f);}
   public customButtonPanel(){this("button",100,30,3,3,0.2f);}
   public customButtonPanel(String labelText,int width,int height,int borderRadius,int offset,float shadowOpacity){this(labelText,width,height,borderRadius,offset,shadowOpacity,null);}     
   public customButtonPanel(String labelText,int width,int height,int borderRadius,int offset,float shadowOpacity,Font font) {
        setFocusable(false);
        buttonFont=font;
        ref=this;
        setOpaque(false);
        this.labelText=labelText;
        this.btnW=width;
        this.btnH=height;
        this.brdRad=borderRadius;
        this.shadowOffset=offset;
        this.shadowOpacity=shadowOpacity;
        src=new BufferedImage(btnW,btnH,BufferedImage.TYPE_INT_ARGB);
        SpringLayout springLayout=new SpringLayout();
        setLayout(springLayout);
        add(cbt=new customButton(this.labelText));
        springLayout.putConstraint(SpringLayout.WEST,cbt,shadowOffset,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,cbt,shadowOffset,SpringLayout.NORTH,this);
   }
    @Override
   public Dimension getPreferredSize() {
      return new Dimension(btnW+4*shadowSize,btnH+4*shadowSize);
   }
   @Override
   public Dimension getMinimumSize() {
      return new Dimension(btnW+4*shadowSize,btnH+4*shadowSize);
   }
   @Override
   public Dimension getMaximumSize() {
      return new Dimension(btnW+4*shadowSize,btnH+4*shadowSize);
   }
   @Override
   protected void paintComponent(Graphics g) {
        if(displayShadow && !btnClicked){
            Graphics2D g2d=src.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillRoundRect(0,0,btnW,btnH,brdRad,brdRad);
            
            if (result == null) {
                result = createDropShadow(g,src, shadowSize);
            }
            g2d.dispose();
            int x =0;
            int y = 0;
            if (!fastRendering) {
                g.drawImage(result, x - shadowSize * 2 + 5, y - shadowSize * 2 + 5, null);
            } else {
                g.drawImage(result, x - shadowSize / 2 + 5, y - shadowSize / 2 + 5, null);
            }
         }  
    }
    public class customButton extends JButton{
        btnMsListener mslstn=new btnMsListener();
        public customButton(String txt){
                super(txt);
                if(buttonFont!=null){
                    setFont(buttonFont);
                    setForeground(new Color(110,110,110));
                }
                setFocusPainted(false);
                setContentAreaFilled(false);
                addMouseListener(mslstn);
                
        }
        @Override
        public void setEnabled(boolean val){
            super.setEnabled(val);
            enabled=val;
            if(!enabled){
               setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
               displayShadow=false;
               removeMouseListener(mslstn);
            }
            else{
               setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
               addMouseListener(mslstn);
            }
            ref.repaint();
            repaint();
        }
        private class btnMsListener extends MouseAdapter{
                @Override
                public void mouseEntered(MouseEvent mev){
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    displayShadow=true;
                    ref.repaint();
                    repaint();
                }
                @Override
                public void mouseExited(MouseEvent mev){
                    displayShadow=false;
                    ref.repaint();
                    repaint();
                }
                @Override
                public void mousePressed(MouseEvent mev){
                    if(mev.getButton() == MouseEvent.BUTTON1){
                        btnClicked=true;
                        ref.repaint();
                        repaint();
                    }
                }
                @Override
                public void mouseReleased(MouseEvent mev){
                    if(mev.getButton() == MouseEvent.BUTTON1){
                        btnClicked=false;
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
             if(enabled){
                GradientPaint paint=null;
                if(displayShadow)
                   paint=new GradientPaint(0, 0, new Color(0xFFFFFF),0, getHeight(), new Color(0xC8D2DE));
                else
                   paint=new GradientPaint(0, 0, new Color(0xeeeeee),0, getHeight(), new Color(0xc3cdd9));
                g2d.setPaint(paint);
             }
             else{
                 Color col=new Color(255,255,255,20);
                 g2d.setColor(col);
             }
             g2d.fillRoundRect(0,0,btnW,btnH,brdRad,brdRad);
             if(enabled)
                g2d.setPaint(oldPaint);
             else
                 g2d.setColor(oldColor);
             super.paintComponent(g);
        }
        @Override
        public void paintBorder(Graphics g){
           if(btnClicked){
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
    
  public BufferedImage createDropShadow(Graphics g,BufferedImage image,int size) {
        BufferedImage shadow = new BufferedImage(
            image.getWidth() + 4 * size,
            image.getHeight() + 4 * size,
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = shadow.createGraphics();
        g2.drawImage(image, 2*size, 2*size, null);
        g2.setComposite(AlphaComposite.SrcIn);
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, shadow.getWidth(), shadow.getHeight());       
        
        g2.dispose();
        
        shadow = getGaussianBlurFilter(size, true).filter(shadow, null);
        shadow = getGaussianBlurFilter(size, false).filter(shadow, null);
        
        return shadow;
  }
    
  public ConvolveOp getGaussianBlurFilter(int radius,
            boolean horizontal) {
        if (radius < 1) {
            throw new IllegalArgumentException("Radius must be >= 1");
        }
        int size = radius * 2 + 1;
        float[] data = new float[size];
        
        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;
        
        for (int i = -radius; i <= radius; i++) {
            float distance = i * i;
            int index = i + radius;
            data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
            total += data[index];
        }
        
        for (int i = 0; i < data.length; i++) {
            data[i] /= total;
        }        
        
        Kernel kernel = null;
        if (horizontal) {
            kernel = new Kernel(size, 1, data);
        } else {
            kernel = new Kernel(1, size, data);
        }
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
   }
}