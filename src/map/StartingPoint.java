package map;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartingPoint extends ColorPoint {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Path> paths = new ArrayList<>();
	static List<StartingPoint> startingPoints;
	
	StartingPoint(int x, int y) {
		super(x, y);
		this.width = Constants.MAX_POINT_WIDTH;
		this.height = Constants.MAX_POINT_HEIGHT;
		for(int i = 0; i < Constants.MAX_PATHS; i++) {
			paths.add(new Path(this.x, this.y));
		}
		startingPoints.add(this);
	}
	
	public static void reset() {
		createStartingPoints();
	}
	
	private static void createStartingPoints() {
		StartingPoint.startingPoints = new ArrayList<>();
		Random r = new Random();
		for(int i = 0; i < 1 + r.nextInt(Constants.MAX_STARTING_POINTS); i++) {
			Point stencil = Constants.generatePoint();
			new StartingPoint(stencil.x, stencil.y);
		}		
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		for(Path p : paths) {
			p.draw(g2d);
		}
	}
	
	public void tick() {
		for(Path p : paths) {
			p.tick();
		}
		
	}

}
