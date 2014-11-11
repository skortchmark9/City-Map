package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

public class ColorPoint extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width;
	int height;
	Color color;
	
	ColorPoint(int x, int y) {
		this.x = x;
		this.y = y;
		this.color = Canvas.color;
		Random r = new Random();
		this.width = r.nextInt(Constants.MAX_POINT_WIDTH);
		this.height = r.nextInt(Constants.MAX_POINT_HEIGHT);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.drawRoundRect(x, y, width, height, 10, 20);
		g2d.fillOval(this.x, this.y, this.width, this.height);
	}	
}
