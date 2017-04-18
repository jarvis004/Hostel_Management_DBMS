package hostelmanagementsystem.lib;

import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.FontFormatException;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.RenderingHints;

import java.io.IOException;


public class smallCustomJLabel extends JLabel{
   private Color col;
   private String text;
   private Font font=null;
   private FontMetrics fm=null;
   private int textHeight,textWidth;
   private boolean underline;
   private boolean link=false;
   smallCustomJLabel(String text,Color col){
       this(text,col,13);
   }
   
   smallCustomJLabel(String text,Color col,int size){
       this(text,col,size,false,Font.BOLD);
   }
   smallCustomJLabel(String text,Color col,int size,boolean underline){
       this(text,col,size,underline,Font.BOLD);
   }
   smallCustomJLabel(String text,Color col,int size,boolean underline,int type){
       this.col=col;
       this.text=text;
       this.underline=underline;
       setFont(getFont().deriveFont(type,size));
       fm=Toolkit.getDefaultToolkit().getFontMetrics(getFont());
       textWidth=fm.stringWidth(text);
       textHeight=fm.getHeight();
       if(this.underline)
           setPreferredSize(new Dimension(textWidth,textHeight+9));
       else
           setPreferredSize(new Dimension(textWidth,textHeight+4));
       repaint();
   }
   public void changeText(String text){
       this.text=text;
       textWidth=fm.stringWidth(text);
       textHeight=fm.getHeight();
       if(this.underline)
           setPreferredSize(new Dimension(textWidth,textHeight+9));
       else
           setPreferredSize(new Dimension(textWidth,textHeight+4));
       revalidate();
       repaint();
   }
   @Override
   protected void paintComponent(Graphics g){
       Graphics2D g2d=(Graphics2D)g;
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(col);
       //g2d.setFont(font);
       if(link)
           g2d.drawString(text,0,textHeight+1);
       else
           g2d.drawString(text,0,textHeight);
      
       if(this.underline)
           g2d.drawLine(0,textHeight+5,getWidth(),textHeight+5);
       g2d.dispose();
   }
   public void drawLine(){
       this.underline=true;
       setPreferredSize(new Dimension(textWidth,textHeight+9));
       link=true;
       revalidate();
       repaint();
   }
   public void removeLine(){
       this.underline=false;
       link=false;
       revalidate();
       setPreferredSize(new Dimension(textWidth,textHeight+4));
       repaint();
   }
}
