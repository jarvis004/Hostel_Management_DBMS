package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;

import java.util.Calendar;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import hostelmanagementsystem.lib.conn.connection;
import java.awt.Component;

public class seatAllotmentPanel extends JPanel{
    public int width=500,height=425;
    CrossLabel crossLabel;
    connection conn=null;
    private customTextField firstname,lastname,roll,email,address1,address2,city,pincode;
    private customComboBox cbBatch,cbStream,cbHostel;
    private PictureChooser pc;
    private customButtonPanel AllotButton;
    private String selectedBatch="Batch",selectedStream="Stream",selectedHostel="Hostel";
    private loadingIcon ldic;
    private SpringLayout springLayout;
    Font font=null;
    seatAllotmentPanel ref=null;
    JDialog picNotChoosedDialog=null;
    File choosedFile=null;
    WarningDialogPanel dp;
    private boolean fieldError=false,firstnameError=false,lastnameError=false,batchError=false,streamError=false,hostelError=false,rollError=false,addressError=false,emailError=false,
                    cityError=false,pincodeError=false,picNotChoosed=true;
    Point prvTootipCord;
    public seatAllotmentPanel(){
        setOpaque(false);
        ref=this;
        Globals.seatAllotPanel=ref;
        crossLabel=new CrossLabel(16,new Color(230,230,230),new Color(20,20,20));
        springLayout=new SpringLayout();
        setLayout(springLayout);
        setFocusable(true);
        addMouseListener(new mouseListener(this));
        
        //font=new Font("verdana",Font.PLAIN,14);
        try{
          font=Font.createFont(Font.TRUETYPE_FONT,LoginPanel.class.getClass().getResourceAsStream("/resource/fonts/Helvetica.ttf"))
                  .deriveFont(Font.PLAIN,14f);
        }
        catch(FontFormatException fe){
       }
       catch(IOException e){
           e.printStackTrace();
       }
        
        pc=new PictureChooser();
        add(pc);
        springLayout.putConstraint(SpringLayout.WEST,pc,70,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,pc,20,SpringLayout.NORTH,this);
        firstname=new customTextField(200,26,"First Name",font);
        add(firstname);
        springLayout.putConstraint(SpringLayout.WEST,firstname,195,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,firstname,28,SpringLayout.NORTH,this);
        firstname.addFocusListener(new focusListener(firstname)); 
        
        lastname=new customTextField(200,26,"Last Name",font);
        add(lastname);
        springLayout.putConstraint(SpringLayout.WEST,lastname,195,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,lastname,10,SpringLayout.SOUTH,firstname);
        lastname.addFocusListener(new focusListener(lastname));
        
        String paramBatch[]={"Batch","2013-2017","2014-2018","2015-2019","2016-2020","2014-2019","2015-2020"};
        cbBatch = new customComboBox(new Dimension(80,20),paramBatch);
        add(cbBatch);
        springLayout.putConstraint(SpringLayout.WEST,cbBatch,190,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,cbBatch,2,SpringLayout.SOUTH,lastname);
        cbBatch.combo.addActionListener(new comboListener(cbBatch));
        
        
        String paramStream[]={"Stream","B.tech IT","B.tech ECE","B.tech IT+MBA","B.tech IT +M.tech IT(HCI)","B.tech IT +M.tech IT(SE)","B.tech IT +M.tech IT(BIM)","B.tech IT +M.tech IT(IS)","B.tech IT +M.tech IT(CLIS)","B.tech IT +M.tech IT(WC)","B.tech IT +M.tech IT(Robotics"};
        cbStream = new customComboBox(new Dimension(80,20),paramStream);
        add(cbStream);
        springLayout.putConstraint(SpringLayout.WEST,cbStream,190,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,cbStream,2,SpringLayout.SOUTH,lastname);
        cbStream.combo.addActionListener(new comboListener(cbStream));
        
        String paramHostel[]={"Hostel","1st","2nd","3rd","4th","5th"};
        cbHostel = new customComboBox(new Dimension(80,20),paramHostel);
        add(cbHostel);
        springLayout.putConstraint(SpringLayout.WEST,cbHostel,190,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,cbHostel,0,SpringLayout.SOUTH,cbBatch);
        cbHostel.combo.addActionListener(new comboListener(cbHostel));
        
        
        roll=new customTextField(320,26,"Roll No",font);
        add(roll);
        springLayout.putConstraint(SpringLayout.WEST,roll,75,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,roll,10,SpringLayout.SOUTH,cbHostel);
        roll.addFocusListener(new focusListener(roll));
        
        
        email=new customTextField(320,26,"Email Address",font);
        add(email);
        springLayout.putConstraint(SpringLayout.WEST,email,75,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,email,10,SpringLayout.SOUTH,roll);
        email.addFocusListener(new focusListener(email));
        
        address1=new customTextField(320,26,"Address Line 1",font);
        add(address1);
        springLayout.putConstraint(SpringLayout.WEST,address1,75,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,address1,10,SpringLayout.SOUTH,email);
        address1.addFocusListener(new focusListener(address1));
        
        address2=new customTextField(320,26,"Address Line 2",font);
        add(address2);
        springLayout.putConstraint(SpringLayout.WEST,address2,75,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,address2,10,SpringLayout.SOUTH,address1);
        
        city=new customTextField(142,26,"City",font);
        add(city);
        springLayout.putConstraint(SpringLayout.WEST,city,75,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,city,10,SpringLayout.SOUTH,address2);
        city.addFocusListener(new focusListener(city));
        
        pincode=new customTextField(142,26,"Zip/Postal Code",font);
        add(pincode);
        springLayout.putConstraint(SpringLayout.WEST,pincode,30,SpringLayout.EAST,city);
        springLayout.putConstraint(SpringLayout.NORTH,pincode,10,SpringLayout.SOUTH,address2);
        pincode.addFocusListener(new focusListener(pincode));
        
        AllotButton=new customButtonPanel("Allot",70,29,7);
        add(AllotButton);
        springLayout.putConstraint(SpringLayout.WEST,AllotButton,75,SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH,AllotButton,20,SpringLayout.SOUTH,city); 
        AllotButton.cbt.addActionListener(new actionListener());
        
        ldic=new loadingIcon();
        add(ldic);
        springLayout.putConstraint(SpringLayout.WEST,ldic,-10,SpringLayout.EAST,AllotButton);
        springLayout.putConstraint(SpringLayout.NORTH,ldic,7,SpringLayout.NORTH,AllotButton);
        ldic.setVisible(false);
        
        
        
    }
    private class focusListener implements FocusListener{
        private customTextField field;
        private Point tooltipCords=new Point();
        private String tooltipText;
        private int tooltipW,tooltipH;
        focusListener(customTextField field){
            this.field=field;
            if(field==firstname){
               tooltipCords.x=350;tooltipCords.y=7;
               tooltipW=135;tooltipH=20;
               tooltipText="First Name is not valid";
            }
            else if(field==lastname){
               tooltipCords.x=350;tooltipCords.y=49;
               tooltipW=132;tooltipH=20;
               tooltipText="Last Name is not valid";
            }
            else if(field==roll){
               tooltipCords.x=100;tooltipCords.y=151;
               tooltipW=90;tooltipH=20;
               tooltipText="Invalid Roll No";
            }
            else if(field==email){
               tooltipCords.x=350;tooltipCords.y=192;
               tooltipW=105;tooltipH=20;
               tooltipText="Email is not valid";
            }
            else if(field==address1){
               tooltipCords.x=350;tooltipCords.y=233;
               tooltipW=112;tooltipH=20;
               tooltipText="Enter the Address";
            }
            else if(field==city){
               tooltipCords.x=150;tooltipCords.y=361;
               tooltipW=120;tooltipH=20;
               tooltipText="Name of the City";
            }
            else if(field==pincode){
               tooltipCords.x=300;tooltipCords.y=361;
               tooltipW=157;tooltipH=20;
               tooltipText="Enter the Zip/Postal Code";
            }
        }
        public void focusGained(FocusEvent e) {
        }

