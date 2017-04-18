package hostelmanagementsystem.lib;
import hostelmanagementsystem.lib.conn.connection;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SpringLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
public class AdminAccountsPanel extends JPanel{
    private int width=620,height=250;
    SpringLayout springLayout;
    public customTextField searchBox=null;
    private customButtonPanel searchButton,returnButton;
    private loadingIcon ldic;
    private Font font;
    float alphaLevel=0.0f;
    private Timer fadingTimer=null;
    private boolean fadingTypeIN;
    private customToolTip tooltip;
    private PicSlider picSlider;
    private String alphaPattern="^[a-zA-Z]+$",numericPattern="^[0-9]+$";
    private AdminAccountsPanel ref;
    public AdminAccountsPanel(float alpha){
        ref=this;
        this.alphaLevel=alpha;
        setPreferredSize(new Dimension(width,height));
        setOpaque(false);
        springLayout=new SpringLayout();
        setLayout(springLayout);
        addMouseListener(new mouseListener(this));
        font=new Font("verdana",Font.PLAIN,14);
        try{
          font=Font.createFont(Font.TRUETYPE_FONT,AdminAccountsPanel.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.PLAIN,14f);
        }
        catch(FontFormatException fe){
       }
       catch(IOException e){
           e.printStackTrace();
       }
        tooltip=new customToolTip(98,27,"  Invalid Query  ",true,false);
        tooltip.addArrowCords(20,7,27,0,34,7,7,7);
        add(tooltip);
        springLayout.putConstraint(SpringLayout.WEST,tooltip,200,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,tooltip,36,SpringLayout.NORTH,this);
        tooltip.setVisible(false);
        searchBox=new customTextField(250,26,"Search",font);
        add(searchBox);
        springLayout.putConstraint(SpringLayout.WEST,searchBox,40,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,searchBox,10,SpringLayout.NORTH,this);
        searchBox.addKeyListener(new keyListener());
        
        searchButton=new customButtonPanel("Search",70,29,7);
        add(searchButton);
        springLayout.putConstraint(SpringLayout.WEST,searchButton,25,SpringLayout.EAST,searchBox);
        springLayout.putConstraint(SpringLayout.NORTH,searchButton,-3,SpringLayout.NORTH,searchBox);
        searchButton.cbt.addActionListener(new actionListener(searchButton));
        
        returnButton=new customButtonPanel("Return",70,29,7);
        add(returnButton);
        springLayout.putConstraint(SpringLayout.WEST,returnButton,0,SpringLayout.EAST,searchButton);
        springLayout.putConstraint(SpringLayout.NORTH,returnButton,-3,SpringLayout.NORTH,searchBox);
        returnButton.cbt.addActionListener(new actionListener(returnButton));
        
        ldic=new loadingIcon();
        add(ldic);
        springLayout.putConstraint(SpringLayout.WEST,ldic,5,SpringLayout.EAST,searchBox);
        springLayout.putConstraint(SpringLayout.NORTH,ldic,5,SpringLayout.NORTH,searchBox);
        ldic.setVisible(false);
        
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
          }
          g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaLevel));
          super.paintChildren(g);
          g2.setComposite(oldComposite);
        }
    
    //handelling server query
    
    public void search(String qry,String tp){
        final String type=tp,query=qry;
        Thread searchThread=new Thread(){ 
          @Override
          public void run(){  
            ldic.setVisible(true);
            searchButton.cbt.setEnabled(false);
            tooltip.setVisible(false);
            try{
                remove(picSlider);
                revalidate();
                repaint();
            }
            catch(Exception e){
            
            }
            connection conn=new connection(Globals.domain+"/HMS/search.php");
            conn.addFormFields("query",query);
            conn.addFormFields("type",type);
            conn.addFormFields("session_id",String.valueOf(Globals.SESSION_ID));
            conn.addFormFields("username",Globals.USERNAME);
            String resp=conn.execute();
            if(conn.getStatus()==200){
                JSONObject json =(JSONObject) JSONValue.parse(resp);
                if(json.get("result").toString().equals("no")){
                    tooltip.setText("No result found");
                    tooltip.setVisible(true);
                }
                else if(json.get("result").toString().equals("yes")){
                    if(type.equals("name")){ 
                        JSONArray JArr=(JSONArray)json.get("data");
                  
                        String [] picName=new String[JArr.size()];
                        String [] name=new String[JArr.size()];
                        String [] roll=new String[JArr.size()];
                        String [] batch=new String[JArr.size()];
                        String [] stream=new String[JArr.size()];
                        for(int i=0;i<JArr.size();i++){
                            json = (JSONObject)JArr.get(i);
                            picName[i]=json.get("pic_name").toString();
                            name[i]=json.get("name").toString();
                            roll[i]=json.get("roll").toString();
                            batch[i]=json.get("batch").toString();
                            stream[i]=json.get("stream").toString();
                        }
                        picSlider=new PicSlider(picName,name,roll,batch,stream);
                        add(picSlider);
                        revalidate();
                        repaint();
                        springLayout.putConstraint(SpringLayout.WEST,picSlider,0,SpringLayout.WEST,ref);
                        springLayout.putConstraint(SpringLayout.NORTH,picSlider,20,SpringLayout.SOUTH,searchBox);
                    }
               
                    else if(type.equals("roll")){
                        String picName=json.get("pic_name").toString();
                        String name=json.get("name").toString();
                        String roll=json.get("roll").toString();
                        String batch=json.get("batch").toString();
                        String stream=json.get("stream").toString();
                        String allotDate=json.get("allot_date").toString();
                        String messDue=json.get("due_mess_bil").toString();
                        String [] date=allotDate.split("-");
                        int mon=Integer.parseInt(date[1])-1;
                        int year=Integer.parseInt(date[0]);
                        BufferedImage pic=null;
                        try{
                            URL url = new URL(Globals.domain+"/HMS/Student_Pic/"+picName);
                            pic=ImageIO.read(url); 
                        
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        JSONArray rent=(JSONArray)json.get("rent_account");
                        JSONArray mess=(JSONArray)json.get("mess_account");
                    
                        boolean [] rentAccount=new boolean[4];
                        json = (JSONObject)rent.get(0);
                        rentAccount[0]=Boolean.valueOf(json.get("jan_mar").toString());
                        rentAccount[1]=Boolean.valueOf(json.get("apr_jun").toString());
                        rentAccount[2]=Boolean.valueOf(json.get("jul_sep").toString());
                        rentAccount[3]=Boolean.valueOf(json.get("oct_dec").toString());
                        boolean [] messAccount=new boolean[12];
                        json=(JSONObject)mess.get(0);
                        messAccount[0]=Boolean.valueOf(json.get("jan").toString());
                        messAccount[1]=Boolean.valueOf(json.get("feb").toString());
                        messAccount[2]=Boolean.valueOf(json.get("mar").toString());
                        messAccount[3]=Boolean.valueOf(json.get("apr").toString());
                       
                        messAccount[4]=Boolean.valueOf(json.get("may").toString());
                        messAccount[5]=Boolean.valueOf(json.get("jun").toString());
                        messAccount[6]=Boolean.valueOf(json.get("jul").toString());
                        messAccount[7]=Boolean.valueOf(json.get("aug").toString());
                       
                        messAccount[8]=Boolean.valueOf(json.get("sep").toString());
                        messAccount[9]=Boolean.valueOf(json.get("oct").toString());
                        messAccount[10]=Boolean.valueOf(json.get("nov").toString());
                        messAccount[11]=Boolean.valueOf(json.get("dec").toString());
                        IndividualAccounts IndActPan=new IndividualAccounts(pic,roll,name,batch,stream,mon,year,rentAccount,messAccount,0.0f,Integer.parseInt(messDue));
                        glassPane customGlassPane=new glassPane(Globals.mainApp.getWidth(),Globals.mainApp.getHeight());
                        Globals.mainApp.setGlassPane(customGlassPane);
                        Globals.currentGlassPane=customGlassPane;
                        Globals.currentGlassPane.glassPaneLayout.addCordinates( (Globals.mainApp.getWidth()-IndActPan.width)/2, 
                                                                            (Globals.mainApp.getHeight()-IndActPan.height)/2
                                                                          );
                        customGlassPane.addComponent(IndActPan,IndActPan.width,IndActPan.height);
                        Globals.mainApp.getGlassPane().setVisible(true);
                        Globals.currentGlassPane.glassPaneLayout.addCordinates(40,25);
                        Globals.currentGlassPane.addComponent(IndActPan,IndActPan.width,IndActPan.height);
                        IndActPan.startFading("IN");
                    }
                }
            }
            else {
                tooltip.setText("Check Your Connection");
                tooltip.setSize(140,27);
                tooltip.setVisible(true);
            }
              ldic.setVisible(false);
              searchButton.cbt.setEnabled(true);
          }
        };
        searchThread.start();
    }
    
    
    
    class keyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent kev){
           int code=kev.getKeyCode();
           if((code>64 && code<90) || code==8 || code==127 || code==32){
              //if(searchBox.getText().matches(alphaPattern)) && searchBox.getText().length()>0)
                if(!searchBox.getText().matches(numericPattern))
                  search(searchBox.getText(),"name");
           }
           else if(code==10){  //if enter key is pressed
              if(searchBox.getText().matches(numericPattern) && searchBox.getText().length()>0){
                 search(searchBox.getText(),"roll");
              }
              else if(searchBox.getText().matches(alphaPattern) && searchBox.getText().length()>0)
                  search(searchBox.getText(),"name");
              else{
                 //this if  statement line is only for a demo part and should be removed
                 if(searchBox.getText().length()>0)
                    tooltip.setVisible(true);
              }
           }
        }
        @Override
        public void keyPressed(KeyEvent kev){
           int code=kev.getKeyCode();
           if(code==KeyEvent.VK_UP)
               try{  picSlider.scrollAndAnimateBy(-1);}catch(Exception e){}
           else if(code==KeyEvent.VK_DOWN)
               try{  picSlider.scrollAndAnimateBy(1);}catch(Exception e){}
        }
    }
    class actionListener implements ActionListener{
        customButtonPanel src;
        actionListener(customButtonPanel src){
           this.src=src; 
        }
        public void actionPerformed(ActionEvent e) {
            if(src==returnButton)
                Globals.mainApp.ToggleStartUpPaneltoWithAdminLoginFromAccountsPanel();
            else{
                if(searchBox.getText().matches(numericPattern) && searchBox.getText().length()>0){
                    search(searchBox.getText(),"roll");
                }
                else if(searchBox.getText().matches(alphaPattern) && searchBox.getText().length()>0)
                    search(searchBox.getText(),"name");
                else{
                    tooltip.setVisible(true);
                }
                //ldic.setVisible(false);
                searchButton.cbt.setEnabled(true);
            }
        }
        
    }
                                                        //for giving focus to the panel
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
    
    
    public void startFading(String type){
       this.fadingTypeIN=type.equals("IN")?true:false;
       FadingEffect fading=new FadingEffect(300.0f,type,this,"AdminAccountsPanel");
       this.fadingTimer=new Timer(30,fading);
       this.fadingTimer.start();
   }
}
