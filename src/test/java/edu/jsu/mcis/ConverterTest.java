package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConverterTest {
    private String csvString;
    private String jsonString;

    @Before
    public void setUp() {
    }
    
    @Test
    public void testConvertCSVtoJSON() {
        CSVHandler CSVWorker = new CSVHandler();
		JSONHandler JSONWorker = new JSONHandler();
		try {
			CSVWorker.readCSVFromFile();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		String[] csvGetWorkingData = CSVWorker.getWorkingData();
		String[] csvGetRowData = CSVWorker.getrowData();
		String[] csvGetColData = CSVWorker.getcolData();
		JSONWorker.setWorkingData(csvGetWorkingData);
		JSONWorker.setColData(csvGetRowData);
		JSONWorker.setRowData(csvGetColData);
		String[] jsonWorkingData = JSONWorker.getWorkingData();
		String[] jsonGetrowData = JSONWorker.getrowData();
		String[] jsonGetColData = JSONWorker.getcolData();
		assertEquals(Arrays.toString(jsonWorkingData), Arrays.toString(csvGetWorkingData));
		assertEquals(Arrays.toString(jsonGetrowData), Arrays.toString(csvGetRowData));
		assertEquals(Arrays.toString(jsonGetColData), Arrays.toString(csvGetColData));
    }

    @Test
    public void testConvertJSONtoCSV() {
        CSVHandler CSVWorker = new CSVHandler();
		JSONHandler JSONWorker = new JSONHandler();
		JSONWorker.readJSONFile();
		String[] jsonWorkingData = JSONWorker.getWorkingData();
		String[] jsonGetrowData = JSONWorker.getrowData();
		String[] jsonGetColData = JSONWorker.getcolData();
		CSVWorker.setWorkingData(jsonWorkingData);
		CSVWorker.setColData(jsonGetColData);
		CSVWorker.setRowData(jsonGetrowData);
		String[] csvGetWorkingData = CSVWorker.getWorkingData();
		String[] csvGetRowData = CSVWorker.getrowData();
		String[] csvGetColData = CSVWorker.getcolData();
		assertEquals(Arrays.toString(jsonWorkingData), Arrays.toString(csvGetWorkingData));
		assertEquals(Arrays.toString(jsonGetrowData), Arrays.toString(csvGetRowData));
		assertEquals(Arrays.toString(jsonGetColData), Arrays.toString(csvGetColData));
		
    }
}