        public void focusLost(FocusEvent e) {
            checkAllotment(field);
            if(fieldError){
                fieldError=false;
                showErrorTooltip(field,null,tooltipCords.x,tooltipCords.y,tooltipW,tooltipH,tooltipText);
            }
            else if(!firstnameError && !lastnameError && !rollError && !emailError && !addressError && !cityError && !pincodeError){
                    Globals.ttlp.removeCustomToolTips("downArrowRegistrationErrorTooltip");
                    Globals.ttlp.removeCustomToolTips("upArrowRegistrationErrorTooltip");
                    revalidate();
                    repaint();
                
           }
           else{
                    if(firstnameError)   showErrorTooltip(firstname,null,350,7,135,20,"First Name is not valid"); 
                else if(lastnameError)   showErrorTooltip(lastname,null,350,49,132,20,"Last Name is not valid"); 
                    else if(rollError)   showErrorTooltip(roll,null,100,151,105,20,"Invalid Roll No"); 
                   else if(emailError)   showErrorTooltip(email,null,350,192,105,20,"Email is not valid");
                 else if(addressError)   showErrorTooltip(address1,null,350,233,112,20,"Enter the Address"); 
                    else if(cityError)   showErrorTooltip(city,null,150,361,130,20,"Name of the City"); 
                 else if(pincodeError)   showErrorTooltip(pincode,null,300,361,157,20,"Enter the Zip/Postal Code"); 
           }
        }
        
    }
   
