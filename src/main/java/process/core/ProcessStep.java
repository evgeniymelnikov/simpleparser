package process.core;

import parse.model.Step;
import process.definitions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessStep extends AProcessStep{
    public ProcessStep(ArrayList<Step> stepList, InputData inputData, ProcessData processData, Map<String, String> inputDataContainer, List<HashMap<String, String>> outputDataContainer, Map<String, String> processDataContainer) {
        super(stepList, inputData, processData, inputDataContainer, outputDataContainer, processDataContainer);
    }
}
