package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.Cursor;

import java.io.File;

import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PictureChooser extends JPanel{
    BufferedImage icon=null;
    private BufferedImage shadow=null,iconWithBorder=null,intrim=null,tmpConv;
    private int width=100,height=100,rad=7,shadowSize=5,brd=4,picW=100,picH=100;
    private boolean clicked=false,displayShadow=false,fileChoosed=false;
    private FileChooserDialog picChooser;
    File img,selectedFile=null;
    PictureChooser ref;
    Timer timer;
    PictureChooser(){
        ref=this;
        try{
            icon=ImageIO.read(getClass().getResource("icons/NPP.png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(width+2*brd+2*shadowSize+3,height+2*brd+3*shadowSize+1));  ///1 is added due to the offset in paintComponent
        addMouseListener(new pcMsListener());
        setOpaque(false);
    }
    File getFileChoosed(){
        if(picChooser!=null){
           if(picChooser.getSelectedFile()==null)
              return null;
           return picChooser.getSelectedFile();
        }
        return null;
    }
    String getChoosedFileExtension(){
        if(picChooser!=null){
           if(picChooser.getSelectedFile()==null)
              return null;
           return picChooser.getChoosedFileExtension();
        }
        return null;
    }
    private class pcMsListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent mev){
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            displayShadow=true;
            repaint();
        }
        @Override
        public void mouseExited(MouseEvent mev){
            displayShadow=false;
            repaint();
        }
        @Override
        public void mousePressed(MouseEvent mev){
            if(mev.getButton() == MouseEvent.BUTTON1){
                clicked=true;
                repaint();
            }
        }
        @Override
        public void mouseReleased(MouseEvent mev){
            if(mev.getButton() == MouseEvent.BUTTON1){
                 clicked=false;
                 repaint();
            }
        }
        @Override
        public void mouseClicked(MouseEvent mev){
            if(mev.getButton() == MouseEvent.BUTTON1){
                picChooser=new FileChooserDialog("*.jpg;*.jpeg;*.png;*.gif");
                timer=new Timer(10,new ActionListener(){
                    public void actionPerformed(ActionEvent acev){
                        img=picChooser.getSelectedFile();
                        if(img!=null){
                            try{
                                timer.stop();
                                icon=ImageIO.read(picChooser.getSelectedFile());
                                picW=icon.getWidth();
                                picH=icon.getHeight();
                                float ratio=0;
                                if(picW>picH){
                                    ratio=(float)picH/100f;
                                    picH=100;
                                    picW/=ratio;
                                }
                                else{
                                    ratio=(float)picW/100f;
                                    picW=100;
                                    picH/=ratio;
                                }
                                icon=ImageResize.resize(icon,icon.getWidth(),icon.getHeight(),picW,picH);
                                icon=ImageResize.desiredSizeImage(icon,100,100);
                                fileChoosed=true;
                                ref.repaint();
                            }
                            catch(IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                timer.start();
                    
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if(iconWithBorder==null || fileChoosed){
            iconWithBorder=new BufferedImage(width+2*brd,height+2*brd,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2=iconWithBorder.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(250,250,250));
            g2.fillRoundRect(0,0,width+2*brd,height+2*brd,rad,rad);
            g2.drawImage(icon,brd,brd,width+brd,height+brd,0,0,width,height,null);
        }
        if(intrim==null){
            intrim=new BufferedImage(iconWithBorder.getWidth(),iconWithBorder.getHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D im_g2d=intrim.createGraphics();
            im_g2d.setColor(new Color(255,255,255));
            im_g2d.fillRoundRect(0,0,iconWithBorder.getWidth(),iconWithBorder.getHeight(),rad,rad);
        }
        int x = (getWidth() - iconWithBorder.getWidth()) / 2;
        int y = (getHeight() - iconWithBorder.getHeight()) / 2;
        int offset=0;
        if(displayShadow){
            if(shadow==null)
               shadow=createDropShadow(g,intrim,shadowSize);
            offset=1;
            Graphics2D g2s = (Graphics2D) g;
            Composite oldComposite = g2s.getComposite();
            g2s.setComposite(AlphaComposite.SrcOver.derive(0.5f));
            
            g.drawImage(shadow, x - shadowSize * 2 +offset+ 5, y - shadowSize * 2+offset + 5, null);
            g2d.setComposite(oldComposite);
       }
       g2d.drawImage(iconWithBorder,x+offset,y+offset,null);
    }
    public BufferedImage createDropShadow(Graphics g,BufferedImage image,
            int size) {
        BufferedImage shadow = new BufferedImage(
            image.getWidth() + 4 * size,
            image.getHeight() + 4 * size,
            BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = shadow.createGraphics();
        g2.drawImage(image, size * 2, size * 2, null);
        
        g2.setComposite(AlphaComposite.SrcIn);
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, shadow.getWidth(), shadow.getHeight(),rad,rad);       
        
        g2.dispose();
        
        shadow = getGaussianBlurFilter(size, false).filter(shadow, null);
        shadow = getGaussianBlurFilter(size, true).filter(shadow, null);
        
        return shadow;
    }
    
    public static ConvolveOp getGaussianBlurFilter(int radius,
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
