package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Color color = Constants.generateColor();
	
	Canvas() {
		setOpaque(false);
	}
	
	
	public void tick() {
		for(StartingPoint p : StartingPoint.startingPoints) {
			p.tick();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		color = Constants.generateColor();
		g2d.setColor(color);
		if (StartingPoint.startingPoints != null)
			for(ColorPoint p : StartingPoint.startingPoints) {
				p.draw(g2d);
			}
	}
}
