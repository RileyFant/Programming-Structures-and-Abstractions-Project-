import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class SimpleBarChart extends JPanel{
	/**
	 * the serial version uid
	 */
	private static final long serialVersionUID = 5L;
	/**The model.*/
	private RegionModel model;
	
	/**
	 * This method draws the bar chart.
	 * @param continents The continents
	 * @param sortBy how to sort the continents
	 */
	public static void drawBarChartContinents(ArrayList<Continent> continents, String sortBy){
		JFrame frame = new JFrame();
		Histogram histogram = new Histogram();
		frame.setTitle("Continents Bar Chart");
		
		int[] count = new int[continents.size()];
		if (sortBy.equalsIgnoreCase("AR")) {
			GeographicRegion.sortBy(continents, "AR");
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((continents.get(i).getArea()));
			}
		}
		else if (sortBy.equalsIgnoreCase("PO")) {
			Continent.sortByPopulation(continents);
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((((Continent)continents.get(i)).getPopulation())/100);
			}
		}
		String[] continentNames = new String[continents.size()];
		for (int i = 0; i<continentNames.length; i++) {
			continentNames[i] = continents.get(i).getName();
		}
		
		histogram.showHistogram(count, continentNames, false);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/**
	 * This method draws the bar chart.
	 * @param countries The countries.
	 * @param sortBy how to sort the countries
	 */
	public static void drawBarChartCountries(ArrayList<Country> countries, String sortBy) {
		JFrame frame = new JFrame();
		frame.setTitle("Countries Bar Chart");
		Histogram histogram = new Histogram();
	
		int[] count = new int[countries.size()];
		if (sortBy.equalsIgnoreCase("AR")) {
			GeographicRegion.sortBy(countries, "AR");
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((countries.get(i).getArea()));
			}
		}
		else if (sortBy.equalsIgnoreCase("PO")) {
			Country.sortByPopulation(countries);
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((((Country)countries.get(i)).getPopulation())/100);
			}
		}
		String[] countryNames = new String[countries.size()];
		for (int i = 0; i<countryNames.length; i++) {
			countryNames[i] = countries.get(i).getName();
		}
		
		histogram.showHistogram(count, countryNames, false);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This method draws a bar chart.
	 * @param cities The cities.
	 */
	public static void drawBarChartCities(ArrayList<City> cities, String sortBy) {
		JFrame frame = new JFrame();
		frame.setTitle("Cities Bar Chart");
		Histogram histogram = new Histogram();
		
		int[] count = new int[cities.size()];
		if (sortBy.equalsIgnoreCase("AR")) {
			GeographicRegion.sortBy(cities, "AR");
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((cities.get(i).getArea()));
			}
		}
		else if (sortBy.equalsIgnoreCase("PO")) {
			City.sortCitiesBy(cities, "PO");
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((((City)cities.get(i)).getPopulation())/100);
			}
		}
		String[] cityNames = new String[cities.size()];
		for (int i = 0; i<cityNames.length; i++) {
			cityNames[i] = cities.get(i).getName();
		}
		
		histogram.showHistogram(count, cityNames, false);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This method draws the bar chart.
	 * @param places The places of interest
	 */
	public static void drawBarChartPlaces(ArrayList<PlaceOfInterest> places, String sortBy) {
		JFrame frame = new JFrame();
		frame.setTitle("Places Bar Chart");
		Histogram histogram = new Histogram();
		
		int[] count = new int[places.size()];
		if (sortBy.equalsIgnoreCase("AR")) {
			PlaceOfInterest.sortPlacesBy(places, "AR");
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((places.get(i).getArea()));
			}
		}
		String[] cityNames = new String[places.size()];
		for (int i = 0; i<cityNames.length; i++) {
			cityNames[i] = places.get(i).getName();
		}
		
		histogram.showHistogram(count, cityNames, false);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	/**
	 * This method sets the model.
	 * @param newModel The new model
	 */
	public void setModel(RegionModel newModel) {
		this.model = newModel;
	}
	
	/**
	 * This method gets the model.
	 * @return The model.
	 */
	public RegionModel getModel() {
		return model;
	}
}
