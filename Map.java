import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Map extends JPanel implements Serializable{
	private static final long serialVersionUID = -2711770387402700577L;
	/**The Model.*/
	private RegionModel model;
	/**The image.*/
	private BufferedImage image;
	/**The cities.*/
	private ArrayList<City> cities;
	/**The points of interest.*/
	private ArrayList<PointOfInterest> points;
	/**How big the image is compared to our coordinate system.*/
	private final int multiplier = 3;
	/**Check the type*/
	private int type;
	/***/
	int length;
	int breadth;
	ArrayList<Region> regions;
	/**
	 * The default (not-arg) constructor
	 */
	public Map() {
		//intially empty
	}
	
	/**
	 * Display a map of the world with strings on it
	 * @param cities the cities to be mapped
	 * @param points the points to be mapped
	 * @param type 1 for cities, 2 for points, 3 for regions
	 */
	public Map(ArrayList<City> cities, ArrayList<PointOfInterest> points, ArrayList<Region> regions, int type, int length, int breadth) {
		try {                
	    	   this.image = ImageIO.read(new File("map.jpg"));
	 		} catch (IOException ex) {
	    	   System.out.println("I could not find the image file.");
	 		}
	 		this.cities = cities;
	 		this.points = points;
	 		this.type = type;
	 		this.regions = regions;
	 		this.breadth = breadth;
	 		this.length = length;
 	}
	

	
	
	/**
	 * This method sets the model.
	 * @param newModel The new model
	 */
	public void setModel(RegionModel newModel) {
		model = newModel;
	}
	
	/**
	 * This method gets the model.
	 * @return The model.
	 */
	public RegionModel getModel() {
		return model;
	}
	
	/**
	 * This is the paintComponent method for the Map view.
	 * @param g the graphics
	 */

	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font f = new Font("sansserif", Font.BOLD, 12);
		g.setFont(f);
		if(type == 1){
			 //draw the map
	        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	        //draw the names on the map
	        for (City city : cities) {
	        
	        	if (city.hasLocation()) {
	        		Point location = getLocationFromCoordinate(city.getLatitude(), city.getLongitude());
	        		g.drawString(city.getName(), (int)(location.getx())*multiplier, (int)(location.gety())*multiplier);
	        		g.fillRect((int)location.getx()*multiplier, (int)location.gety()*multiplier, 3, 3);
	        	}
	        }
		}
		if(type == 2) {
			//draw the map
	        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	        //draw the names on the map
	        for (PointOfInterest point : points) {
	        	if (point.hasLocation()) {
	        		Point location = getLocationFromCoordinate(point.getLatitude(), point.getLongitude());
	        		g.drawString(point.getName(), (int)(location.getx())*multiplier, (int)(location.gety())*multiplier);
	        	}
	        }
		}
		if (type == 3) {
			g.drawImage(image, 0,0,getWidth(),getHeight(), this);
			for(Region region : regions) {
				if (region.hasLocation()) {
					Point location = getLocationFromCoordinate(region.getLatitude(), region.getLongitude());
					g.drawString(region.getName(), (int)location.getx()*multiplier, (int)(location.gety())*multiplier);
					g.drawRect((int)location.getx()*multiplier - (length/2)*3, (int)(location.gety())*multiplier - (breadth/2)*3, 
							length*multiplier, breadth*multiplier);
				}
			}
		}
    }
	
	/**
	 * This method locates the cities based on coordinates
	 * @param latitude The latitude
	 * @param longitude The longitude
	 * @return the Point
	 */
	protected static Point getLocationFromCoordinate(String latitude, String longitude) {
	    
		//the coordinates corresponding to latitude and longitude
		float x = 0;
		float y = 0;

		/*Convert from latitude and longitude to x and y where (N90,W180) = (0,0)*/
		if (latitude.substring(0, 1).equals("N")){
			y = 90 - Float.parseFloat(latitude.substring(1));
		}
		else if (latitude.substring(0,1).equals("S")) {
			y = 90 + Float.parseFloat(latitude.substring(1));
		}
		if (longitude.substring(0, 1).equals("E")) {
			x = 180 + Float.parseFloat(longitude.substring(1));

		}
		else if (longitude.substring(0, 1).equals("W")) {
			x = 180 - Float.parseFloat(longitude.substring(1));
		}
		
		return new Point(x, y);
	}
	
	 /**
	 * Displays a plate carree map projection of the cities.
	 * @param cities the cities to be displayed
	 */
	public static void drawMapCities(ArrayList<City> cities) {
		// Use a label to display the image
		JFrame frame = new JFrame();
		frame.setSize(1080, 540);
		Map map = new Map((ArrayList<City>)(cities), null, null, 1, 0, 0);
		frame.add(map);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Displays a map of the points.
	 * @param points the points
	 */
	public static void drawMapPoints(ArrayList<PointOfInterest> points) {
		// Use a label to display the image
		JFrame frame = new JFrame();
		frame.setSize(1080, 540);
		Map map = new Map(null, (ArrayList<PointOfInterest>)(points), null, 2, 0, 0);
		frame.add(map);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void drawMapRegions(ArrayList<Region> regions, int length, int breadth) {
		JFrame frame = new JFrame();
		frame.setSize(1080, 540);
		Map map = new Map(null, null, regions, 3, length, breadth);
		frame.add(map);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	
}