    //handelling error tooltip
    
    private void showErrorTooltip(customTextField field,customComboBox cb,int tooltipCordsX,int tooltipCordsY,int tooltipW,int tooltipH,String tooltipText){
        String downArrowTooltipName="downArrowRegistrationErrorTooltip",upArrowTooltipName="upArrowRegistrationErrorTooltip";
        if(field==city || field==pincode || cb==cbHostel || cb==cbStream){
            int index=Globals.ttlp.isToolTipExist(upArrowTooltipName);
            Globals.ttlp.removeCustomToolTips("downArrowRegistrationErrorTooltip");
            if(index==-1){
                Globals.ttlp.addCustomToolTips(tooltipW,tooltipH,tooltipText,upArrowTooltipName,25,8,32,1,39,8);
                Globals.ttlp.tooltipLayeredLayout.addCordinates(tooltipCordsX,tooltipCordsY);
                prvTootipCord=new Point(tooltipCordsX,tooltipCordsY); 
            }
            else{
                Globals.ttlp.changeCordinates(prvTootipCord.x,prvTootipCord.y,tooltipCordsX,tooltipCordsY);
                prvTootipCord=new Point(tooltipCordsX,tooltipCordsY);
            }
        }
                        
        else{
             int index=Globals.ttlp.isToolTipExist(downArrowTooltipName);
             Globals.ttlp.removeCustomToolTips("upArrowRegistrationErrorTooltip");
             if(index==-1){
                Globals.ttlp.addCustomToolTips(tooltipW,tooltipH,tooltipText,downArrowTooltipName,25,20,32,27,39,20);
                Globals.ttlp.tooltipLayeredLayout.addCordinates(tooltipCordsX,tooltipCordsY);
                prvTootipCord=new Point(tooltipCordsX,tooltipCordsY);
             }
             else{
                 Globals.ttlp.changeCordinates(prvTootipCord.x,prvTootipCord.y,tooltipCordsX,tooltipCordsY);
                 prvTootipCord=new Point(tooltipCordsX,tooltipCordsY);
              }
       }
       customToolTip tooltip;
       int index=Globals.ttlp.isToolTipExist(downArrowTooltipName);
       if(index!=-1)
          tooltip=Globals.ttlp.getTooltip("downArrowRegistrationErrorTooltip");
       else
          tooltip=Globals.ttlp.getTooltip("upArrowRegistrationErrorTooltip");
       tooltip.setSize(tooltipW,tooltipH);
       tooltip.setBounds(0, 0,tooltipW,tooltipH+7);
       tooltip.setText(tooltipText);
       revalidate();
    }
    
    
    //validating fields
    
