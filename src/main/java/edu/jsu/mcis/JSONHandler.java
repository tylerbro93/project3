package edu.jsu.mcis;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHandler {

	private JSONParser holder = new JSONParser();
	private String[] colData;
	private String[] workingData;
	private String[] rowData;
	
	JSONHandler(){
		workingData = new String[100];
		rowData = new String[100];
		colData = new String[100];
	}
	
	public void readJSONFile(){
		int count = 0;
		String garbage;
		String cleanUpFromGarbage;
		String[] workingDataHolder;
		try {
			Object obj = holder.parse(new FileReader("resources/grades.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray colPieces = (JSONArray) jsonObject.get("colHeaders");
			Iterator<String> goThroughColPieces = colPieces.iterator();
			colData[0] = "ID";
			while(goThroughColPieces.hasNext() == true){
				colData[count+1] = goThroughColPieces.next();

				count += 1;
			}
			count = 0;
			
			JSONArray rowPieces = (JSONArray) jsonObject.get("rowHeaders");
			Iterator<String> goThroughrowPieces = rowPieces.iterator();
			while(goThroughrowPieces.hasNext() == true){
				rowData[count] = goThroughrowPieces.next();
				count += 1;
			}
			count = 0;
			
			JSONArray workingPieces = (JSONArray) jsonObject.get("data");
			garbage = workingPieces.toJSONString();
			workingDataHolder = garbage.split("],");
			for(int i = 0; i < workingDataHolder.length; i++){
				cleanUpFromGarbage = workingDataHolder[i];
				cleanUpFromGarbage = cleanUpFromGarbage.replaceAll("\\[\\[", "\\[");
				cleanUpFromGarbage = cleanUpFromGarbage.replaceAll("\\]\\]", "");
				cleanUpFromGarbage = cleanUpFromGarbage + "]";
				workingData[i] = cleanUpFromGarbage;
			}
		}
		catch(ParseException | IOException e) {
			
		}
	}
	public void writeJSONFile(){
		JSONObject holder = new JSONObject();
		holder.put(colDataReadable(), "colHeaders");
		holder.put(rowDataReadable(), "rowHeaders");
		
		holder.put(worrkingDataReadable(), "data");
		try {
			FileWriter fileToBeWritten = new FileWriter("resources/gradesout.json");
			fileToBeWritten.write(holder.toJSONString());
			fileToBeWritten.flush();
			fileToBeWritten.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private JSONArray worrkingDataReadable(){
		JSONArray workingDataList = new JSONArray();
		int count = 0;
		String data = ""; 
		while(workingData[count] != null){
			data = workingData[count];
			workingDataList.add(data);
			count++;
		}
		data = workingDataList.toString();
		return workingDataList;
	}
	private JSONArray colDataReadable(){
		int count = 0;
		JSONArray colDataList = new JSONArray();
		while(colData[count] != null){
			colDataList.add(colData[count]);
			count++;
		}
		return colDataList;
	}
	private JSONArray rowDataReadable(){
		int count = 0;
		JSONArray rowDataList = new JSONArray();
		while(rowData[count] != null){
			rowDataList.add(rowData[count]);
			count++;
		}
		return rowDataList;
	}
	public String[] getWorkingData(){
		return workingData;
	}
	public String[] getrowData(){
		return rowData;
	}
	public String[] getcolData(){
		return colData;
	}
	public void setWorkingData(String[] newWorkingData){
		workingData =  newWorkingData;
	}
	public void setColData(String[] newColData){
		colData =  newColData;
	}
	public void setRowData(String[] newRowData){
		rowData =  newRowData;
	}
}
