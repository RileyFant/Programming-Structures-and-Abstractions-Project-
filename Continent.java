import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Project #3
 * CS 2334, Section 10 (Lab 11)
 * March 4, 2014
 * <P>
 * This class Continent.
 * </P>
 * @version 1.0
 */
public class Continent extends GeographicRegion implements Serializable, Comparator<GeographicRegion>, Comparable<GeographicRegion>{
	
	private static final long serialVersionUID = -454930332685561407L;
	/**All of the countries on the continent*/
	private ArrayList<Country> countries = new ArrayList<Country>();
	/**The population of the Continent*/
	private Long population;
	/**The places of interest on the continent.*/
	private ArrayList<PlaceOfInterest> placesOfInterest = new ArrayList<PlaceOfInterest>(); 
	/**The points of interest on the country*/
	private ArrayList<PointOfInterest> pointsOfInterest = new ArrayList<PointOfInterest>();
	
	/**
	 * Constructs a continent with the specified information
	 * @param name The name of the Continent
	 * @param area The area of the Continent in square miles
	 * @param population the population of the Continent
	 */
	public Continent(String name, long population, float area) {
		this.name = name;
		this.population = population;
		this.area = area;
	}
	
	/**
	 * Constructs a continent with the specified information
	 * @param name The name of the Continent
	 * @param area The area of the Continent in square miles
	 * @param population The population of the Continent
	 * @param countries The countries on the Continent
	 */
	public Continent(String name, long population, float area, ArrayList<Country> countries) {
		this.name = name;
		this.population = population;
		this.area = area;
		this.countries = countries;
	}
	
	/**
	 * Gets the population of the Continent
	 * @return the population of the Continent
	 */
	public long getPopulation() {
		return population;
	}
	
	/**
	 * Sets the population of the Continent
	 * @param newPopulation
	 * @return true if the population was equal to or greater than zero and was set, false otherwise
	 */
	public boolean setPopulation(long newPopulation) {
		if (newPopulation >=0) {
			this.population = newPopulation;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the countries on the continent
	 * @return the countries on the continent
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}
	
	/**
	 * Sets the countries on the continent
	 * @param newCountries the countries on the continent to be set
	 */
	public void setCountries(ArrayList<Country> newCountries) {
		this.countries = newCountries;
	}
	
	/**
	 * Gets the POIs.
	 * @return pois the POIs
	 */
	public ArrayList<PlaceOfInterest> getPlacesOfInterest() {
		return placesOfInterest;
	}
	
	/**
	 * This method sets the POIS
	 * @param newPlacesOfInterest the pois to be set
	 */
	public void setPlacesOfInterest(ArrayList<PlaceOfInterest> newPlacesOfInterest) {
		this.placesOfInterest = newPlacesOfInterest;
	}
	
	/**
	 * Gets the POIs.
	 * @return pois the POIs
	 */
	public ArrayList<PointOfInterest> getPointsOfInterest() {
		return pointsOfInterest;
	}
	
	/**
	 * This method sets the POIS
	 * @param newPointsOfInterest the pois to be set
	 */
	public void setPointsOfInterest(ArrayList<PointOfInterest> newPointsOfInterest) {
		this.pointsOfInterest = newPointsOfInterest;
	}
	
	/**
	 * This method adds a poi.
	 * @param poi The poi to be added.
	 * @return true of the POI was added, false otherwise
	 */
	public boolean addPlaceOfInterest(PlaceOfInterest poi) {
		if (this.name.equals(poi.getName())) {
			placesOfInterest.add(poi);
			return true;
		}
		return false;
	}
	
	/**
	 * This method adds a poi.
	 * @param poi The poi to be added.
	 * @return true of the POI was added, false otherwise
	 */
	public boolean addPointOfInterest(PointOfInterest poi) {
		if (this.name.equals(poi.getName())) {
			pointsOfInterest.add(poi);
			return true;
		}
		return false;
	}
	
	/**
	 * This method removes a poi.
	 * @param poi the poi to be removed.
	 */
	public void removePlaceOfInterest(PlaceOfInterest poi) {
		placesOfInterest.remove(poi);
	}
	
	/**
	 * This method removes a poi.
	 * @param poi the poi to be removed.
	 */
	public void removePointOfInterest(PointOfInterest poi) {
		pointsOfInterest.remove(poi);
	}
	
	/**
	 * This method allows you to add a country to a continent.
	 * @param country The country object to be added.
	 * @return true if the country's continent name matched the continent name and was added,
	 * false otherwise
	 */
	public boolean addCountry(Country country) {
		if (country.getContinentName().equals(this.name)) {
			countries.add(country);
			return true;
		}
		return false;
	}
	
	/**
	 * This method allows you to remove a country from a continent.
	 * @param country The Country to be removed.
	 */
	public void removeCountry(Country country) {
		countries.remove(country);
	}
	
	/**
	 * Generates a string representation of the Continent
	 * @return the string representation of the Continent
	 */
	public String toString() {
		return name + "," + population + "," + area;
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
	 * This method sorts the continents by population.
	 * @param continents The ArrayList of continents to be sorted by population.
	 * @return True if the continents got sorted.
	 */
	public static boolean sortByPopulation(ArrayList<Continent> continents){
		Comparator<Continent> populationOrder = new Comparator<Continent>() {
			public int compare(Continent c1, Continent c2) {
				return c1.getPopulation() < c2.getPopulation() ? -1
					 : c1.getPopulation() > c2.getPopulation() ? 1
					 : 0;
			}
		};
		
		Collections.sort(continents, populationOrder);
		return true;
	}
}