    private void checkAllotment(customTextField fld){
       String alphaPattern="^[a-zA-Z]+$",numericPattern="^[0-9]+$",stringPattern = "^(?![A-Za-z]+$)(?!\\d+$)[a-zA-Z0-9]+$";
       //checking on focus lost
       if(fld==firstname || fld==lastname || fld==city){
           if(fld.getText().matches(alphaPattern)){   if(fld==firstname) firstnameError=false;  if(fld==lastname)  lastnameError=false;  if(fld==city) cityError=false;  fieldError=false;}
           else{   if(fld==firstname) firstnameError=true;   if(fld==lastname)  lastnameError=true;   if(fld==city) cityError=true;   fieldError=true;}
       }
       if(fld==email || fld==null){
           String val=email.getText();
           int indexAtCh=val.indexOf('@'),indexCOMStr=val.indexOf(".com");
           if(indexAtCh!=-1 && indexAtCh!=0 && indexAtCh!=indexCOMStr-1 && indexCOMStr==val.length()-4){  emailError=false;  fieldError=false;}
           else{  emailError=true;  fieldError=true;}
       }
       
       if(fld==roll || fld==null){
           if(roll.getText().matches(stringPattern)){ rollError=false;  fieldError=false; } else{ rollError=true;  fieldError=true; }
       }
       //checking on both focus lost as well as allot button clicked
       
       if(fld==pincode || fld==null){  
          if(pincode.getText().matches(numericPattern)) {  
            if(pincode.getText().length()==5 || pincode.getText().length()==6){ pincodeError=false;  fieldError=false; }  else {pincodeError=true;    fieldError=true;}
          }   
          else{   pincodeError=true;    fieldError=true;}
       }
       
       
       if(fld==address1 || fld==null){   if(address1.getText().length()==0){  addressError=true;  fieldError=true;} else{ addressError=false;    fieldError=false;}}
       
       
       
       
       //if allot button is clicked
       if(fld==null){
           customTextField [] arr={firstname,lastname,city};
           for(int i=0;i<arr.length;i++){
               fld=arr[i];
               if(fld.getText().matches(alphaPattern)){   if(fld==firstname) firstnameError=false;  if(fld==lastname)  lastnameError=false;  if(fld==city) cityError=false;  fieldError=false;}
               else{   if(fld==firstname) firstnameError=true;   if(fld==lastname)  lastnameError=true;   if(fld==city) cityError=true;   fieldError=true;}
           }
           if(selectedBatch.equals("Batch"))    batchError=true;  else batchError=false;
           if(selectedStream.equals("Stream"))  streamError=true; else streamError=false;
           if(selectedHostel.equals("Hostel"))  hostelError=true; else hostelError=false;
           
               if(firstnameError)   showErrorTooltip(firstname,null,350,7,135,20,"First Name is not valid"); 
          else if(lastnameError)   showErrorTooltip(lastname,null,350,49,132,20,"Last Name is not valid"); 
                   
             else if(batchError)   showErrorTooltip(null,cbBatch,230,85,81,20,"Select Batch");      
            else if(streamError)   showErrorTooltip(null,cbStream,350,125,90,20,"Select Stream"); 
            else if(hostelError)   showErrorTooltip(null,cbHostel,245,155,90,20,"Select Hostel"); 
                      
              else if(rollError)   showErrorTooltip(roll,null,100,151,105,20,"Invalid Roll No"); 
             else if(emailError)   showErrorTooltip(email,null,350,192,105,20,"Email is not valid");
           else if(addressError)   showErrorTooltip(address1,null,350,233,112,20,"Enter the Address"); 
              else if(cityError)   showErrorTooltip(city,null,150,361,120,20,"Name of the City"); 
           else if(pincodeError)   showErrorTooltip(pincode,null,300,361,157,20,"Enter the Zip/Postal Code"); 
            
            choosedFile=pc.getFileChoosed();
            if(choosedFile!=null){
                picNotChoosed=false;
                
            }
           
       } 
        
    }
    //handelling combo box selection
    
    private class comboListener implements ActionListener{
        customComboBox source;
        comboListener(customComboBox src){
            this.source=src;
        }
        public void actionPerformed(ActionEvent acev) {
            if(source==cbBatch)
                selectedBatch=String.valueOf(cbBatch.combo.getSelectedItem());
            else if(source==cbStream)
                selectedStream=String.valueOf(cbStream.combo.getSelectedItem());
            else if(source==cbHostel)
                selectedHostel=String.valueOf(cbHostel.combo.getSelectedItem());
        }
        
    }
    
    
    
