/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komponen;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author luthfy
 */
public class ButtonGlass extends JButton {
    
    private Paint paint;
    private Paint glass;
    private Shape shape;
    private boolean over;
    private Color warna;
    
    public ButtonGlass(){
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        warna = new Color(getBackground().getRed(), getBackground().getGreen(), getBackground().getBlue(), 70);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setOver(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setOver(false);
            }
            
        });
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
    
    
    
    

    @Override
    protected void paintComponent(Graphics g) {
       
        
        Graphics2D gd = (Graphics2D)g.create();
        
        
        
        shape = new Rectangle(0, 0, getWidth(), getHeight());
        
        if(isOver()){  
            glass = new GradientPaint(0, 0, new Color(1F, 1F, 1F, 0.1F), 0, getHeight(), new Color(1F, 1F, 1F, 0.1F));
        }else{
             glass = new GradientPaint(0, 0, new Color(1F, 1F, 1F, 0F), 0, getHeight(), new Color(1F, 1F, 1F, 0F));
        }
        
        gd.setColor(warna);
        gd.fill(shape);
                                  
        super.paintComponent(g);
        
        gd.setPaint(glass);
        gd.fill(shape);
         
  
        
       
    }
     
}
