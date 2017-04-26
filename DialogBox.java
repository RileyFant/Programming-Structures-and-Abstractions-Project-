import java.awt.GridLayout;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * This class represents a modulus JFrame to add or edit various Geographic Regions
 */
public class DialogBox extends JFrame {
	/** Serial Version UID*/
	private static final long serialVersionUID = 2L;
	/**Text field for the name of the geographic region*/
	protected JTextField jtfName;
	/**Text field for the area of the geographic region*/
	protected JTextField jtfArea;
	/**The text field for the population of the  geo*/
	protected JTextField jtfPopulation;;
	/**The text field for the latitude of the */
	protected JTextField jtfLatitude;
	/**The text field for the longitude*/
	protected JTextField jtfLongitude;
	/**The text field for the elevation*/
	protected JTextField jtfElevation;
	/**The text field for the name of the region the geo belongs to*/
	protected JComboBox<String> jcbRegion;
	/**The text field for the description of the region*/
	protected JTextField jtfDescription;
	
	/**The button to add the continent*/
	protected JButton jbtAddContinent = new JButton("Add");
	/**The button to submit the edited version of the continent*/
	protected JButton jbtEditContinent = new JButton("Edit");
	/**The button to add the country*/
	protected JButton jbtAddCountry = new JButton("Add");
	/**The button to edit a country*/
	protected JButton jbtEditCountry = new JButton("Edit");
	/**The button to add a city*/
	protected JButton jbtAddCity = new JButton("Add");
	/**The button to edit a city*/
	protected JButton jbtEditCity = new JButton("Edit");
	/**The button to add a point*/
	protected JButton jbtAddPoint = new JButton("Add");
	/**The button to edit a point*/
	protected JButton jbtEditPoint = new JButton("Edit");
	/**The button to add a place*/
	protected JButton jbtAddPlace = new JButton("Add");
	/**The button to edit a place*/
	protected JButton jbtEditPlace = new JButton("Edit");
	/**The button to cancel the dialog box*/
	protected JLabel filler = new JLabel(" ");
	
	/**
	 * Default no-arg constructor
	 */
	public DialogBox() {
		//intentionally empty
	}
	
