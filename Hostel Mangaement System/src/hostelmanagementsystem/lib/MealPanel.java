
package hostelmanagementsystem.lib;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class MealPanel extends JPanel{
    public int width=400,height=200;
    CrossLabel crossLabel;
    private JCheckBox veg,non_veg,meal_off;
    private ButtonGroup mealGroup;
    private showWarning warning;
    private String title;
    private SpringLayout springLayout;
    Font font=null;
    MealPanel ref=null;
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
    public MealPanel(String title){
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
        
        font=new Font("Comic Sans MS",Font.BOLD,14);
        
        
       Box mealBox = Box.createVerticalBox();
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
       veg = new JCheckBox("VEG Meal");
       non_veg = new JCheckBox("NON-VEG Meal");
       meal_off = new JCheckBox("Meal off for today");
       mealGroup = new ButtonGroup();
       mealGroup.add(veg);
       mealGroup.add(non_veg);
       mealGroup.add(meal_off);
       mealBox.add(veg);
       mealBox.add(non_veg);
       mealBox.add(meal_off);
       veg.setOpaque(false);
       veg.setForeground(Color.white);
       non_veg.setOpaque(false);
       non_veg.setForeground(Color.white);
       meal_off.setOpaque(false);
       meal_off.setForeground(Color.white);
       mealBox.setBorder(BorderFactory.createTitledBorder(null, "Meal Descion", TitledBorder.CENTER, TitledBorder.TOP, font, Color.LIGHT_GRAY));
       add(mealBox);
       springLayout.putConstraint(SpringLayout.WEST, mealBox, 130, SpringLayout.WEST, this);
       springLayout.putConstraint(SpringLayout.NORTH,mealBox,60,SpringLayout.NORTH,this);
       ListenForCheck chk = new ListenForCheck();
       veg.addActionListener(chk);
       non_veg.addActionListener(chk);
       meal_off.addActionListener(chk);    
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
    
    private class ListenForCheck implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(veg.isSelected()){
                JOptionPane.showMessageDialog(MealPanel.this, "VEGETARIAN MEAL IS SELECTED FOR TODAY", "DONE", JOptionPane.INFORMATION_MESSAGE);
            }
            if(non_veg.isSelected()){
                JOptionPane.showMessageDialog(MealPanel.this, "NON-VEGETARIAN MEAL IS SELECTED FOR TODAY", "DONE", JOptionPane.INFORMATION_MESSAGE);
            }
            if(meal_off.isSelected()){
                JOptionPane.showMessageDialog(MealPanel.this, "MEAL IS OFF FOR TODAY", "DONE", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
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
