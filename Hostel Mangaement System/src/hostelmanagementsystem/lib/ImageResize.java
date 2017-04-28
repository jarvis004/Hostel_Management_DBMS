package hostelmanagementsystem.lib;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
public class ImageResize {
   public static BufferedImage resize(BufferedImage input,int startWidth, int startHeight,int endWidth, int endHeight) {
        int currentWidth = startWidth;
        int currentHeight=startHeight;
        BufferedImage currentImage = input;
        int delta = currentWidth - endWidth;
        int sizeBy2 = currentWidth >> 1;
        int i=0;
        while (currentWidth > 1) {
            if (delta <= sizeBy2) {
                if (currentWidth != endWidth) {
                    BufferedImage tmp = new BufferedImage(endWidth,endHeight, BufferedImage.TYPE_INT_RGB);
                    Graphics g = tmp.getGraphics();
                    ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g.drawImage(currentImage, 0, 0, tmp.getWidth(),tmp.getHeight(), null);
                    currentImage = tmp;
                }
                return currentImage;
            } else {
                BufferedImage tmp = new BufferedImage(currentWidth >> 1,currentHeight >> 1, BufferedImage.TYPE_INT_RGB);
                Graphics g = tmp.getGraphics();
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(currentImage, 0, 0, tmp.getWidth(), tmp.getHeight(), null);
                currentImage = tmp;
                currentWidth = currentImage.getWidth();
                currentHeight = currentImage.getHeight();
                delta = currentWidth - endWidth;
                sizeBy2 = currentWidth >> 1;
            }
            i++;
        }
        return currentImage;
    }
   
   public static BufferedImage desiredSizeImage(BufferedImage icon,int width,int height){
       BufferedImage tmp = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
       Graphics2D g2d=tmp.createGraphics();
       g2d.drawImage(icon,0,0,width,height,0,0,width,height,null);
       return tmp;
   }
}
