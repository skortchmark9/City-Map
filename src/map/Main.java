package map;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("City Map");
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		Constants.SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
		Constants.SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		readConfig();		
		
		final Canvas p = new Canvas();	
		frame.getContentPane().add(p);
		StartingPoint.reset();

		frame.pack();
		frame.setVisible(true);
        GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .setFullScreenWindow(frame);

		
		Timer t = new Timer(Constants.DELAY, new ActionListener() {
			
	        public void actionPerformed(ActionEvent event) {
	        	if (Path.numRunning == 0) {
	        		try {
						Thread.sleep(Constants.DELAY_BETWEEN_RUNS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	        		StartingPoint.reset();
	        		return;
	        	}
	            p.tick();
	            p.repaint();
	        }
	    });
		t.setRepeats(true);
		
		t.start();
	}

	private static void readConfig() {
		String line;
		String fileName = "config.txt";
		final Pattern tab = Pattern.compile("\t");
		Map<String, Integer> parsed = new HashMap<>();
		try {
			FileReader fileReader = new FileReader(fileName); //Attempts to read the given file
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) !=null) { //Loops while bufferedReader can find a next line
				String[] lineArray = tab.split(line);
				if (lineArray[0].equals("ORGANIC_MODE")) {
					Constants.ORGANIC_MODE = Boolean.parseBoolean(lineArray[1]);
				} else {
					parsed.put(lineArray[0], Integer.parseInt(lineArray[1]));
				}
			}		 
			bufferedReader.close(); 
		}
		catch(FileNotFoundException ex) {
			System.exit(1);
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
				
		 Constants.MAX_STARTING_POINTS = parsed.get("MAX_STARTING_POINTS"); //Default: 1
		 Constants.MAX_POINT_WIDTH = parsed.get("MAX_POINT_WIDTH");//Default: 50
		 Constants.MAX_POINT_HEIGHT = parsed.get("MAX_POINT_HEIGHT");//Default: 50
		 Constants.MAX_PATHS = parsed.get("MAX_PATHS");//Default: 20
		
		 Constants.MIN_POINT_DISTANCE = parsed.get("MIN_POINT_DISTANCE"); //Default: 50
		 Constants.MAX_POINT_DISTANCE = parsed.get("MAX_POINT_DISTANCE");; //Default: 90

		 Constants.OPACITY = parsed.get("OPACITY");; //Default: 125
		
		 Constants.DELAY = parsed.get("DELAY"); //Default: 25 (ms)

		 Constants.MAX_COLOR_INC = parsed.get("MAX_COLOR_INC"); //Default: 3
		 Constants.DELAY_BETWEEN_RUNS = parsed.get("DELAY_BETWEEN_RUNS");; //Default: 2000
		
	}
}