   //handelling button action
    private class actionListener implements ActionListener{
        public void actionPerformed(ActionEvent acev){
       /*     
        firstname.setText("Indrajeet");
        lastname.setText("Mishra");
        cbBatch.combo.setSelectedIndex(1);
        cbStream.combo.setSelectedIndex(2);
        cbHostel.combo.setSelectedIndex(1);
        roll.setText("09108001099");
        email.setText("jeet.indra18@yahoo.com");
        address1.setText("Parbelia");
        address2.setText("");
        city.setText("Asansol");
        pincode.setText("723121");
       */ 
      
            checkAllotment(null);
            if(!firstnameError && !lastnameError && !batchError && !streamError && !rollError && !emailError && !addressError && !cityError && !pincodeError){
               if(picNotChoosed){
                  showDialog();
               }
               else
                   submit();
            }
          
          
        }
    }
    private void showDialog(){
        picNotChoosedDialog=new JDialog(Globals.mainApp, "Choose Photograph", true);
        customButtonPanel cb1=new customButtonPanel("Yes",70,28,5);
        customButtonPanel cb2=new customButtonPanel("No",70,28,5);
        dp=new WarningDialogPanel("Don't Have a Photograph?",300,150,cb1,40,70,cb2,180,70,"picDialoge");
        picNotChoosedDialog.getContentPane().add(dp);
        picNotChoosedDialog.setSize(300,150);
        picNotChoosedDialog.setResizable(false);
        picNotChoosedDialog.setLocationRelativeTo(ref);
        picNotChoosedDialog.setVisible(true);
        
    }
    void submit(){
        if(picNotChoosedDialog!=null){
            picNotChoosedDialog.getContentPane().remove(dp);
            picNotChoosedDialog.dispose();
            picNotChoosedDialog.setVisible(false);
        }
        conn=new connection(Globals.domain+"/HMS/add_student.php");
        
        conn.addFormFields("firstname",firstname.getText());
        conn.addFormFields("lastname",lastname.getText());
        conn.addFormFields("batch",selectedBatch);
        conn.addFormFields("stream",selectedStream);
        conn.addFormFields("hostel",selectedHostel);
        conn.addFormFields("roll",roll.getText());
        conn.addFormFields("email",email.getText());
        conn.addFormFields("address1",address1.getText());
        conn.addFormFields("address2",address2.getText());
        conn.addFormFields("city",city.getText());
        conn.addFormFields("pincode",pincode.getText());
        conn.addFormFields("session_id",String.valueOf(Globals.SESSION_ID));
        conn.addFormFields("username",Globals.USERNAME);
         
        
        
        if(!picNotChoosed){
            conn.addImageFormField(pc.icon,pc.getChoosedFileExtension(),"studentpic","stpic");
        }
        Thread addStudentThread=new Thread(){
          @Override
          public void run(){
              Globals.ttlp.removeCustomToolTips("ConnectionErrorTooltip");
              Globals.ttlp.removeCustomToolTips("SeatNotAvailableTooltip");
              AllotButton.cbt.setEnabled(false);
              ldic.setVisible(true);
              String resp=conn.execute();
              if(conn.getStatus()==200){
                JSONObject json =(JSONObject) JSONValue.parse(resp);
                if(Boolean.valueOf(json.get("error").toString())==true){
                    if(json.get("reason").toString().equals("session expire")){
                        Globals.mainApp.ToggleStartUpPaneltoWithoutLoginFromAdminLogin();
                        Globals.currentGlassPane.removeComponent((Globals.scrollablePanel));
                        Globals.currentGlassPane.setVisible(false);
                    }
                    else if(json.get("reason").toString().equals("roll exist")){
                       showErrorTooltip(roll,null,100,141,128,20,"Roll No Already Exist"); 
                       roll.requestFocus();
                    }
                    else if(json.get("reason").toString().equals("email exist")){
                       showErrorTooltip(email,null,350,182,120,20,"Email Already Exist");
                       email.requestFocus();
                    }
                    else if(json.get("reason").toString().equals("seat not available")){
                       Globals.ttlp.addCustomToolTips(115,20,"Seat Not Available","SeatNotAvailableTooltip",0,0,0,0,0,0);
                       Globals.ttlp.tooltipLayeredLayout.addCordinates(180,394);
                        revalidate();
                    }
                }
                else if(Boolean.valueOf(json.get("error").toString())==false){
                    Calendar cal=Calendar.getInstance();
                    int mon=cal.get(Calendar.MONTH);
                    int year=cal.get(Calendar.YEAR);
                    String full_name=firstname.getText()+" "+lastname.getText();
                    
                    boolean [] rentBox={false,false,false,false};
                    boolean [] messBox={false,false,false,false,false,false,false,false,false,false,false,false};
                    IndividualAccounts IndActPan=new IndividualAccounts(pc.icon,roll.getText(),full_name,selectedBatch,selectedStream,mon,year,rentBox,messBox,0.0f);
                    Globals.scrollablePanel.startFading("OUT");
                    Globals.layeredPane.removeAllComponent();
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
              else{
                  Globals.ttlp.addCustomToolTips(140,20,"Check Your Connection","ConnectionErrorTooltip",0,0,0,0,0,0);
                  Globals.ttlp.tooltipLayeredLayout.addCordinates(180,394);
                  revalidate();
              }
              AllotButton.cbt.setEnabled(true);
              ldic.setVisible(false); 
          }
        };
        addStudentThread.start();
    }
    void cancelSubmit(){
        picNotChoosedDialog.getContentPane().remove(dp);
        picNotChoosedDialog.dispose();
        picNotChoosedDialog.setVisible(false);
    }
    //trannsferring focus to panel
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
    
    @Override
    public Dimension getPreferredSize(){
         return new Dimension(this.width,this.height);
    }
}
