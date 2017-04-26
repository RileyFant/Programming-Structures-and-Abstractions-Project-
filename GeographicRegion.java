import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Project #3
 * CS 2334, Section 10 (Lab 11)
 * March 4, 2014
 * <P>
 * This abstract class represents a Geographic Region
 * </P>
 * @version 1.0
 */
public class GeographicRegion implements Serializable, Comparable<GeographicRegion>, Comparator<GeographicRegion> {
	/**The serial version UID number*/
	private static final long serialVersionUID = 2L;
	/**The name of the GeographicRegion*/
	protected String name;
	/**The area of the GeographicRegion in square miles*/
	protected Float area;//in square miles
	
	/**
	 * The default (no-arg) constructor.
	 */
	public GeographicRegion() {
		
	}
	
	public GeographicRegion(String name) {
		this.name = name;
	}
	
	public GeographicRegion(String name, Float area) {
		this.name = name;
		this.area = area;
	}
	
	/**
	 * Gets the name of the GeographicRegion
	 * @return the name of the GeographicRegion
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the GeographicRegion to the specified String
	 * @param newName The new name of the GeographicRegion to be set
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * Gets the area of the GeographicRegion to the specified String
	 * @return the area of the GeographicRegion in square miles
	 */
	public float getArea() {
		return area;
	}
	
	/**
	 * Sets the area of the GeographicRegion to the specified value
	 * @param newArea the new area of the GeographicRegion
	 * @return true if the value was greater than zero and the area was set, and false otherwise
	 */
	public boolean setArea(float newArea) {
		if (newArea>0) {
			this.area = newArea;
			return true;
		}
		return false;
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
	 * This method sorts an ArrayList of GeographicRegions by area, name, or population
	 * @param geo the ArrayList of GeographicRegions to be sorted
	 * @param sortBy "AR" to sort by area, "PO" to sort by population, "LE" to sort lexicographically
	 * @return true if the data could be sorted by sortBy, false otherwise
	 */
	public static boolean sortBy(ArrayList<? extends GeographicRegion> geo, String sortBy) {
		if (sortBy.equalsIgnoreCase("AR")) {	
			Comparator<GeographicRegion> areaOrder = new Comparator<GeographicRegion>() {
				public int compare(GeographicRegion c1, GeographicRegion c2) {
					return c1.getArea() < c2.getArea() ? -1
						 : c1.getArea() > c2.getArea() ? 1
						 : 0;
				}
			};
			Collections.sort(geo, areaOrder);
			return true;
		}
		else if (sortBy.equalsIgnoreCase("LE")) {	
			Comparator<GeographicRegion> nameOrder = new Comparator<GeographicRegion>() {
				public int compare(GeographicRegion c1, GeographicRegion c2) {
					return c1.getName().compareTo(c2.getName());
				}
			};
			Collections.sort(geo, nameOrder);
			return true;
		}
		else if (sortBy.equalsIgnoreCase("RA")) {
			Collections.shuffle(geo);
			return true;
		}
		else{
			System.out.println("Please enter AR, LE, or RA!");
			return false;
		}
	}
	
	/**
	 * compares two objects to see if they are the same
	 * @param o the object being compared to this
	 * @return true if the objects are equal, false otherwise
	 */
	public boolean equals(Object o) {
		//check to see if they refer to the same object
		if (o == this) {
			return true;
		}
		//check to see if the objects are of the same class
		if (!(o instanceof City)) {
			return false;
		}
		//cast, now that its safe
		City o1 = (City)o;
		//compare field by field
		return
			this.name.equals(o1.getName()) &&
			this.area.equals(o1.getArea());
	}
	
}
