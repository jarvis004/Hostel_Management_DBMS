/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hostelmanagementsystem.lib;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Sankat
 */
public class studentAccountsPanel extends JPanel {
    public int width=500,height=325;
    CrossLabel crossLabel;
    private JLabel firstname,lastname,batch,stream,l1,l2,l3;
    
    //example name for display//
    /* TO BE IMPLEMENTED AFTER DB CREATION*/
    String first_name="Pritish",last_name="Tiwari",sbatch="2009-13",sstream="C.S.E";
    private Image img;
    private String title;
    private SpringLayout springLayout;
    private BufferedImage img1=null;
    Font font=null;
    studentAccountsPanel ref=null;
    float alphaLevel=1.0f;
    public studentAccountsPanel(){
        setOpaque(false);
        ref=this;
        this.title=title;
        crossLabel=new CrossLabel(16,new Color(230,230,230),new Color(0,0,0));
        springLayout=new SpringLayout();
        setLayout(springLayout);
        setFocusable(true);
        addMouseListener(new mouseListener(this));
        
        font=new Font("verdana",Font.BOLD,18);
        try{
          font=Font.createFont(Font.TRUETYPE_FONT,LoginPanel.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.BOLD,18f);
        }
        catch(FontFormatException fe){
       }
       catch(IOException e){
           e.printStackTrace();
       }
        firstname = new JLabel(first_name);
        add(firstname);
        firstname.setForeground(Color.white);
        springLayout.putConstraint(SpringLayout.WEST,firstname,25,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,firstname,15,SpringLayout.NORTH,this);
        
        lastname = new JLabel(last_name);
        add(lastname);
        lastname.setForeground(Color.white);
        springLayout.putConstraint(SpringLayout.WEST,lastname,5,SpringLayout.EAST,firstname);
        springLayout.putConstraint(SpringLayout.NORTH,lastname,15,SpringLayout.NORTH,this);
        
        stream = new JLabel(sstream);
        add(stream);
        stream.setForeground(Color.white);
        springLayout.putConstraint(SpringLayout.WEST,stream,25,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,stream,5,SpringLayout.SOUTH,firstname);
        
        batch = new JLabel(sbatch);
        add(batch);
        batch.setForeground(Color.white);
        springLayout.putConstraint(SpringLayout.WEST,batch,25,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,batch,5,SpringLayout.SOUTH,stream);
       
        l1 = new JLabel("Amount to be paid next :");
        add(l1);
        l1.setForeground(Color.white);
        springLayout.putConstraint(SpringLayout.WEST,l1,15,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,l1,75,SpringLayout.SOUTH,batch);
        
        l2 = new JLabel("DUE DATE :");
        add(l2);
        l2.setForeground(Color.white);
        springLayout.putConstraint(SpringLayout.WEST,l2,15,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,l2,5,SpringLayout.SOUTH,l1);
        
        l3 = new JLabel("Payment made till :");
        add(l3);
        l3.setForeground(Color.white);
        springLayout.putConstraint(SpringLayout.WEST,l3,15,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,l3,5,SpringLayout.SOUTH,l2);
        try {
            img1 = ImageIO.read(getClass().getResource("icons/1.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
       
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(img1,350,15,125,95,null);
    }
   
    
    
    
    
    
    
    private class mouseListener extends MouseAdapter{
        private JPanel reference;
        mouseListener(JPanel panel){
            this.reference=panel;
        }
        @Override
       public void mouseClicked(MouseEvent mev){
           reference.requestFocus();
       }
    }
    @Override
    public Dimension getPreferredSize(){
         return new Dimension(this.width,this.height);
    }
        
    }
    
    

