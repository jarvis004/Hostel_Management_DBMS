package hostelmanagementsystem;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.LinearGradientPaint;
import java.awt.Color;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.Calendar;

import hostelmanagementsystem.lib.customHeaderLabel;
import hostelmanagementsystem.lib.customBackgroundPanel;
import hostelmanagementsystem.lib.StartUpPanelWithoutLogin;
import hostelmanagementsystem.lib.StartUpPanelWithAdminLogin;
import hostelmanagementsystem.lib.StartUpPanelWithStudentLogin;
import hostelmanagementsystem.lib.curvePanel;
import hostelmanagementsystem.lib.scrollablePanel;
import hostelmanagementsystem.lib.Globals;
import hostelmanagementsystem.lib.IndividualAccounts;
import hostelmanagementsystem.lib.AdminAccountsPanel;
import hostelmanagementsystem.lib.PicSlider;



public class HostelManagementSystemApp extends JFrame{
    customBackgroundPanel backgroundPanel;
    customHeaderLabel lbl=null;
    StartUpPanelWithoutLogin startUpPanelWithoutLogin=null;
    StartUpPanelWithAdminLogin startUpPanelWithAdminLogin=null;
    StartUpPanelWithStudentLogin startUpPanelWithStudentLogin=null;
    AdminAccountsPanel adminAccountsPanel=null;
    private PicSlider avtar;
    IndividualAccounts ia=null;
    scrollablePanel seatAlotment=null;
    curvePanel signCurve=null;
    SpringLayout springLayout=null;
    long session_id=0L;
    HostelManagementSystemApp(){
        Globals.mainApp=this;
                        //Globals.layeredPane.setOpaque(true);
                       // btn.setVisible(false);
        setSize(700,600);
        LinearGradientPaint backgroundPaint=new LinearGradientPaint(0.0f, 0.0f, 0.0f,getHeight(),
          new float[] { 0.0f,0.03f,0.075f,0.076f,0.097f,0.5f,1.0f },
          new Color[] { new Color(72,61,139),
                        new Color(72,61,129),
                        new Color(23,31,100),
                        new Color(72,61,139),
                        new Color(32,39,45),
                        new Color(72,61,129),
                        new Color(150,161,187) });
        backgroundPanel=new customBackgroundPanel(getWidth()-6,getHeight(),backgroundPaint);
        backgroundPanel.addMouseListener(new mouseListener(backgroundPanel));
        Font font=new Font("Verdana",Font.BOLD,16);
            try{
                font=Font.createFont(Font.PLAIN,HostelManagementSystemApp.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.BOLD,16f);
          
            }
            catch(FontFormatException fe){
            }
            catch(IOException e){
                e.printStackTrace();
            }
        springLayout=new SpringLayout();
        backgroundPanel.setLayout(springLayout);
        LinearGradientPaint pnt=new LinearGradientPaint(0.0f, 0.0f, 0.0f, 25.0f,
                                                            new float[] { 0.0f, 0.499f, 0.5f, 1.0f },
                                                            new Color[] { new Color(121,130,143),
                                                            new Color(76,84,98),
                                                            new Color(32,39,58),
                                                            new Color(52,59,78) });
        lbl=new customHeaderLabel("Hostel Management System",getWidth()-6,25,2,pnt,20,19,Color.WHITE,font);
        backgroundPanel.add(lbl);
        springLayout.putConstraint(SpringLayout.WEST,lbl,0,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,lbl,15,SpringLayout.NORTH,this);
        
        
        
        /*startUpPanelWithStudentLogin=new StartUpPanelWithStudentLogin(550,200,0.0f);
        backgroundPanel.add(startUpPanelWithStudentLogin);
        springLayout.putConstraint(SpringLayout.WEST,startUpPanelWithStudentLogin,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,startUpPanelWithStudentLogin,60,SpringLayout.SOUTH,lbl);
        startUpPanelWithStudentLogin.startFading("IN");
        */
        
        /*
        startUpPanelWithAdminLogin=new StartUpPanelWithAdminLogin(550,200,0.0f);
        backgroundPanel.add(startUpPanelWithAdminLogin);
        springLayout.putConstraint(SpringLayout.WEST,startUpPanelWithAdminLogin,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,startUpPanelWithAdminLogin,60,SpringLayout.SOUTH,lbl);
        startUpPanelWithAdminLogin.startFading("IN");
        
                
        */
        
        startUpPanelWithoutLogin=new StartUpPanelWithoutLogin(560,200,0.0f);
        backgroundPanel.add(startUpPanelWithoutLogin);
        springLayout.putConstraint(SpringLayout.WEST,startUpPanelWithoutLogin,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,startUpPanelWithoutLogin,60,SpringLayout.SOUTH,lbl);
        startUpPanelWithoutLogin.startFading("IN");
        
        
        /*
        seatAlotment=new scrollablePanel("Seat Alotment");
        backgroundPanel.add(seatAlotment);
        springLayout.putConstraint(SpringLayout.WEST,seatAlotment,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,seatAlotment,60,SpringLayout.SOUTH,lbl);
        */
        
        /*
        BufferedImage icon=null;
        try{
            icon=ImageIO.read(getClass().getResource("lib/icons/NPP.png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
        Calendar date=Calendar.getInstance();
        int admDay=date.get(Calendar.DAY_OF_MONTH);
        int admMon=date.get(Calendar.MONTH);
        int admYear=date.get(Calendar.YEAR);
        boolean [] rentBox={true,true,true,true};
        boolean [] messBox={false,false,false,false,false,false,false,false,false,false,false,false};
        ia=new IndividualAccounts(icon,"09108001099","Indrajeet Mishra","2009-2013","CSE",admDay,admMon,admYear,rentBox,messBox);
        backgroundPanel.add(ia);
        springLayout.putConstraint(SpringLayout.WEST,ia,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,ia,60,SpringLayout.SOUTH,lbl);
        */
        /*
        adminAccountsPanel=new AdminAccountsPanel(0.0f);
        adminAccountsPanel.startFading("IN");
        backgroundPanel.add(adminAccountsPanel);
        springLayout.putConstraint(SpringLayout.WEST,adminAccountsPanel,0,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,adminAccountsPanel,20,SpringLayout.SOUTH,lbl);
        
        */
        
        
        signCurve=new curvePanel(getWidth(),100);
        backgroundPanel.add(signCurve);
        springLayout.putConstraint(SpringLayout.WEST,signCurve,0,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.SOUTH,signCurve,-30,SpringLayout.SOUTH,this); 
       
        add(backgroundPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void ToggleStartUpPaneltoWithAdminLogin(){
        backgroundPanel.remove(startUpPanelWithoutLogin);
        startUpPanelWithAdminLogin=new StartUpPanelWithAdminLogin(550,200,0.0f);
        backgroundPanel.add(startUpPanelWithAdminLogin);
        springLayout.putConstraint(SpringLayout.WEST,startUpPanelWithAdminLogin,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,startUpPanelWithAdminLogin,60,SpringLayout.SOUTH,lbl);
        startUpPanelWithAdminLogin.startFading("IN");
    }
    public void ToggleStartUpPaneltoWithStudentLogin(){
        backgroundPanel.remove(startUpPanelWithoutLogin);
        backgroundPanel.revalidate();
        startUpPanelWithStudentLogin=new StartUpPanelWithStudentLogin(550,200,0.0f);
        backgroundPanel.add(startUpPanelWithStudentLogin);
        springLayout.putConstraint(SpringLayout.WEST,startUpPanelWithStudentLogin,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,startUpPanelWithStudentLogin,60,SpringLayout.SOUTH,lbl);
        startUpPanelWithStudentLogin.startFading("IN");
    }
    public void ToggleStartUpPaneltoWithAdminLogintoAccountsPanel(){
        backgroundPanel.remove(startUpPanelWithAdminLogin);
        
        adminAccountsPanel=new AdminAccountsPanel(1.0f);
        adminAccountsPanel.startFading("IN");
        backgroundPanel.add(adminAccountsPanel);
        backgroundPanel.revalidate();
        springLayout.putConstraint(SpringLayout.WEST,adminAccountsPanel,0,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,adminAccountsPanel,20,SpringLayout.SOUTH,lbl);
        adminAccountsPanel.searchBox.requestFocus();
        backgroundPanel.repaint();
    }
    public void ToggleStartUpPaneltoWithoutLoginFromStudentLogin(){
        backgroundPanel.remove(startUpPanelWithStudentLogin);
        backgroundPanel.revalidate();
        startUpPanelWithoutLogin=new StartUpPanelWithoutLogin(560,200,0.0f);
        backgroundPanel.add(startUpPanelWithoutLogin);
        springLayout.putConstraint(SpringLayout.WEST,startUpPanelWithoutLogin,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,startUpPanelWithoutLogin,60,SpringLayout.SOUTH,lbl);
        startUpPanelWithoutLogin.startFading("IN");
    }
    public void ToggleStartUpPaneltoWithoutLoginFromAdminLogin(){
        backgroundPanel.remove(startUpPanelWithAdminLogin);
        backgroundPanel.revalidate();
        startUpPanelWithoutLogin=new StartUpPanelWithoutLogin(560,200,0.0f);
        backgroundPanel.add(startUpPanelWithoutLogin);
        springLayout.putConstraint(SpringLayout.WEST,startUpPanelWithoutLogin,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,startUpPanelWithoutLogin,60,SpringLayout.SOUTH,lbl);
        startUpPanelWithoutLogin.startFading("IN");
    }
    public void ToggleStartUpPaneltoWithAdminLoginFromAccountsPanel(){
        adminAccountsPanel.startFading("OUT");
        backgroundPanel.remove(adminAccountsPanel);
        backgroundPanel.revalidate();
        backgroundPanel.repaint();
        startUpPanelWithAdminLogin=new StartUpPanelWithAdminLogin(550,200,1.0f);
        backgroundPanel.add(startUpPanelWithAdminLogin);
        springLayout.putConstraint(SpringLayout.WEST,startUpPanelWithAdminLogin,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,startUpPanelWithAdminLogin,60,SpringLayout.SOUTH,lbl);
        startUpPanelWithAdminLogin.startFading("IN");
    }
    
    
    public static void main(String [] ar){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
              HostelManagementSystemApp app=new HostelManagementSystemApp();
              app.setVisible(true);
            }
        });
    }
    private class mouseListener extends MouseAdapter{
        private customBackgroundPanel ref;
        mouseListener(customBackgroundPanel panel){
            this.ref=panel;
        }
        @Override
       public void mouseClicked(MouseEvent mev){
           ref.requestFocus();
       }
    }
}