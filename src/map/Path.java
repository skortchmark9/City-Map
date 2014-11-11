package map;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Path {
	List<ColorPoint> points;
	boolean stopped = false;
	static int numRunning;
	
	Path(int x, int y) {
		points = new ArrayList<>();
		points.add(new ColorPoint(x, y));
		Path.numRunning++;
	}
	
	public void generatePoint() {
		Point oldPoint = points.get(points.size() - 1);
		Random r = new Random();
		int newX = (Constants.ORGANIC_MODE ? 0 : Constants.MIN_POINT_DISTANCE) + r.nextInt(Constants.MAX_POINT_DISTANCE - Constants.MIN_POINT_DISTANCE);
		int newY = (Constants.ORGANIC_MODE ? 0 : Constants.MIN_POINT_DISTANCE) + r.nextInt(Constants.MAX_POINT_DISTANCE - Constants.MIN_POINT_DISTANCE);		
		int positiveX = r.nextInt(2);
		int positiveY = r.nextInt(2);
		newX *= (positiveX == 1 ? 1 : -1);
		newY *= (positiveY == 1 ? 1 : -1);
		
		newX += oldPoint.x;
		newY += oldPoint.y;
		
		if (Constants.isOutOfBounds(newX, newY)) {
			numRunning--;
			this.stopped = true;
			return;
		}
		
		ColorPoint newPoint = new ColorPoint(newX, newY);
		points.add(newPoint);
	}
	
	public void draw(Graphics2D g2d) {
		int size = points.size();
		for (int i = 0; i < size - 1; i++) {
			ColorPoint p = points.get(i);
			ColorPoint nextPoint = points.get(i + 1);
			g2d.drawLine(p.x, p.y, nextPoint.x, nextPoint.y);
			p.draw(g2d);
		}
	}

	public void tick() {
		if (!stopped) {
			generatePoint();
		}
		
	}

}
