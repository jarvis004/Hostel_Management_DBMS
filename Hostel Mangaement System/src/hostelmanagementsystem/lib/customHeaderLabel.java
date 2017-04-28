
package hostelmanagementsystem.lib;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Paint;
import java.awt.LinearGradientPaint;
import java.awt.Color;
import java.awt.Dimension;


public class customHeaderLabel extends JLabel{
        private int width,height,brdRadius,stringX,stringY;
        String labelString;
        LinearGradientPaint paint;
        Color stringColor;
        Font font;
        public customHeaderLabel(String string,int width,int height,int radius,LinearGradientPaint paint,int x,int y,Color col){
            this(string,width,height,radius,paint,x,y,col,null);
        }
        public customHeaderLabel(String string,int width,int height,int radius,LinearGradientPaint paint,int x,int y,Color col,Font font){
            this.labelString=string;
            this.width=width;
            this.height=height;
            this.width=width;
            this.brdRadius=radius;
            this.paint=paint;
            this.stringX=x;
            this.stringY=y;
            this.stringColor=col;
            this.font=font;
            setOpaque(true);
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2d=(Graphics2D)g;
            Paint oldPaint=g2d.getPaint();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(this.paint);
            g2d.fillRoundRect(0,0,this.width,this.height,this.brdRadius,this.brdRadius);
            g2d.setPaint(oldPaint);
            
            Color oldColor=g2d.getColor();
            g2d.setColor(Color.BLACK);
            g2d.drawLine(0,this.height+1,this.width,this.height+1);
            g2d.setColor(this.stringColor);
            int fontSize=g2d.getFont().getSize();
            if(font!=null)
               g2d.setFont(this.font);
            g2d.drawString(this.labelString,this.stringX,this.stringY);
            g2d.setColor(oldColor);
            g2d.dispose();
            super.paintComponent(g);
            
        }
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(this.width,this.height+2);
        }
        @Override
        public Dimension getMinimumSize(){
            return new Dimension(this.width,this.height+2);
        }
        @Override
        public Dimension getMaximumSize(){
            return new Dimension(this.width,this.height+2);
        }
    }
    
