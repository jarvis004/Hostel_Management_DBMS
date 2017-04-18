
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import hostelmanagementsystem.lib.conn.connection;

public class LoginPanel extends JPanel{
    public int width=400,height=200;
    CrossLabel crossLabel;
    public customTextField username;
    private customPasswordField password;
    private customButtonPanel loginButton;
    private loadingIcon ldic;
    private showWarning warning;
    private String title;
    private SpringLayout springLayout;
    Font font=null;
    LoginPanel ref=null;
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
    public LoginPanel(String title){
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
          font=Font.createFont(Font.TRUETYPE_FONT,LoginPanel.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.PLAIN,14f);
        }
        catch(FontFormatException fe){
       }
       catch(IOException e){
           e.printStackTrace();
       }
        username=new customTextField(250,26,"Username",font);
        add(username);
        springLayout.putConstraint(SpringLayout.WEST,username,65,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,username,60,SpringLayout.NORTH,this);
        
        password=new customPasswordField(170,26,"Password",font);
        add(password);
        springLayout.putConstraint(SpringLayout.WEST,password,65,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,password,10,SpringLayout.SOUTH,username);
        password.addKeyListener(new loginByEnter());
        
        loginButton=new customButtonPanel("Login",70,29,7);
        add(loginButton);
        springLayout.putConstraint(SpringLayout.WEST,loginButton,5,SpringLayout.EAST,password);
        springLayout.putConstraint(SpringLayout.NORTH,loginButton,8,SpringLayout.SOUTH,username);
        loginButton.cbt.addActionListener(new login(this));
        
        ldic=new loadingIcon();
        add(ldic);
        springLayout.putConstraint(SpringLayout.WEST,ldic,-15,SpringLayout.EAST,loginButton);
        springLayout.putConstraint(SpringLayout.NORTH,ldic,16,SpringLayout.SOUTH,username);
        ldic.setVisible(false);
        username.requestFocus();
    }
    private class login implements ActionListener{
        private LoginPanel parent=null;
        login(LoginPanel pane){
            parent=pane;
        }
        public void actionPerformed(ActionEvent acev){
            Thread loginThread=new Thread(){
               @Override
               public void run(){loginProcess();}
            };
            loginThread.start();
        }
    }
    private class loginByEnter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent kev){
            if(kev.getKeyCode()==10){
                Thread loginThread=new Thread(){
                    @Override
                    public void run(){loginProcess();}
                };
                loginThread.start();
            }
        }
    }
    
    public void loginProcess(){
        if(username.getText().length()==0){
               Globals.currentGlassPane.removeCustomToolTips("usernameTooltip");
               Globals.currentGlassPane.removeCustomToolTips("passwordTooltip");   //the computation which determines that whether this tooltip exist or not will be handelled by the glass pane function
               Globals.currentGlassPane.addCustomToolTips(132,20,"Enter Your Username","usernameTooltip",25,20,32,27,39,20);  //last cords are related with tooltip rect cords not with tooltip cords
               Globals.currentGlassPane.glassPaneLayout.addCordinates(200,139);
               revalidate();
               username.requestFocus();
        }
        else if(password.getText().length()==0){
               Globals.currentGlassPane.removeCustomToolTips("usernameTooltip");
               Globals.currentGlassPane.removeCustomToolTips("passwordTooltip");
               Globals.currentGlassPane.addCustomToolTips(129,20,"Enter Your Password","passwordTooltip",30,8,37,1,44,8);
               Globals.currentGlassPane.glassPaneLayout.addCordinates(190,228);
               password.requestFocus();
               revalidate();
        }
        else{
            loginButton.cbt.setEnabled(false);
            ldic.setVisible(true);
            Globals.currentGlassPane.removeCustomToolTips("usernameTooltip");
            Globals.currentGlassPane.removeCustomToolTips("passwordTooltip");
            connection conn=null;
            if(title.equals("Admin Login")){
                conn=new connection(Globals.domain+"/HMS/adminlogin.php");
                Globals.TypeOfLogin="ADL";
            }
            else if(title.equals("Student Login")){
                conn=new connection(Globals.domain+"/HMS/studentlogin.php");
                Globals.TypeOfLogin="STL";
            }
            String uname=username.getText();
            conn.addFormFields("username",uname);
            conn.addFormFields("password",password.getText());
            String resp=conn.execute();
            JSONObject json =(JSONObject) JSONValue.parse(resp);
            if(Boolean.valueOf(json.get("login").toString())==true){
                Globals.SESSION_ID=(Long)json.get("session_id");
                Globals.USERNAME=uname;
                if(Globals.TypeOfLogin.contains("STL"))
                    Globals.student_name=json.get("student_name").toString();
                FadingEffect fading=new FadingEffect(500.0f,"OUT",ref,"LoginPanel");
                fadingTimer=new Timer(30,fading);
                fading.setGlassPaneFading(true);
                fadingTimer.start();
            }
            else{
                if(Boolean.valueOf(json.get("login_reason_username").toString())==true){
                    username.requestFocus();
                    Globals.currentGlassPane.addCustomToolTips(109,20,"Invalid Username","usernameTooltip",25,20,32,27,39,20);
                    Globals.currentGlassPane.glassPaneLayout.addCordinates(200,139);   
                }
                else if(Boolean.valueOf(json.get("login_reason_password").toString())==true){
                    Globals.currentGlassPane.addCustomToolTips(108,20,"Invalid Password","passwordTooltip",30,8,37,1,44,8);
                    Globals.currentGlassPane.glassPaneLayout.addCordinates(190,228);
                    password.requestFocus();
                }
            }
            loginButton.cbt.setEnabled(true);
            ldic.setVisible(false); 
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
