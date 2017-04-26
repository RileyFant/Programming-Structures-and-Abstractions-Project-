import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RegionModel implements Serializable {
	/**The serial version uid*/
	private static final long serialVersionUID = 8L;
	private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
	/**List of continents.*/
	private ArrayList<Continent> continents;
	/**List of countries.*/
	private ArrayList<Country> countries;
	/**List of cities.*/
	private ArrayList<City> cities;
	/**List of places of interest.*/
	private ArrayList<PlaceOfInterest> placesOfInterest;
	/**List of points of interest.*/
	private ArrayList<PointOfInterest> pointsOfInterest;
	
	/**
	 * The default no-arg constructor
	 */
	public RegionModel() {
		
	}
	
	/**
	 * Constructs a new model with the given data
	 * @param continents the continents of the model
	 * @param countries the countries of the model
	 * @param cities the cities of the model
	 * @param placesOfInterest the places of the model
	 * @param pointsOfInterest the points of the model
	 */
	public RegionModel(ArrayList<Continent> continents, ArrayList<Country> countries, 
			ArrayList<City> cities, ArrayList<PlaceOfInterest> placesOfInterest, 
			ArrayList<PointOfInterest> pointsOfInterest) {
		this.continents = continents;
		this.countries = countries;
		this.cities = cities;
		this.placesOfInterest = placesOfInterest;
		this.pointsOfInterest = pointsOfInterest;
		actionListenerList = new ArrayList<ActionListener>();
	}
	
	/**
	 * gets the continents of the model
	 * @return the continents
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}
	
	/**
	 * gets the countries of the model
	 * @return the countries
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}
	
	/**
	 * gets the cities of the model
	 * @return the cities
	 */
	public ArrayList<City> getCities() {
		return cities;
	}
	
	/**
	 * gets the places of the model
	 * @return the places
	 */
	public ArrayList<PlaceOfInterest> getPlacesOfInterest() {
		return placesOfInterest;
	}
	
	/**
	 * gets the points of the model
	 * @return the points
	 */
	public ArrayList<PointOfInterest> getPointsOfInterest() {
		return pointsOfInterest;
	}
	
	/**
	 * sorts the continents by name
	 */
	public void sortContinents() {
		Comparator<Continent> nameOrder = new Comparator<Continent>() {
			public int compare(Continent c1, Continent c2) {
				return c1.getName().compareTo(c2.getName());
			}
		};
		Collections.sort(continents, nameOrder);
	}
	
	/**
	 * sorts the countries by name
	 */
	public void sortCountries() {
		Comparator<Country> nameOrder = new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c1.getName().compareTo(c2.getName());
			}
		};
		Collections.sort(countries, nameOrder);
	}
	
	/**
	 * sorts the cities by name
	 */
	public void sortCities() {
		Comparator<City> nameOrder = new Comparator<City>() {
			public int compare(City c1, City c2) {
				return c1.getName().compareTo(c2.getName());
			}
		};
		Collections.sort(cities, nameOrder);
	}
	
	/**
	 * sorts the places by name
	 */
	public void sortPlaces() {
		Comparator<PlaceOfInterest> nameOrder = new Comparator<PlaceOfInterest>() {
			public int compare(PlaceOfInterest c1, PlaceOfInterest c2) {
				return c1.getName().compareTo(c2.getName());
			}
		};
		Collections.sort(placesOfInterest, nameOrder);
	}
	
	/**
	 * sorts the points by name
	 */
	public void sortPoints() {
		Comparator<PointOfInterest> nameOrder = new Comparator<PointOfInterest>() {
			public int compare(PointOfInterest c1, PointOfInterest c2) {
				return c1.getName().compareTo(c2.getName());
			}
		};
		Collections.sort(pointsOfInterest, nameOrder);
	}
	
	/**
	 * This method adds an action event listener.
	 * @param actionListener The ActionListener to be added.
	 */
	public synchronized void addActionListener(ActionListener actionListener) {
		actionListenerList.add(actionListener);
	}
	
	/**
	 * This method removes an action event listener.
	 * @param actionListener The ActionListener to be removed.
	 */
	public synchronized void removeActionListener(ActionListener actionListener) {
		actionListenerList.remove(actionListener);
	}
}
