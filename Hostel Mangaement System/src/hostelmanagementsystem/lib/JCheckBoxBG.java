package hostelmanagementsystem.lib;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Paint;
import java.awt.GradientPaint;
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Insets;
import javax.swing.JCheckBox;

public class JCheckBoxBG extends JCheckBox {

        public JCheckBoxBG(String chkname, JCheckBoxBG obj )
        {
            setOpaque(false);
            JCheckBox obj1 = new JCheckBox(chkname);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D)g;
            Paint oldPaint = g2.getPaint();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));//estable la transparencia   
            g2.setPaint(new GradientPaint(0.0f, 0.0f, Color.blue, 
                    0.0f, getHeight(), Color.blue)); 
            g2.setPaint(oldPaint);
            super.paintComponent(g);
        }
}
