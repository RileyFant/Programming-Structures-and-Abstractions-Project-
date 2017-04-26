import java.io.Serializable;
import java.util.Comparator;


public class PointOfInterest extends Region implements Serializable, Comparable<GeographicRegion>, Comparator<GeographicRegion>{
	
	private static final long serialVersionUID = 3736424784374121652L;
	/**The description of the Point of Interest*/
	private String description;
	/**The latitude of the point of interest.*/
	private String latitude;
	/**The longitude of the point of interest.*/
	private String longitude;
	/**The elevation of the point of interest.*/
	private Integer elevation;
	/**The list of regions it is located.*/
	private String region;
	
	/**
	 * A constructor for the point of interest class
	 * @param description the description
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param elevation the elevation
	 * @param region the region it is on
	 */
	public PointOfInterest(String name, String description, String latitude, String longitude, Integer elevation, 
			String region) {
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.region = region;
	}
	
	public PointOfInterest(String name, String description, String region) {
		this.name = name;
		this.description = description;
		this.region = region;
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
	 * This method gets the latitude of a city and returns it as a String.
	 * @return latitude The latitude of the city.
	 */
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * This method sets the latitude of a city.
	 * @param newLatitude The latitude to be set.
	 */
	public void setLatitude(String newLatitude) {
		this.latitude = newLatitude;
	}
	
	/**
	 * This method gets the longitude of a city and returns it as a String.
	 * @return longitude The longitude of a city.
	 */
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * This method sets the longitude.
	 * @param newLongitude The longitude to be set.
	 */
	public void setLongitude(String newLongitude) {
		this.longitude = newLongitude;
	}
	
	/**
	 * This method gets the elevation of a city and returns it as an int.
	 * @return elevation The elevation of a city.
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
	 * This method gets the regions it is located on.
	 * @return The regions
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * This method sets the regions
	 * @param newRegion the region to be set
	 */
	public void setRegion(String newRegion) {
		this.region = newRegion;
	}

	/**
	 * Generates a string representation of the POI
	 * @return the string representation of the POI
	 */
	public String toString() {
		return this.name + "," + this.description + "," + this.latitude + 
				"," + this.longitude + "," + this.elevation + "," + this.region;
	}
	/**
	 * This method is the compareTo method from the Comparable interface.  
	 * It compares a continent object to another continent.
	 * @param geo the geographic region to be compared to
	 * @return A negative integer, zero, or a positive integer as the first 
	 * argument is less than, equal to, or greater
	 * than the second.
	 */
	public int compareTo(GeographicRegion geo) {
		return 0;
	}
	
	/**
	 * This method is the compare method from the Comparator interface.  
	 * It compares two cities based on a specific way to compare them.
	 * @param geo1 The first geo to be compared.
	 * @param geo2 The second geo to be compared.
	 * @return A negative integer, zero, or a positive integer as the first 
	 * argument is less than, equal to, or greater
	 * than the second.
	 */
	public int compare(GeographicRegion geo1, GeographicRegion geo2) {
		return geo1.compareTo(geo2);
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
}
