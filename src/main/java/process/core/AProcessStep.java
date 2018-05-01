package process.core;

import parse.model.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for process step - implements common methods used in every process step implementation
 */
public abstract class AProcessStep implements IProcessStep {

    private ArrayList<Step> stepList;

    private Map<String, Double> mapForAllXAndValuesInDoubles = new HashMap<>();

    /**
     * The number of the concrete step. Will be used in the for loop.
     */
    private int stepNumber;


    public double valToDouble(String valFromPDC) {
        return Double.parseDouble(valFromPDC);
    }

    /**
     * Takes the full name of var x (checks all values found in the configuration of a particular step).
     * Than takes value of var x (for ex., 'x1' = 'ac', takes 'ac') and pulls from process data container its own value ('ac' = '6').
     * After casts needed value to double ('6' to (double) 6) and put corresponding key (x1) and value ((double) 6) to map.
     * Particular step is defined by method perform().
     *
     * @param inputData - container of key-value pairs according to process configuration file.
     * @see ProcessStep#perform()
     */
    @Override
    public void setInput(IProcessData inputData) {
        for (Map.Entry<String, String> entry : ((InputData) inputData).getInputDataContainerOnSpecificStep().entrySet()) {
            mapForAllXAndValuesInDoubles.put(entry.getKey(), valToDouble(ProcessData.processDataContainer.get(inputData.getValue(entry.getKey()))));
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


    public Map<String, Double> getMapOfAllXAndValuesInDouble() {
        return mapForAllXAndValuesInDoubles;
    }


}

