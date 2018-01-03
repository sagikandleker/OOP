package GUI;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import Algorithms.Algo_Main;
import Data_Setup.Mac;
import Data_Setup.Position;
import Data_Setup.Record_Mac_Signal;
import Data_Setup.Signal;
import Main_App.Analayze_Files;
import Main_App.Read_From;
import Main_App.Write_2_CSV;

public class GUI_Wrapper {
	
	//private static final String DefaultTableModel = null;
	public static File folder=new File("");
	public static File file = new File("");
	public static File nogpsfile = new File("");
	public static File combfile = new File("");
	public static File savefile = new File("");
	public static File algorithm1 = new File("");
	public static File algorithm2 = new File("");
	
	public static void choosefolder() throws IOException, ParseException
	{
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Browse the folder to process");
	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        
	    } else {
	       System.out.println("No Selection");
	    }
	    
	     folder = chooser.getSelectedFile();

	     if (!folder.isDirectory()) {
		        folder = folder.getParentFile();
		    }
	     
	 Analayze_Files.getFiles(folder);
	 
	}
	
	public static void chooseCSVFile(String name) throws IOException, ParseException {
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV (Comma delimited) (*.csv)", "csv","CSV");
		chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Browse the folder to process");
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    
	
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    } else {
	        System.out.println("No Selection ");
	    }
	  if(name.equalsIgnoreCase("mainfile"))
	  {
	     file = chooser.getSelectedFile();
	     Read_From.wigle_File(file);
	  }
	  else if(name.equalsIgnoreCase("nogpsfile"))
	  {
		  nogpsfile = chooser.getSelectedFile();
		  
	  }
	  else if(name.equalsIgnoreCase("combfile"))
	  {
		  combfile = chooser.getSelectedFile();
		  
	  }
	}
	
	public static void clearData() {
	
		Write_2_CSV.clearData();
		
	}
	
	public static void saveTOCSV(String name) throws IOException, ParseException {
	    
		
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV (Comma delimited) (*.csv)", "csv","CSV");
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		  
		if(name.equalsIgnoreCase("WigleSorted")) {
			savefile = fileChooser.getSelectedFile();
			Write_2_CSV.Write(savefile+".csv");		
		}
		else if(name.equalsIgnoreCase("Algo1")) {
			algorithm1 = fileChooser.getSelectedFile();
			Algo_Main.ChooseAlgo1();
		 }
		
		else if(name.equalsIgnoreCase("Algo2")) {
			algorithm2 = fileChooser.getSelectedFile();
			Algo_Main.ChooseAlgo2();
		  }
		}
	}
	
	
	
	public static void exportAlgo1() throws IOException, ParseException {
		saveTOCSV("Algo1");
	}
	public static void exportAlgo2() throws IOException, ParseException {
		saveTOCSV("Algo2");		
	}
	
	public static Position algo1Short(String mac) throws IOException {
			 return Algo_Main.algo1_Mac(mac);
			
	}
	
	public static void algo2Short(ArrayList<String> Allm_Alls) throws IOException, ParseException {
		
		Mac mac;
		Signal signal;
		Record_Mac_Signal ms;
		ArrayList<Record_Mac_Signal> three_ms = new ArrayList<Record_Mac_Signal>();
		
		for (int i = 0; i < Allm_Alls.size(); i++) {
			
			mac = new Mac(Allm_Alls.get(i));
			signal = new Signal(Allm_Alls.get(i+1));
			ms = new Record_Mac_Signal(mac, signal);
			three_ms.add(ms);
		}
		
		Algo_Main.algo2_all(three_ms);
	}
}
