package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Cursor;
import java.awt.Composite;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.RenderingHints;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.SpringLayout;

public class PicSlider extends JPanel{
    private List<Image> avtars;
    private Thread pictureLoader;
     private static final double SCROLL_DELAY = 450;
    
    private Timer faderTimer;
    private Timer scrollerTimer;
    
    private boolean loadingDone=false;
    private boolean damaged=true;
    
    private int avtarIndex;
    private double avtarPosition=0.0;
    private double avtarSpacing=0.4;
    private int avtarAmount=5;
    
    private double sigma;
    private double rho;
    private double exp_multiplier;
    private double exp_member;
    
    private float alphaLevel=1.0f;
    
    private DrawableAvtar[] drawableAvtars;
    
    FocusGrabber focusGrabber;
    AvtarScroller avtarScroller;
    CursorChanger cursorChanger;
    MouseWheelScroller mouseWheelScroller;
    KeyScroller keyScroller;
    String name="",roll="",batch="",stream="";
    String [] studentsPic;
    String [] studentName;
    String [] studentStream;
    String [] studentRoll;
    String [] studentBatch;
    smallCustomJLabel nameLabel,rollLabel,streamLabel,batchLabel;
    public PicSlider(String [] picName,String [] stName,String [] stRoll,String [] stBatch,String [] stStream){
        this.studentsPic=picName;
        this.studentName=stName;
        this.studentRoll=stRoll;
        this.studentBatch=stBatch;
        this.studentStream=stStream;
        this.name=stName[0];
        this.roll=stRoll[0];
        this.batch=stBatch[0];
        this.stream=stStream[0];
        setPreferredSize(new Dimension(600,220));
        SpringLayout springLayout=new SpringLayout();
        setLayout(springLayout);
        
        
        findAvtar();
        setSigma(0.5f);
        
        initInputListener();
        addInputListener();
        
        
        nameLabel=new smallCustomJLabel(name,new Color(250,250,250),11);
        add(nameLabel);
        springLayout.putConstraint(SpringLayout.WEST,nameLabel,255,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,nameLabel,0,SpringLayout.NORTH,this);
        
        rollLabel=new smallCustomJLabel(roll,new Color(250,250,250),11);
        add(rollLabel);
        springLayout.putConstraint(SpringLayout.WEST,rollLabel,255,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,rollLabel,-3,SpringLayout.SOUTH,nameLabel); 
        
        streamLabel=new smallCustomJLabel(stream,new Color(250,250,250),11);
        add(streamLabel);
        springLayout.putConstraint(SpringLayout.WEST,streamLabel,255,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,streamLabel,-3,SpringLayout.SOUTH,rollLabel); 
        
        smallCustomJLabel dashLabel=new smallCustomJLabel("_",new Color(250,250,250),11);
        add(dashLabel);
        springLayout.putConstraint(SpringLayout.WEST,dashLabel,6,SpringLayout.EAST,streamLabel);
        springLayout.putConstraint(SpringLayout.NORTH,dashLabel,-8,SpringLayout.SOUTH,rollLabel);
        
        batchLabel=new smallCustomJLabel(batch,new Color(250,250,250),11);
        add(batchLabel);
        springLayout.putConstraint(SpringLayout.WEST,batchLabel,5,SpringLayout.EAST,dashLabel);
        springLayout.putConstraint(SpringLayout.NORTH,batchLabel,-3,SpringLayout.SOUTH,rollLabel);
        
        
        
    }
    private void drawLabels(){
      name=this.studentName[avtarIndex];
      roll=this.studentRoll[avtarIndex];
      batch=this.studentBatch[avtarIndex];
      stream=this.studentStream[avtarIndex];
      nameLabel.changeText(name);
      rollLabel.changeText(roll); 
      batchLabel.changeText(batch);
      streamLabel.changeText(stream);
    }
    private void findAvtar(){
        avtars=new ArrayList<Image>();
        pictureLoader=new Thread(new PictureLoaderThread());
        pictureLoader.start();
    }
    class PictureLoaderThread implements Runnable{
        public void run(){
            try{
                for(int i=0;i<studentsPic.length;i++){
                    URL url = new URL(Globals.domain+"/HMS/Student_Pic/"+studentsPic[i]);
                    BufferedImage pic=ImageIO.read(url); 
                    avtars.add(ImageReflaction.createReflactedImage(pic,0));
                        setIndex(0);
                }
                repaint();
            }catch(IOException e){
                e.printStackTrace();
            }
            loadingDone=true;
        }
    }
    @Override
    public boolean isOpaque() {
        return false;
    }
    private void setIndex(int index){
        avtarIndex=index;
    }
    
    
    
    
    
    
    
