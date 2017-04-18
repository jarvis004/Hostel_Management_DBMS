
package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Paint;
import java.awt.LinearGradientPaint;
import java.awt.Color;
import java.awt.Dimension;
public class customBackgroundPanel extends JPanel{
        private int width,height;
        private LinearGradientPaint paint;
        public customBackgroundPanel(int width,int height){
            this(width,height,null);
        }
        public customBackgroundPanel(int width,int height,LinearGradientPaint paint){
            Globals.CustomBackgroundPanel=this;
            if(paint==null)
                this.paint=new LinearGradientPaint(0.0f, 0.0f, 0.0f,getHeight(),
                           new float[] { 0.0f,0.03f,0.075f,0.076f,0.097f,0.5f,1.0f },
                           new Color[] {new Color(99,108,124),
                                        new Color(23,31,47),
                                        new Color(23,31,47),
                                        new Color(164,168,179),
                                        new Color(32,39,45),
                                        new Color(60,69,87),
                                        new Color(150,161,187) }
                           );
            else
                this.paint=paint;
            this.width=width;
            this.height=height;
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
            Graphics2D g2d=(Graphics2D)g;
            Paint oldPaint=g2d.getPaint();
            g2d.setPaint(this.paint);
            g2d.fillRect(0,0,this.width,this.height);
            g2d.setPaint(oldPaint);
        }
        
    }
