package process.core;

import parse.model.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class for process step - implements common methods used in every process step implementation
 */
public abstract class AProcessStep implements IProcessStep {
	
	private ArrayList<Step> stepList;
	
	/**
	 * The system is scalable with respect to the number of variables X.
	 */
	private List<String> allXNamesInString = new ArrayList<>();
	private Map<String, Double> mapForAllXAndValuesInDoubles = new HashMap<>();
	
	/**
	 * The number of the concrete step. Will be used in the for loop.
	 */
	private int stepNumber;
	
	
	public double valToDouble(String valFromPDC) {
		return Double.parseDouble(valFromPDC);
	}
	
	/**
	 * Takes the full name of var x (needed to perform an arithmetic operation) and checks if it exists in input data of the current step.
	 * Than takes value of var x (for ex., 'x1' = 'ac', takes 'ac') and checks if that value ('ac') has in process data container its own value ('ac' = '6'). If true, cast needed value to double and put corresponding value to map ('ac' = (double) 6)
	 *
	 * @param inputData - container of key-value pairs according to process configuration file
	 */
	@Override
	public void setInput(IProcessData inputData) {
		for (String str : allXNamesInString) {
			if (inputData.has(str)) {
				if (ProcessData.processDataContainer.containsKey(inputData.getValue(str))) {
					mapForAllXAndValuesInDoubles.put(str, valToDouble(ProcessData.processDataContainer.get(inputData.getValue(str))));
				} else throw new IllegalArgumentException();
				
			} else throw new IllegalArgumentException();
		}
	}
	
	@Override
	public IProcessData getOutput() {
		return OutputData.outputDataContainer.get(stepNumber);
	}
	
	/**
	 * @param key   - value from output data container (for ex., 'y' = '4ac'; needed value is '4ac')
	 * @param value - result of arithmetic operation at the current step (for ex., (double) 24; so key = '4ac', value = '24' (after cast to String))
	 */
	public void newVarToPDC(String key, double value) {
		ProcessData.processDataContainer.put(key, String.valueOf(value));
	}
	
	
	public ArrayList<Step> getStepList() {
		return stepList;
	}
	
	public void setStepList(ArrayList<Step> stepList) {
		this.stepList = stepList;
	}
	
	
	public int getStepNumber() {
		return stepNumber;
	}
	
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	
	
	public List<String> getListOfAllXNamesInString() {
		return allXNamesInString;
	}
	
	public Map<String, Double> getMapOfAllXAndValuesInDouble() {
		return mapForAllXAndValuesInDoubles;
	}
	
	
}

