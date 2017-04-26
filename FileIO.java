import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project #3
 * CS 2334, Section 10 (Lab 11)
 * March 4, 2014
 * <P>
 * This is a helper class for inputting and outputting information
 *  about various Geographic Regions
 * </P>
 * @version 1.0
 */
public class FileIO implements Serializable {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3229282457158260208L;

	/**
	 * This method reads the file of cities, and returns the LinkedHashMap of the cities.
	 * @param fileName The name of the file.
	 */
	public static ArrayList<City> readCityFile(String fileName) {
		/*This is where all of the cities will be stored*/
		ArrayList<City> cities = new ArrayList<City>();
		BufferedReader br;
		/*Read in the file, ensuring the file is found.*/
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
		}
		catch(FileNotFoundException e) {
			System.out.println("There was no file named " + fileName); 
			return null;
		}
		
		/*Read through the file.*/
		String nextLine;
		do {
			String[] lineData;
			try{
				nextLine = br.readLine();
				lineData = nextLine.split(",");
			}catch(IOException e){break;}
			catch(NullPointerException e) {break;}
			
			/*Create a city without location information*/
			if (lineData.length == 4) {
				cities.add( new City(lineData[0].trim(), Long.parseLong(lineData[1].replaceAll("\\s+","")), 
						Float.parseFloat(lineData[2].replaceAll("\\s+","")), lineData[3].replaceFirst("\\s+", "")));
			}
			
			/*Create a city with location information*/
			else if (lineData.length == 7) {
				cities.add( new City(lineData[0].trim(), Long.parseLong(lineData[1].replaceAll("\\s+","")), 
						Float.parseFloat(lineData[2].replaceAll("\\s+","")), lineData[3].replaceFirst("\\s+", ""),
						lineData[4].replaceAll("\\s+",""), lineData[5].replaceAll("\\s+",""), 
						Integer.parseInt(lineData[6].replaceAll("\\s+",""))));
			}
		}while(nextLine != null);
		
