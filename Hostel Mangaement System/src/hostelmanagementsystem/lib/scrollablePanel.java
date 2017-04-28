package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.LinearGradientPaint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Composite;

import java.io.IOException;
public class scrollablePanel extends JPanel{
    public int width=500,height=325;
    seatAllotmentPanel seatAllotment;
    private int scrollTrackWidth=10;
    JScrollPane sp,sp1;
    private Font font=null;
    seatAllotmentPanel ref=null;
    studentAccountsPanel studentAccount;
    private Timer fadingTimer=null;
    private boolean fadingStarted=false,fadingTypeIN;
    private String title;
    float alphaLevel=1.0f;
    LinearGradientPaint backgroundPaint=new LinearGradientPaint(0.0f, 0.0f, 0.0f,this.height,
          new float[] { 0.0f,0.066f,0.077f,0.087f,0.5f,1.0f },
          new Color[] { new Color(99,108,124),
                        new Color(23,31,47),
                        new Color(164,168,179),
                        new Color(32,39,45),
                        new Color(60,69,87),
                        new Color(150,161,187) });
    public scrollablePanel(String title,float opacity){
        this(title);
        this.alphaLevel=opacity;
    }
    public scrollablePanel(String title){
      if(title=="Seat Alotment"){ 
        Globals.scrollablePanel=this;
        setOpaque(false);
        this.title=title;
        font=new Font("verdana",Font.PLAIN,14);
        try{
          font=Font.createFont(Font.TRUETYPE_FONT,scrollablePanel.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.PLAIN,14f);
        }
        catch(FontFormatException fe){
       }
       catch(IOException e){
           e.printStackTrace();
       }
        
        SpringLayout springLayout=new SpringLayout();
        CrossLabel crossLabel=new CrossLabel(16,new Color(230,230,230),new Color(20,20,20));
        add(crossLabel);
        springLayout.putConstraint(SpringLayout.EAST,crossLabel,-10,SpringLayout.EAST,this);
        springLayout.putConstraint(SpringLayout.NORTH,crossLabel,2,SpringLayout.NORTH,this);
        seatAllotment=new seatAllotmentPanel();
        tooltipLayerdPane ttlp=new tooltipLayerdPane();
        ttlp.tooltipLayeredLayout.addCordinates(0,0);
        ttlp.addComponent(seatAllotment,seatAllotment.getPreferredSize().width,seatAllotment.getPreferredSize().height);
        sp=new JScrollPane(ttlp,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.getVerticalScrollBar().setUI(new myScrollBarUI(scrollTrackWidth,20,20));
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setBorder(null);
        add(sp);
        setLayout(springLayout);
        Dimension dim=seatAllotment.getPreferredSize();
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(scrollTrackWidth,this.height));
        sp.setPreferredSize(new Dimension(dim.width,285));
        springLayout.putConstraint(SpringLayout.WEST,sp,0,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,sp,30,SpringLayout.NORTH,this);
      }
      else
          this.scrollableStudentAccount(title);
      
    }
    
    public void scrollableStudentAccount(String Title){
        setOpaque(false);
        this.title=Title;
        font=new Font("verdana",Font.PLAIN,14);
        try{
          font=Font.createFont(Font.TRUETYPE_FONT,LoginPanel.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.PLAIN,14f);
        }
        catch(FontFormatException fe){
       }
       catch(IOException e){
           e.printStackTrace();
       }
        SpringLayout springLayout=new SpringLayout();
        CrossLabel crossLabel=new CrossLabel(16,new Color(230,230,230),new Color(20,20,20));
        add(crossLabel);
        springLayout.putConstraint(SpringLayout.EAST,crossLabel,-10,SpringLayout.EAST,this);
        springLayout.putConstraint(SpringLayout.NORTH,crossLabel,2,SpringLayout.NORTH,this);
                       
        studentAccount=new studentAccountsPanel();
        sp1=new JScrollPane(studentAccount,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp1.getVerticalScrollBar().setUI(new myScrollBarUI(scrollTrackWidth,20,20));
        sp1.setOpaque(false);
        sp1.getViewport().setOpaque(false);
        sp1.setBorder(null);
        add(sp1);
        setLayout(springLayout);
        Dimension dim2=studentAccount.getPreferredSize();
        sp1.getVerticalScrollBar().setPreferredSize(new Dimension(scrollTrackWidth,this.height));
        sp1.setPreferredSize(new Dimension(dim2.width,265));
        springLayout.putConstraint(SpringLayout.WEST,sp1,0,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,sp1,30,SpringLayout.NORTH,this);
        
        
    }
    
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Composite oldComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaLevel));
        super.paintChildren(g);
        g2.setComposite(oldComposite);
    }
    @Override
    public void paintComponent(Graphics g){
        if(this.alphaLevel<1.0f && this.alphaLevel>0.0f && !fadingStarted){
            fadingStarted=true;
        }
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle clip=g2d.getClipBounds();
        Paint oldPaint=g2d.getPaint();
        Composite oldComposite=g2d.getComposite();
        g2d.setPaint(backgroundPaint);
        if((this.alphaLevel>=1.0f) && this.fadingTimer!=null && this.fadingTypeIN){
                if(this.fadingTimer.isRunning())
                    this.fadingTimer.stop();
        }
        if((this.alphaLevel<=0.0f) && this.fadingTimer!=null && !this.fadingTypeIN){
           if(this.fadingTimer.isRunning())
              this.fadingTimer.stop();
        }
        AlphaComposite composite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alphaLevel);
        g2d.setComposite(composite);
        g2d.fillRoundRect(0,0,this.width,this.height,4,4);
        g2d.setPaint(oldPaint);
        g2d.setColor(Color.WHITE);
        font=g2d.getFont().deriveFont(Font.BOLD,17);
        Font oldFont=g2d.getFont();
        g2d.setFont(font);
        g2d.drawString(this.title,10,15);
        g2d.setFont(oldFont);
        g2d.setComposite(oldComposite);
                
    }
    @Override
    public Dimension getPreferredSize(){
         return new Dimension(this.width,this.height);
    }
    public void startFading(String type){
            this.fadingTypeIN=type.equals("IN")?true:false;
            FadingEffect fading=new FadingEffect(500.0f,type,Globals.scrollablePanel,"scrollablePanel");
            this.fadingTimer=new Timer(30,fading);
            this.fadingTimer.start();
    }
}
