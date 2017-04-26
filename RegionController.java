import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegionController {
	/**The model*/
	private RegionModel model;
	/**The selection view*/
	private SelectionView selectionView;
	/**The DialogBox to add a continent*/
	private DialogBox addContinent;
	/**The DialogBox to edit a continent*/
	private DialogBox editContinent;
	/**The DialogBox to add a country*/
	private DialogBox addCountry;
	/**The DialogBox to edit a country*/
	private DialogBox editCountry;
	/**The DialogBox to add a city*/
	private DialogBox addCity;
	/**The DialogBox to edit a city*/
	private DialogBox editCity ;
	/**The DialogBox to add a Place of Interest*/
	private DialogBox addPlace;
	/**The DialogBox to edit a Place of Interest*/
	private DialogBox editPlace;
	/**The DialogBox to add a Point of Interest*/
	private DialogBox addPoint;
	/**The DialogBox to edit a Point of Interest*/
	private DialogBox editPoint;
//	/**The FileIODialog to open a file*/
//	private FileIODialog open;
//	/**The FileIODialog to save to a file*/
//	private FileIODialog save;
	

	/**
	 * This is the constructor for the Region Controller class.
	 */
	public RegionController() {
		//intentionally empty
	}
//	/**
//	 * Provides a method to select a file
//	 */
//	private class GetSelectedFileNameListener implements ActionListener {
//		public void actionPerformed(ActionEvent e){
//			open = new FileIODialog("Continent", true);
//			open.setLocationRelativeTo(selectionView);
//			open.setVisible(true);
//			open.jbtOpen.addActionListener(new OpenFileNameFromDialogListener());
//		}
//	}
//	/**
//	 * Provides a method to open a file
//	 */
//	private class OpenFileNameFromDialogListener implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			String fileName = open.jfc.getSelectedFile().getPath();
//			System.out.println(fileName);
//		}
//	}
	
	/**
	 * Provides a method to add a continent from a dialog
	 */
	private class AddContinentFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = addContinent.jtfName.getText();
			long population;
			float area;
			try {
			population = Long.parseLong(addContinent.jtfPopulation.getText());
			area = Float.parseFloat(addContinent.jtfArea.getText());
			}
			catch(NumberFormatException i) {
				addContinent.setVisible(false);
				JOptionPane.showMessageDialog(selectionView, "You are not smart", 
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (Continent cont : model.getContinents()) {
				if (cont.getName().equals(name)) {
					addContinent.setVisible(false);
					JOptionPane.showMessageDialog(selectionView.continentPanel.spGeos, 
							"A continent named " + name + " already exists", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			Continent continent = new Continent(name, population, area);
			model.getContinents().add(continent);
			selectionView.populatePanels();
			addContinent.setVisible(false);
		}
	}
	
	/**
	 * Provides a method to edit a continent from a dialog
	 */
	private class EditContinentFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int[] indices = selectionView.continentPanel.jlGeoList.getSelectedIndices();
			for (int i = 0; i<indices.length; i++) {
				model.getContinents().remove(indices[i]);
				String name = editContinent.jtfName.getText();
				long population = Long.parseLong(editContinent.jtfPopulation.getText());
				float area = Float.parseFloat(editContinent.jtfArea.getText());
				Continent continent = new Continent(name, population, area);
				model.getContinents().add(continent);
			}
			model.sortContinents();
			selectionView.populatePanels();
			editContinent.setVisible(false);	
		}
	}
	
	/**
	 * Provides a method to add a country from a dialog
	 */
	private class AddCountryFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = addCountry.jtfName.getText();
			long population = Long.parseLong(addCountry.jtfPopulation.getText());
			float area = Float.parseFloat(addCountry.jtfArea.getText());
			String continentName = (String)addCountry.jcbRegion.getSelectedItem();
			for (Country c : model.getCountries()) {
				if (c.getName().equals(name)) {
					addCountry.setVisible(false);
					JOptionPane.showMessageDialog(selectionView.countryPanel.spGeos, 
							"A country named " + name + " already exists", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			float totalArea = 0;
			for (Country c : model.getCountries()) {
				if (c.getContinentName().equals(continentName)) {
					totalArea += c.getArea();
				}
			}
			float continentArea = 0;
			for (Continent c : model.getContinents()) {
				if (c.getName().equals(continentName)){
					continentArea = c.getArea();
				}
			}
			if (totalArea>continentArea) {
				addCountry.setVisible(false);
				JOptionPane.showMessageDialog(selectionView.countryPanel.spGeos, 
						"The country is too big to add to " + continentName, 
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Country country = new Country(name, population, area, continentName);
			model.getCountries().add(country);
			selectionView.populatePanels();
			addCountry.setVisible(false);
		}
	}
	
	/**
	 * Provides a method to edit a continent from a dialog
	 */
	private class EditCountryFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.countryPanel.jlGeoList.getSelectedIndex();
			model.getCountries().remove(index);;
			String name = editCountry.jtfName.getText();
			long population = Long.parseLong(editCountry.jtfPopulation.getText());
			float area = Float.parseFloat(editCountry.jtfArea.getText());
			String continentName = (String)editCountry.jcbRegion.getSelectedItem();
			Country country = new Country(name, population, area, continentName);
			model.getCountries().add(country);
			model.sortCountries();
			selectionView.populatePanels();
			editCountry.setVisible(false);
		}
	}
	
	/**
	 * Provides a method to add a city from a dialog
	 */
	private class AddCityFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = addCity.jtfName.getText();
			long population = Long.parseLong(addCity.jtfPopulation.getText());
			float area = Float.parseFloat(addCity.jtfArea.getText());
			String countryName = (String)addCity.jcbRegion.getSelectedItem();
			String latitude = addCity.jtfLatitude.getText();
			String longitude = addCity.jtfLongitude.getText();
			Point point = Map.getLocationFromCoordinate(latitude, longitude);
			int elevation = Integer.parseInt(addCity.jtfElevation.getText());
			for (City c : model.getCities()) {
				if (c.getName().equals(name)) {
					addCity.setVisible(false);
					JOptionPane.showMessageDialog(selectionView.cityPanel.spGeos, 
							"A City named " + name + " already exists", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			float totalArea = 0;
			for (City c : model.getCities()) {
				if (c.getCountryName().equals(countryName)) {
					totalArea += c.getArea();
				}
			}
			float countryArea = 0;
			for (Country c : model.getCountries()) {
				if (c.getName().equals(countryName)){
					countryArea = c.getArea();
				}
			}
			if (totalArea>countryArea) {
				addCity.setVisible(false);
				JOptionPane.showMessageDialog(selectionView.cityPanel.spGeos, 
						"The city is too big to add to " + countryName, 
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			City city;
			if ( point.getx() == 0 || point.gety() == 0 ) {
				city = new City(name, population, area, countryName);
			}
			else {
				city = new City(name, population, area, countryName, latitude, longitude, elevation);
			}
			model.getCities().add(city);
			model.sortCities();
			selectionView.populatePanels();
			addCity.setVisible(false);
		}
	}
	
	/**
	 * Provides a method to edit a city from a dialog
	 */
	private class EditCityFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.cityPanel.jlGeoList.getSelectedIndex();
			model.getCities().remove(index);
			String name = editCity.jtfName.getText();
			long population = Long.parseLong(editCity.jtfPopulation.getText());
			float area = Float.parseFloat(editCity.jtfArea.getText());
			String countryName = (String)editCity.jcbRegion.getSelectedItem();
			String latitude = editCity.jtfLatitude.getText();
			String longitude = editCity.jtfLongitude.getText();
			City city;
			if (editCity.jtfElevation.getText().equals("")) {
				city = new City(name, population, area, countryName);
			}
			else {
				int elevation = Integer.parseInt(editCity.jtfElevation.getText());
				city = new City(name, population, area, countryName, latitude, longitude, elevation);
			}
			model.getCities().add(city);
			model.sortCities();
			selectionView.populatePanels();
			editCity.setVisible(false);
		}
	}
	
	/**
	 * Provides a method to add a place from a dialog
	 */
	private class AddPlaceFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = addPlace.jtfName.getText();
			float area = Float.parseFloat(addPlace.jtfArea.getText());
			String description = addPlace.jtfDescription.getText();
			for (PlaceOfInterest c : model.getPlacesOfInterest()) {
				if (c.getName().equals(name)) {
					addPlace.setVisible(false);
					JOptionPane.showMessageDialog(selectionView.placePanel.spGeos, 
							"A Place Of Interest named " + name + " already exists", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			PlaceOfInterest place = new PlaceOfInterest(name, area, description);
			model.getPlacesOfInterest().add(place);
			model.sortPlaces();
			selectionView.populatePanels();
			addPlace.setVisible(false);
		}
	}
	
	/**
	 * Provides a method to edit a place from a dialog
	 */
	private class EditPlaceFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.placePanel.jlGeoList.getSelectedIndex();
			model.getPlacesOfInterest().remove(index);
			String name = editPlace.jtfName.getText();
			float area = Float.parseFloat(editPlace.jtfArea.getText());
			String description = editPlace.jtfDescription.getText();
			PlaceOfInterest place = new PlaceOfInterest(name, area, description);
			model.getPlacesOfInterest().add(place);
			model.sortPlaces();
			selectionView.populatePanels();
			editPlace.setVisible(false);
		}
	}
	
	/**
	 * Provides a method to add a point from a dialog
	 */
	private class AddPointFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = addPoint.jtfName.getText();
			String description = addPoint.jtfDescription.getText();
			String region = (String)addPoint.jcbRegion.getSelectedItem();
			for (PointOfInterest c : model.getPointsOfInterest()) {
				if (c.getName().equals(name)) {
					addPoint.setVisible(false);
					JOptionPane.showMessageDialog(selectionView.pointPanel.spGeos, 
							"A point of interest named " + name + " already exists", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			PointOfInterest point = new PointOfInterest(name, description, region);
			model.getPointsOfInterest().add(point);
			model.sortPoints();
			selectionView.populatePanels();
			addPoint.setVisible(false);
		}
	}
	
	/**
	 * Provides a method to edit a point from a dialog
	 */
	private class EditPointFromDialogListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.pointPanel.jlGeoList.getSelectedIndex();
			model.getPointsOfInterest().remove(index);
			String name = editPoint.jtfName.getText();
			String description = editPoint.jtfDescription.getText();
			String region = (String)editPoint.jcbRegion.getSelectedItem();
			String latitude = editPoint.jtfLatitude.getText();
			String longitude = editPoint.jtfLongitude.getText();
			int elevation = Integer.parseInt(editPoint.jtfElevation.getText());
			PointOfInterest point;
			if (editPoint.jtfElevation.getText().equals("")) {
				point = new PointOfInterest(name, description, region);
			}
			else {
				point = new PointOfInterest(name, description, latitude, longitude, elevation, region);
			}
			model.getPointsOfInterest().add(point);
			model.sortPoints();
			selectionView.populatePanels();
			editPoint.setVisible(false);
			
		}
	}
	
	/**
	 * AddContinentListener provides a method to add a continent to the list of continents.
	 */
	private class AddContinentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addContinent = new DialogBox(null, "continent", null, true);
			addContinent.jbtAddContinent.addActionListener(new AddContinentFromDialogListener());
			addContinent.jbtAddContinent.addActionListener(new EnableAllButtonsListener());
			addContinent.setLocationRelativeTo(selectionView.continentPanel.spGeos);
			addContinent.setVisible(true);
		}
		
	}
	
	/**
	 * Provides a method to edit a continent
	 */
	private class EditContinentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int[] indices = selectionView.continentPanel.jlGeoList.getSelectedIndices();
			if (indices.length <= 0) {
				JOptionPane.showMessageDialog(null, "You must select a continent to edit", 
						"Select A Continent", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (int i = 0; i < indices.length; i++) {
				Continent continent = model.getContinents().get(indices[i]);
				editContinent = new DialogBox(continent, "continent", null, false);
				editContinent.jbtEditContinent.addActionListener(new EditContinentFromDialogListener());
				editContinent.setLocationRelativeTo(selectionView.continentPanel.spGeos);
				editContinent.setVisible(true);
			}
		}
	}
	
	/**
	 * provides a method to delete a continent from the list
	 */
	private class DeleteContinentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.continentPanel.jlGeoList.getSelectedIndex();
			model.getContinents().remove(index);
			selectionView.populatePanels();
		}
	}
	
	/**
	 * provides a method to add a country to the list
	 */
	private class AddCountryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] regions = new String[model.getContinents().size()];
			for (int i = 0; i<regions.length; i++) {
				regions[i] = model.getContinents().get(i).getName();
			}
			addCountry = new DialogBox(null, "country", regions, true);
			addCountry.jbtAddCountry.addActionListener(new AddCountryFromDialogListener());
			addCountry.setLocationRelativeTo(selectionView.countryPanel.spGeos);
			addCountry.setVisible(true);
		}
	}
	
	/**
	 * provides a method to edit a country on the list
	 */
	private class EditCountryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.countryPanel.jlGeoList.getSelectedIndex();
			if (index <= -1) {
				JOptionPane.showMessageDialog(null, "You must select a Country to edit", 
						"Select A Country", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Country country = model.getCountries().get(index);
			String[] regions = new String[model.getContinents().size()];
			for (int i = 0; i<regions.length; i++) {
				regions[i] = model.getContinents().get(i).getName();
			}
			editCountry = new DialogBox(country, "Country", regions, false);
			editCountry.jbtEditCountry.addActionListener(new EditCountryFromDialogListener());
			editCountry.setLocationRelativeTo(selectionView.countryPanel.spGeos);
			editCountry.setVisible(true);
		}
	}
	
	/**
	 * provides a method to delete a country from the list
	 */
	private class DeleteCountryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String countryName = (String)selectionView.countryPanel.jlGeoList.getSelectedValue();
			for (int i = 0; i < model.getCountries().size(); i++) {
				if (model.getCountries().get(i).getName().equals(countryName)) {
					model.getCountries().remove(i);
				}
			}
			for (int i = 0; i < model.getCities().size(); i++) {
				if (model.getCities().get(i).getCountryName().equals(countryName)) {
					model.getCities().remove(i);
				}
			}
			selectionView.populatePanels();
		}
	}
	
	/**
	 * provides a method to add a city to the model.
	 */
	private class AddCityListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] regions = new String[model.getCountries().size()];
			for (int i = 0; i<regions.length; i++) {
				regions[i] = model.getCountries().get(i).getName();
			}
			addCity = new DialogBox(null, "city", regions, true);
			addCity.jbtAddCity.addActionListener(new AddCityFromDialogListener());
			addCity.setLocationRelativeTo(selectionView.cityPanel.spGeos);
			addCity.setVisible(true);
		}
	}
	
	/**
	 * provides a method to edit a city.
	 */
	private class EditCityListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.cityPanel.jlGeoList.getSelectedIndex();
			if (index <= -1) {
				JOptionPane.showMessageDialog(null, "You must select a City to edit", 
						"Select A City", JOptionPane.ERROR_MESSAGE);
				return;
			}
			City city = model.getCities().get(index);
			String[] regions = new String[model.getCountries().size()];
			for (int i = 0; i<regions.length; i++) {
				regions[i] = model.getCountries().get(i).getName();
			}
			editCity = new DialogBox(city, "city", regions, false);
			editCity.jbtEditCity.addActionListener(new EditCityFromDialogListener());
			editCity.setLocationRelativeTo(selectionView.cityPanel.spGeos);
			editCity.setVisible(true);
		}
	}
	
	/**
	 * provides a method to delete a city.
	 */
	private class DeleteCityListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cityName = (String)selectionView.cityPanel.jlGeoList.getSelectedValue();
			for (int i = 0; i < model.getCities().size(); i++) {
				if (model.getCities().get(i).getName().equals(cityName)) {
					model.getCities().remove(i);
				}
			}
			for (int i = 0; i < model.getPointsOfInterest().size(); i++) {
				if (model.getPointsOfInterest().get(i).getRegion().equals(cityName)) {
					model.getPointsOfInterest().remove(i);
				}
			}
			selectionView.populatePanels();
		}
	}
	
	/**
	 * provides a method to add a place of interest.
	 */
	private class AddPlaceOfInterestListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addPlace = new DialogBox(null, "place", null, true);
			addPlace.jbtAddPlace.addActionListener(new AddPlaceFromDialogListener());
			addPlace.setLocationRelativeTo(selectionView.placePanel.spGeos);
			addPlace.setVisible(true);
		}
	}
	
	/**
	 * provides a method to edit a place of interest.
	 */
	private class EditPlaceOfInterestListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.placePanel.jlGeoList.getSelectedIndex();
			if (index <= -1) {
				JOptionPane.showMessageDialog(null, "You must select a Place to edit", 
						"Select A Place", JOptionPane.ERROR_MESSAGE);
				return;
			}
			PlaceOfInterest place = model.getPlacesOfInterest().get(index);
			editPlace = new DialogBox(place, "Place", null, false);
			editPlace.jbtEditPlace.addActionListener(new EditPlaceFromDialogListener());
			editPlace.setLocationRelativeTo(selectionView.placePanel.spGeos);
			editPlace.setVisible(true);
		}
	}
	
	/**
	 * provides a method to delete a place of interest.
	 */
	private class DeletePlaceOfInterestListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String placeName = (String)selectionView.placePanel.jlGeoList.getSelectedValue();
			for (int i = 0; i < model.getPlacesOfInterest().size(); i++) {
				if (model.getPlacesOfInterest().get(i).getName().equals(placeName)) {
					model.getPlacesOfInterest().remove(i);
				}
			}
			selectionView.populatePanels();
		}
	}
	
	/**
	 * provides a method to add a point of interest.
	 */
	private class AddPointOfInterestListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] regionTypes = new String[]{"Continents", "Countries", "Cities", "Places of Interest"};
			String regionType =  (String)JOptionPane.showInputDialog(selectionView.pointPanel.spGeos,
                    "Add the Point to what regions?",
                    "Add Point", 0, null,regionTypes, "Continents");
			String[] regions;
			if (regionType.equals("Continents")) {
				regions = new String[model.getContinents().size()];
				for (int i = 0; i<regions.length; i++) {
					regions[i] = model.getContinents().get(i).getName();
				}
			}
			else if (regionType.equals("Countries")) {
				regions = new String[model.getCountries().size()];
				for (int i = 0; i<regions.length; i++) {
					regions[i] = model.getCountries().get(i).getName();
				}
			}
			else if (regionType.equals("Cities")) {
				regions = new String[model.getCities().size()];
				for (int i = 0; i<regions.length; i++) {
					regions[i] = model.getCities().get(i).getName();
				}
			}
			else {
				regions = new String[model.getPlacesOfInterest().size()];
				for (int i = 0; i<regions.length; i++) {
					regions[i] = model.getPlacesOfInterest().get(i).getName();
				}
			}
			
			addPoint = new DialogBox(null, "point", regions, true);
			addPoint.jbtAddPoint.addActionListener(new AddPointFromDialogListener());
			addPoint.setLocationRelativeTo(selectionView.pointPanel.spGeos);
			addPoint.setVisible(true);
		}
	}
	
	/**
	 * provides a method to edit a point of interest.
	 */
	private class EditPointOfInterestListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = selectionView.pointPanel.jlGeoList.getSelectedIndex();
			if (index <= -1) {
				JOptionPane.showMessageDialog(null, "You must select a Point to edit", 
						"Select A Point", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String[] regions = new String[model.getContinents().size()];
			for (int i = 0; i<regions.length; i++) {
				regions[i] = model.getContinents().get(i).getName();
			}
			PointOfInterest point = model.getPointsOfInterest().get(index);
			editPoint = new DialogBox(point, "Point", regions, false);
			editPoint.jbtEditPoint.addActionListener(new EditPointFromDialogListener());
			editPoint.setLocationRelativeTo(selectionView.pointPanel.spGeos);
			editPoint.setVisible(true);
		}
	}
	
	/**
	 * provides a method to delete a point of interest.
	 */
	private class DeletePointOfInterestListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pointName = (String)selectionView.pointPanel.jlGeoList.getSelectedValue();
			for (int i = 0; i < model.getPointsOfInterest().size(); i++) {
				if (model.getPointsOfInterest().get(i).getName().equals(pointName)) {
					model.getPointsOfInterest().remove(i);
				}
			}
			selectionView.populatePanels();
		}
	}
	
	/**
	 * provides a method to load geographic data from a binary file
	 */
	private class LoadGeographyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RegionModel model = FileIO.load("save.txt");
			setModel(model);
			selectionView.setModel(model);
			selectionView.populatePanels();
			//allow the user to save and export
			selectionView.saveGeo.setEnabled(true);
			selectionView.exportGeo.setEnabled(true);
		}
	}
	
	/**
	 * provides a method to save the geographic data to a binary file
	 */
	private class SaveGeographyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FileIO.save(model, "save.txt");
		}
	}
	
	/**
	 * provides a method to import geographic data from 4 text files
	 */
	private class ImportGeographyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			RegionModel model = FileIO.importFromFiles();
			setModel(model);
			selectionView.setModel(model);
			selectionView.saveGeo.setEnabled(true);
			selectionView.exportGeo.setEnabled(true);
			selectionView.populatePanels();
			
			selectionView.continentPanel.enableButtons();
			selectionView.countryPanel.enableButtons();
			selectionView.cityPanel.enableButtons();
			selectionView.placePanel.enableButtons();
			selectionView.pointPanel.enableButtons();
			
			
		}
	}
	
	//export
	/**
	 * provides a method to save the geographic data to 4 text files
	 */
	private class ExportGeographyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] fileNames = new String[]{"0", "1", "2", "3", "4"};
			FileIO.export(model, fileNames);
		}
	}
	
	///////////////////////////////////GRAPH\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*The simple bar chart*/
	/**
	 * provides a listener for the continents(population)
	 */
	private class PopContinentsListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			ArrayList<Continent> continents = model.getContinents();
			SimpleBarChart.drawBarChartContinents(continents, "PO");
		}
	}
	
	/**
	 * provides a listener for the countries(population)
	 */
	private class PopCountriesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Country> countries = model.getCountries();
			SimpleBarChart.drawBarChartCountries(countries, "PO");
		}
	}
	
	/**
	 * provides a listener for the cities(population)
	 */
	private class PopCitiesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<City> cities = model.getCities();
			SimpleBarChart.drawBarChartCities(cities, "PO");
		}
	}
	
	/**
	 * provides a listener for the places(population)
	 */
	private class PopPlacesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<PlaceOfInterest> places = model.getPlacesOfInterest();
			SimpleBarChart.drawBarChartPlaces(places, "PO");
		}
	}
	
	/**
	 * provides a listener for the continents(area)
	 */
	private class AreaContinentsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Continent> continents = model.getContinents();
			SimpleBarChart.drawBarChartContinents(continents, "AR");
		}
	}
	
	/**
	 * provides a listener for the countries(area)
	 */
	private class AreaCountriesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Country> countries = model.getCountries();
			SimpleBarChart.drawBarChartCountries(countries, "AR");
		}
	}
	
	/**
	 * provides a listener for the cities(area)
	 */
	private class AreaCitiesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<City> cities = model.getCities();
			SimpleBarChart.drawBarChartCities(cities, "AR");
		}
	}
	
	/**
	 * provides a listener for the places(area)
	 */
	private class AreaPlacesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<PlaceOfInterest> places = model.getPlacesOfInterest();
			SimpleBarChart.drawBarChartPlaces(places, "AR");
		}
	}
	
	private class EnableAllButtonsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Enable all of the buttons on the SelectionView
			
			selectionView.saveGeo.setEnabled(true);
			selectionView.exportGeo.setEnabled(true);
			selectionView.continentPanel.enableButtons();
			selectionView.countryPanel.enableButtons();
			selectionView.cityPanel.enableButtons();
			selectionView.placePanel.enableButtons();
			selectionView.pointPanel.enableButtons();
		}
	}
	
	/*The stacked bar char*/
	/**
	 * provides a listener for the countries in continents(population)
	 */
	private class PopCountriesInContinentsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] continentNames = new String[model.getContinents().size()];
			ArrayList<Continent> continents = model.getContinents();
			for(int i=0; i<continents.size(); ++i){
				Continent continent = continents.get(i);
				continentNames[i] = continent.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Continent",
			        "Choose Continent",JOptionPane.QUESTION_MESSAGE, null, continentNames, continentNames[0]);
			for(Continent continent: continents) {
				if(continent.getName().equals(selection)) {
					Continent selectedContinent = continent;
					ArrayList<Country> countries = model.getCountries();
					ArrayList<Country> continentCountries = new ArrayList<Country>();
					for(Country country: countries) {
						if(country.getContinentName().equals(selectedContinent.getName())) {
							continentCountries.add(country);
						}
					}
					StackedBarChart.drawStackedBarChartContinent(selectedContinent, continentCountries, "PO");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the cities in countries (population)
	 */
	private class PopCitiesInCountriesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] countryNames = new String[model.getCountries().size()];
			ArrayList<Country> countries = model.getCountries();
			for(int i=0; i<countries.size(); ++i){
				Country country = countries.get(i);
				countryNames[i] = country.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Country",
			        "Choose Country",JOptionPane.QUESTION_MESSAGE, null, countryNames, countryNames[0]);
			for(Country country: countries) {
				if(country.getName().equals(selection)) {
					Country selectedCountry = country;
					ArrayList<City> cities = model.getCities();
					ArrayList<City> countryCities = new ArrayList<City>();
					for(City city: cities) {
						if(city.getCountryName().equals(selectedCountry.getName())) {
							countryCities.add(city);
						}
					}
					StackedBarChart.drawStackedBarChartCountry(selectedCountry, countryCities, "PO");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the places in continents(population)
	 */
	private class PopPlacesInContinentsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] continentNames = new String[model.getContinents().size()];
			ArrayList<Continent> continents = model.getContinents();
			for(int i=0; i<continents.size(); ++i){
				Continent continent = continents.get(i);
				continentNames[i] = continent.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Continent",
			        "Choose Continent",JOptionPane.QUESTION_MESSAGE, null, continentNames, continentNames[0]);
			for(Continent continent: continents) {
				if(continent.getName().equals(selection)) {
					Continent selectedContinent = continent;
					ArrayList<PlaceOfInterest> places = model.getPlacesOfInterest();
					ArrayList<PlaceOfInterest> continentPlaces = new ArrayList<PlaceOfInterest>();
					for(PlaceOfInterest place: places) {
						if(place.getLocations().contains(selectedContinent.getName())) {
							continentPlaces.add(place);
						}
					}
					StackedBarChart.drawSegmentedBarChartPlacesInContinents(selectedContinent, continentPlaces, "PO");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the places in countries (population)
	 */
	private class PopPlacesInCountriesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] countryNames = new String[model.getCountries().size()];
			ArrayList<Country> countries = model.getCountries();
			for(int i=0; i<countries.size(); ++i){
				Country country = countries.get(i);
				countryNames[i] = country.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Country",
			        "Choose Country",JOptionPane.QUESTION_MESSAGE, null, countryNames, countryNames[0]);
			for(Country country: countries) {
				if(country.getName().equals(selection)) {
					Country selectedCountry = country;
					ArrayList<PlaceOfInterest> places = model.getPlacesOfInterest();
					ArrayList<PlaceOfInterest> continentPlaces = new ArrayList<PlaceOfInterest>();
					for(PlaceOfInterest place: places) {
						if(place.getLocations().contains(selectedCountry.getName())) {
							continentPlaces.add(place);
						}
					}
					StackedBarChart.drawSegmentedBarChartPlacesInCountries(selectedCountry, continentPlaces, "PO");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the places in cities (population)
	 */
	private class PopPlacesInCitiesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] cityNames = new String[model.getCities().size()];
			ArrayList<City> cities = model.getCities();
			for(int i=0; i<cities.size(); ++i){
				City city = cities.get(i);
				cityNames[i] = city.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a city",
			        "Choose city",JOptionPane.QUESTION_MESSAGE, null, cityNames, cityNames[0]);
			for(City city: cities) {
				if(city.getName().equals(selection)) {
					City selectedcity = city;
					ArrayList<PlaceOfInterest> places = model.getPlacesOfInterest();
					ArrayList<PlaceOfInterest> continentPlaces = new ArrayList<PlaceOfInterest>();
					for(PlaceOfInterest place: places) {
						if(place.getLocations().contains(selectedcity.getName())) {
							continentPlaces.add(place);
						}
					}
					StackedBarChart.drawSegmentedBarChartPlacesInCities(selectedcity, continentPlaces, "PO");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the countries in continents (area)
	 */
	private class AreaCountriesInContinentsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] continentNames = new String[model.getContinents().size()];
			ArrayList<Continent> continents = model.getContinents();
			for(int i=0; i<continents.size(); ++i){
				Continent continent = continents.get(i);
				continentNames[i] = continent.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Continent",
			        "Choose Continent",JOptionPane.QUESTION_MESSAGE, null, continentNames, continentNames[0]);
			for(Continent continent: continents) {
				if(continent.getName().equals(selection)) {
					Continent selectedContinent = continent;
					ArrayList<Country> countries = model.getCountries();
					ArrayList<Country> continentCountries = new ArrayList<Country>();
					for(Country country: countries) {
						if(country.getContinentName().equals(selectedContinent.getName())) {
							continentCountries.add(country);
						}
					}
					StackedBarChart.drawStackedBarChartContinent(selectedContinent, continentCountries, "AR");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the cities in countries(area)
	 */
	private class AreaCitiesInCountriesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] countryNames = new String[model.getCountries().size()];
			ArrayList<Country> countries = model.getCountries();
			for(int i=0; i<countries.size(); ++i){
				Country country = countries.get(i);
				countryNames[i] = country.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Country",
			        "Choose Country",JOptionPane.QUESTION_MESSAGE, null, countryNames, countryNames[0]);
			for(Country country: countries) {
				if(country.getName().equals(selection)) {
					Country selectedCountry = country;
					ArrayList<City> cities = model.getCities();
					ArrayList<City> countryCities = new ArrayList<City>();
					for(City city: cities) {
						if(city.getCountryName().equals(selectedCountry.getName())) {
							countryCities.add(city);
						}
					}
					StackedBarChart.drawStackedBarChartCountry(selectedCountry, countryCities, "AR");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the places in continents (area)
	 */
	private class AreaPlacesInContinentsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] continentNames = new String[model.getContinents().size()];
			ArrayList<Continent> continents = model.getContinents();
			for(int i=0; i<continents.size(); ++i){
				Continent continent = continents.get(i);
				continentNames[i] = continent.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Continent",
			        "Choose Continent",JOptionPane.QUESTION_MESSAGE, null, continentNames, continentNames[0]);
			for(Continent continent: continents) {
				if(continent.getName().equals(selection)) {
					Continent selectedContinent = continent;
					ArrayList<PlaceOfInterest> places = model.getPlacesOfInterest();
					ArrayList<PlaceOfInterest> continentPlaces = new ArrayList<PlaceOfInterest>();
					for(PlaceOfInterest place: places) {
						if(place.getLocations().contains(selectedContinent.getName())) {
							continentPlaces.add(place);
						}
					}
					StackedBarChart.drawSegmentedBarChartPlacesInContinents(selectedContinent, continentPlaces, "AR");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the places in countries (area)
	 */
	private class AreaPlacesInCountriesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] countryNames = new String[model.getCountries().size()];
			ArrayList<Country> countries = model.getCountries();
			for(int i=0; i<countries.size(); ++i){
				Country country = countries.get(i);
				countryNames[i] = country.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Country",
			        "Choose Country",JOptionPane.QUESTION_MESSAGE, null, countryNames, countryNames[0]);
			for(Country country: countries) {
				if(country.getName().equals(selection)) {
					Country selectedCountry = country;
					ArrayList<PlaceOfInterest> places = model.getPlacesOfInterest();
					ArrayList<PlaceOfInterest> continentPlaces = new ArrayList<PlaceOfInterest>();
					for(PlaceOfInterest place: places) {
						if(place.getLocations().contains(selectedCountry.getName())) {
							continentPlaces.add(place);
						}
					}
					StackedBarChart.drawSegmentedBarChartPlacesInCountries(selectedCountry, continentPlaces, "AR");
				}
			}
		}
	}
	
	/**
	 * provides a listener for the places in cities (area)
	 */
	private class AreaPlacesInCitiesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] cityNames = new String[model.getCities().size()];
			ArrayList<City> cities = model.getCities();
			for(int i=0; i<cities.size(); ++i){
				City city = cities.get(i);
				cityNames[i] = city.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a city",
			        "Choose city",JOptionPane.QUESTION_MESSAGE, null, cityNames, cityNames[0]);
			for(City city: cities) {
				if(city.getName().equals(selection)) {
					City selectedcity = city;
					ArrayList<PlaceOfInterest> places = model.getPlacesOfInterest();
					ArrayList<PlaceOfInterest> continentPlaces = new ArrayList<PlaceOfInterest>();
					for(PlaceOfInterest place: places) {
						if(place.getLocations().contains(selectedcity.getName())) {
							continentPlaces.add(place);
						}
					}
					StackedBarChart.drawSegmentedBarChartPlacesInCities(selectedcity, continentPlaces, "AR");
				}
			}
		}
	}
	
	//This is for the map option
	/**
	 * provides a listener for the cities in countries (map)
	 */
	private class MapCitiesInCountriesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JFrame frame = new JFrame();
			Object[] countryNames = new String[model.getCountries().size()];
			ArrayList<Country> countries = model.getCountries();
			for(int i=0; i<countries.size(); ++i){
				Country country = countries.get(i);
				countryNames[i] = country.getName();
			}
		
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Country",
			        "Choose Country",JOptionPane.QUESTION_MESSAGE, null, countryNames, countryNames[0]);
			for(Country country: countries) {
				if(country.getName().equals(selection)) {
					Country selectedCountry = country;
					ArrayList<City> cities = model.getCities();
					ArrayList<City> countryCities = new ArrayList<City>();
					for(City city: cities) {
						if(city.getCountryName().equals(selectedCountry.getName())) {
							countryCities.add(city);
						}
					}
					Map.drawMapCities(countryCities);
				}
			}
		}
	}
	

	/**
	 * provides a listener for the cities in continents (map)
	 */
	private class MapCitiesInContinentsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] continentNames = new String[model.getContinents().size()];
			ArrayList<Continent> continents = model.getContinents();
			for(int i=0; i<continents.size(); ++i){
				Continent continent = continents.get(i);
				continentNames[i] = continent.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Continent",
					"Choose Continent",JOptionPane.QUESTION_MESSAGE, null, continentNames, continentNames[0]);
			
			for(Continent continent: continents) {
			
				if(continent.getName().equals(selection)) {
					Continent selectedContinent = continent;
					ArrayList<Country> countries = model.getCountries();
					ArrayList<Country> continentCountries = new ArrayList<Country>();
					ArrayList<City> cities = model.getCities();
					ArrayList<City> citiesInContinentCountries = new ArrayList<City>();
					for(Country country: countries) {
					
						if(country.getContinentName().equals(selectedContinent.getName())) {
							continentCountries.add(country);
						}
					}
					for(Country country: continentCountries) {
						for(City city: cities) {
							if(city.getCountryName().equals(country.getName())){
								citiesInContinentCountries.add(city);
							}
					
						}
					}
					Map.drawMapCities(citiesInContinentCountries);
				}
			}
		}
	}
	
	/**
	 * provides a listener for the cities worldwide (map)
	 */
	private class MapCitiesWorldwideListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Map.drawMapCities(model.getCities());
		}
	}
	
	/**
	 * provides a listener for the points in continents (map)
	 */
	private class MapPointsInContinentsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] continentNames = new String[model.getContinents().size()];
			ArrayList<Continent> continents = model.getContinents();
			for(int i=0; i<continents.size(); ++i){
				Continent continent = continents.get(i);
				continentNames[i] = continent.getName();
			}
	
	
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Continent",
					"Choose Continent",JOptionPane.QUESTION_MESSAGE, null, continentNames, continentNames[0]);
			
			for(Continent continent: continents) {
				if(continent.getName().equals(selection)) {
					Continent selectedContinent = continent;
					ArrayList<PointOfInterest> points = model.getPointsOfInterest();
				
					ArrayList<PointOfInterest> pointsOnContinent = new ArrayList<PointOfInterest>();
					for(PointOfInterest point: points) {
						if(point.getRegion().equals(selectedContinent.getName())) {
							pointsOnContinent.add(point);
						}
					}
					Map.drawMapPoints(pointsOnContinent);
				}
			}
	
		}
	}
	
	/**
	 * provides a listener for the points in countries (map)
	 */
	private class MapPointsInCountriesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] countryNames = new String[model.getCountries().size()];
			ArrayList<Country> countries = model.getCountries();
			for(int i=0; i<countries.size(); ++i){
				Country country = countries.get(i);
				countryNames[i] = country.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a Country",
			        "Choose Country",JOptionPane.QUESTION_MESSAGE, null, countryNames, countryNames[0]);
			for(Country country: countries) {
				if(country.getName().equals(selection)) {
					Country selectedCountry = country;
					ArrayList<PointOfInterest> points = model.getPointsOfInterest();
					ArrayList<PointOfInterest> pointsOnContinent = new ArrayList<PointOfInterest>();
					for(PointOfInterest point: points) {
						if(point.getRegion().equals(selectedCountry.getName())) {
						
							pointsOnContinent.add(point);
						}
					}
					Map.drawMapPoints(pointsOnContinent);
				}
			}
		}
	}
	
	/**
	 * provides a listener for the points in cities (map)
	 */
	private class MapPointsInCitiesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			Object[] cityNames = new String[model.getCities().size()];
			ArrayList<City> cities = model.getCities();
			for(int i=0; i<cities.size(); ++i){
				City city = cities.get(i);
				cityNames[i] = city.getName();
			}
		
			String selection = (String) JOptionPane.showInputDialog(frame, "Pick a city",
			        "Choose city",JOptionPane.QUESTION_MESSAGE, null, cityNames, cityNames[0]);
			for(City city: cities) {
				if(city.getName().equals(selection)) {
					City selectedcity = city;
					ArrayList<PointOfInterest> points = model.getPointsOfInterest();
					ArrayList<PointOfInterest> pointsOnContinent = new ArrayList<PointOfInterest>();
					for(PointOfInterest point: points) {
						if(point.getRegion().equals(selectedcity.getName())) {
							pointsOnContinent.add(point);
						}
					}
					Map.drawMapPoints(pointsOnContinent);
				}
			}

		}
	}
	
	/**
	 * provides a listener for the points worldwide (map)
	 */
	private class MapPointsWorldwideListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Map.drawMapPoints(model.getPointsOfInterest());
		}

	}
	
	private class ListNeighborhoodListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Region> regions = new ArrayList<Region>();
			for (City city : model.getCities()) {
				if (city.hasLocation())
					regions.add((Region)city);
			}
			for (PointOfInterest point : model.getPointsOfInterest()) {
				if (point.hasLocation())
					regions.add((Region)point);
			}
			JFrame frame = new JFrame();
			Object[] regionNames = new String[regions.size()];
			for(int i=0; i<regions.size(); ++i){
				regionNames[i] = regions.get(i).getName();
			}
			
			String selection = (String) JOptionPane.showInputDialog(frame, "Choose",
			        "Pick a City or Point",JOptionPane.QUESTION_MESSAGE, null, regionNames, regionNames[0]);
			Region region = null;
			for (Region r : regions) {
				if (r.getName().equals(selection)) {
					region = r;
				}
			}
			
			JTextField xField = new JTextField(5);
		    JTextField yField = new JTextField(5);

		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("length:"));
		    myPanel.add(xField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("bredth:"));
		    myPanel.add(yField);
		    
		    int length = 0;
		    int breadth = 0;
		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	length = Integer.parseInt(xField.getText());
		    	breadth = Integer.parseInt(yField.getText());
		    }
		    ArrayList<Region> neighborhood = Region.findNeighborhood(breadth, length, region, regions);
		    Object[] hoodNames = new String[neighborhood.size()];
		    for (int i = 0; i< hoodNames.length; i++) {
		    	hoodNames[i] = neighborhood.get(i).getName();
		    }
		    JOptionPane.showMessageDialog(null, hoodNames, selection + "'s neighbors", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private class ListNeighborhoodRecursivelyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Region> regions = new ArrayList<Region>();
			for (City city : model.getCities()) {
				if (city.hasLocation())
					regions.add((Region)city);
			}
			for (PointOfInterest point : model.getPointsOfInterest()) {
				if (point.hasLocation())
					regions.add((Region)point);
			}
			JFrame frame = new JFrame();
			Object[] regionNames = new String[regions.size()];
			for(int i=0; i<regions.size(); ++i){
				regionNames[i] = regions.get(i).getName();
			}
			
			String selection = (String) JOptionPane.showInputDialog(frame, "Choose",
			        "Pick a City or Point",JOptionPane.QUESTION_MESSAGE, null, regionNames, regionNames[0]);
			Region region = null;
			for (Region r : regions) {
				if (r.getName().equals(selection)) {
					region = r;
				}
			}
			
			JTextField xField = new JTextField(5);
		    JTextField yField = new JTextField(5);

		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("length:"));
		    myPanel.add(xField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("bredth:"));
		    myPanel.add(yField);
		    
		    int length = 0;
		    int breadth = 0;
		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	length = Integer.parseInt(xField.getText());
		    	breadth = Integer.parseInt(yField.getText());
		    }
		    ArrayList<Region> neighborhood = Region.findNeighborhoodRecursively(breadth, length, region, regions);
		    Object[] hoodNames = new String[neighborhood.size()];
		    for (int i = 0; i< hoodNames.length; i++) {
		    	hoodNames[i] = neighborhood.get(i).getName();
		    }
		    JOptionPane.showMessageDialog(null, hoodNames, selection + "'s neighbors", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private class CheckNeighborhoodListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Region> regions = new ArrayList<Region>();
			for (City city : model.getCities()) {
				if (city.hasLocation())
					regions.add((Region)city);
			}
			for (PointOfInterest point : model.getPointsOfInterest()) {
				if (point.hasLocation())
					regions.add((Region)point);
			}
			JFrame frame = new JFrame();
			Object[] regionNames = new String[regions.size()];
			for(int i=0; i<regions.size(); ++i){
				regionNames[i] = regions.get(i).getName();
			}
			
			String selection1 = (String) JOptionPane.showInputDialog(frame, "Choose",
			        "Pick a City or Point",JOptionPane.QUESTION_MESSAGE, null, regionNames, regionNames[0]);
			String selection2 = (String) JOptionPane.showInputDialog(frame, "Choose",
			        "Pick a City or Point",JOptionPane.QUESTION_MESSAGE, null, regionNames, regionNames[0]);
			Region region1 = null;
			for (Region r : regions) {
				if (r.getName().equals(selection1)) {
					region1 = r;
				}
			}
			Region region2 = null;
			for (Region r : regions) {
				if (r.getName().equals(selection2)) {
					region2 = r;
				}
			}
			
			JTextField xField = new JTextField(5);
		    JTextField yField = new JTextField(5);

		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("length:"));
		    myPanel.add(xField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("bredth:"));
		    myPanel.add(yField);
		    
		    int length = 0;
		    int breadth = 0;
		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	length = Integer.parseInt(xField.getText());
		    	breadth = Integer.parseInt(yField.getText());
		    }
		    ArrayList<Region> neighborhood1 = Region.findNeighborhood(breadth, length, region1, regions);
		    if (neighborhood1.contains(region2)){
		    	JOptionPane.showMessageDialog(selectionView, region1.getName() + " and " + region2.getName() + " are neighbors");
		    }
		    else {
		    	JOptionPane.showMessageDialog(selectionView, region1.getName() + " and " + region2.getName() + " are not neighbors");
		    }
		}
	}
	
	private class CheckNeighborhoodRecursivelyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Region> regions = new ArrayList<Region>();
			for (City city : model.getCities()) {
				if (city.hasLocation())
					regions.add((Region)city);
			}
			for (PointOfInterest point : model.getPointsOfInterest()) {
				if (point.hasLocation())
					regions.add((Region)point);
			}
			JFrame frame = new JFrame();
			Object[] regionNames = new String[regions.size()];
			for(int i=0; i<regions.size(); ++i){
				regionNames[i] = regions.get(i).getName();
			}
			
			String selection1 = (String) JOptionPane.showInputDialog(frame, "Choose",
			        "Pick a City or Point",JOptionPane.QUESTION_MESSAGE, null, regionNames, regionNames[0]);
			String selection2 = (String) JOptionPane.showInputDialog(frame, "Choose",
			        "Pick a City or Point",JOptionPane.QUESTION_MESSAGE, null, regionNames, regionNames[0]);
			Region region1 = null;
			for (Region r : regions) {
				if (r.getName().equals(selection1)) {
					region1 = r;
				}
			}
			Region region2 = null;
			for (Region r : regions) {
				if (r.getName().equals(selection2)) {
					region2 = r;
				}
			}
			
			JTextField xField = new JTextField(5);
		    JTextField yField = new JTextField(5);

		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("length:"));
		    myPanel.add(xField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("bredth:"));
		    myPanel.add(yField);
		    
		    int length = 0;
		    int breadth = 0;
		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	length = Integer.parseInt(xField.getText());
		    	breadth = Integer.parseInt(yField.getText());
		    }
		    ArrayList<Region> neighborhood1 = Region.findNeighborhoodRecursively(breadth, length, region1, regions);
		    
		    if (neighborhood1.contains(region2)){
		    	JOptionPane.showMessageDialog(selectionView, region1.getName() + " and " + region2.getName() + " are neighbors");
		    }
		    else {
		    	JOptionPane.showMessageDialog(selectionView, region1.getName() + " and " + region2.getName() + " are not neighbors");
		    }
		    
		}
	}
	
	private class MapNeighborhoodListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Region> regions = new ArrayList<Region>();
			for (City city : model.getCities()) {
				if (city.hasLocation())
					regions.add((Region)city);
			}
			for (PointOfInterest point : model.getPointsOfInterest()) {
				if (point.hasLocation())
					regions.add((Region)point);
			}
			JFrame frame = new JFrame();
			Object[] regionNames = new String[regions.size()];
			for(int i=0; i<regions.size(); ++i){
				regionNames[i] = regions.get(i).getName();
			}
			
			String selection = (String) JOptionPane.showInputDialog(frame, "Choose",
			        "Pick a City or Point",JOptionPane.QUESTION_MESSAGE, null, regionNames, regionNames[0]);
			Region region = null;
			for (Region r : regions) {
				if (r.getName().equals(selection)) {
					region = r;
				}
			}
			
			JTextField xField = new JTextField(5);
		    JTextField yField = new JTextField(5);

		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("length:"));
		    myPanel.add(xField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("bredth:"));
		    myPanel.add(yField);
		    
		    int length = 0;
		    int breadth = 0;
		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	length = Integer.parseInt(xField.getText());
		    	breadth = Integer.parseInt(yField.getText());
		    }
		    ArrayList<Region> neighborhood = Region.findNeighborhood(breadth, length, region, regions);
		    Object[] hoodNames = new String[neighborhood.size()];
		    for (int i = 0; i< hoodNames.length; i++) {
		    	hoodNames[i] = neighborhood.get(i).getName();
		    }
		    Map.drawMapRegions(neighborhood, length, breadth);
		}
	}
	
	private class MapNeighborhoodRecursivelyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Region> regions = new ArrayList<Region>();
			for (City city : model.getCities()) {
				if (city.hasLocation())
					regions.add((Region)city);
			}
			for (PointOfInterest point : model.getPointsOfInterest()) {
				if (point.hasLocation())
					regions.add((Region)point);
			}
			JFrame frame = new JFrame();
			Object[] regionNames = new String[regions.size()];
			for(int i=0; i<regions.size(); ++i){
				regionNames[i] = regions.get(i).getName();
			}
			
			String selection = (String) JOptionPane.showInputDialog(frame, "Choose",
			        "Pick a City or Point",JOptionPane.QUESTION_MESSAGE, null, regionNames, regionNames[0]);
			Region region = null;
			for (Region r : regions) {
				if (r.getName().equals(selection)) {
					region = r;
				}
			}
			
			JTextField xField = new JTextField(5);
		    JTextField yField = new JTextField(5);

		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("length:"));
		    myPanel.add(xField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("bredth:"));
		    myPanel.add(yField);
		    
		    int length = 0;
		    int breadth = 0;
		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	length = Integer.parseInt(xField.getText());
		    	breadth = Integer.parseInt(yField.getText());
		    }
		    ArrayList<Region> neighborhood = Region.findNeighborhoodRecursively(breadth, length, region, regions);
		    Object[] hoodNames = new String[neighborhood.size()];
		    for (int i = 0; i< hoodNames.length; i++) {
		    	hoodNames[i] = neighborhood.get(i).getName();
		    }
		    Map.drawMapRegions(neighborhood, length, breadth);
		}
	}
	
	/**
	 * This method sets the model.
	 * @param newModel The new model
	 */
	public void setModel(RegionModel newModel) {
		this.model = newModel;
	}
	
	/**
	 * This method sets the main selectionView
	 * @param selectionView
	 */
	public void setView(SelectionView selectionView) {
		// Set the master view.
		this.selectionView = selectionView;
		//register all of the listeners to the 15 add/edit/delete buttons of the selectionView:
		//continents
		this.selectionView.continentPanel.addButton().addActionListener(new AddContinentListener());
		this.selectionView.continentPanel.editButton().addActionListener(new EditContinentListener());
		this.selectionView.continentPanel.deleteButton().addActionListener(new DeleteContinentListener());
		//countries
		this.selectionView.countryPanel.addButton().addActionListener(new AddCountryListener());
		this.selectionView.countryPanel.editButton().addActionListener(new EditCountryListener());
		this.selectionView.countryPanel.deleteButton().addActionListener(new DeleteCountryListener());
		//cities
		this.selectionView.cityPanel.addButton().addActionListener(new AddCityListener());
		this.selectionView.cityPanel.editButton().addActionListener(new EditCityListener());
		this.selectionView.cityPanel.deleteButton().addActionListener(new DeleteCityListener());
		//places of interest
		this.selectionView.placePanel.addButton().addActionListener(new AddPlaceOfInterestListener());
		this.selectionView.placePanel.editButton().addActionListener(new EditPlaceOfInterestListener());
		this.selectionView.placePanel.deleteButton().addActionListener(new DeletePlaceOfInterestListener());
		//points of interest
		this.selectionView.pointPanel.addButton().addActionListener(new AddPointOfInterestListener());
		this.selectionView.pointPanel.editButton().addActionListener(new EditPointOfInterestListener());
		this.selectionView.pointPanel.deleteButton().addActionListener(new DeletePointOfInterestListener());
		//register all of the listeners for the menu bar items
		this.selectionView.loadGeo.addActionListener(new LoadGeographyListener());
		this.selectionView.loadGeo.addActionListener(new EnableAllButtonsListener());
		this.selectionView.importGeo.addActionListener(new ImportGeographyListener());
		this.selectionView.saveGeo.addActionListener(new SaveGeographyListener());
		this.selectionView.exportGeo.addActionListener(new ExportGeographyListener());
		this.selectionView.continents.addActionListener(new PopContinentsListener());
		this.selectionView.countries.addActionListener(new PopCountriesListener());
		this.selectionView.cities.addActionListener(new PopCitiesListener());
		this.selectionView.places.addActionListener(new PopPlacesListener());
		this.selectionView.continentsA.addActionListener(new AreaContinentsListener());
		this.selectionView.countriesA.addActionListener(new AreaCountriesListener());
		this.selectionView.citiesA.addActionListener(new AreaCitiesListener());
		this.selectionView.placesA.addActionListener(new AreaPlacesListener());
		this.selectionView.countriesInContinents.addActionListener(new PopCountriesInContinentsListener());
		this.selectionView.citiesInCountries.addActionListener(new PopCitiesInCountriesListener());
		this.selectionView.placesInContinents.addActionListener(new PopPlacesInContinentsListener());
		this.selectionView.placesInCountries.addActionListener(new PopPlacesInCountriesListener());
		this.selectionView.placesInCities.addActionListener(new PopPlacesInCitiesListener());
		this.selectionView.countriesInContinentsA.addActionListener(new AreaCountriesInContinentsListener());
		this.selectionView.citiesInCountriesA.addActionListener(new AreaCitiesInCountriesListener());
		this.selectionView.placesInContinentsA.addActionListener(new AreaPlacesInContinentsListener());
		this.selectionView.placesInCountriesA.addActionListener(new AreaPlacesInCountriesListener());
		this.selectionView.placesInCitiesA.addActionListener(new AreaPlacesInCitiesListener());
		this.selectionView.citiesInCountriesM.addActionListener(new MapCitiesInCountriesListener());
		this.selectionView.citiesInContinentsM.addActionListener(new MapCitiesInContinentsListener());
		this.selectionView.citiesWorldwideM.addActionListener(new MapCitiesWorldwideListener());
		this.selectionView.pointsInCitiesM.addActionListener(new MapPointsInCitiesListener());
		this.selectionView.pointsInCountriesM.addActionListener(new MapPointsInCountriesListener());
		this.selectionView.pointsInContinentsM.addActionListener(new MapPointsInContinentsListener());
		this.selectionView.pointsWorldwideM.addActionListener(new MapPointsWorldwideListener());
		this.selectionView.hoodList.addActionListener(new ListNeighborhoodListener());
		this.selectionView.recursiveHoodList.addActionListener(new ListNeighborhoodRecursivelyListener());
		this.selectionView.hoodCheck.addActionListener(new CheckNeighborhoodListener());
		this.selectionView.recursiveHoodCheck.addActionListener(new CheckNeighborhoodRecursivelyListener());
		this.selectionView.hoodMap.addActionListener(new MapNeighborhoodListener());
		this.selectionView.recursiveHoodMap.addActionListener(new MapNeighborhoodRecursivelyListener());
	}
	
	/**
	 * This method sets all of the dialog boxes
	 * @param addContinent the add Continent dialog to be set
	 * @param editContinent the edit Continent dialog to be set
	 * @param addCountry the add Country dialog to be set
	 * @param editCountry the editCountry dialog to be set
	 * @param addCity the add City dialog to be set
	 * @param editCity the edit City dialog to be set
	 * @param addPlace the add Place dialog to be set
	 * @param editPlace the edit Place dialog to be set
	 * @param addPoint the add Point dialog to be set
	 * @param editPoint the edit Point dialog to be set
	 */
	public void setDialogBoxes(DialogBox addContinent, DialogBox editContinent, DialogBox addCountry, 
			DialogBox editCountry, DialogBox addCity, DialogBox editCity, DialogBox addPlace, 
			DialogBox editPlace, DialogBox addPoint, DialogBox editPoint) {
		this.addContinent = addContinent;
		this.editContinent = editContinent;
		this.addCountry = addCountry;
		this.editCountry = editCountry;
		this.addCity = addCity;
		this.editCity = editCity;
		this.addPlace = addPlace;
		this.editPlace = editPlace;
		this.addPoint = addPoint;
		this.editPoint = editPoint;
	}
	
//	/**
//	 * This method sets the FileIODialog
//	 * @param open the open FileIODialog
//	 * @param save the save FileIODialog
//	 */
//	public void setFileIODialogBoxes(FileIODialog open, FileIODialog save) {
//		this.open = open;
//		this.save = save;
//		//register the listeners for the open/save buttons
//		this.open.jbtOpen.addActionListener(new GetSelectedFileNameListener());
//		this.save.jbtSave.addActionListener(new GetSelectedFileNameListener());
//	}
	
	/**
	 * This method gets the model.
	 * @return The model.
	 */
	public RegionModel getModel() {
		return model;
	}
}