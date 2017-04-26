import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This class displays a region model in bunch of components
 * @author Riley Fant
 *
 */
public class SelectionView extends JFrame implements ActionListener {
	/**Whatever*/
	private static final long serialVersionUID = 1L;

	/**The Model*/
	private RegionModel model = new RegionModel();
	
	/**The five RegionPanels*/
	protected RegionPanel continentPanel = new RegionPanel("Continents");
	protected RegionPanel countryPanel = new RegionPanel("Countries");
	protected RegionPanel cityPanel = new RegionPanel("Cities");
	protected RegionPanel placePanel = new RegionPanel("Places");
	protected RegionPanel pointPanel = new RegionPanel("Points");
	
	protected JMenuItem loadGeo = new JMenuItem("Load Geography");
	protected JMenuItem saveGeo = new JMenuItem("Save Geography");
	protected JMenuItem importGeo = new JMenuItem("Import Geography");
	protected JMenuItem exportGeo = new JMenuItem("Export Geography");
	
	/*The simple bar chart option*/
	protected JMenuItem continents = new JMenuItem("Continents");
	protected JMenuItem countries = new JMenuItem("Countries");
	protected JMenuItem cities = new JMenuItem("Cities");
	protected JMenuItem places = new JMenuItem("Places of Interest");
	protected JMenuItem continentsA = new JMenuItem("Continents");
	protected JMenuItem countriesA = new JMenuItem("Countries");
	protected JMenuItem citiesA = new JMenuItem("Cities");
	protected JMenuItem placesA = new JMenuItem("Places of Interest");
	
	/*The stacked bar chart option.*/
	protected JMenuItem countriesInContinents = new JMenuItem("Countries within Continents");
	protected JMenuItem citiesInCountries = new JMenuItem("Cities within Countries");
	protected JMenuItem placesInContinents = new JMenuItem("Places of Interest within Continents");
	protected JMenuItem placesInCountries = new JMenuItem("Places of Interest within Countries");
	protected JMenuItem placesInCities = new JMenuItem("Places of Interes within Cities");
	protected JMenuItem countriesInContinentsA = new JMenuItem("Countries within Continents");
	protected JMenuItem citiesInCountriesA = new JMenuItem("Cities within Countries");
	protected JMenuItem placesInContinentsA = new JMenuItem("Places of Interest within Continents");
	protected JMenuItem placesInCountriesA = new JMenuItem("Places of Interest within Countries");
	protected JMenuItem placesInCitiesA = new JMenuItem("Places of Interes within Cities");
	
	/*The map option*/
	protected JMenuItem citiesInCountriesM = new JMenuItem("Cities within Countries");
	protected JMenuItem citiesInContinentsM = new JMenuItem("Cities within Continents");
	protected JMenuItem citiesWorldwideM= new JMenuItem("Cities Worldwide");
	protected JMenuItem pointsInCitiesM = new JMenuItem("Points of Interest within Cities");
	protected JMenuItem pointsInCountriesM = new JMenuItem("Points of Interest within Countries");
	protected JMenuItem pointsInContinentsM = new JMenuItem("Points of Interest within Continents");
	protected JMenuItem pointsWorldwideM = new JMenuItem("Points of Interest Worldwide");
	
