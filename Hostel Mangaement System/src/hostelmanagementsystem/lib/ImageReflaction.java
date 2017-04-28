package hostelmanagementsystem.lib;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

public class ImageReflaction {
    public static BufferedImage createReflactedImage(BufferedImage image){
        BufferedImage rslt=createReflactedImage(image,5);
        return rslt;
    }
    public static BufferedImage createReflactedImage(BufferedImage image,int offset){
        int width=image.getWidth();
        int height=image.getHeight();
        BufferedImage result=new BufferedImage(width,height<<1,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d=result.createGraphics();
        g2d.drawImage(image,0,0, null);
        g2d.scale(1.0,-1.0);
        g2d.drawImage(image,0,-((height<<1)+offset),null);
        g2d.scale(1.0,-1.0);
        g2d.translate(0,height+offset);
        
        GradientPaint mask=new GradientPaint(0,0,new Color(1.0f,1.0f,1.0f,0.5f),0,height/2,new Color(1.0f,1.0f,1.0f,1.0f));
        Paint oldPaint=g2d.getPaint();
        g2d.setComposite(AlphaComposite.DstOut);
        g2d.setPaint(mask);
        g2d.fillRect(0,0, width, height);
        g2d.dispose();
        return result;
    }
    
}
