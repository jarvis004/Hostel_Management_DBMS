
package hostelmanagementsystem.lib;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.image.BufferedImage;


import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class StartUpPanelWithoutLogin extends JPanel{
      private int width,height;
      float alphaLevel=1.0f;
      private Timer fadingTimer=null;
      private boolean fadingTypeIN=true;
       public StartUpPanelWithoutLogin(int width,int height,float alpha){
           this.width=width;
           this.height=height;
           this.alphaLevel=alpha;
           setBackground(Color.WHITE);
           Globals.startUpPanelWithoutLogin=this;
           setOpaque(false);
           BufferedImage icon=null;
            try{
                icon=ImageIO.read(getClass().getResource("icons/loginIcon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capLogin=new customAreaPanel(234,50,icon,"Login","Take a look on your account","capLogin");
            SpringLayout springLayout=new SpringLayout();
            setLayout(springLayout);
            add(capLogin);
            springLayout.putConstraint(SpringLayout.WEST,capLogin,0,SpringLayout.WEST,this);
            springLayout.putConstraint(SpringLayout.NORTH,capLogin,0,SpringLayout.NORTH,this);
            capLogin.addMouseListener(new mouseListener(capLogin));
            
            try{
                icon=ImageIO.read(getClass().getResource("icons/searchIcon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capFind=new customAreaPanel(280,50,icon,"Seat Availablity","Find available seats for allocation","capFind");
            add(capFind);
            springLayout.putConstraint(SpringLayout.WEST,capFind,40,SpringLayout.EAST,capLogin);
            springLayout.putConstraint(SpringLayout.NORTH,capFind,0,SpringLayout.NORTH,this);
            capFind.addMouseListener(new mouseListener(capFind));
            
            try{
                icon=ImageIO.read(getClass().getResource("icons/adminLoginIcon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capAdminLogin=new customAreaPanel(190,50,icon,"Admin Login","System maintainence","capAdminLogin");
            add(capAdminLogin);
            springLayout.putConstraint(SpringLayout.WEST,capAdminLogin,0,SpringLayout.WEST,this);
            springLayout.putConstraint(SpringLayout.NORTH,capAdminLogin,30,SpringLayout.SOUTH,capLogin);
            capAdminLogin.addMouseListener(new mouseListener(capAdminLogin));
        
            try{
                icon=ImageIO.read(getClass().getResource("icons/aboutIcon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capAbout=new customAreaPanel(250,50,icon,"About","What is the system meant for !! ","capAbout");
            add(capAbout);
            springLayout.putConstraint(SpringLayout.WEST,capAbout,50,SpringLayout.EAST,capLogin);
            springLayout.putConstraint(SpringLayout.NORTH,capAbout,35,SpringLayout.SOUTH,capFind); 
            capAbout.addMouseListener(new mouseListener(capAbout));
            
            //setOpaque(false);
        }
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(this.width,this.height);
        }
        @Override
        public Dimension getMinimumSize(){
            return new Dimension(this.width,this.height);
        }
        @Override
        public Dimension getMaximumSize(){
            return new Dimension(this.width,this.height);
        }
        @Override
        protected void paintChildren(Graphics g){
            
            Graphics2D g2 = (Graphics2D) g;
            if((this.alphaLevel>=1.0f) && this.fadingTimer!=null && this.fadingTypeIN){
                if(this.fadingTimer.isRunning())
                    this.fadingTimer.stop();
            }
            if((this.alphaLevel<=0.0f) && this.fadingTimer!=null && !this.fadingTypeIN){
                if(this.fadingTimer.isRunning())
                    this.fadingTimer.stop();
            }
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaLevel));
            super.paintChildren(g);
            g2.setComposite(oldComposite);
        }
        private class mouseListener extends MouseAdapter{
            customAreaPanel listenerPanel;
            mouseListener(customAreaPanel panel){
                this.listenerPanel=panel;
            }
            @Override
            public void mouseClicked(MouseEvent mev){
                boolean click=listenerPanel.clickable(mev.getX(),mev.getY());
                if(click){
                	
                    if(listenerPanel.getID().equals("capLogin") || listenerPanel.getID().equals("capAdminLogin") || listenerPanel.getID().equals("capAbout")){
                        LoginPanel loginPanel=null;
                        if(listenerPanel.getID().equals("capLogin")){
                            loginPanel=new LoginPanel("Student Login");
                        }
                        if(listenerPanel.getID().equals("capAdminLogin")){
                            loginPanel=new LoginPanel("Admin Login");
                        }
                        if (listenerPanel.getID().equals("capAbout")) {
                            //loginPanel=new LoginPanel("About");
                            JFrame frame = new JFrame("About");
                            final MyTextPane textPane = new MyTextPane(2);
                            frame.add(textPane);

                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                            textPane.setEditable(false);
                            frame.setSize(700,600);
                        }
                       
                        
                        JFrame frame = (JFrame)SwingUtilities.getRoot(listenerPanel);
                        glassPane customGlassPane;
                        Globals.mainApp.setGlassPane(customGlassPane=new glassPane(frame.getWidth(),frame.getHeight()));
                        Globals.currentGlassPane=customGlassPane;
                        Globals.currentGlassPane.glassPaneLayout.addCordinates( (Globals.mainApp.getWidth()-loginPanel.width)/2, 
                                                                            (Globals.mainApp.getHeight()-loginPanel.height)/2
                                                                          );
                        customGlassPane.addComponent(loginPanel, loginPanel.width, loginPanel.height);
                        frame.getGlassPane().setVisible(true);
                        loginPanel.username.requestFocus();
                    }
                }
            }
        }
        public void startFading(String type){
            this.fadingTypeIN=type.equals("IN")?true:false;
            FadingEffect fading=new FadingEffect(1000.0f,type,this,"StartUpPanelWithoutLogin");
            this.fadingTimer=new Timer(30,fading);
            this.fadingTimer.start();
        }
}

