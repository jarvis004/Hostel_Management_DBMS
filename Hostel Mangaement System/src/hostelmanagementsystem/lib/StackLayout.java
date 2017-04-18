
package hostelmanagementsystem.lib;
import java.awt.LayoutManager2;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Insets;
import java.util.List;
import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.Point;

import java.util.List;
import java.util.ArrayList;


public class StackLayout implements LayoutManager2{
   public static final String TOP="top";
   public static final String BOTTOM="bottom";
   private List<Point> cords=new ArrayList<Point>();
   private List<Component> components=new LinkedList<Component>();
   
   public void addLayoutComponent(Component comp,Object constraints){
       synchronized(comp.getTreeLock()){
            if(BOTTOM.equals(constraints))
                components.add(0,comp);
            else if (TOP.equals(constraints)) {
                components.add(comp);
            } else {
                components.add(comp);
            }
       }
   }
   public void addCordinates(int x,int y){
       cords.add(new Point(x,y));
   }
   public void changeCordinates(int preX,int preY,int newX,int newY){
       int index=cords.indexOf(new Point(preX,preY));
       cords.remove(index);
       cords.add(index,new Point(newX,newY));
   }
   public void addLayoutComponent(String string,Component comp){
       addLayoutComponent(comp, TOP);
   }
   public void removeLayoutComponent(Component comp){
       synchronized (comp.getTreeLock()) {
            int index=components.indexOf(comp);
            components.remove(index);
            cords.remove(index);
        }
   }
   public float getLayoutAlignmentX(Container ct){
       return 0.5f;
   }
   public float getLayoutAlignmentY(Container ct){
       return 0.5f;
   }
   public void invalidateLayout(Container ct){
       
   } 
   public Dimension preferredLayoutSize(Container ct){
       synchronized(ct.getTreeLock()){
            int width=0;
            int height=0;
            for(Component comp:components){
                Dimension size=comp.getPreferredSize();
                width=Math.max(size.width, width);
                height=Math.max(size.height,height);
            }
            Insets insets=ct.getInsets();
            width+=insets.left+insets.right;
            height+=insets.top+insets.bottom;
            return new Dimension(width,height);
       }
   }
   public Dimension minimumLayoutSize(Container ct){
       synchronized(ct.getTreeLock()){
            int width=0;
            int height=0;
            for(Component comp:components){
                Dimension size=comp.getMinimumSize();
                width=Math.max(size.width, width);
                height=Math.max(size.height,height);
            }
            Insets insets=ct.getInsets();
            width+=insets.left+insets.right;
            height+=insets.top+insets.bottom;
            return new Dimension(width,height);
       }
   }
   public Dimension maximumLayoutSize(Container ct){
       return new Dimension(Integer.MAX_VALUE,Integer.MIN_VALUE);
   }
   public void layoutContainer(Container ct){
       synchronized(ct.getTreeLock()){
           int componentsCount=components.size();
           for(int i=0;i<componentsCount;i++){
              try{
                Component comp=components.get(i);
                comp.setBounds(new Rectangle(0,0,comp.getWidth(),comp.getHeight()));
                comp.setLocation(cords.get(i).x,cords.get(i).y);
                ct.setComponentZOrder(comp, componentsCount-i-1);
              }
              catch(Exception e){
                  //do nothing
              }
            }
           
       }
   }
}
