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
import javax.swing.SwingUtilities;

public class StartUpPanelWithStudentLogin extends JPanel{
      private int width,height;
      float alphaLevel=1.0f;
      private Timer fadingTimer=null;
      private boolean fadingTypeIN=true;
       public StartUpPanelWithStudentLogin(int width,int height,float alpha){
           this.width=width;
           this.height=height;
           Globals.startUpPanelWithStudentLogin=this;
           this.alphaLevel=alpha;
           setOpaque(false);
           BufferedImage icon=null;
            try{
                icon=ImageIO.read(getClass().getResource("icons/user_account_book_icon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capUserAccountBook=new customAreaPanel(235,50,icon,"Account Book","View your payments and dues","capUserAccountBook");
            SpringLayout springLayout=new SpringLayout();
            setLayout(springLayout);
            add(capUserAccountBook);
            springLayout.putConstraint(SpringLayout.WEST,capUserAccountBook,5,SpringLayout.WEST,this);
            springLayout.putConstraint(SpringLayout.NORTH,capUserAccountBook,0,SpringLayout.NORTH,this);
            capUserAccountBook.addMouseListener(new mouseListener(capUserAccountBook));
            try{
                icon=ImageIO.read(getClass().getResource("icons/meal_icon.jpg"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capMealCorner=new customAreaPanel(235,50,icon,"Meal Corner","Change Your Meal Options","capMealCorner");
            add(capMealCorner);
            springLayout.putConstraint(SpringLayout.WEST,capMealCorner,95,SpringLayout.EAST,capUserAccountBook);
            springLayout.putConstraint(SpringLayout.NORTH,capMealCorner,0,SpringLayout.NORTH,this);
            capMealCorner.addMouseListener(new mouseListener(capMealCorner));
            
            try{
                icon=ImageIO.read(getClass().getResource("icons/application_icon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capApplication=new customAreaPanel(305,50,icon,"Fee Extension","Submit an application for fee extension","capApplication");
            add(capApplication);
            springLayout.putConstraint(SpringLayout.WEST,capApplication,5,SpringLayout.WEST,this);
            springLayout.putConstraint(SpringLayout.NORTH,capApplication,35,SpringLayout.SOUTH,capUserAccountBook);
            capApplication.addMouseListener(new mouseListener(capApplication));
        
            try{
                icon=ImageIO.read(getClass().getResource("icons/logout_icon.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            customAreaPanel capUserLogout=new customAreaPanel(215,50,icon,"Logout","End your current session","capUserLogout");
            add(capUserLogout);
            springLayout.putConstraint(SpringLayout.WEST,capUserLogout,95,SpringLayout.EAST,capUserAccountBook);
            springLayout.putConstraint(SpringLayout.NORTH,capUserLogout,35,SpringLayout.SOUTH,capMealCorner); 
            capUserLogout.addMouseListener(new mouseListener(capUserLogout));
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
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaLevel));
            super.paintChildren(g);
            g2.setComposite(oldComposite);
            if((this.alphaLevel>=1.0f) && this.fadingTimer!=null && this.fadingTypeIN){
                if(this.fadingTimer.isRunning())
                    this.fadingTimer.stop();
            }
            if((this.alphaLevel<=0.0f) && this.fadingTimer!=null && !this.fadingTypeIN){
                if(this.fadingTimer.isRunning())
                    this.fadingTimer.stop();
                Globals.mainApp.ToggleStartUpPaneltoWithoutLoginFromStudentLogin();
            }
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
                    if(listenerPanel.getID().equals("capUserLogout")){
                        startFading("OUT");
                        System.out.println(Globals.SESSION_ID);
                    }
                    if(listenerPanel.getID().equals("capUserAccountBook")){
                        //startFading("IN");
                        scrollablePanel capUserAccountBookScrollablePanel=new scrollablePanel("Account Details");
                        glassPane customGlassPane=new glassPane(Globals.mainApp.getWidth(),Globals.mainApp.getHeight());
                        Globals.mainApp.setGlassPane(customGlassPane);
                        Globals.currentGlassPane=customGlassPane;
                        Globals.currentGlassPane.glassPaneLayout.addCordinates( (Globals.mainApp.getWidth()-capUserAccountBookScrollablePanel.width)/2, 
                                                                            (Globals.mainApp.getHeight()-capUserAccountBookScrollablePanel.height)/2
                                                                          );
                        customGlassPane.addComponent(capUserAccountBookScrollablePanel, capUserAccountBookScrollablePanel.width, capUserAccountBookScrollablePanel.height);
                        Globals.mainApp.getGlassPane().setVisible(true);
                        System.out.println("app clicked");
                    }
                    if(listenerPanel.getID().equals("capMealCorner")){
                        MealPanel mealPanel = new MealPanel("Meal Corner");
                        System.out.println("here in meal");
                    
                    
                    JFrame frame = (JFrame)SwingUtilities.getRoot(listenerPanel);
                    glassPane customGlassPane;
                    Globals.mainApp.setGlassPane(customGlassPane=new glassPane(frame.getWidth(),frame.getHeight()));
                    Globals.currentGlassPane=customGlassPane;
                    Globals.currentGlassPane.glassPaneLayout.addCordinates( (Globals.mainApp.getWidth()-mealPanel.width)/2, 
                                                                            (Globals.mainApp.getHeight()-mealPanel.height)/2
                                                                          );
                    customGlassPane.addComponent(mealPanel, mealPanel.width, mealPanel.height);
                    frame.getGlassPane().setVisible(true);
                   }
                }
                if(listenerPanel.getID().equals("capApplication")){
                    feeExtensionPanel feeExt = new feeExtensionPanel("Submit an application for Due Date Extension");
                    
                    JFrame frame = (JFrame)SwingUtilities.getRoot(listenerPanel);
                    glassPane customGlassPane;
                    Globals.mainApp.setGlassPane(customGlassPane=new glassPane(frame.getWidth(),frame.getHeight()));
                    Globals.currentGlassPane=customGlassPane;
                    Globals.currentGlassPane.glassPaneLayout.addCordinates( (Globals.mainApp.getWidth()-feeExt.width)/2, 
                                                                            (Globals.mainApp.getHeight()-feeExt.height)/2
                                                                          );
                    customGlassPane.addComponent(feeExt, feeExt.width, feeExt.height);
                    frame.getGlassPane().setVisible(true);
                }
            }
        }
        public void startFading(String type){
            this.fadingTypeIN=type.equals("IN")?true:false;
            FadingEffect fading=new FadingEffect(1000.0f,type,this,"StartUpPanelWithStudentLogin");
            this.fadingTimer=new Timer(30,fading);
            this.fadingTimer.start();
        }
}


