package edu.jsu.mcis;
import java.io.IOException;
import java.util.Scanner;

public class convertCSVandJSON {
	private Scanner input;
	
	convertCSVandJSON(){
	}
	public void actionPrompt(){
		boolean escape = false;
		while(escape != true){
			input = new Scanner(System.in);
			System.out.println("1. csv to json");
			System.out.println("2. json to csv");
			System.out.print("\nWhat would you like to do: ");
			int dataEntered = input.nextInt();
			escape = performAction(dataEntered);
		}		
	}
	public boolean performAction(int input){
		boolean escape = false;
		if(input == 1){
			toJSON();
			escape = true;
			
		}
		if(input == 2){
			escape = true;
			toCSV();
		}
		return escape;
	}
	private void toJSON(){
		CSVHandler CSVWorker = new CSVHandler();
		JSONHandler JSONWorker = new JSONHandler();
		try {
			CSVWorker.readCSVFromFile();
			CSVWorker.displayWorkingDataToConsole();
			JSONWorker.setWorkingData(CSVWorker.getWorkingData());
			JSONWorker.setRowData(CSVWorker.getrowData());
			JSONWorker.setColData(CSVWorker.getcolData());
			JSONWorker.writeJSONFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void toCSV(){
		System.out.println("ran");
		CSVHandler CSVWorker = new CSVHandler();
		JSONHandler JSONWorker = new JSONHandler();
		try {
			JSONWorker.readJSONFile();
			String[] workingData = JSONWorker.getWorkingData();
			System.out.println(workingData);
			CSVWorker.setWorkingData(JSONWorker.getWorkingData());
			CSVWorker.setRowData(JSONWorker.getrowData());
			CSVWorker.setColData(JSONWorker.getcolData());
			CSVWorker.writeCSVDataToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
