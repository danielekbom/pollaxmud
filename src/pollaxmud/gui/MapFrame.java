package pollaxmud.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pollaxmud.entities.Player;

public class MapFrame extends Canvas{
	
	Player player;
	
	public MapFrame(Player player){
        setSize(200, 200);
        setBackground(Color.white);
        this.player = player;
    }

    public void paint(Graphics g){
     
        g.setColor(Color.RED);
        
        Image img1 = Toolkit.getDefaultToolkit().getImage("map.jpg"); 
        g.drawImage(img1, 0, 0, this); 
        //g.fillOval(182, 185, 10, 10);
        g.fillOval(player.getCurrentLocation().getXPosition(), player.getCurrentLocation().getYPosition(), 10, 10);
    }
	
}
