package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.LinearGradientPaint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JTextField;

public class feeExtensionPanel extends JPanel{
    public int width=400,height=200;
    CrossLabel crossLabel;
    private showWarning warning;
    private String title;
    private JTextField display = new JTextField();
    private JButton open = new JButton("Open");
    
    private SpringLayout springLayout;
    Font font=null;
    feeExtensionPanel ref=null;
    private Timer fadingTimer=null;
    private boolean fadingStarted=false;
    float alphaLevel=1.0f;
    LinearGradientPaint backgroundPaint=new LinearGradientPaint(0.0f, 0.0f, 0.0f,this.height,
          new float[] { 0.0f,0.10f,0.106f,0.123f,0.5f,1.0f },
          new Color[] { new Color(99,108,124),
                        new Color(23,31,47),
                        new Color(164,168,179),
                        new Color(32,39,45),
                        new Color(60,69,87),
                        new Color(150,161,187) });
    public feeExtensionPanel(String title){
        setOpaque(false);
        ref=this;
        this.title=title;
        crossLabel=new CrossLabel(16,new Color(230,230,230),new Color(20,20,20));
        springLayout=new SpringLayout();
        setLayout(springLayout);
        add(crossLabel);
        setFocusable(true);
        springLayout.putConstraint(SpringLayout.EAST,crossLabel,-10,SpringLayout.EAST,this);
        springLayout.putConstraint(SpringLayout.NORTH,crossLabel,2,SpringLayout.NORTH,this);
        addMouseListener(new mouseListener(this));
        
        font=new Font("verdana",Font.PLAIN,14);
        try{
          font=Font.createFont(Font.TRUETYPE_FONT,feeExtensionPanel.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.PLAIN,14f);
        }
        catch(FontFormatException fe){
       }
       catch(IOException e){
           e.printStackTrace();
       }
        open.addActionListener(new OpenClass());
        add(open);
        
        springLayout.putConstraint(SpringLayout.WEST,open,28,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,open,155,SpringLayout.NORTH,this);
        
        
        display.setEditable(false);
        display.setPreferredSize(new Dimension(350,30));  
        add(display);
        springLayout.putConstraint(SpringLayout.WEST,display,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,display,55,SpringLayout.NORTH,this);
        
               
    }
    
    class OpenClass implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      FileChooserDialog chooser = new FileChooserDialog("*.pdf;*.doc",display);
          
    
        /*int option = chooser.showOpenDialog(warning);
        if(option == JFileChooser.APPROVE_OPTION) {
            */
      /*}
       
        if(option == JFileChooser.CANCEL_OPTION) {
            display.setText("You canceled.");
      }*/
  }
 }
 
    
    
   
    
    
    
    @Override
    public Dimension getPreferredSize(){
         return new Dimension(this.width,this.height);
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
        if( this.alphaLevel<=0.0f){
            if(fadingStarted){
                if(fadingTimer.isRunning()){
                    fadingTimer.stop();
                }
                fadingStarted=false;
            }
            this.alphaLevel=0.0f;
            Globals.currentGlassPane.setVisible(false);
        }
        g2d.setPaint(backgroundPaint);
        AlphaComposite composite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alphaLevel);
        g2d.setComposite(composite);
        g2d.fillRoundRect(0,0,this.width,this.height,4,4);
        g2d.setPaint(oldPaint);
        g2d.setColor(Color.WHITE);
        font=g2d.getFont().deriveFont(Font.BOLD,17);
        g2d.setFont(font);
        g2d.drawString(this.title,10,15);
        g2d.setComposite(oldComposite);
                
    }
    private class mouseListener extends MouseAdapter{
        private JPanel ref;
        mouseListener(JPanel panel){
            this.ref=panel;
        }
        @Override
       public void mouseClicked(MouseEvent mev){
           ref.requestFocus();
       }
    }
}
