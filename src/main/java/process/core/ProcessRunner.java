package process.core;

import parse.ParserInitialization;
import parse.model.Configuration;
import parse.model.Step;
import parse.split.SplitStepInOut;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Properties;

public class ProcessRunner {
	
	Configuration configuration;
	
	/**
	 * Data from input.properties (namely a, const4, etc). Will be used as process data container.
	 * Here also name of input file ([name].properties) and path to a needed directory.
	 *
	 * @throws Exception
	 */
	public void configurationReading() throws Exception {
		
		ParserInitialization parser = new ParserInitialization();
		configuration = parser.fromXmlToObject(parser.filePath);
		
		String nameOfInputFile = configuration.getProcess_input();
		FileInputStream fis;
		fis = new FileInputStream("src/main/resources/" + nameOfInputFile);
		Properties properties = new Properties();
		properties.load(fis);
		fis.close();
		for (String name : properties.stringPropertyNames()) {
			ProcessData.processDataContainer.put(name, properties.getProperty(name));
		}
		/*
		 * Sort by step number. Just in case.
		 */
		Collections.sort(configuration.getStepList(), Step.STEP_BY_ID);
	}
	
	/**
	 * Step by step processing data (splits strings, puts them into map, uses key-value pairs from many sources, does mathematics operations, put new key-value pairs to map (for next steps)).
	 *
	 * @throws Exception
	 */
	public void stepProcessing() throws Exception {
		ProcessStep processStep = new ProcessStep();
		processStep.setStepList(configuration.getStepList());
		for (int i = 0; i < configuration.getStepList().size(); i++) {
			SplitStepInOut.splitToMap(configuration.getStepList().get(i).getInput(), configuration.getStepList().get(i).getOutput());
			processStep.setStepNumber(i);
			processStep.perform();
		}
	}
	
	public void writingResultToFile() throws FileNotFoundException {
		
		String result1 = ProcessData.processDataContainer.get("result1");
		String result2 = ProcessData.processDataContainer.get("result2");
		File folder = new File("output/");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File("output/output.txt");
		
		PrintWriter pw = new PrintWriter(file);
		pw.print("");
		pw.println("result1=" + result1);
		pw.println("result2=" + result2);
		pw.close();
	}
}






