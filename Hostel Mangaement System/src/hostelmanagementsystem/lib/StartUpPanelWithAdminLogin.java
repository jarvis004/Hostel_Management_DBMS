package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;

import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

public class StartUpPanelWithAdminLogin extends JPanel{
      private int width,height;
      float alphaLevel=1.0f;
      private Timer fadingTimer=null;
      private boolean fadingTypeIN=true;
       public StartUpPanelWithAdminLogin(int width,int height,float alpha){
           this.width=width;
           this.height=height;
           Globals.startUpPanelWithAdminLogin=this;
           this.alphaLevel=alpha;
           setOpaque(false);
           BufferedImage icon=null;
            try{
                icon=ImageIO.read(getClass().getResource("icons/seat_icon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capSeatAllotment=new customAreaPanel(200,50,icon,"Seat Allotment","Allot a seat to a person","capSeatAllotment");
            SpringLayout springLayout=new SpringLayout();
            setLayout(springLayout);
            add(capSeatAllotment);
            springLayout.putConstraint(SpringLayout.WEST,capSeatAllotment,10,SpringLayout.WEST,this);
            springLayout.putConstraint(SpringLayout.NORTH,capSeatAllotment,0,SpringLayout.NORTH,this);
            capSeatAllotment.addMouseListener(new mouseListener(capSeatAllotment));
            
            try{
                icon=ImageIO.read(getClass().getResource("icons/accounts_icon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capAccounts=new customAreaPanel(235,50,icon,"Accounts","View and manage accounts","capAccounts");
            add(capAccounts);
            springLayout.putConstraint(SpringLayout.WEST,capAccounts,100,SpringLayout.EAST,capSeatAllotment);
            springLayout.putConstraint(SpringLayout.NORTH,capAccounts,0,SpringLayout.NORTH,this);
            capAccounts.addMouseListener(new mouseListener(capAccounts));
            
            try{
                icon=ImageIO.read(getClass().getResource("icons/mess_icon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capMess=new customAreaPanel(210,50,icon,"Mess Corner","Access to mess corner","capMess");
            add(capMess);
            springLayout.putConstraint(SpringLayout.WEST,capMess,10,SpringLayout.WEST,this);
            springLayout.putConstraint(SpringLayout.NORTH,capMess,35,SpringLayout.SOUTH,capSeatAllotment);
            capMess.addMouseListener(new mouseListener(capMess));
            
            try{
                icon=ImageIO.read(getClass().getResource("icons/logout_icon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capLogout=new customAreaPanel(215,50,icon,"Logout","End your current session","capLogout");
            add(capLogout);
            springLayout.putConstraint(SpringLayout.WEST,capLogout,105,SpringLayout.EAST,capSeatAllotment);
            springLayout.putConstraint(SpringLayout.NORTH,capLogout,35,SpringLayout.SOUTH,capAccounts); 
            capLogout.addMouseListener(new mouseListener(capLogout));
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
            Composite oldComposite = g2.getComposite();
            if((this.alphaLevel>=1.0f) && this.fadingTimer!=null && this.fadingTypeIN){
                if(this.fadingTimer.isRunning())
                    this.fadingTimer.stop();
            }
            if((this.alphaLevel<=0.0f) && this.fadingTimer!=null && !this.fadingTypeIN){
                if(this.fadingTimer.isRunning())
                    this.fadingTimer.stop();
                Globals.mainApp.ToggleStartUpPaneltoWithoutLoginFromAdminLogin();
                
            }
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
                    if(listenerPanel.getID().equals("capLogout")){
                        startFading("OUT");
                    }
                    if(listenerPanel.getID().equals("capSeatAllotment")){
                        scrollablePanel seatAlotmentScrollablePanel=new scrollablePanel("Seat Alotment",0.0f);
                        JLayeredPane layeredPane =Globals.mainApp.getRootPane().getLayeredPane();
                        layeredPane.setLayout(new OverlayLayout(layeredPane));
                        customLayeredPane clp=new customLayeredPane();
                        Globals.layeredPane.addComponent(seatAlotmentScrollablePanel,50,30);
                        layeredPane.add(clp, (Integer) (JLayeredPane.DEFAULT_LAYER+50));
                        revalidate();
                        seatAlotmentScrollablePanel.startFading("IN");
                    }
                    else if(listenerPanel.getID().equals("capAccounts")){
                        Globals.mainApp.ToggleStartUpPaneltoWithAdminLogintoAccountsPanel();
                    }
                    else if(listenerPanel.getID().equals("capMess")){
                    	JFrame frame = new JFrame("Mess Corner");
                        final MyTextPane textPane = new MyTextPane(1);
                        frame.add(textPane);

                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                        textPane.setEditable(false);
                        frame.setSize(700,600);
                    }
                }
            }
        }
        public void startFading(String type){
            this.fadingTypeIN=type.equals("IN")?true:false;
            FadingEffect fading=new FadingEffect(1000.0f,type,this,"StartUpPanelWithAdminLogin");
            this.fadingTimer=new Timer(30,fading);
            this.fadingTimer.start();
        }
}


