package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;


import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontMetrics;

import java.util.List;
import java.util.ArrayList;
public class tooltipLayerdPane extends JPanel{
    public StackLayout tooltipLayeredLayout=new StackLayout();
    private List<customToolTip> allToolTips=new ArrayList<customToolTip>();
    private List<String> allToolTipsName=new ArrayList<String>();
    customToolTip tooltip;
    tooltipLayerdPane(){
        Globals.ttlp=this;
        setOpaque(false);
        setLayout(tooltipLayeredLayout);
    }
    public void addComponent(JPanel comp,int compWidth,int compHeight){
        add(comp,StackLayout.TOP);
        comp.setBounds(0, 0, compWidth, compHeight);
    }
    public void addCustomToolTips(int w,int h,String txt,String toolTipName,int tx1,int ty1,int tx2,int ty2,int tx3,int ty3){
        addCustomToolTips(w,h,txt,toolTipName,tx1,ty1,tx2,ty2,tx3,ty3,true);
    }
    public void addCustomToolTips(int w,int h,String txt,String toolTipName,int tx1,int ty1,int tx2,int ty2,int tx3,int ty3,boolean visibility){
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
       tooltip=new customToolTip(w,h+strinHeight,txt,upType,leftType);
                                                                                        //adding tooltip and its name to List
       allToolTips.add(tooltip);
       allToolTipsName.add(toolTipName);
       
       tooltip.addArrowCords(tx1, ty1+strinHeight, tx2, ty2+strinHeight, tx3, ty3+strinHeight,tw,th);
       tooltip.setBounds(0, 0, w+tw, h+strinHeight+th);
       tooltip.setVisible(visibility);
       add(tooltip,StackLayout.TOP);
       revalidate();
    }
    public void changeCordinates(int preX,int preY,int newX,int newY){
       tooltipLayeredLayout.changeCordinates(preX,preY,newX,newY);
       validate();
       revalidate();
   }
    public void removeCustomToolTips(String toolTipName){
        int tooltipIndex=allToolTipsName.indexOf(toolTipName);
        if(tooltipIndex!=-1){
            remove(allToolTips.get(tooltipIndex));    //fetching the tooltip to delete from container
            allToolTips.remove(tooltipIndex);
            allToolTipsName.remove(tooltipIndex);
            revalidate();
            repaint();
        }
    }
    public int isToolTipExist(String toolTipName){
        int tooltipIndex=allToolTipsName.indexOf(toolTipName);
        return tooltipIndex;
    }
    
    public int getNoOfTooltip(){
       return allToolTips.size();
    }
    public customToolTip getTooltip(String toolTipName){
        int tooltipIndex=allToolTipsName.indexOf(toolTipName);
        if(tooltipIndex!=-1){
            return allToolTips.get(tooltipIndex);   //fetching the tooltip to delete from container
        }
        return null;
    }
    public void setTooltipText(String toolTipName,String txt){
        int tooltipIndex=allToolTipsName.indexOf(toolTipName);
        if(tooltipIndex!=-1){
            allToolTips.get(tooltipIndex).setText(txt);    //fetching the tooltip to delete from container
            revalidate();
        }
    }
    
}
