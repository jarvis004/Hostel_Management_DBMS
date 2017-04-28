package hostelmanagementsystem.lib;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import javax.swing.JPanel;


import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.SpringLayout;

public class customLayeredPane extends JPanel{
   float alphaLevel=0.5f;
   private int width,height;
   private SpringLayout springLayout=new SpringLayout();
   customLayeredPane(){
         Globals.layeredPane=this;
         this.width=Globals.mainApp.getWidth();
         this.height=Globals.mainApp.getHeight();
         setBackground(new Color(10,10,10));
         setLayout(springLayout);
         setOpaque(false);
   }
   public void addComponent(JPanel comp,int x,int y){
        add(comp);
        springLayout.putConstraint(SpringLayout.WEST,comp,x,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,comp,y,SpringLayout.NORTH,this);
    }
   public void removeComponent(JPanel comp){
        remove(comp);
        revalidate();
        repaint();
    }
   public void removeAllComponent(){
       Component [] comp=Globals.layeredPane.getComponents();
       for(int i=0;i<comp.length;i++)
          Globals.layeredPane.remove(comp[i]);
       Globals.layeredPane.setVisible(false);
   }
    @Override
    public Dimension getPreferredSize(){
         return new Dimension(this.width,this.height);
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
