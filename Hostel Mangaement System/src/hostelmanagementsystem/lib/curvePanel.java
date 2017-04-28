/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hostelmanagementsystem.lib;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JPanel;
import java.awt.geom.GeneralPath;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Dimension;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Indrajeet
 */
public class curvePanel extends JPanel{
    private RenderingHints rederingHints;
    private int counter=0;
    private Color startColor=new Color(173,183,202,200);
    private Color endColor=new Color(173,183,202,0);
    private int width,height;
    private curvePanel self_ref;
    public curvePanel(int width,int height){
        super(new FlowLayout());
        this.width=width;
        this.height=height;
        self_ref=this;
        setOpaque(false);
        //using timer to animate the curve
        Timer timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                self_ref.animate();
                self_ref.repaint();
            }
        });
        timer.start();
    }
    public void animate(){
        counter++;
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        RenderingHints oldHint=g2d.getRenderingHints();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        float width=this.width;
        float height=this.height;
        drawCurve(g2d,
                30.0f, -15.0f, 50.0f, 15.0f,
                width / 2.0f - 40.0f, 1.0f,
                15.0f, -25.0f,
                width / 2.0f, 1.0f / 2.0f,
                0.0f, 25.0f,
                15.0f, 9.0f, false);
        drawCurve(g2d,
                35.0f, -5.0f, 50.0f, 10.0f,
                width / 2.0f - 40.0f, 1.0f,
                35.0f, -25.0f,
                width / 2.0f, 1.0f / 2.0f,
                20.0f, 25.0f,
                25.0f, 7.0f, true);
        g2d.setRenderingHints(oldHint);
    }
    
    private void drawCurve(Graphics2D g2d,float y1,float y1_offset,float y2,float y2_offset,
        float cx1,float cx1_offset,float cy1,float cy1_offset,float cx2,float cx2_offset,float cy2,float cy2_offset,
        float thickness,float speed,boolean invert)
    {
        float width=this.width;
        float offset = (float) Math.sin(counter / (speed * Math.PI));
        
        float start_x = 0.0f;
        float start_y = offset * y1_offset + y1;
        float end_x = width;
        float end_y = offset * y2_offset + y2;
        
        float ctrl1_x = offset * cx1_offset + cx1;
        float ctrl1_y = offset * cy1_offset + cy1;
        float ctrl2_x = offset * cx2_offset + cx2;
        float ctrl2_y = offset * cy2_offset + cy2;
       
        GeneralPath thickCurve = new GeneralPath();
        thickCurve.moveTo(start_x, start_y);
        thickCurve.curveTo(ctrl1_x, ctrl1_y,
                ctrl2_x, ctrl2_y,
                end_x, end_y);
        thickCurve.lineTo(end_x, end_y + thickness);
        thickCurve.curveTo(ctrl2_x, ctrl2_y + thickness,
                ctrl1_x, ctrl1_y + thickness,
                start_x, start_y + thickness);
        thickCurve.lineTo(start_x, start_y);
        
        Rectangle bounds = thickCurve.getBounds();
        if (!bounds.intersects(g2d.getClipBounds())) {
            return;
        }
      
        GradientPaint painter = new GradientPaint(0, bounds.y,
                invert ? endColor : startColor,
                0, bounds.y + bounds.height,
                invert ? startColor : endColor);

        Paint oldPainter = g2d.getPaint();
        g2d.setPaint(painter);
        g2d.fill(thickCurve);
        
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(this.width,this.height);
    }
    @Override
    public Dimension getMinimumSize(){
         return new Dimension(this.width,this.height);
    }
    @Override
    public Dimension getMaximumSize(){
         return new Dimension(this.width,this.height);
    }
}

