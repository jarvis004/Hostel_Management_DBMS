package hostelmanagementsystem.lib;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;
import java.util.LinkedList;
import java.io.IOException;
public class loadingIcon extends JLabel{
    private List<BufferedImage> icons=null;
    private BufferedImage img=null;
    private String icon_name="";
    private int counter=0;
    Timer animationTimer=null;
    loadingIcon(){
      setOpaque(false);
      icons=new LinkedList<BufferedImage>();
      for(int i=0;i<16;i++){
         try{
            icon_name="ic"+i;
            icons.add(i,ImageIO.read(getClass().getResource("icons/loading_icon/"+icon_name+".png")));
         }catch(IOException e){
            e.printStackTrace();
         }
      }
       animationTimer=new Timer(30,new ActionListener(){
          public void actionPerformed(ActionEvent acev){
              if(counter==15)
                  counter=1;
              else
                  counter++;
              repaint();
          }    
       });
       animationTimer.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(icons.get(counter),1,1,null);
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(20,20);
    }
}
