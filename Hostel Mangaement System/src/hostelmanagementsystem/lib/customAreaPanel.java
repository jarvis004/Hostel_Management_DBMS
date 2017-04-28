
package hostelmanagementsystem.lib;
import javax.swing.SpringLayout;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;
public class customAreaPanel extends JPanel{
    private BufferedImage icon=null,result;
    private String headingText,description;
    private Color color1=new Color(220,220,220),color2=new Color(140,140,140);
    private Font font=new Font("Verdana",Font.BOLD,18);;
    private int fontSize=18,width,height,iconSeperation=10,textWidth=0;
    private boolean hasMouseOver=false;
    private Rectangle clickAllowableArea=null;
    private boolean clickAllowable=false;
    private String id=null;
    public customAreaPanel(int width,int height,BufferedImage img,String headingText,String description,String id){
        this.width=width;this.height=(height+img.getHeight()/2);this.icon=img;this.headingText=headingText;
        this.description=description;this.id=id;
        setOpaque(true);
        Font weightedFont=font.deriveFont(Font.BOLD,fontSize);
        panelMouseListener mslsnr=new panelMouseListener();
        panelMouseMotionListener msMotionListener=new panelMouseMotionListener();
        addMouseListener(mslsnr);
        addMouseMotionListener(msMotionListener);
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
    protected void paintComponent(Graphics g){
        if(result==null){
            result=new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
            Graphics2D im_g2d=result.createGraphics();
            im_g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            BufferedImage reflectedIcon=ImageReflaction.createReflactedImage(this.icon);
            im_g2d.drawImage(reflectedIcon,0,3,null);
            if(font!=null){
                Font oldFont=im_g2d.getFont();
                im_g2d.setFont(font);
                fontSize=im_g2d.getFont().getSize();
            }
            FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(font);
            textWidth=fm.stringWidth(this.headingText);
            Font weightedFont=im_g2d.getFont().deriveFont(Font.BOLD,fontSize);
            
            
            im_g2d.setFont(weightedFont);
            im_g2d.setColor(color1);
            im_g2d.drawString(this.headingText,icon.getWidth()+iconSeperation,fontSize);
            
            weightedFont=im_g2d.getFont().deriveFont(Font.BOLD,fontSize-7);
            im_g2d.setColor(color2);
            im_g2d.setFont(weightedFont);
            im_g2d.drawString(this.description,icon.getWidth()+iconSeperation+1,2*fontSize+1);
            
            im_g2d.dispose();
        }
        Graphics2D g2d=(Graphics2D)g;
        if(hasMouseOver && result!=null){
            Color oldColor=g2d.getColor();
            g2d.setColor(color1);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            g2d.drawLine(icon.getWidth()+iconSeperation,fontSize+5,icon.getWidth()+iconSeperation+textWidth,fontSize+5);
            g2d.setColor(oldColor);
        }
        else{
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        g2d.drawImage(result,0,0,null);
        g2d.dispose();
        super.paintComponent(g);
    }
    private class panelMouseListener extends MouseAdapter{
        @Override
        public void mouseExited(MouseEvent mev){
            hasMouseOver=false;
            repaint(0,0,getWidth(),getHeight());
            getParent().repaint();
        }
        @Override
        public void mouseClicked(MouseEvent mev){
            
        }
    }
    private class panelMouseMotionListener extends MouseMotionAdapter{
        @Override
        public void mouseMoved(MouseEvent mev){
            clickAllowableArea=new Rectangle(icon.getWidth()+iconSeperation,0,textWidth,fontSize+5);
            if(clickAllowableArea.contains(mev.getX(),mev.getY()))
                hasMouseOver=true;
            else
                hasMouseOver=false;
            repaint(0,0,getWidth(),getHeight());
            getParent().repaint();
        }
    }
    public boolean clickable(int x,int y){
        if(clickAllowableArea.contains(x, y))
            return true;
        else
            return false;
    }
    public String getID(){
        return this.id;
    }
}
