package map;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Constants {

	public static int MAX_STARTING_POINTS; //Default: 1
	public static int MAX_POINT_WIDTH;//Default: 50
	public static int MAX_POINT_HEIGHT;//Default: 50
	public static int MAX_PATHS;//Default: 20
	
	public static int MIN_POINT_DISTANCE; //Default: 50
	public static int MAX_POINT_DISTANCE; //Default: 90

	public static int OPACITY; //Default: 125
	
	public static int DELAY; //Default: 25 (ms)

	public static int MAX_COLOR_INC; //Default: 3
	protected static int DELAY_BETWEEN_RUNS; //Default: 2000
	public static boolean ORGANIC_MODE; //Default: true

	public static Color CURRENT_COLOR = randomColor();
	public static int SCREEN_HEIGHT;
	public static int SCREEN_WIDTH;	


	public static Point generatePoint() {
		Random r = new Random();
		int y = r.nextInt(Constants.SCREEN_HEIGHT);
		int x = r.nextInt(Constants.SCREEN_WIDTH);
		return new Point(x, y);
	}
	
	public static Color randomColor() {
		Random r = new Random();
		Color c = new Color(r.nextInt(64) + 64, 64 + r.nextInt(64), 64 + r.nextInt(64));
		System.out.println(String.format("R: %d, G: %d, B: %d", c.getRed(), c.getGreen(), c.getBlue()));
		return c;
	}
	
	public static Color generateColor() {
		Random rand = new Random();
		int r = CURRENT_COLOR.getRed();
		int g = CURRENT_COLOR.getBlue();
		int b = CURRENT_COLOR.getGreen();
		switch (rand.nextInt(9)) {
		case 0:
			return Constants.CURRENT_COLOR;
		case 1:
		case 2:
			 r += rand.nextInt(Constants.MAX_COLOR_INC);
			 r %= 256;
			break;
		case 3:
		case 4:
			 g += rand.nextInt(Constants.MAX_COLOR_INC);
			 g %= 256;
			break;
		case 5:
		case 6:
			 b += rand.nextInt(Constants.MAX_COLOR_INC);
			 b %= 256;
			break;
		default:
			break;
		}
		
		Constants.CURRENT_COLOR =  new Color(r, g, b, OPACITY);
		return Constants.CURRENT_COLOR;
	}


	public static boolean isOutOfBounds(int x, int y) {
		boolean result = (y <= 0 || y >= SCREEN_HEIGHT || x <= 0 || x >= SCREEN_WIDTH);
		return result;
	}
}