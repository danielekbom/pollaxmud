package pollaxmud.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import pollaxmud.entities.Player;

public class MapCanvas extends Canvas{
	
	private static final long serialVersionUID = 1L;
	Player player;
	
	public MapCanvas(Player player){
        setSize(340, 670);
        setBackground(Color.white);
        this.player = player;
    }

    public void paint(Graphics g){
        Image mapImage = Toolkit.getDefaultToolkit().getImage("map.jpg");
        Image playerImage = Toolkit.getDefaultToolkit().getImage("player.gif");
        g.drawImage(mapImage, 0, 0, this);
        g.drawImage(playerImage, player.getCurrentLocation().getXPosition() - 10, player.getCurrentLocation().getYPosition() - 15, 20, 25, this); 
    }
	
}
