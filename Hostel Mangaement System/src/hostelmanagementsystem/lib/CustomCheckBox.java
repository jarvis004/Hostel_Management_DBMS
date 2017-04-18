package hostelmanagementsystem.lib;

import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

public class CustomCheckBox extends JCheckBox{
    
    CustomCheckBox(String label,Color col){
      super(label);
      setForeground(col);
      setFont(getFont().deriveFont(Font.BOLD,12));
      setOpaque(false);
      setFocusPainted(false);
      setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
