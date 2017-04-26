
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * Project #4
 * CS 2334, Section 10 (Lab 11)
 * March 4, 2014
 * <P>
 * This program allows a user to manipulate and save various Geographic Regions (Driver).
 * </P>
 * @version 0.1
 */
public class GeoGrapher {
	/**The model*/
	private RegionModel model; 
	/**The main view*/
	private SelectionView selectionView = new SelectionView();
	/**The controller for the model and all of the views*/
	private RegionController controller = new RegionController();
	/**The DialogBox to add a continent*/
	private DialogBox addContinent = new DialogBox();
	/**The DialogBox to edit a continent*/
	private DialogBox editContinent = new DialogBox();
	/**The DialogBox to add a country*/
	private DialogBox addCountry = new DialogBox();
	/**The DialogBox to edit a country*/
	private DialogBox editCountry = new DialogBox();
	/**The DialogBox to add a city*/
	private DialogBox addCity = new DialogBox();
	/**The DialogBox to edit a city*/
	private DialogBox editCity = new DialogBox();
	/**The DialogBox to add a place of interest*/
	private DialogBox addPlace = new DialogBox();
	/**The DialogBox to edit a place of interest*/
	private DialogBox editPlace = new DialogBox();
	/**The DialogBox to add a point point of interest*/
	private DialogBox addPoint = new DialogBox();
	/**The DialogBox to edit a point of interest*/
	private DialogBox editPoint = new DialogBox();
//	/**The FileIODialog to open a file*/
//	private FileIODialog open = new FileIODialog();
//	/**The FileIODialog to save to a file*/
//	private FileIODialog save = new FileIODialog();
	
	public GeoGrapher(String continentsFileName, String countriesFileName, String citiesFileName,
			String placesFileName, String pointsFileName) {
		this.model = new RegionModel(FileIO.readContinentFile(continentsFileName), 
				FileIO.readCountryFile(countriesFileName), FileIO.readCityFile(citiesFileName),
				FileIO.readPlaceFile(placesFileName), FileIO.readPointFile(pointsFileName));
		selectionView.setModel(model);
		controller.setModel(model);
		controller.setView(selectionView);
		controller.setDialogBoxes(addContinent, editContinent, addCountry, editCountry, addCity,
				editCity, addPlace, editPlace, addPoint, editPoint);
//		controller.setFileIODialogBoxes(open, save);
	}
	/**
	 * This is the main method for the program
	 * @param args The program arguments
	 */
	public static void main(String[] args){
		/*try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(UnsupportedLookAndFeelException e) {
			System.out.println("oops");
		}
		catch(IllegalAccessException e) {
			System.out.println("oops");
		}
		catch(InstantiationException e) {
			System.out.println("oops");
		}
		catch(ClassNotFoundException e) {
			System.out.println("oops");
		}*/
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		if (args.length > 0) {
			new GeoGrapher(args[0], args[1], args[2], args[3], args[4]);
		} else {
			System.out.println("Usage: Please specify the file names as the arguments.");
		}
	}//End of main method
}