		try {
			br.close();//Close bufferedReader
		}
		catch (IOException e) {
			System.out.println("The city file could not be closed.");
		}
		cities.trimToSize();//Trim the array to size.
		return cities;
	}
	
	/**
	 * This method reads the file of countries, and returns the LinkedHashMap of the Countries.
	 * @param fileName The name of the file.
	 */
	public static ArrayList<Country> readCountryFile(String fileName) {
		ArrayList<Country> countries = new ArrayList<Country>();
		BufferedReader br;
		/*Read in the file, ensuring the file is found.*/
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
		}
		catch(FileNotFoundException e) {
			System.out.println("There was no file named " + fileName); 
			return null;
		}
		
		/*Read through the file.*/
		String nextLine;
		do {
			String[] lineData;
			try{
				nextLine = br.readLine();
				lineData = nextLine.split(",");
			}catch(IOException e){break;}
			catch(NullPointerException e) {break;}
			
			/*Create Country objects that have commas and spaces in their name.*/
			if (lineData.length == 5) {
				countries.add(new Country((lineData[1] + " " + lineData[0]).trim(), Long.parseLong(lineData[2]), 
						Float.parseFloat(lineData[3]), lineData[4]));
			}
			/*Create Country objects.*/
			else if (lineData.length == 4) {
				countries.add(new Country(lineData[0].trim(), Long.parseLong(lineData[1]), 
						Float.parseFloat(lineData[2]), lineData[3]));
			}
		}while(nextLine != null);
		
		try {
			br.close();//Close bufferedReader
		}
		catch (IOException e) {
			System.out.println("The country file could not be closed.");
		}
		countries.trimToSize();//Trim the array to size
		return countries;
	}
	
	/**
	 * This method reads the file of continents, and returns the LinkedHashMap of Continents.
	 * @param fileName The name of the file.
	 */
	public static ArrayList<Continent> readContinentFile(String fileName) {
		ArrayList<Continent> continents = new ArrayList<Continent>();
		BufferedReader br;
		/*Read in the file, ensuring the file is found.*/
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
		}
		catch(FileNotFoundException e) {
			System.out.println("There was no file named " + fileName); 
			return null;
		}
		/*Read through the file.*/
		String nextLine;
		do {
			String[] lineData;
			try{
				nextLine = br.readLine();
				lineData = nextLine.split(",");
			}catch(IOException e){break;}
			catch(NullPointerException e) {break;}
			Continent temp = new Continent( lineData[0].trim(), Long.parseLong(lineData[1].replaceAll("\\s+","")),
					Float.parseFloat(lineData[2].replaceAll("\\s+","")) );
			continents.add(temp);
		}while(nextLine != null);
		
		try {
			br.close();//Close bufferedReader
		}
		catch (IOException e) {
			System.out.println("The continent file could not be closed.");
		}
		continents.trimToSize();//Trim the array to size
		return continents;
	}
	
	/**
	 * This method reads the file of continents, and returns the LinkedHashMap of Continents.
	 * @param fileName The name of the file.
	 */
	public static ArrayList<PlaceOfInterest> readPlaceFile(String fileName) {
		ArrayList<PlaceOfInterest> placesOfInterest = new ArrayList<PlaceOfInterest>();
		BufferedReader br;
		/*Read in the file, ensuring the file is found*/
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
		}
		catch(FileNotFoundException e) {
			System.out.println("There was no file named " + fileName);
			return null;
		}
		
		String nextLine;
		do {
			String[] lineData;
			try{
				nextLine = br.readLine();
				lineData = nextLine.split(",");
			}catch(IOException e){break;}
			catch(NullPointerException e) {break;}
			PlaceOfInterest temp = new PlaceOfInterest(lineData[0].trim(),
					Float.parseFloat(lineData[1].replaceAll("\\s+","")),
					lineData[2].replaceAll("\\s+",""));
			for (int i = 3; i<lineData.length; i++) {
				temp.addLocation(lineData[i]);
			}
			placesOfInterest.add(temp);
		}while(nextLine != null);
		try {
			br.close();//Close bufferedReader
		}
		catch (IOException e) {
			System.out.println("The place file could not be closed.");
		}
		return placesOfInterest;
	}
	
	/**
	 * This method reads the file of points, and returns the arraylist of points.
	 * @param fileName The name of the file.
	 * @return the points of interest
	 */
	public static ArrayList<PointOfInterest> readPointFile(String fileName) {
		ArrayList<PointOfInterest> pointsOfInterest = new ArrayList<PointOfInterest>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
		}
		catch(FileNotFoundException e) {
			System.out.println("There was no file named " + fileName);
		}
		
		String nextLine = null;
		do {
			String[] lineData = null;
			try{
				nextLine = br.readLine();
				lineData = nextLine.split(",");
			}
			catch(IOException e) {
				System.out.println("There was a problem reading the file " + fileName);
			}
			catch(NullPointerException e) {break;}
			/*PointOfInterest(String name, String description, String latitude, String longitude, Integer elevation, 
			GeographicRegion region)*/
			PointOfInterest temp = new PointOfInterest(lineData[0].trim(), lineData[1], lineData[2].replaceAll("\\s+", ""),
					lineData[3].replaceAll("\\s+", ""), Integer.parseInt(lineData[4].replaceAll("\\s+", "")),
					lineData[5].trim());
			pointsOfInterest.add(temp);
		}while (nextLine != null);
		try {
			br.close();//Close bufferedReader
		}
		catch (IOException e) {
			System.out.println("The point file could not be closed.");
		}
		return pointsOfInterest;
	}
	
	/**
	 * This method adds all of the cities to their respective countries, and adds all of the countries to their respective continents
	 * @param cities The cities to be added to their respective countries
	 * @param countries The countries to be added to their respective continents
	 * @param continents The continents
	 */
	public static void aggregate(ArrayList<Continent> continents, ArrayList<Country> countries,
			ArrayList<City> cities) {
		/*Puts city objects into the correct country ArrayList.*/
		for(City city: cities)
		{
			for(Country country : countries)
			{
				if(city.getCountryName().equalsIgnoreCase(country.getName()))
				{
					country.addCity(city);
				}//end of if statement
			}//end of inner loop
		}//end of outer loop
		
		/* Puts country objects into the correct continent ArrayList.*/
		for(Country country: countries)
		{
			for(Continent continent : continents)
			{
				if(country.getContinentName().equalsIgnoreCase(continent.getName()))
				{
					continent.addCountry(country);
				}//end of if statement
			}//end of inner for loop
		}//end of outer for loop
	}
	
	/**
	 * This method prints information to a text file.
	 * @param model The information.
	 * @param fileNames The names of the files.
	 */
	public static void export(RegionModel model, String[] fileNames) {
		/*This is the same as last project*/
		String string = "";
		/*Get the name of the Geographic Region.*/
		for (Continent continent : model.getContinents()) {
			string += continent.toString() + "\n";
		}
		try {
			FileWriter outfile = new FileWriter(fileNames[0]);
			BufferedWriter bw = new BufferedWriter(outfile);
			bw.write(string);//Write the file
			bw.newLine();
			bw.close();//Close bufferedWriter
			
			string = "";
			for (Country country : model.getCountries()) {
				string += country.toString() + "\n";
			}
			outfile = new FileWriter(fileNames[1]);
			bw = new BufferedWriter(outfile);
			bw.write(string);//Write the file
			bw.newLine();
			bw.close();//Close bufferedWriter
			string = "";
			for (City city : model.getCities()) {
				string += city.toString() + "\n";
			}
			outfile = new FileWriter(fileNames[2]);
			bw = new BufferedWriter(outfile);
			bw.write(string);//Write the file
			bw.newLine();
			bw.close();//Close bufferedWriter
			string = "";
			for (PlaceOfInterest place : model.getPlacesOfInterest()) {
				string += place.toString() + "\n";
			}
			outfile = new FileWriter(fileNames[3]);
			bw = new BufferedWriter(outfile);
			bw.write(string);//Write the file
			bw.newLine();
			bw.close();//Close bufferedWriter
			string = "";
			for (PointOfInterest point : model.getPointsOfInterest()) {
				string += point.toString() + "\n";
			}
			outfile = new FileWriter(fileNames[4]);
			bw = new BufferedWriter(outfile);
			bw.write(string);//Write the file
			bw.newLine();
			bw.close();//Close bufferedWriter
		}
		catch(IOException e) {
			System.out.println("fileio 351: error writing to files");
		}
	}
	
	/**
	 * This method prints all of the Geographic information to a binary file.
	 * @param model The model.
	 * @param fileName The name of the file.
	 */
	public static void save(RegionModel model, String fileName) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(model);
			objectOutputStream.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(fileName + " could not be found");
		}
		catch(IOException e) {
			System.out.println("The model could not be saved: " + e.getMessage());
		}
	}
	
	/**
	 * This method loads
	 * @param fileName the fileName
	 * @return the model
	 */
	public static RegionModel load(String fileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			RegionModel model = (RegionModel)objectInputStream.readObject();
			objectInputStream.close();
			
			return model;
		}
		catch(ClassCastException e) {
			System.out.println("class cast exception: " + e.getMessage());
			return null;
		}
		catch (ClassNotFoundException e) {
			System.out.println("there was something wrong with the data.");
			return null;
		}
		catch (FileNotFoundException e) {
			System.out.println("I could not find the file to load from (" + fileName + ").");
			return null;
		}
		catch (IOException e) {
			System.out.println("there was something wrong with the file:" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * imports
	 * @return the model
	 */
	public static RegionModel importFromFiles() {
			RegionModel model = new RegionModel(FileIO.readContinentFile("continents.txt"), FileIO.readCountryFile("countries.txt"), 
			FileIO.readCityFile("cities.txt"), FileIO.readPlaceFile("placesofinterest.txt"), 
			FileIO.readPointFile("pointsofinterest.txt"));
			return model;
	}

}