	/**
	 * Constructor for an add/edit dialog box
	 * @param geo the geographic region to be edited. if null, a geographic region will be constructed
	 * @param type the type of dialogbox
	 * @param trueIfAddingFalseIfEditing self explanatory
	 */
	public DialogBox(GeographicRegion geo, String type, String[] regions, boolean trueIfAddingFalseIfEditing){
		//if null is passed for geo, the dialog box should allow the addition of a region
		
		if (trueIfAddingFalseIfEditing) {
			if (type.equalsIgnoreCase("continent")) {
				setTitle("Add Continent");
				setLayout(new GridLayout(4,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField());
				add(new JLabel(" Population:"));
				add(jtfPopulation = new JTextField());
				add(new JLabel(" Area:"));
				add(jtfArea = new JTextField());
				add(filler);
				add(jbtAddContinent);
			}
			else if (type.equalsIgnoreCase("country")) {
				setTitle("Add Country");
				setLayout(new GridLayout(5,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField());
				add(new JLabel(" Population:"));
				add(jtfPopulation = new JTextField());
				add(new JLabel(" Area:"));
				add(jtfArea = new JTextField());
				add(new JLabel(" Continent:"));
				add(jcbRegion = new JComboBox<String>(regions));
				add(filler);
				add(jbtAddCountry);
			}
			else if (type.equalsIgnoreCase("city")) {
				setTitle("Add City");
				setLayout(new GridLayout(8,2));
				add(new JLabel(" Name"));
				add(jtfName = new JTextField());
				add(new JLabel(" Population"));
				add(jtfPopulation = new JTextField());
				add(new JLabel(" Area:"));
				add(jtfArea = new JTextField());
				add(new JLabel(" Latitude"));
				add(jtfLatitude = new JTextField());
				add(new JLabel(" Longitude"));
				add(jtfLongitude = new JTextField());
				add(new JLabel(" Elevation:"));
				add(jtfElevation = new JTextField());
				add(new JLabel(" Country:"));
				add(jcbRegion = new JComboBox<String>(regions));
				add(filler);
				add(jbtAddCity);
			}
			else if (type.equalsIgnoreCase("place")) {
				setTitle("Add Place Of Interest");
				setLayout(new GridLayout(4,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField());
				add(new JLabel(" Area"));
				add(jtfArea = new JTextField());
				add(new JLabel(" Description"));
				add(jtfDescription = new JTextField());
				add(filler);
				add(jbtAddPlace);
			}
			else if (type.equalsIgnoreCase("point")) {
				setTitle("Add Point Of Interest");
				setLayout(new GridLayout(7,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField());
				add(new JLabel(" Description"));
				add(jtfDescription = new JTextField());
				add(new JLabel(" Latitude"));
				add(jtfLatitude = new JTextField());
				add(new JLabel(" Longitude"));
				add(jtfLongitude = new JTextField());
				add(new JLabel(" Elevation"));
				add(jtfElevation = new JTextField());
				add(new JLabel(" Region:"));
				add(jcbRegion = new JComboBox<String>(regions));
				add(filler);
				add(jbtAddPoint);
			}
			else {
				throw new InputMismatchException("The type must be \"continent, country, city, place, or point\"");
			}
		}
		/*else the dialog box should allow the user to edit the passed geo, 
		with the text boxes defaulting geo's info*/
		else {
			if (type.equalsIgnoreCase("continent")) {
				Continent continent = (Continent)geo;
				setTitle("Edit Continent");
				setLayout(new GridLayout(4,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField(continent.getName()));
				add(new JLabel(" Population:"));
				add(jtfPopulation = new JTextField(Long.toString(continent.getPopulation())));
				add(new JLabel(" Area:"));
				add(jtfArea = new JTextField(Float.toString(continent.getArea())));
				add(filler);
				add(jbtEditContinent);
			}
			else if (type.equalsIgnoreCase("country")) {
				Country country = (Country)geo;
				setTitle("Edit Country");
				setLayout(new GridLayout(5,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField(country.getName()));
				add(new JLabel(" Population:"));
				add(jtfPopulation = new JTextField(Long.toString(country.getPopulation())));
				add(new JLabel(" Area:"));
				add(jtfArea = new JTextField(Float.toString(country.getArea())));
				add(new JLabel(" Continent:"));
				add(jcbRegion = new JComboBox<String>(regions));
				jcbRegion.setSelectedItem(country.getContinentName());
				add(filler);
				add(jbtEditCountry);
			}
			else if (type.equalsIgnoreCase("city")) {
				City city = (City)geo;
				setTitle("Edit City");
				setLayout(new GridLayout(8,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField(city.getName()));
				add(new JLabel(" Population:"));
				add(jtfPopulation = new JTextField(Long.toString(city.getPopulation())));
				add(new JLabel(" Area:"));
				add(jtfArea = new JTextField(Float.toString(city.getArea())));
				add(new JLabel(" Latitude:"));
				add(jtfLatitude = new JTextField(city.getLatitude()));
				add(new JLabel(" Longitude:"));
				add(jtfLongitude = new JTextField(city.getLongitude()));
				add(new JLabel(" Elevation:"));
				if (city.getElevation() == null)
					add(jtfElevation = new JTextField());
				else
					add(jtfElevation = new JTextField(Integer.toString(city.getElevation())));
				add(new JLabel("Country:"));
				add(jcbRegion = new JComboBox<String>(regions));
				jcbRegion.setSelectedItem(city.getCountryName());
				add(filler);
				add(jbtEditCity);
			}
			else if (type.equalsIgnoreCase("place")) {
				PlaceOfInterest place = (PlaceOfInterest)geo;
				setTitle("Edit Place Of Interest");
				setLayout(new GridLayout(4,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField(place.getName()));
				add(new JLabel(" Area"));
				add(jtfArea = new JTextField(Float.toString(place.getArea())));
				add(new JLabel(" Description"));
				add(jtfDescription = new JTextField(place.getDescription()));
				add(filler);
				add(jbtEditPlace);
			}
			else if (type.equalsIgnoreCase("point")) {
				PointOfInterest point = (PointOfInterest)geo;
				setTitle("Edit Point Of Interest");
				setLayout(new GridLayout(7,2));
				add(new JLabel(" Name:"));
				add(jtfName = new JTextField(point.getName()));
				add(new JLabel(" Description"));
				add(jtfDescription = new JTextField(point.getDescription()));
				add(new JLabel(" Latitude"));
				add(jtfLatitude = new JTextField(point.getLatitude()));
				add(new JLabel(" Longitude"));
				add(jtfLongitude = new JTextField(point.getLongitude()));
				add(new JLabel(" Elevation"));
				add(jtfElevation = new JTextField(Integer.toString(point.getElevation())));
				add(new JLabel(" Region:"));
				add(jcbRegion = new JComboBox<String>(regions));
				jcbRegion.setSelectedItem(point.getRegion());
				add(filler);
				add(jbtEditPoint);
			}
			else {
				throw new InputMismatchException("The type must be \"continent, country, city, place, or point\"");
			}
		}
		pack();
	}

}