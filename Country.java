import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Project #3
 * CS 2334, Section 10 (Lab 11)
 * March 4, 2014
 * <P>
 * This class represents a Country on Earth.
 * </P>
 * @version 1.0
 */
public class Country extends GeographicRegion implements Serializable, Comparable<GeographicRegion>, Comparator<GeographicRegion> {
	
	private static final long serialVersionUID = 1329056629233570449L;
	/**The cities on the country*/
	private ArrayList<City> cities = new ArrayList<City>();
	/**The places of interest on the country*/
	private ArrayList<PlaceOfInterest> placesOfInterest = new ArrayList<PlaceOfInterest>(); 
	/**The points of interest on the country*/
	private ArrayList<PointOfInterest> pointsOfInterest = new ArrayList<PointOfInterest>();
	/**The name of the continent that the country is on*/
	private String continentName;
	/**The population of the country*/
	private Long population;
	
	/**
	 * Constructs a Country with the specified information
	 * @param name the name of the country
	 * @param area the area of the country in square miles
	 * @param population the population of the country
	 */
	public Country(String name, long population, float area, String continentName) {
		super.name = name;
		this.population = population;
		super.area = area;
		this.continentName = continentName;
	}
	
	/**
	 * Constructs a Country with the specified name
	 * @param name the name of the country
	 */
	public Country(String name) {
		super.name = name;
	}
	
	/**
	 * Constructs a Country with the specified information
	 * @param name the name of the Country
	 * @param area The area of the Country
	 * @param population the population of the Country
	 * @param cities The cities in the Country
	 */
	public Country(String name, float area, long population, String continentName, ArrayList<City> cities) {
		super.name = name;
		this.population = population;
		super.area = area;
		this.continentName = continentName;
		this.cities = cities;
	}
	
	/**
	 * Gets the population of the Country
	 * @return the population of the country
	 */
	public long getPopulation() {
		return population;
	}
	
	/**
	 * Sets the population of the Country
	 * @param newPopulation the new population of the Country to be set
	 * @return true if the population was equal to or greater than zero and the population was set, false otherwise
	 */
	public boolean setPopulation(long newPopulation) {
		if (newPopulation >= 0) {
			this.population = newPopulation;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the Cities in the Country
	 * @return the cities in the Country
	 */
	public ArrayList<City> getCities() {
		return cities;
	}
	
	/**
	 * Gets the places of interest in the country.
	 * @return the places of interest on the country
	 */
	public ArrayList<PlaceOfInterest> getPlacesOfInterest() {
		return placesOfInterest;
	}
	
	/**
	 * This method sets the POIS
	 * @param pois the pois to be set
	 */
	public void setPlacesOfInterest(ArrayList<PlaceOfInterest> pois) {
		this.placesOfInterest = pois;
	}
	
	/**
	 * Gets the POIs in the country.
	 * @return pois the POIs
	 */
	public ArrayList<PointOfInterest> getPointsOfInterest() {
		return pointsOfInterest;
	}
	
	/**
	 * This method sets the POIS
	 * @param pois the pois to be set
	 */
	public void setPointsOfInterest(ArrayList<PointOfInterest> pois) {
		this.pointsOfInterest = pois;
	}
	
	/**
	 * Gets the name of the continent that the country is on
	 * @return the name of the continent the country is on
	 */
	public String getContinentName() {
		return continentName;
	}
	
	/**
	 * Sets the name of the continent to which the country belongs
	 * @param newContinentName the new name of the continent to which the country belongs
	 */
	public void setContinentName(String newContinentName) {
		this.continentName = newContinentName;
	}
	
	/**
	 * Sets the cities of the Country
	 * @param newCities the new cities of the country
	 */
	public void setCities(ArrayList<City> newCities) {
		this.cities = newCities;
	}
	
	/**
	 * This method allows users to add a city to a country.
	 * @param city The city object to be added.
	 * @return true if the city's county name matched the name of the country it is being added to
	 * and the city was added, and false otherwise
	 */
	public boolean addCity(City city) {
		if (city.getCountryName().equals(name)) {
			cities.add(city);
			return true;
		}
		return false;
	}

	/**
	 * This method adds a poi.
	 * @param poi The poi to be added.
	 * @return true of the POI was added, false otherwise
	 */
	public boolean addPlaceOfInterest(PlaceOfInterest poi) {
		ArrayList<String> locations = new ArrayList<String>();
		for (String geo : poi.getLocations()) {
			locations.add(geo);
		}
		if (locations.contains(name)) {
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
		if (poi.getRegion().equals(name)) {
			pointsOfInterest.add(poi);
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
	 * This method allows users to remove a city from a country.
	 * @param city The city object to be removed.
	 */
	public void removeCity(City city) {
		cities.remove(city);
	}
	
	/**
	 * Generates a string representation of the Country
	 * @return the string representation of the Countries
	 */
	public String toString() {
		return name + ", " + population + "," + area + "," + continentName;
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
	 * @param countries The ArrayList of countries to be sorted by population.
	 * @return True if the continents got sorted.
	 */
	public static boolean sortByPopulation(ArrayList<Country> countries){
		Comparator<Country> populationOrder = new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c1.getPopulation() < c2.getPopulation() ? -1
					 : c1.getPopulation() > c2.getPopulation() ? 1
					 : 0;
			}
		};
		
		Collections.sort(countries, populationOrder);
		return true;
	}

}
