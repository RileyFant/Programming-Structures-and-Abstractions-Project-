import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Project #3
 * CS 2334, Section 10 (Lab 11)
 * March 4, 2014
 * <P>
 * This class represents a Place of Interest.
 * </P>
 * @version 1.0
 */
public class PlaceOfInterest extends GeographicRegion implements Serializable, Comparable<GeographicRegion>, Comparator<GeographicRegion>{
	
	private static final long serialVersionUID = -3447494753194220834L;
	/**A description of the type of place of interest it is (lake, park, etc.)*/
	private String description;
	/**The continents/countries/cities the place of interest is on*/
	private ArrayList<String> locations = new ArrayList<String>();
	/**The points of interest on the place of interest*/
	private ArrayList<PointOfInterest> pointsOfInterest = new ArrayList<PointOfInterest>();
	
	/**
	 * Constructs a place of interest with the given information
	 * @param name the name of the POI
	 * @param area the area of the POI in square miles
	 * @param description a short description of the POI
	 * @param locations the locations where the place is
	 */
	public PlaceOfInterest(String name, float area, String description, ArrayList<String> locations) {
		super.name = name;
		super.area = area;
		this.description = description;
		this.locations = locations;
	}
	
	/**
	 * Constructs a place of interest with the given information
	 * @param name the name of the POI
	 * @param area the area of the POI in square miles
	 * @param description a short description of the POI
	 */
	public PlaceOfInterest(String name, float area, String description) {
		super.name = name;
		super.area = area;
		this.description = description;
	}
	
	/**
	 * Gets the short description of the POI
	 * @return the description of the POI
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description to the specified String
	 * @param newDescription the new description of the POI
	 */
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	/**
	 * Gets the locations where the POI is located
	 * @return the locations where the POI is located
	 */
	public ArrayList<String> getLocations() {
		return locations;
	}
	
	/**
	 * Sets the locations where the POI is located
	 * @param newLocations the new countries where the POI is located
	 */
	public void setLocations(ArrayList<String> newLocations) {
		this.locations = newLocations;
	}
	
	/**
	 * Gets the points of interest on the place of interest.
	 * @return the points of interest on the place of interest
	 */
	public ArrayList<PointOfInterest> getPointsOfInterest() {
		return pointsOfInterest;
	}
	
	/**
	 * This method sets the POIS
	 * @param newPointsOfInterest the points of interest to be set
	 */
	public void setPointsOfInterest(ArrayList<PointOfInterest> newPointsOfInterest) {
		this.pointsOfInterest = newPointsOfInterest;
	}
	
	/**
	 * This method adds a point of interest to the place of interest.
	 * @param poi The place of interest to be added.
	 */
	public void addPointOfInterest(PointOfInterest poi) {
		this.pointsOfInterest.add(poi);
	}
	
	/**
	 * This method removes a point of interest
	 * @param poi the point of interest to be removed.
	 */
	public void removePointOfInterest(PointOfInterest poi) {
		this.pointsOfInterest.remove(poi);
	}
	
	/**
	 * Adds a location to where the place of interest is located
	 * @param newLocation the new location where the place of interest is located
	 */
	public void addLocation(String newLocation) {
		this.locations.add(newLocation);
	}
	
	/**
	 * Generates a string representation of the POI
	 * @return the string representation of the POI
	 */
	public String toString() {
		return this.name + "," + this.area  + "," + this.description 
				+ "," + this.locations.toString();
	}
	
	/**
	 * This method is the compareTo method from the Comparable interface.  
	 * It compares a continent object to another continent.
	 * @param geo The Geographic Region to be compared.
	 * @return A negative integer, zero, or a positive integer as the first 
	 * argument is less than, equal to, or greater
	 * than the second.
	 */
	public int compareTo(GeographicRegion geo) {
		return this.toString().compareTo(geo.toString());
	}
	
	/**
	 * This method is the compare method from the Comparator interface.  
	 * It compares two cities based on a specific way to compare them.
	 * @param geo1 The first Geographic Region to be compared.
	 * @param geo2 The second Geographic Region to be compared.
	 * @return A negative integer, zero, or a positive integer as the first 
	 * argument is less than, equal to, or greater
	 * than the second.
	 */
	public int compare(GeographicRegion geo1, GeographicRegion geo2) {
		return geo1.compareTo(geo2);
	}
	
	/**
	 * Sorts the pois according to the sortBy string
	 * @param pois the POIs to be sorted
	 * @param sortBy how the POIs will be sorted
	 * @return the sorted POIs
	 */
	public static ArrayList<PlaceOfInterest> sortPlacesBy(ArrayList<PlaceOfInterest> pois, String sortBy){
		if (sortBy.equalsIgnoreCase("AR")) {	
			Comparator<PlaceOfInterest> areaOrder = new Comparator<PlaceOfInterest>() {
				public int compare(PlaceOfInterest p1, PlaceOfInterest p2) {
					return p1.getArea() < p2.getArea() ? -1
						 : p1.getArea() > p2.getArea() ? 1
						 : 0;
				}
			};
			Collections.sort(pois, areaOrder);
			return pois;
		}
		else if (sortBy.equalsIgnoreCase("LE")) {	
			Comparator<PlaceOfInterest> nameOrder = new Comparator<PlaceOfInterest>() {
				public int compare(PlaceOfInterest p1, PlaceOfInterest p2) {
					return p1.getName().compareTo(p2.getName());
				}
			};
			Collections.sort(pois, nameOrder);
			return pois;
		}
		else if (sortBy.equalsIgnoreCase("RA")) {
			Collections.shuffle((List<?>) pois);
			return pois;
		}
		else{
			System.out.println("Please enter AR, PO, LE, or RA!");
			return null;
		}
	}
	
}
