

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Project #3
 * CS 2334, Section 10 (Lab 11)
 * March 4, 2014
 * <P>
 * This class represents a City.
 * </P>
 * @version 1.0
 */
public class City extends Region implements Serializable, Comparable<GeographicRegion>, Comparator<GeographicRegion> {
	
	private static final long serialVersionUID = 685250343195354324L;
	/** The name of the country the city is in. */
	private String countryName;
	/**The population of the city.*/
	private Long population;
	/** The latitude of the city. */
	private String latitude;
	/** The longitude of the city. */
	private String longitude;
	/** the elevation of the city. */
	private Integer elevation;
	/**The places of interest on the city*/
	private ArrayList<PlaceOfInterest> placesOfInterest = new ArrayList<PlaceOfInterest>(); 
	/**The points of interest on the city*/
	private ArrayList<PointOfInterest> pointsOfInterest = new ArrayList<PointOfInterest>();
	
	/**
	 * This is the constructor for the City class for cities without longitude, latitude, or
	 * elevation values.
	 */
	public City(String name, Long population, float area, String countryName) {
		super.name = name;
		this.population = population;
		super.area = area;
		this.countryName = countryName;
	}
	
	/**
	 * This is the constructor for the City class for cities with longitude, latitude, and 
	 * elevation values.
	 */
	public City(String name, Long population, float area, String countryName, String latitude, 
			String longitude, Integer elevation) {
		super.name = name;
		this.population = population;
		super.area = area;
		this.countryName = countryName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
	}
	
	/**
	 * This method gets the name of the Country that the city is in.
	 * @return continentName The name of the continent.
	 */
	public String getCountryName() {
		return countryName;
	}
	
	/**
	 * This method sets the name of the Country the City is in.
	 * @param newCountryName The name of the Country to be set.
	 */
	public void setCountryName(String newCountryName) {
		this.countryName = newCountryName;
	}
	
	/**
	 * This method gets the population for a city.
	 * @return population The population of the city.
	 */
	public long getPopulation() {
		return population;
	}
	
	/**
	 * This method sets the population of a city. 
	 * @return true if the population was zero or greater and the population was set, false otherwise
	 */
	public boolean setPopulation(long newPopulation) {
		if (newPopulation >= 0) {
			this.population = newPopulation;
			return true;
		}
		return false;
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
	 * Gets the POIs.
	 * @return pois the POIs
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
	 * Gets the POIs.
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
	 * This method adds a poi.
	 * @param placeOfInterest The poi to be added.
	 * @return true of the POI was added, false otherwise
	 */
	public boolean addPlaceOfInterest(PlaceOfInterest placeOfInterest) {
		ArrayList<String> locations = new ArrayList<String>();
		for (String geo : placeOfInterest.getLocations()) {
			locations.add(geo);
		}
		if (locations.contains(this.name)) {
			placesOfInterest.add(placeOfInterest);
			return true;
		}
		return false;
	}
	
	/**
	 * This method removes a poi.
	 * @param poi the poi to be removed.
	 * @return true if removed, false otherwise.
	 */
	public boolean removePlaceOfInterest(PlaceOfInterest poi) {
		if (placesOfInterest.contains(poi)) {
			placesOfInterest.remove(poi);
			return true;
		}
		return false;
	}
	
	/**
	 * This method removes a poi.
	 * @param poi the poi to be removed.
	 * @return true if removed, false otherwise.
	 */
	public boolean removePointOfInterest(PointOfInterest poi) {
		if (pointsOfInterest.contains(poi)) {
			pointsOfInterest.remove(poi);
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
		if (poi.getRegion().equals(this.name)) {
			pointsOfInterest.add(poi);
			return true;
		}
		return false;
	}

	/**
	 * Generates a string representation of the City
	 * @return the string representation of the City
	 */
	public String toString() {
		if (latitude != null) {
			return this.name  + "," + this.area  + "," + this.population  + "," +  
					this.countryName  + "," + this.latitude  + "," + this.longitude 
					+ "," + elevation;
		}
		return this.name  + "," + this.area  + "," + this.population  + "," +  
			this.countryName;
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
			this.area.equals(o1.getArea()) &&
			this.population.equals(o1.getPopulation()) &&
			this.latitude.equals(o1.getLatitude()) &&
			this.longitude.equals(o1.getLongitude()) &&
			this.elevation.equals(o1.getElevation());
	}
	
	/**
	 * This method sorts the cities using Comparators based on user input.
	 * @param cities The ArrayList of City objects.
	 * @param sortBy The user input.
	 * @return true if the cities get sorted.
	 */

	public static boolean sortCitiesBy(ArrayList<City> cities, String sortBy) {
		ArrayList<City> citiesWLO = new ArrayList<City>();
		for (City city : cities) {
			if (city.getLongitude() != (null))
				citiesWLO.add(city);
		}
		if (sortBy.equalsIgnoreCase("LO")) {
			Comparator<City> longitudeOrder = new Comparator<City>(){
				public int compare(City c1, City c2) {
					return c1.getLongitude().compareTo(c2.getLongitude());
				}
			};
			Collections.sort(citiesWLO, longitudeOrder);
			return true;
		}
		else if (sortBy.equalsIgnoreCase("LA")) {
			Comparator<City> latitudeOrder = new Comparator<City>(){
				public int compare(City c1, City c2) {
					return c1.getLatitude().compareTo(c2.getLatitude());
				}
			};
			Collections.sort(citiesWLO, latitudeOrder);
			return true;
		}
		else if (sortBy.equalsIgnoreCase("EL")) {
			Comparator<City> elevationOrder = new Comparator<City>(){
				public int compare(City c1, City c2) {
					return c1.getElevation() < c2.getElevation() ? -1
						 : c1.getElevation() > c2.getElevation() ? 1
					     : 0;
				}
			};
			Collections.sort(citiesWLO, elevationOrder);
			return true;
		}
		else if (sortBy.equalsIgnoreCase("PO")) {
			Comparator<City> populationOrder = new Comparator<City>() {
				public int compare(City c1, City c2) {
					return c1.getPopulation() < c2.getPopulation() ? -1
						 : c1.getPopulation() > c2.getPopulation() ? 1
						 : 0;
				}
			};
			Collections.sort(cities, populationOrder);
			return true;
		}
		else if (sortBy.equalsIgnoreCase("RA")) {
			Collections.shuffle(cities);
			return true;
		}
		else{
			System.out.println("Please enter AR, PO, LA, LO, EL, LE, or RA!");
			return false;
		}
	}//End of sortCitiesBy
	

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