    private void setSigma(float sigma){
        this.sigma = sigma;
        this.rho = 1.0;
        ComputeEquationPart();
        this.rho = computeModifierUnprotected(0.0);
        ComputeEquationPart();
        this.damaged = true;
        repaint();
    }
    
    
    
  private class DrawableAvtar implements Comparable{
      private int index,width,height;
      private double x,y,zOrder,position;
      DrawableAvtar(int index,double x,double y,int width,int height,double position,double zOrder){
          this.index=index;this.x=x;this.y=y;this.width=width;this.height=height;this.position=position;this.zOrder=zOrder;
      }
      public int compareTo(Object o){
          double zOrder2=((DrawableAvtar)o).zOrder;
          if(zOrder<zOrder2)
              return -1;
          else if(zOrder>zOrder2)
              return 1;
          else
              return 0;
      }
      public int getIndex(){
          return index;
      }
      public double getX(){
          return x;
      }
      public double getY(){
          return y;
      }
      public int getWidth(){
          return width;
      }
      public int getHeight(){
          return height;
      }
      public double getPosition(){
          return position;
      }
      public double getAlpha(){
          return zOrder*alphaLevel;
      }
  } 
  
  @Override
  public boolean isFocusable() {
        return true;
  }
  @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Composite oldComposite = g2.getComposite();
        super.paintChildren(g);
        g2.setComposite(oldComposite);
    }
  @Override
  public void paintComponent(Graphics g){
      super.paintComponent(g);
      if(!loadingDone && faderTimer==null){
          return;
      }
      Insets insets=getInsets();
      int x=insets.left;
      int y=insets.top;
      int width=getWidth()-insets.left-insets.right;
      int height=getHeight()-insets.top-insets.bottom;
      
      Graphics2D g2=(Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
      Composite oldComposite = g2.getComposite();
      if(damaged){
          drawableAvtars=sortAvtarByDepth(x,y,width,height);
          damaged=false;
      }
      drawAvtars(g2,drawableAvtars);
      g2.setComposite(oldComposite);
   }
  private void drawAvtars(Graphics2D g,DrawableAvtar[] drawable){
      for(DrawableAvtar avtar:drawable){
          AlphaComposite composite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)avtar.getAlpha());
          g.setComposite(composite);
          g.drawImage(avtars.get(avtar.getIndex()),(int)avtar.getX(),(int)avtar.getY(),avtar.getWidth(),avtar.getHeight(),null);
          
      }
  }
  private DrawableAvtar[] sortAvtarByDepth(int x,int y,int width,int height){
      
      List<DrawableAvtar> drawables=new LinkedList<DrawableAvtar>();
      for(int i=0;i<avtars.size();i++){
          promoteAvtarToDrawable(drawables,x,y,width,height,(i-avtarIndex));
      }
      DrawableAvtar [] drawableAvtars=new DrawableAvtar[drawables.size()];
      drawableAvtars=drawables.toArray(drawableAvtars);
      Arrays.sort(drawableAvtars);
      return drawableAvtars;
  }
  private void promoteAvtarToDrawable(List<DrawableAvtar> drawable,int x,int y,int width,int height,int offset){
      double spacing=offset*avtarSpacing;
      double position=this.avtarPosition+spacing;
      if(offset+avtarIndex<0 || avtarIndex+offset>=avtars.size()){
          return;
      }
      Image avtar=avtars.get(offset+avtarIndex);
      
      int avtarWidth=avtar.getWidth(null);
      int avtarHeight=avtar.getHeight(null);
      double result=computeModifier(position);
      int newWidth=(int)(avtarWidth*result);
      if(newWidth==0)
          return;
      int newHeight=(int)(avtarHeight*result);
      if(newHeight==0)
          return;
      
      double avtar_x=x+(width-newWidth)/2.0;
      double avtar_y=y+(height-newHeight/2.0)/2.0;
      double semiWidth = width / 2.0;
      avtar_x += position * semiWidth;

      if (avtar_x >= width || avtar_x < -newWidth) {
            return;
      }
      drawable.add(new DrawableAvtar(offset+avtarIndex,avtar_x,avtar_y,newWidth,newHeight,position,result));  
       
  } 
  private double computeModifier(double x){
      double res=computeModifierUnprotected(x);
      if(res>1.0){
          return 1.0;
      }
      else if(res<-1.0){
          return -1.0;
      }
      else
          return res;
  }
  private void ComputeEquationPart(){
      exp_multiplier=Math.sqrt(2.0*Math.PI)/sigma/rho;
      exp_member=4.0*sigma*sigma;
  }
  private double computeModifierUnprotected(double x){
      return exp_multiplier * Math.exp((-x * x) / exp_member);
  }
  
  
  private class DamageManager extends ComponentAdapter{
      public void componentResized(ComponentEvent cpec){
          damaged=true;
      }
  }
  
  private void initInputListener(){
     focusGrabber=new FocusGrabber();
     avtarScroller=new AvtarScroller();
     cursorChanger=new CursorChanger();
     mouseWheelScroller=new MouseWheelScroller();
     keyScroller=new KeyScroller();
  }
  
  private class FocusGrabber extends MouseAdapter{
      @Override
      public void mouseClicked(MouseEvent mev){
          requestFocus();
      }
  }
  private class AvtarScroller extends MouseAdapter{
      @Override
      public void mouseClicked(MouseEvent mev){
          if((faderTimer!=null && faderTimer.isRunning()) || (scrollerTimer!=null && scrollerTimer.isRunning()) || drawableAvtars==null)
              return;
          if(mev.getButton()==MouseEvent.BUTTON1){
              DrawableAvtar avtar=getHitAvtar(mev.getX(),mev.getY());
              if(avtar!=null && avtar.getIndex()!=avtarIndex)
                  scrollAndAnimate(avtar);
          }
      }
  }
  private DrawableAvtar getHitAvtar(int x,int y){
      for(DrawableAvtar avtar:drawableAvtars){
          Rectangle2D rect=new Rectangle2D.Double(avtar.getX(),avtar.getY(),avtar.getWidth(),avtar.getHeight());
          if(rect.contains(x,y))
              return avtar;
      }
      return null;
  }
  private void scrollAndAnimate(DrawableAvtar avtar){
      if(loadingDone){
        scrollerTimer=new Timer(10,new AutoScroller(avtar));
        scrollerTimer.start();
      }
  }
  private class AutoScroller implements ActionListener{
      private long start;
      private int index;
      private double position;
      AutoScroller(DrawableAvtar avtar){
          this.start=System.currentTimeMillis();
          this.index=avtar.getIndex();
          this.position=avtar.getPosition();
      }
      
      public void actionPerformed(ActionEvent acev){
          long elapsed=System.currentTimeMillis()-start;
          double newPosition=(elapsed/SCROLL_DELAY)*(-position);
          if(elapsed>SCROLL_DELAY){
              ((Timer)acev.getSource()).stop();
              setIndex(this.index);
              setPosition(0.0);
              drawLabels();
              return;
          }
          setPosition(newPosition);
      }
  }
  public void setPosition(Double position){
      this.avtarPosition=position;
      this.damaged=true;
      repaint();
  }
  
  private class CursorChanger extends MouseMotionAdapter{
      @Override
      public void mouseMoved(MouseEvent mev){
         if((scrollerTimer==null || scrollerTimer.isRunning()) && drawableAvtars==null)
             return;
         DrawableAvtar avtar=getHitAvtar(mev.getX(),mev.getY());
         if(avtar!=null)
             getParent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         else
             getParent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }
  }
  private class MouseWheelScroller implements MouseWheelListener{
      public void mouseWheelMoved(MouseWheelEvent mhev){
          int increment=mhev.getWheelRotation();
          scrollAndAnimateBy(increment);
      }
  }
  public void scrollAndAnimateBy(int increment){
      if(loadingDone && (scrollerTimer==null || !scrollerTimer.isRunning())){
            int index=avtarIndex+increment;
            if(index<0)
                index=0;
            if(index>=avtars.size())
                index=avtars.size()-1;
            for(DrawableAvtar avtar:drawableAvtars){
                if(avtar.index==index){
                    scrollAndAnimate(avtar);
                    break;
                }
                            
            }
      }
  }
  
  private class KeyScroller extends KeyAdapter{
      @Override
      public void keyPressed(KeyEvent kev){
          int keyCode=kev.getKeyCode();
          switch(keyCode){
              case KeyEvent.VK_LEFT:
              case KeyEvent.VK_UP:
                     scrollAndAnimateBy(-1);
                     break;
              case KeyEvent.VK_RIGHT:
              case KeyEvent.VK_DOWN:
                     scrollAndAnimateBy(1);
                     break;
              case KeyEvent.VK_END:
                     scrollTO(avtars.size()-1);
                     break;
              case KeyEvent.VK_HOME:
                     scrollTO(0);
          }
      }
  }
  private void scrollTO(int index){
      if(loadingDone){
          setIndex(index);
          drawLabels();
          damaged=true;
          repaint();
          drawLabels();
      }
  }
  private void addInputListener(){
      addMouseListener(focusGrabber);
      addMouseListener(avtarScroller);
      addMouseMotionListener(cursorChanger);
      addMouseWheelListener(mouseWheelScroller);
      addKeyListener(keyScroller);
  }
    
}



