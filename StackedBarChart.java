import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class StackedBarChart extends JPanel{
	/**
	 * the serial version uid
	 */
	private static final long serialVersionUID = 6L;
	
	/**
	 * This method draws the segmented bar chart.
	 * @param continent The continent
	 */
	public static void drawStackedBarChartContinent(Continent continent, ArrayList<Country> allCountries, String sortBy){
		JFrame frame = new JFrame();
		Histogram histogram = new Histogram();
		frame.setTitle("Countries in a Continent Stacked Bar Chart");
		String continentName = continent.getName();
		
		ArrayList<Country> countries = new ArrayList<Country>();
		for(Country country: allCountries){
			if(country.getContinentName().equals(continentName)){
				countries.add(country);
			}
		}
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
		histogram.showHistogram(count, countryNames, true);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This method draws the segmented bar chart.
	 * @param country The country.
	 */
	public static void drawStackedBarChartCountry(Country country, ArrayList<City> allCities, String sortBy) {
		JFrame frame = new JFrame();
		Histogram histogram = new Histogram();
		frame.setTitle("Cities in a Country Stacked Bar Chart");
		String countryName = country.getName();
		
		ArrayList<City> cities = new ArrayList<City>();
		for(City city: allCities){
			if(city.getCountryName().equals(countryName)){
				cities.add(city);
			}
		}
		int[] count = new int[cities.size()];
		if (sortBy.equalsIgnoreCase("AR")) {
			City.sortCitiesBy(cities, "AR");
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
		histogram.showHistogram(count, cityNames, true);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This method draws a segmented bar chart for places
	 * @param continent The continent
	 * @param allPlaces The places
	 * @param sortBy How to sort
	 */
	public static void drawSegmentedBarChartPlacesInContinents(Continent continent,
			ArrayList<PlaceOfInterest> allPlaces, String sortBy) {
		JFrame frame = new JFrame();
		Histogram histogram = new Histogram();
		frame.setTitle("Places in a Continent Stacked Bar Chart");
		String continentName = continent.getName();
		
		ArrayList<PlaceOfInterest> places = new ArrayList<PlaceOfInterest>();
		for(PlaceOfInterest place: allPlaces){
			if(place.getLocations().contains(continentName)){
				places.add(place);
			}
		}
		int[] count = new int[places.size()];
		if (sortBy.equalsIgnoreCase("AR")) {
			PlaceOfInterest.sortPlacesBy(places, "AR");
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((places.get(i).getArea()));
			}
		}
		String[] placeNames = new String[places.size()];
		for (int i = 0; i<placeNames.length; i++) {
			placeNames[i] = places.get(i).getName();
		}
		histogram.showHistogram(count, placeNames, true);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This method draws a segmented bar chart for places.
	 * @param country the country
	 * @param allPlaces the places
	 * @param sortBy how to sort
	 */
	public static void drawSegmentedBarChartPlacesInCountries(Country country,
			ArrayList<PlaceOfInterest> allPlaces, String sortBy) {
		JFrame frame = new JFrame();
		Histogram histogram = new Histogram();
		frame.setTitle("Places in a Country Stacked Bar Chart");
		String countryName = country.getName();
		
		ArrayList<PlaceOfInterest> places = new ArrayList<PlaceOfInterest>();
		for(PlaceOfInterest place: allPlaces){
			if(place.getLocations().contains(countryName)){
				places.add(place);
			}
		}
		int[] count = new int[places.size()];
		if (sortBy.equalsIgnoreCase("AR")) {
			PlaceOfInterest.sortPlacesBy(places, "AR");
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((places.get(i).getArea()));
			}
		}
		String[] placeNames = new String[places.size()];
		for (int i = 0; i<placeNames.length; i++) {
			placeNames[i] = places.get(i).getName();
		}
		histogram.showHistogram(count, placeNames, true);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This method draws a segmented bar chart for places.
	 * @param city the city
	 * @param allPlaces the places
	 * @param sortBy how to sort
	 */
	public static void drawSegmentedBarChartPlacesInCities(City city,
			ArrayList<PlaceOfInterest> allPlaces, String sortBy) {
		JFrame frame = new JFrame();
		Histogram histogram = new Histogram();
		frame.setTitle("Places in a Country Stacked Bar Chart");
		String cityName = city.getName();
		
		ArrayList<PlaceOfInterest> places = new ArrayList<PlaceOfInterest>();
		for(PlaceOfInterest place: allPlaces){
			if(place.getLocations().contains(cityName)){
				places.add(place);
			}
		}
		int[] count = new int[places.size()];
		if (sortBy.equalsIgnoreCase("AR")) {
			PlaceOfInterest.sortPlacesBy(places, "AR");
			for (int i = 0; i<count.length; i++) {
				count[i] = (int)((places.get(i).getArea()));
			}
		}
		String[] placeNames = new String[places.size()];
		for (int i = 0; i<placeNames.length; i++) {
			placeNames[i] = places.get(i).getName();
		}
		histogram.showHistogram(count, placeNames, true);
		frame.setLocationRelativeTo(null);
		frame.add(histogram);
		frame.pack();
		frame.setVisible(true);
	}
}
