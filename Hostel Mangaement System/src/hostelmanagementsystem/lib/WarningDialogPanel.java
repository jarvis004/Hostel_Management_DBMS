package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class WarningDialogPanel extends JPanel{
    private String dialog;
    customButtonPanel yesBtn,noBtn;
    private int width,height;
    private Color rectCol=new Color(129,140,164),textCol=new Color(250,250,250);
    private WarningDialogPanel ref;
    WarningDialogPanel(String dlg,int w,int h,customButtonPanel ybtn,int pos1X,int pos1Y,customButtonPanel nbtn,int pos2X,int pos2Y,String type){
        ref=this;
        this.width=w;
        this.height=h;
        this.dialog=dlg;
        this.setPreferredSize(new Dimension(w,h));
        setOpaque(false);
        SpringLayout layout=new SpringLayout();
        setLayout(layout);
        add(ybtn);
        layout.putConstraint(SpringLayout.WEST,ybtn,pos1X,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,ybtn,pos1Y,SpringLayout.NORTH,this);
        
        add(nbtn);
        layout.putConstraint(SpringLayout.WEST,nbtn,pos2X,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,nbtn,pos2Y,SpringLayout.NORTH,this);
        
        
        if(type.equals("picDialoge")){
           ybtn.cbt.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent acev){
                 Globals.seatAllotPanel.cancelSubmit();
             }
           });
           nbtn.cbt.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent acev){
                Globals.seatAllotPanel.submit();
             }
        });
        }
        
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Color oldCol=g2d.getColor();
        g2d.setColor(rectCol);
        g2d.fillRect(0,0,width,height);
        g2d.setColor(textCol);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,17));
        FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(g2d.getFont());
        g2d.drawString(dialog,(getWidth()-fm.stringWidth(dialog))/2, 20);
        g2d.setColor(oldCol);
    }
}
