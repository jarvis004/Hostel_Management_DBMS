
package hostelmanagementsystem.lib;

import java.awt.event.ItemEvent;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.lang.reflect.Field;
import javax.swing.JLabel;
import javax.swing.JViewport;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.PopupMenuListener;

public class customComboBox extends JPanel {
  private Dimension labelDimension;
  public static int height=0,width=0;
  private String options[]=null;
  customComboBox ref;
  JComboBox combo;
  public customComboBox(Dimension dim,String[] param){
    this.options=param;
    ref=this;
    setOpaque(false);
    UIManager.put("ComboBox.background", new ColorUIResource(new Color(128,128,128)));
    UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(20,20,20)));
    UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
    height=dim.height;
    width=dim.width;
    labelDimension=dim;
    combo = new JComboBox(options);
    combo.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);  //preventing list selection by arrow key traversal
    combo.setEditable(true);
    combo.setEditor(new ComboBoxEditorExample(options[0]));
    combo.setMaximumRowCount(6);
    combo.setUI(ColorArrowUI.createUI(combo));
    add(combo);
}

  class Entry extends Component{
     private final String title;
     public Entry(String title) {
        this.title = title;
     }
     public String getTitle() {
        return title;
     }
     @Override
     public String toString() {
        return title;
     }
  }

  class ComboBoxEditorExample implements ComboBoxEditor {
     ComboPanel panel;
     comboLabel label;
     public ComboBoxEditorExample(String defaultChoice) {
        panel = new ComboPanel(defaultChoice);
     }

     public void setItem(Object anObject) {
        if(anObject != null) 
          label.setText(anObject.toString());
     }
     public Component getEditorComponent() {return panel;}public Object getItem() {return panel.getText();}
     public void selectAll() {}public void addActionListener(ActionListener l) {}public void removeActionListener(ActionListener l) {}
     class ComboPanel extends JPanel {
        public ComboPanel(String initialEntry) {
           setLayout(new BorderLayout());
           label= new comboLabel(initialEntry,labelDimension);
           add(label, BorderLayout.EAST);
        }
        public String getText() {
           return (label.getText());
        }
     }
  }
}
class ColorArrowUI extends BasicComboBoxUI {

    public static ColorArrowUI createUI(JComponent c) {
        return new ColorArrowUI();
    }

    @Override 
    protected JButton createArrowButton() {
        return new ComboArrowButton();
    }
    @Override
    protected ComboPopup createPopup(){  
      BasicComboPopup popUp = new BasicComboPopup(comboBox){  
            @Override
         protected JScrollPane createScroller() {  
             JScrollPane sp=new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
             sp.getVerticalScrollBar().setUI(new myScrollBarUI(6,20,20));
             sp.getVerticalScrollBar().setPreferredSize(new Dimension(6,customComboBox.height));
             sp.setPreferredSize(new Dimension(customComboBox.width,customComboBox.height));
             sp.setOpaque(false);
             sp.getViewport().setOpaque(false);
             sp.setBorder(null);
             return sp;  
         }  
       };  
       return popUp;  
    }    
}
