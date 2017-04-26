import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a point on earth with a location
 */
public class Region extends GeographicRegion implements Serializable{

	/** The serial version uid*/
	private static final long serialVersionUID = 10L;
	/** The latitude of the region. */
	private String latitude;
	/** The longitude of the region. */
	private String longitude;
	/** The elevation of the region. */
	private Integer elevation;
	/** The set of regions that have already been visited*/
	public static HashSet<Region> visited = new HashSet<Region>();
	
	/**
	 * The default no-arg constructor
	 */
	public Region() {
		super();
	}
	
	/**
	 * The constructor for the coordinate regions.
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param elevation the elevation
	 */
	public Region(String name, String latitude, String longitude, Integer elevation) {
		super.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
	}
	
	/**
	 * This method gets the latitude
	 * @return latitude The latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * This method sets the latitude
	 * @param newLatitude The latitude to be set.
	 */
	public void setLatitude(String newLatitude) {
		this.latitude = newLatitude;
	}
	
	/**
	 * This method gets the longitude
	 * @return longitude The longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * Sets the longitude
	 * @param newLongitude The longitude to be set.
	 */
	public void setLongitude(String newLongitude) {
		this.longitude = newLongitude;
	}
	
	/**
	 * Gets the elevation
	 * @return elevation The elevation.
	 */
	public Integer getElevation() {
		return elevation;
	}
	
	/**
	 * This method sets the elevation of a city.
	 * @param newElevation The elevation to be set.
	 */
	public void setElevation(int newElevation) {
	
	this.elevation = newElevation;
	}
	
	/**
	 * Returns whether or not the city has location data stored
	 * @return true if it has a location, false otherwise
	 */
	public boolean hasLocation() {
		if (latitude != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Finds the neighboring regions.
	 * @param breadth The breadth
	 * @param length the length
	 * @param region the region
	 * @param regions the coordinate regions
	 * @return the coordinate regions in the neighborhood
	 */
	public static ArrayList<Region> findNeighborhood(int breadth, int length, Region region, 
			ArrayList<Region> regions) {
		Point point = Map.getLocationFromCoordinate(region.getLatitude(), region.getLongitude());
		ArrayList<Region> neighborhood = new ArrayList<Region>();
		//the user entered only a breadth
		if(breadth!=0 && length==0) {
			for(Region r: regions) {
				Point SregionPoint = Map.getLocationFromCoordinate(r.getLatitude(), r.getLongitude());
				if(Math.abs(point.gety()-SregionPoint.gety())<breadth) {
					neighborhood.add(r);
				}
			}
			return neighborhood;
		}
		//the user entered only a length
		else if(length!=0 && breadth==0) {
			for(Region r: regions) {
				Point SregionPoint = Map.getLocationFromCoordinate(r.getLatitude(), r.getLongitude());
				if(Math.abs(point.getx()-SregionPoint.getx())<length) {
					neighborhood.add(r);
				}
			}
			return neighborhood;
		}
		//the user entered both a length and breadth
		else {
			for(Region r: regions) {
				Point SregionPoint = Map.getLocationFromCoordinate(r.getLatitude(), r.getLongitude());
				if(Math.abs(point.gety()-SregionPoint.gety())<breadth && Math.abs(
						point.getx()-SregionPoint.getx())<length) {
					neighborhood.add(r);
				}
			}
			return neighborhood;
		}
	}
	
	/**
	 * Finds the neighboring regions and neighbors neighbors
	 * @param breadth The breadth
	 * @param length the length
	 * @param region the region
	 * @param regions the coordinate regions
	 * @return the coordinate regions in the neighborhood
	 */
	public static ArrayList<Region> findNeighborhoodRecursively(int breadth, int length, Region region, 
			ArrayList<Region> regions) {
		HashSet<Region> neighborhood = new HashSet<Region>();
		Point CregionPoint = Map.getLocationFromCoordinate(region.getLatitude(), region.getLongitude());
		//the user entered only a length
		if(length!=0 && breadth==0) {
			if(visited.contains(region)) {
				return new ArrayList<Region>(neighborhood);
			}
			for(Region regionS: regions) {
				Point SregionPoint = Map.getLocationFromCoordinate(regionS.getLatitude(), regionS.getLongitude());
				if(Math.abs(CregionPoint.getx()-SregionPoint.getx())<length) {
					neighborhood.add(regionS);
				}
			}
			visited.add(region);
			ArrayList<Region> addOns = new ArrayList<Region>();
			for(Region nregion: neighborhood) {
			
			addOns.addAll(findNeighborhoodRecursively(breadth, length, nregion, regions));
			}
			neighborhood.addAll(addOns);
			return new ArrayList<Region>(neighborhood);
		}
		//the user entered only a breadth
		else if(breadth!=0 && length==0) {
			if(visited.contains(region)) {
				return new ArrayList<Region>(neighborhood);
			}
			for(Region r: regions) {
				Point SregionPoint = Map.getLocationFromCoordinate(r.getLatitude(), r.getLongitude());
				if(Math.abs(CregionPoint.gety()-SregionPoint.gety())<breadth) {
					neighborhood.add(r);
				}
			}
			visited.add(region);
			ArrayList<Region> addOns = new ArrayList<Region>();
			for(Region nregion: neighborhood) {
				addOns.addAll(findNeighborhoodRecursively(breadth, length, nregion, regions));
			}
			neighborhood.addAll(addOns);
			return new ArrayList<Region>(neighborhood);
		}
		//the user entered both a length and breadth
		else {
			if(visited.contains(region)) {
				return new ArrayList<Region>(neighborhood);
			}
			for(Region r: regions) {
				Point SregionPoint = Map.getLocationFromCoordinate(r.getLatitude(), r.getLongitude());
				if(Math.abs(CregionPoint.gety()-SregionPoint.gety())<breadth && Math.abs(
						CregionPoint.getx()-SregionPoint.getx())<length) {
					neighborhood.add(r);
				}
			}
			visited.add(region);
			ArrayList<Region> addOns = new ArrayList<Region>();
			for(Region r: neighborhood) {
				addOns.addAll(findNeighborhoodRecursively(breadth, length, r, regions));
			}
			neighborhood.addAll(addOns);
			return new ArrayList<Region>(neighborhood);
		}
	}
	
}