package hostelmanagementsystem.lib;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
public class customToolTip extends JLabel{
    private int width=1,height=1,rad=3,tw=5,th=5;
    private String text=null;
    private Color backgroundColor=new Color(25,25,25),forgroundColor=new Color(250,250,250);
    int [] traingleX={0,5,5};
    int [] traingleY={5,0,10};
    boolean upType=false,leftType=false;
    public customToolTip(int w,int h,String txt,boolean upType,boolean leftType){
        this.width=w;this.height=h;this.text=txt;
        this.upType=upType;
        this.leftType=leftType;
        setOpaque(false);
    }
    public void addArrowCords(int x1,int y1,int x2,int y2,int x3,int y3,int w,int h){
        traingleX[0]=x1;traingleX[1]=x2;traingleX[2]=x3;
        traingleY[0]=y1;traingleY[1]=y2;traingleY[2]=y3;
        this.tw=w;
        this.th=h;
        revalidate();
    }
    @Override
    public void setSize(int w,int h){
        this.width=w;this.height=h;
    }
    @Override
    public void setText(String txt){
        this.text=txt;
    }
    @Override
    public Dimension getPreferredSize(){
       return new Dimension(this.width,this.height);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Color oldCol=g2d.getColor();
        g2d.setColor(backgroundColor);
        g2d.fillPolygon(traingleX,traingleY,3);
        int arrowShiftY=0,arrowShiftX=0,arrowShiftR=0;
        if(upType)
           arrowShiftY=th;
        else if(leftType)
           arrowShiftX=tw;
        g2d.fillRoundRect(arrowShiftX,arrowShiftY, width, height,rad, rad);
        g2d.setColor(forgroundColor);
        g2d.setFont(new Font("Verdana",Font.BOLD,10));
        FontMetrics fmc=Toolkit.getDefaultToolkit().getFontMetrics(g2d.getFont());
                
        String [] textLines=text.split("\r\n|\r|\n");
        int offsetY=0;
        int i=0;
        for(String line:textLines){
           offsetY+=fmc.getHeight();
           if(i==0)
              g2d.drawString(line,5+arrowShiftX,arrowShiftY+offsetY-1);   //if the arrow is at top then arrow shift will be added
           else
              g2d.drawString(line,5+arrowShiftX,offsetY-1);
           i++;
         }
         g2d.setColor(oldCol);
   }
}
