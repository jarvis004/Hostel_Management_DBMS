package hostelmanagementsystem.lib;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class customPasswordField extends JPasswordField{
    private int width,height,placeholderHeight=0;
    private String placeholder;
    private Font font;
    private boolean fontSet=false,hasFocus=false,phVis=true;
    Color color1=new Color(0f,0f,0f,0.2f);
    Color color2=new Color(0f,0f,0f,0.1f);
    Timer passflfocusTimer=null;
    customPasswordField reference=null;
    FontMetrics fm=null;
    public customPasswordField(int width,int height,String placeholder) {
        super();
        reference=this;
        addListeners();
        setForeground(new Color(25,25,25));
        this.width=width+5;
        this.height=height+5;
        this.placeholder=placeholder;
        this.font=null;
        setBorder(BorderFactory.createCompoundBorder(getBorder(), 
                  BorderFactory.createEmptyBorder(5, 5,5, 5)));
        setOpaque(false); // As suggested by @AVD in comment.
     }
     public customPasswordField(int width,int height,String placeholder,Font font) {
        super();
        reference=this;
        addListeners();
        setForeground(new Color(25,25,25));
        super.setFont(font);
        this.width=width+5;
        this.height=height+5;
        this.placeholder=placeholder;
        this.font=font;
        setBorder(BorderFactory.createCompoundBorder(getBorder(), 
                  BorderFactory.createEmptyBorder(5,5,5,5)));
        setOpaque(false); // As suggested by @AVD in comment.
     }
     private void addListeners(){
         addFocusListener(new FocusListener(){
             public void focusGained(FocusEvent fe){
                hasFocus=true;
                passflfocusTimer=new Timer(10,new ActionListener(){
                    public void actionPerformed(ActionEvent acev){
                        if(reference.getText().length()>0){
                            if(reference.phVis){
                               reference.phVis=false;
                               repaint(0,0,getWidth(),getHeight()); 
                            }
                        }
                        else{
                            if(!reference.phVis){
                               reference.phVis=true;
                               repaint(0,0,getWidth(),getHeight()); 
                            }
                        }
                     }
                  });
                  passflfocusTimer.start();
                  repaint(0,0,getWidth(),getHeight());
               }
               public void focusLost(FocusEvent fe){
                  hasFocus=false;
                  repaint(0,0,getWidth(),getHeight()); 
                  passflfocusTimer.stop();
               }
           });
        }
        @Override
    protected void paintComponent(Graphics g) {
        Font oldFont=null;
        Graphics2D g2d=(Graphics2D)g;
        g2d.setColor(new Color(230,230,230));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(1, 1, this.width-2, this.height-2, 5, 5);
            
        if(!fontSet){
           if(font!=null){
               oldFont=g2d.getFont();
               g2d.setFont(font);
            }  
            fm=Toolkit.getDefaultToolkit().getFontMetrics(g2d.getFont());
            this.placeholderHeight=fm.getHeight();
         }
         if(!hasFocus) {   //if the Password field has focus
            g2d.setColor(new Color(130,130,130));
         }
         else{
             g2d.setColor(new Color(190,190,190));
         }
         if(reference.phVis)
            g2d.drawString(this.placeholder,7,(this.height)/2-2-1+placeholderHeight/2);
         if(!fontSet && font!=null){
            g2d.setFont(oldFont);
            fontSet=true;
            fm=Toolkit.getDefaultToolkit().getFontMetrics(g2d.getFont());
         }
         super.paintComponent(g);
    }
    @Override
    protected void paintBorder(Graphics g) {
        Color oldColor=g.getColor();
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if(!hasFocus) { 
            g2d.setColor(new Color(0f,0f,0f,0.7f));
            g2d.drawRoundRect(0, 0, this.width-1, this.height-1, 5, 5);
        }
        else{
             g2d.setColor(new Color(0.6f,0.4f,0.2f,1f));
             g2d.drawRoundRect(0, 0, this.width-1,this.height-1, 5, 5);
        }
        g2d.setColor(oldColor);
        g2d.dispose();
     }

     @Override
     public Dimension getPreferredSize(){
        return new Dimension(this.width,this.height);
      }
   }