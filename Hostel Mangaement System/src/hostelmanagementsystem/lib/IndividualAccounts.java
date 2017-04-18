package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.Calendar;



import hostelmanagementsystem.lib.conn.connection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class IndividualAccounts extends JPanel{
    BufferedImage pic;
    private IndividualAccounts ref=null;
    private String roll,name,batch,stream;
    int width=530,height=320,currentYear;
    private int admissionDate,admissionMonth,admissionYear,messDue,changedYear=0;
    private Calendar cal;
    private Font font;
    private Timer fadingTimer=null;
    private boolean fadingStarted=false,fadingTypeIN;
    private String title;
    private customButtonPanel updateButton;
    private loadingIcon ldic,ldicYC;
    float alphaLevel=1.0f;
    private boolean glassPanefading=false;
    
    private boolean [] messBox;
    private boolean [] rentBox;
    private SpringLayout springLayout=new SpringLayout();
    private smallCustomJLabel yearLabel,prevYearLabel,nextYearLabel;
    private CustomCheckBox JanMarChkRent,AprJunChkRent,JulSepChkRent,OctDecChkRent,JanChkMess,FebChkMess,MarChkMess,AprChkMess,MayChkMess,JunChkMess,
                           JulChkMess,AugChkMess,SepChkMess,OctChkMess,NovChkMess,DecChkMess;
    private PicLabel picLabel;
    LinearGradientPaint backgroundPaint=new LinearGradientPaint(0.0f, 0.0f, 0.0f,this.height,
          new float[] { 0.0f,0.066f,0.077f,0.087f,0.5f,1.0f },
          new Color[] { new Color(99,108,124),
                        new Color(23,31,47),
                        new Color(164,168,179),
                        new Color(32,39,45),
                        new Color(60,69,87),
                        new Color(150,161,187) });
    
    public IndividualAccounts(BufferedImage pic,String roll,String name,String batch,String stream,int admMon,int admYear,boolean[] rentBox,boolean [] messBox)
    {  this(pic,roll,name,batch,stream,admMon,admYear,rentBox,messBox,0); }
    public IndividualAccounts(BufferedImage pic,String roll,String name,String batch,String stream,int admMon,int admYear,boolean[] rentBox,boolean [] messBox,float opacity)
    {  this(pic,roll,name,batch,stream,admMon,admYear,rentBox,messBox,opacity,0);}
    
    public IndividualAccounts(BufferedImage pic,String roll,String name,String batch,String stream,int admMon,int admYear,boolean[] rentBox,boolean [] messBox,float opacity,int messDue){
        setOpaque(false);
        this.alphaLevel=opacity;
        ref=this;
        this.messDue=messDue;
        this.currentYear=cal.getInstance().get(Calendar.YEAR);
        this.admissionMonth=admMon;
        this.admissionYear=admYear;
        this.roll=roll;
        this.rentBox=rentBox;
        this.messBox=messBox;
        setPreferredSize(new Dimension(width,height));
        setLayout(springLayout);
        font=new Font("verdana",Font.PLAIN,14);
        
        //changing the llok and feel
        try{
          font=Font.createFont(Font.TRUETYPE_FONT,IndividualAccounts.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.PLAIN,14f);
        }
        catch(FontFormatException fe){
       }
       catch(IOException e){
           e.printStackTrace();
       }
        
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        } 
         
        
        
        CrossLabel crossLabel=new CrossLabel(16,new Color(230,230,230),new Color(20,20,20));
        add(crossLabel);
        springLayout.putConstraint(SpringLayout.EAST,crossLabel,-10,SpringLayout.EAST,this);
        springLayout.putConstraint(SpringLayout.NORTH,crossLabel,2,SpringLayout.NORTH,this);
        picLabel=new PicLabel(pic);
        add(picLabel);
        springLayout.putConstraint(SpringLayout.WEST,picLabel,30,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,picLabel,40,SpringLayout.NORTH,this);
        
        smallCustomJLabel nameLabel=new smallCustomJLabel(name,new Color(250,250,250));
        add(nameLabel);
        springLayout.putConstraint(SpringLayout.WEST,nameLabel,20,SpringLayout.EAST,picLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nameLabel,40,SpringLayout.NORTH,this);
        
        
        smallCustomJLabel rollLabel=new smallCustomJLabel(roll,new Color(250,250,250));
        add(rollLabel);
        springLayout.putConstraint(SpringLayout.WEST,rollLabel,20,SpringLayout.EAST,picLabel);
        springLayout.putConstraint(SpringLayout.NORTH,rollLabel,5,SpringLayout.SOUTH,nameLabel);
        
        smallCustomJLabel streamLabel=new smallCustomJLabel(stream,new Color(250,250,250));
        add(streamLabel);
        springLayout.putConstraint(SpringLayout.WEST,streamLabel,20,SpringLayout.EAST,picLabel);
        springLayout.putConstraint(SpringLayout.NORTH,streamLabel,5,SpringLayout.SOUTH,rollLabel); 
        
        smallCustomJLabel dashLabel=new smallCustomJLabel("_",new Color(250,250,250));
        add(dashLabel);
        springLayout.putConstraint(SpringLayout.WEST,dashLabel,6,SpringLayout.EAST,streamLabel);
        springLayout.putConstraint(SpringLayout.NORTH,dashLabel,-1,SpringLayout.SOUTH,rollLabel);
        
        smallCustomJLabel batchLabel=new smallCustomJLabel(batch,new Color(250,250,250));
        add(batchLabel);
        springLayout.putConstraint(SpringLayout.WEST,batchLabel,5,SpringLayout.EAST,dashLabel);
        springLayout.putConstraint(SpringLayout.NORTH,batchLabel,5,SpringLayout.SOUTH,rollLabel);
        
        
        if(messDue>0){
           smallCustomJLabel messDueLabel=new smallCustomJLabel("Mess Due - "+String.valueOf(messDue),new Color(250,250,250));
           add(messDueLabel);
           springLayout.putConstraint(SpringLayout.WEST,messDueLabel,20,SpringLayout.EAST,picLabel);
           springLayout.putConstraint(SpringLayout.NORTH,messDueLabel,15,SpringLayout.SOUTH,batchLabel); 
        }
        
        //Account Edit Panel
        
        //handelling Prev link
        
        if(this.admissionYear<this.currentYear){
            prevYearLabel=new smallCustomJLabel("Prev",new Color(250,250,250),12,false,Font.PLAIN);
            add(prevYearLabel);
            springLayout.putConstraint(SpringLayout.EAST,prevYearLabel,55,SpringLayout.WEST,this);
            springLayout.putConstraint(SpringLayout.NORTH,prevYearLabel,20,SpringLayout.SOUTH,picLabel);
            prevYearLabel.addMouseListener(new linkMouseListner(prevYearLabel)); 
        }
         
        
        
        
        
        
        
        
        smallCustomJLabel EditAccountLabel=new smallCustomJLabel("Edit Paid Fee Detail - ",new Color(250,250,250),16,true);
        add(EditAccountLabel);
        springLayout.putConstraint(SpringLayout.WEST,EditAccountLabel,40,SpringLayout.WEST,picLabel);
        springLayout.putConstraint(SpringLayout.NORTH,EditAccountLabel,15,SpringLayout.SOUTH,picLabel);
        
        yearLabel=new smallCustomJLabel(String.valueOf(currentYear),new Color(250,250,250),16,true);
        add(yearLabel);
        springLayout.putConstraint(SpringLayout.WEST,yearLabel,0,SpringLayout.EAST,EditAccountLabel);
        springLayout.putConstraint(SpringLayout.NORTH,yearLabel,15,SpringLayout.SOUTH,picLabel);
        
        ldicYC=new loadingIcon();
        add(ldicYC);
        springLayout.putConstraint(SpringLayout.WEST,ldicYC,60,SpringLayout.EAST,yearLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ldicYC,20,SpringLayout.SOUTH,picLabel);
        ldicYC.setVisible(false);
        
        
        
        
        //hostel rent part
        
        smallCustomJLabel feeTypeRent=new smallCustomJLabel("Hostel Rent : ",new Color(250,250,250),16);
        add(feeTypeRent);
        springLayout.putConstraint(SpringLayout.WEST,feeTypeRent,0,SpringLayout.WEST,EditAccountLabel);
        springLayout.putConstraint(SpringLayout.NORTH,feeTypeRent,7,SpringLayout.SOUTH,EditAccountLabel);
        
        JanMarChkRent=new CustomCheckBox("Jan-Mar",new Color(250,250,250));
        add(JanMarChkRent);
        springLayout.putConstraint(SpringLayout.WEST,JanMarChkRent,0,SpringLayout.EAST,feeTypeRent);
        springLayout.putConstraint(SpringLayout.NORTH,JanMarChkRent,10,SpringLayout.SOUTH,EditAccountLabel);
        
        
        AprJunChkRent=new CustomCheckBox("Apr-Jun",new Color(250,250,250));
        add(AprJunChkRent);
        springLayout.putConstraint(SpringLayout.WEST,AprJunChkRent,14,SpringLayout.EAST,JanMarChkRent);
        springLayout.putConstraint(SpringLayout.NORTH,AprJunChkRent,10,SpringLayout.SOUTH,EditAccountLabel);
        
        
        JulSepChkRent=new CustomCheckBox("Jul-Sep",new Color(250,250,250));
        add(JulSepChkRent);
        springLayout.putConstraint(SpringLayout.WEST,JulSepChkRent,14,SpringLayout.EAST,AprJunChkRent);
        springLayout.putConstraint(SpringLayout.NORTH,JulSepChkRent,10,SpringLayout.SOUTH,EditAccountLabel);
        
        
        OctDecChkRent=new CustomCheckBox("Oct-Dec",new Color(250,250,250));
        add(OctDecChkRent);
        springLayout.putConstraint(SpringLayout.WEST,OctDecChkRent,14,SpringLayout.EAST,JulSepChkRent);
        springLayout.putConstraint(SpringLayout.NORTH,OctDecChkRent,10,SpringLayout.SOUTH,EditAccountLabel);
        
        
        //Mess Charge part
        
        smallCustomJLabel feeTypeMess=new smallCustomJLabel("Mess Charge : ",new Color(250,250,250),16);
        add(feeTypeMess);
        springLayout.putConstraint(SpringLayout.WEST,feeTypeMess,0,SpringLayout.WEST,EditAccountLabel);
        springLayout.putConstraint(SpringLayout.NORTH,feeTypeMess,11,SpringLayout.SOUTH,feeTypeRent);
        
        
        
        JanChkMess=new CustomCheckBox("Jan",new Color(250,250,250));
        add(JanChkMess);
        springLayout.putConstraint(SpringLayout.WEST,JanChkMess,0,SpringLayout.EAST,feeTypeMess);
        springLayout.putConstraint(SpringLayout.NORTH,JanChkMess,15,SpringLayout.SOUTH,feeTypeRent);
        
        
        
        
        FebChkMess=new CustomCheckBox("Feb",new Color(250,250,250));
        add(FebChkMess);
        springLayout.putConstraint(SpringLayout.WEST,FebChkMess,10,SpringLayout.EAST,JanChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,FebChkMess,15,SpringLayout.SOUTH,feeTypeRent);
        
        MarChkMess=new CustomCheckBox("Mar",new Color(250,250,250));
        add(MarChkMess);
        springLayout.putConstraint(SpringLayout.WEST,MarChkMess,10,SpringLayout.EAST,FebChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,MarChkMess,15,SpringLayout.SOUTH,feeTypeRent);
        
        AprChkMess=new CustomCheckBox("Apr",new Color(250,250,250));
        add(AprChkMess);
        springLayout.putConstraint(SpringLayout.WEST,AprChkMess,10,SpringLayout.EAST,MarChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,AprChkMess,15,SpringLayout.SOUTH,feeTypeRent);
        
        MayChkMess=new CustomCheckBox("May",new Color(250,250,250));
        add(MayChkMess);
        springLayout.putConstraint(SpringLayout.WEST,MayChkMess,10,SpringLayout.EAST,AprChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,MayChkMess,15,SpringLayout.SOUTH,feeTypeRent);
        
        
        JunChkMess=new CustomCheckBox("Jun",new Color(250,250,250));
        add(JunChkMess);
        springLayout.putConstraint(SpringLayout.WEST,JunChkMess,10,SpringLayout.EAST,MayChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,JunChkMess,15,SpringLayout.SOUTH,feeTypeRent);
        
        
        JulChkMess=new CustomCheckBox("Jul",new Color(250,250,250));
        add(JulChkMess);
        springLayout.putConstraint(SpringLayout.WEST,JulChkMess,0,SpringLayout.WEST,JanChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,JulChkMess,0,SpringLayout.SOUTH,JanChkMess);
        
        AugChkMess=new CustomCheckBox("Aug",new Color(250,250,250));
        add(AugChkMess);
        springLayout.putConstraint(SpringLayout.WEST,AugChkMess,15,SpringLayout.EAST,JulChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,AugChkMess,0,SpringLayout.SOUTH,JanChkMess);
        
        
        
        SepChkMess=new CustomCheckBox("Sep",new Color(250,250,250));
        add(SepChkMess);
        springLayout.putConstraint(SpringLayout.WEST,SepChkMess,6,SpringLayout.EAST,AugChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,SepChkMess,0,SpringLayout.SOUTH,JanChkMess);
        
        
        OctChkMess=new CustomCheckBox("Oct",new Color(250,250,250));
        add(OctChkMess);
        springLayout.putConstraint(SpringLayout.WEST,OctChkMess,10,SpringLayout.EAST,SepChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,OctChkMess,0,SpringLayout.SOUTH,JanChkMess);
        
        NovChkMess=new CustomCheckBox("Nov",new Color(250,250,250));
        add(NovChkMess);
        springLayout.putConstraint(SpringLayout.WEST,NovChkMess,10,SpringLayout.EAST,OctChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,NovChkMess,0,SpringLayout.SOUTH,JanChkMess);
        
        
        DecChkMess=new CustomCheckBox("Dec",new Color(250,250,250));
        add(DecChkMess);
        springLayout.putConstraint(SpringLayout.WEST,DecChkMess,12,SpringLayout.EAST,NovChkMess);
        springLayout.putConstraint(SpringLayout.NORTH,DecChkMess,0,SpringLayout.SOUTH,JanChkMess);
        
        //After adding the checkbox calling the function to check,uncheck or disabled the check box
        
        tracePrevNextDetail(this.rentBox,this.messBox,this.currentYear);
        
        
        
        Font fnt=new Font("Verdana",Font.BOLD,12);
        updateButton=new customButtonPanel("Update",80,29,5,fnt);
        add(updateButton);
        springLayout.putConstraint(SpringLayout.WEST,updateButton,-1,SpringLayout.WEST,feeTypeMess);
        springLayout.putConstraint(SpringLayout.NORTH,updateButton,20,SpringLayout.SOUTH,feeTypeMess);
        updateButton.cbt.addActionListener(new updateRecord());
        
        ldic=new loadingIcon();
        add(ldic);
        springLayout.putConstraint(SpringLayout.WEST,ldic,-10,SpringLayout.EAST,updateButton);
        springLayout.putConstraint(SpringLayout.NORTH,ldic,27,SpringLayout.SOUTH,feeTypeMess);
        ldic.setVisible(false);
        
        
        //changing the look and feel back to original
        
        
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
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
           if(this.glassPanefading){
               Globals.currentGlassPane.removeComponent(ref);
               Globals.currentGlassPane.setVisible(false);
           }
        }
        AlphaComposite composite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,this.alphaLevel);
        g2d.setComposite(composite);
        g2d.fillRoundRect(0,0,this.width,this.height,4,4);
        g2d.setPaint(oldPaint);
        g2d.setColor(Color.WHITE);
        Font oldFont=g2d.getFont();
        font=g2d.getFont().deriveFont(Font.BOLD,17);
        g2d.setFont(font);
        g2d.drawString("Account Management",10,15);
        g2d.setComposite(oldComposite);
        g2d.setFont(oldFont);
                
    }
    
    
    
    
    private class chkBxActionListener implements ActionListener{
        CustomCheckBox src;
        int boxIndex;
        String boxType;
        chkBxActionListener(CustomCheckBox src,int index,String type){
            this.src=src;
            this.boxIndex=index;
            this.boxType=type;
        }
        public void actionPerformed(ActionEvent e) {
            if(src.isSelected()){
                if(boxType.equals("rentBox"))
                    rentBox[this.boxIndex]=true;
                else
                    messBox[this.boxIndex]=true;
            }
            else{
                if(boxType.equals("rentBox"))
                    rentBox[this.boxIndex]=false;
                else
                    messBox[this.boxIndex]=false;
            }
        }
    }
    
    private class linkMouseListner extends MouseAdapter{
        smallCustomJLabel src;
        linkMouseListner(smallCustomJLabel lb){
            src=lb;
        }
        @Override
        public void mouseEntered(MouseEvent mev){
            src.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            src.drawLine();
        }
        @Override
        public void mouseExited(MouseEvent mev){
            src.removeLine();
        }
        @Override
        public void mouseClicked(MouseEvent mev){
            if(src==prevYearLabel){
                if(changedYear==0)
                  changedYear=currentYear-1;
                else
                    changedYear-=1;
            }
            else if(src==nextYearLabel){
                if(changedYear==0)
                  changedYear=currentYear+1;
                else
                    changedYear+=1;
            }
            getMoreDetails("roll");
        }
    }
    
    
    private void getMoreDetails(String tp){
        final String type=tp;
        Thread moreDetail=new Thread(){
            @Override
            public void run(){
               Globals.currentGlassPane.removeCustomToolTips("ConnectionErrorTooltip");
               revalidate();
               ldicYC.setVisible(true);
               connection conn=new connection(Globals.domain+"/HMS/search.php");
               conn.addFormFields("query",roll);
               conn.addFormFields("type",type);
               conn.addFormFields("year",String.valueOf(changedYear));
               conn.addFormFields("session_id",String.valueOf(Globals.SESSION_ID));
               conn.addFormFields("username",Globals.USERNAME);
               String resp=conn.execute();
            if(conn.getStatus()==200){
                JSONObject json =(JSONObject) JSONValue.parse(resp);
                String allotDate=json.get("allot_date").toString();
                String [] date=allotDate.split("-");
                int mon=Integer.parseInt(date[1])-1;
                int year=Integer.parseInt(date[0]);
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
                tracePrevNextDetail(rentAccount,messAccount,changedYear);
            }
            else {
                Globals.currentGlassPane.addCustomToolTips(140,20,"Check Your Connection","ConnectionErrorTooltip",0,0,0,0,0,0);
                Globals.currentGlassPane.glassPaneLayout.addCordinates(190,310);
                revalidate();
            }
            
            
            
            if(admissionYear==changedYear){
                try{remove(prevYearLabel);}catch(Exception e){}
            }
            else{
                try{remove(prevYearLabel);}catch(Exception e){}
                prevYearLabel=new smallCustomJLabel("Prev",new Color(250,250,250),12,false,Font.PLAIN);
                add(prevYearLabel);
                springLayout.putConstraint(SpringLayout.EAST,prevYearLabel,55,SpringLayout.WEST,ref);
                springLayout.putConstraint(SpringLayout.NORTH,prevYearLabel,20,SpringLayout.SOUTH,picLabel);
                prevYearLabel.addMouseListener(new linkMouseListner(prevYearLabel)); 
                revalidate();
            }
            
            
            if(changedYear==currentYear){
                try{remove(nextYearLabel);}catch(Exception e){}
            }
            else{
                try{remove(nextYearLabel);}catch(Exception e){}
                nextYearLabel=new smallCustomJLabel("Next",new Color(250,250,250),12,false,Font.PLAIN);
                add(nextYearLabel);
                springLayout.putConstraint(SpringLayout.WEST,nextYearLabel,20,SpringLayout.EAST,yearLabel);
                springLayout.putConstraint(SpringLayout.NORTH,nextYearLabel,20,SpringLayout.SOUTH,picLabel);
                nextYearLabel.addMouseListener(new linkMouseListner(nextYearLabel));
                revalidate();
            }
            yearLabel.changeText(String.valueOf(changedYear));
            ldicYC.setVisible(false);
          }
        };moreDetail.start();
    }
    
    //tracing the fee pay checkfox
    private void tracePrevNextDetail(boolean [] rent,boolean [] mess,int year){
        //tracing for rent
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>2)){
            if(rent[0])
                JanMarChkRent.setSelected(true);
             else 
                JanMarChkRent.setSelected(false);
            JanMarChkRent.addActionListener(new chkBxActionListener(JanMarChkRent,0,"rentBox"));
        }
        else
            JanMarChkRent.setEnabled(false);
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>5)){
            if(rent[1])
                AprJunChkRent.setSelected(true);
            else 
                AprJunChkRent.setSelected(false);
            AprJunChkRent.addActionListener(new chkBxActionListener(AprJunChkRent,1,"rentBox"));
        }
        else
            AprJunChkRent.setEnabled(false);
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>8)){
            if(rent[2])
                JulSepChkRent.setSelected(true);
             else 
                JulSepChkRent.setSelected(false);
            JulSepChkRent.addActionListener(new chkBxActionListener(JulSepChkRent,2,"rentBox"));
        }
        else
            JulSepChkRent.setEnabled(false);
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>11)){
            if(rent[3])
                OctDecChkRent.setSelected(true);
             else 
                OctDecChkRent.setSelected(false);
            OctDecChkRent.addActionListener(new chkBxActionListener(OctDecChkRent,3,"rentBox"));
        }
        else
            OctDecChkRent.setEnabled(false); 
        
        
        //tracing for mess option
       if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>0)){
            if(mess[0])
                JanChkMess.setSelected(true);
             else 
                JanChkMess.setSelected(false);
            JanChkMess.addActionListener(new chkBxActionListener(JanChkMess,0,"messBox"));
        }
        else
            JanChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>1)){
            if(mess[1])
                FebChkMess.setSelected(true);
            else 
                FebChkMess.setSelected(false);
            FebChkMess.addActionListener(new chkBxActionListener(FebChkMess,1,"messBox"));
        }
        else
            FebChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>2)){
            if(mess[2])
                MarChkMess.setSelected(true);
            else 
                MarChkMess.setSelected(false);
            MarChkMess.addActionListener(new chkBxActionListener(MarChkMess,2,"messBox"));
        }
        else
            MarChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>3)){
            if(mess[3])
                AprChkMess.setSelected(true);
            else 
                AprChkMess.setSelected(false);
            AprChkMess.addActionListener(new chkBxActionListener(AprChkMess,3,"messBox"));
        }
        else
            AprChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>4)){
            if(mess[4])
                MayChkMess.setSelected(true);
            else 
                MayChkMess.setSelected(false);
            MayChkMess.addActionListener(new chkBxActionListener(MayChkMess,4,"messBox"));
        }
        else
            MayChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>5)){
            if(mess[5])
                JunChkMess.setSelected(true);
            else 
                JunChkMess.setSelected(false);
            JunChkMess.addActionListener(new chkBxActionListener(JunChkMess,5,"messBox"));
        }
        else
            JunChkMess.setEnabled(false);
        
        
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>6)){
            if(mess[6])
                JulChkMess.setSelected(true);
            else 
                JulChkMess.setSelected(false);
            JulChkMess.addActionListener(new chkBxActionListener(JulChkMess,6,"messBox"));
        }
        else
            JulChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>7)){
            if(mess[7])
                AugChkMess.setSelected(true);
            else 
                AugChkMess.setSelected(false);
            AugChkMess.addActionListener(new chkBxActionListener(AugChkMess,7,"messBox"));
        }
        else
            AugChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>8)){
            if(mess[8])
                SepChkMess.setSelected(true);
            else 
                SepChkMess.setSelected(false);
            SepChkMess.addActionListener(new chkBxActionListener(SepChkMess,8,"messBox"));
        }
        else
            SepChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>9)){
            if(mess[9])
                OctChkMess.setSelected(true);
            else 
                OctChkMess.setSelected(false);
            OctChkMess.addActionListener(new chkBxActionListener(OctChkMess,9,"messBox"));
        }
        else
            OctChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>10)){
            if(mess[10])
                NovChkMess.setSelected(true);
            else 
                NovChkMess.setSelected(false);
            NovChkMess.addActionListener(new chkBxActionListener(NovChkMess,10,"messBox"));
        }
        else
            NovChkMess.setEnabled(false);
        
        if(this.admissionYear<=year && !(this.admissionYear==year && this.admissionMonth>11)){
            if(mess[11])
                DecChkMess.setSelected(true);
            else 
                DecChkMess.setSelected(false);
            DecChkMess.addActionListener(new chkBxActionListener(DecChkMess,11,"messBox"));
        }
        else
            DecChkMess.setEnabled(false);
        
    }
    
    
    
    private class updateRecord implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            Thread updateThread=new Thread(){
              @Override
              public void run(){
                   Globals.currentGlassPane.removeCustomToolTips("ConnectionErrorTooltip");
                   revalidate();
                   updateButton.cbt.setEnabled(false);
                   ldic.setVisible(true);
                  String rent = new String();
                  rent="";
                  for (int i = 0; i <rentBox.length; i++) {
                    if (rentBox[i]) rent+="1"; else rent+="0";
                  }
                  
                  String mess = new String();
                  mess="";
                  for (int i = 0; i <messBox.length; i++) {
                    if (messBox[i]) mess+="1"; else mess+="0";
                  }
                  connection conn=new connection(Globals.domain+"/HMS/update_account.php");
                  conn.addFormFields("rentBox",rent);
                  conn.addFormFields("messBox",mess);
                  conn.addFormFields("roll",roll);
                  conn.addFormFields("year",String.valueOf(currentYear));
                  conn.addFormFields("session_id",String.valueOf(Globals.SESSION_ID));
                  conn.addFormFields("username",Globals.USERNAME);
                  
                  String resp=conn.execute();
                  if(conn.getStatus()==200)
                       startFading("OUT",true);  
                  else{
                      Globals.currentGlassPane.addCustomToolTips(140,20,"Check Your Connection","ConnectionErrorTooltip",0,0,0,0,0,0);
                      Globals.currentGlassPane.glassPaneLayout.addCordinates(190,310);
                      revalidate();
                  }
                  
                  updateButton.cbt.setEnabled(true);
                  ldic.setVisible(false);
              }  
            };
            updateThread.start();
        }
        
    }
    
    public void startFading(String type){
            this.fadingTypeIN=type.equals("IN")?true:false;
            FadingEffect fading=new FadingEffect(500.0f,type,ref,"IndividualAccountsPanel");
            this.fadingTimer=new Timer(30,fading);
            this.fadingTimer.start();
    }
    public void startFading(String type,boolean glassPanefading){
            this.glassPanefading=glassPanefading;
            this.fadingTypeIN=type.equals("IN")?true:false;
            FadingEffect fading=new FadingEffect(1000.0f,type,ref,"IndividualAccountsPanel");
            fading.setGlassPaneFading(glassPanefading);
            this.fadingTimer=new Timer(30,fading);
            this.fadingTimer.start();
    }
    
    
    
}