	/*The neighborhood option*/
	protected JMenuItem hoodList = new JMenuItem("Neighborhood List");
	protected JMenuItem recursiveHoodList = new JMenuItem("Recursive Neighborhood List");
	protected JMenuItem hoodCheck = new JMenuItem("Geographic Neighborhood check");
	protected JMenuItem recursiveHoodCheck = new JMenuItem("Recursive Geographic Neighborhood check");
	protected JMenuItem hoodMap = new JMenuItem("Geographic Neighborhood");
	protected JMenuItem recursiveHoodMap = new JMenuItem("Recursive Geographic Neighborhood");
	
	
	public SelectionView() {
		this.setTitle("GeoGrapher");
		
		JPanel center = new JPanel(new GridLayout(1,5));
		center.add(continentPanel);
		center.add(countryPanel);
		center.add(cityPanel);
		center.add(placePanel);
		center.add(pointPanel);
		
		//The menu information
		JMenuBar menuBar = new JMenuBar();
		
		/*The file menu*/
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(loadGeo);
		fileMenu.add(saveGeo);
		saveGeo.setEnabled(false);
		fileMenu.add(importGeo);
		fileMenu.add(exportGeo);
		exportGeo.setEnabled(false);
		
		/*the neighborhood menu*/ 
		JMenu hoodMenu = new JMenu("Neighborhoods");
		hoodMenu.add(hoodList);
		hoodMenu.add(recursiveHoodList);
		hoodMenu.add(hoodCheck);
		hoodMenu.add(recursiveHoodCheck);
		hoodMenu.add(hoodMap);
		hoodMenu.add(recursiveHoodMap);
		
		
		/*The graph menu*/
		JMenu graphMenu = new JMenu("Graph");
		
		/*The simple bar chart option*/
		JMenu barGraph = new JMenu("Simple Bar Chart");
		JMenu areaSimple = new JMenu("Area");
		areaSimple.add(continentsA);
		areaSimple.add(countriesA);
		areaSimple.add(citiesA);
		areaSimple.add(placesA);
		JMenu populationSimple = new JMenu("Population");
		populationSimple.add(continents);
		populationSimple.add(countries);
		populationSimple.add(cities);
		populationSimple.add(places);
		barGraph.add(areaSimple);
		barGraph.add(populationSimple);
		
		/*The stacked bar chart option*/
		JMenu stackedBarGraph = new JMenu("Stacked Bar Chart");
		JMenu areaStacked = new JMenu("Area");
		JMenu populationStacked = new JMenu("Population");
		areaStacked.add(countriesInContinentsA);
		areaStacked.add(citiesInCountriesA);
		areaStacked.add(placesInContinentsA);
		areaStacked.add(placesInCountriesA);
		areaStacked.add(placesInCitiesA);
		populationStacked.add(countriesInContinents);
		populationStacked.add(citiesInCountries);
		populationStacked.add(placesInContinents);
		populationStacked.add(placesInCountries);
		populationStacked.add(placesInCities);
		stackedBarGraph.add(areaStacked);
		stackedBarGraph.add(populationStacked);
		
		/*The map option*/
		JMenu map = new JMenu("Map");
		map.add(citiesInCountriesM);
		map.add(citiesInContinentsM);
		map.add(citiesWorldwideM);
		map.add(pointsInCitiesM);
		map.add(pointsInCountriesM);
		map.add(pointsInContinentsM);
		map.add(pointsWorldwideM);
		graphMenu.add(barGraph);
		graphMenu.add(stackedBarGraph);
		graphMenu.add(map);
		menuBar.add(fileMenu);
		menuBar.add(graphMenu);
		menuBar.add(hoodMenu);
		
		
		
		//Add the menu and outer panel to the frame
		add(menuBar, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		
		//Set the view to appear
		setSize(new Dimension(1371, 255));
		continentPanel.addButton().setEnabled(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * This method sets the model.
	 * @param newModel The new model
	 */
	public void setModel(RegionModel newModel) {
		this.model = newModel;
		if (this.model != null) {
			// Register the view as listener for the model
			model.addActionListener(this);
		}
	}
	
	/**
	 * Add the 5 lists of regions of the model to their respective list on the view
	 */
	public void populatePanels() {
		if (model != null) {
			continentPanel.setGeoModel(model.getContinents());
			countryPanel.setGeoModel(model.getCountries());
			cityPanel.setGeoModel(model.getCities());
			placePanel.setGeoModel(model.getPlacesOfInterest());
			pointPanel.setGeoModel(model.getPointsOfInterest());
		}
		else {
			//nothing
		}
	}
	
	/**
	 * This method gets the model.
	 * @return The model.
	 */
	public RegionModel getModel() {
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
