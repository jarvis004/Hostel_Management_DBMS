package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.SpringLayout;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Composite;
import java.awt.AlphaComposite;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;

import java.util.List;
import java.util.ArrayList;
public class glassPane extends JComponent{
    public float alphaLevel=0.5f;
    public StackLayout glassPaneLayout=new StackLayout();
    public List<customToolTip> allToolTips=new ArrayList<customToolTip>();
    public List<String> allToolTipsName=new ArrayList<String>();
    public static final Color[] GradientColors=new Color[]{
        Color.GRAY,Color.DARK_GRAY,Color.BLACK,Color.GRAY
    };
    public static final float[] gradientFraction=new float[]{
      0.0f,0.499f,0.5f,1.0f  
    };
    private int width,height;
    public glassPane(int width,int height){
        Globals.currentGlassPane=this;
        alphaLevel=0.5f;
        this.width=width-6;
        this.height=height-28;
        setBackground(new Color(10,10,10));
        setFont(new Font("Default Font",Font.BOLD,16));
        setLayout(glassPaneLayout);
        
    }
    public void addComponent(JPanel comp,int compWidth,int compHeight){
        add(comp,StackLayout.TOP);
        int x=(this.width-compWidth)/2;
        int y=(this.height-compHeight)/2;
        comp.setBounds(x, y, compWidth, compWidth);
    }
    public void removeComponent(JPanel comp){
        remove(comp);
        revalidate();
        repaint();
    }
    public void addCustomToolTips(int w,int h,String txt,String toolTipName,int tx1,int ty1,int tx2,int ty2,int tx3,int ty3){
      //if the string lines are more then one line
       String [] lines=txt.split("\r\n|\r|\n");
       FontMetrics fmc=Toolkit.getDefaultToolkit().getFontMetrics(new Font("Verdana",Font.BOLD,10));
       int strinHeight=0;
       if(lines.length>0)
         strinHeight=((lines.length-1)*fmc.getHeight())-1;
                                        //for checking whether the arrow is upward or downward
       int maxY=ty1-ty2>0?ty1-ty3>0?ty1:ty3:ty2-ty3>0?ty2:ty3;
       int minY=ty1-ty2<0?ty1-ty3<0?ty1:ty3:ty2-ty3<0?ty2:ty3;
       boolean upType=(maxY==ty1 && maxY==ty2) && maxY!=ty3?true:(maxY==ty1 && maxY==ty3) && maxY!=ty2?true:(maxY==ty2 && maxY==ty3) && maxY!=ty1?true:false;
       int maxX=tx1-tx2>0?tx1-tx3>0?tx1:tx3:tx2-tx3>0?tx2:tx3;
       int minX=tx1-tx2<0?tx1-tx3<0?tx1:tx3:tx2-tx3<0?tx2:tx3;
       boolean leftType=(maxX==tx1 && maxX==tx2) && maxX!=tx3?true:(maxX==tx1 && maxX==tx3) && maxX!=tx2?true:(maxX==tx2 && maxX==tx3) && maxX!=tx1?true:false;
       int tw=tx1-tx2==0?tx1-tx3==0?tx2-tx3==0?0:Math.abs(tx2-tx3):Math.abs(tx1-tx3):Math.abs(tx1-tx2);
       int th=ty1-ty2==0?ty1-ty3==0?ty2-ty3==0?0:Math.abs(ty2-ty3):Math.abs(ty1-ty3):Math.abs(ty1-ty2);
       customToolTip tooltip=new customToolTip(w,h+strinHeight,txt,upType,leftType);
                                                                                        //adding tooltip and its name to List
       allToolTips.add(tooltip);
       allToolTipsName.add(toolTipName);
       
       tooltip.addArrowCords(tx1, ty1+strinHeight, tx2, ty2+strinHeight, tx3, ty3+strinHeight,tw,th);
       tooltip.setBounds(0, 0, w+tw, h+strinHeight+th);
       add(tooltip,StackLayout.TOP);
    }
    public void removeCustomToolTips(String toolTipName){
        int tooltipIndex=allToolTipsName.indexOf(toolTipName);
        if(tooltipIndex!=-1){
            remove(allToolTips.get(tooltipIndex));    //fetching the tooltip to delete from container
            allToolTips.remove(tooltipIndex);
            allToolTipsName.remove(tooltipIndex);
            revalidate();
        }
    }
    public int isToolTipExist(String toolTipName){
        int tooltipIndex=allToolTipsName.indexOf(toolTipName);
        return tooltipIndex;
    }
    @Override
    public void setVisible(boolean val){
        addMouseListener(new MouseAdapter() { });
        addMouseMotionListener(new MouseMotionAdapter() { });
        addKeyListener(new KeyAdapter() { });
        requestFocusInWindow();
        setFocusTraversalKeysEnabled(false);
        super.setVisible(val);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        Rectangle clip=g2d.getClipBounds();
        AlphaComposite composite=AlphaComposite.SrcOver.derive(alphaLevel);
        Composite oldComposite=g2d.getComposite();
        g2d.setComposite(composite);
        g2d.setColor(getBackground());
        g2d.fillRect(clip.x, clip.y,clip.width,clip.height);
        g2d.setComposite(oldComposite);
    }
}
