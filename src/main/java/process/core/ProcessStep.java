package process.core;

import parse.model.Step;
import process.definitions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessStep extends AProcessStep{

    private ArrayList<Step> stepList;
    private int stepNumber;

    private InputData inputData;
    private ProcessData processData;
    private Map<String,String> inputDataContainer;
    private List<HashMap<String, String>> outputDataContainer;
    private Map<String,String> processDataContainer;
    private String x1;
    private String x2;

    //need update (need to check the key that is in the input map)
    public String x1ValueFromInput(){
        if(inputData.has("x1")){
            //x1 = Integer.parseInt(inputData.getValue("x1"));
            }
        else throw new IllegalArgumentException();

        return inputData.getValue("x1");
    }

    public String x2ValueFromInput(){
        if(inputData.has("x2")){
            //x1 = Integer.parseInt(inputData.getValue("x1"));
        }
        else throw new IllegalArgumentException();

        return inputData.getValue("x2");
    }

    public String x1ValueFromPDC(String valueX1){
        if(processData.has(valueX1)){
            //x1 = Integer.parseInt(inputData.getValue("x1"));
        }
        else throw new IllegalArgumentException();

        return processData.getValue(valueX1);
    }

    public String x2ValueFromPDC(String valueX2){
        if(processData.has(valueX2)){
            //x1 = Integer.parseInt(inputData.getValue("x1"));
        }
        else throw new IllegalArgumentException();

        return processData.getValue(valueX2);
    }

    public int valToInt(String valFromPDC){
        return Integer.parseInt(valFromPDC);
    }

    public void newVarToPDC(String key, int value){
        processDataContainer.put(key, String.valueOf(value));
    }

    public void allActionsWithX1Only(ArithmeticWithX1Only arithmeticOperation){
        x1 = x1ValueFromInput();
        int stepResult = arithmeticOperation.doOperation(valToInt(x1ValueFromPDC(x1)));
        newVarToPDC(outputDataContainer.get(stepNumber).get("y") , stepResult);
    }

    public void allActionsWithBothX(ArithmeticWithBothX arithmeticOperation){
        x1 = x1ValueFromInput();
        x2 = x2ValueFromInput();
        int stepResult = arithmeticOperation.doOperation(valToInt(x1ValueFromPDC(x1)), valToInt(x2ValueFromPDC(x2)));
        newVarToPDC(outputDataContainer.get(stepNumber).get("y") , stepResult);
    }

    @Override
    public void perform() throws Exception {
        //System.out.println(stepList.get(stepNumber).toString());

        switch (stepList.get(stepNumber).getOperationClassName()) {
            case "process.definitions.Difference": {
                allActionsWithBothX(new Difference());
                break;
            }
            case "process.definitions.Division": {
                allActionsWithBothX(new Division());
                break;
            }
            case "process.definitions.Multiplication": {
                allActionsWithBothX(new Multiplication());
                break;
            }

            case "process.definitions.Negative": {
                allActionsWithX1Only(new Negative());
                break;
            }
            case "process.definitions.Square":{
                allActionsWithX1Only(new Square());
                break;
            }
            case "process.definitions.SquareRoot": {
                allActionsWithX1Only(new SquareRoot());
                break;
            }
            case "process.definitions.Sum": {
                allActionsWithBothX(new Sum());
                break;
            }
        }
    }


    @Override
    public void setInput(IProcessData inputData) {
            //обработать должен их

    }

    @Override
    public IProcessData getOutput() {
        return null;
    }

    public ProcessStep(ArrayList<Step> stepList, InputData inputData, ProcessData processData, Map<String, String> inputDataContainer, List<HashMap<String, String>> outputDataContainer, Map<String, String> processDataContainer) {
        this.stepList = stepList;
        this.inputData = inputData;
        this.processData = processData;
        this.inputDataContainer = inputDataContainer;
        this.outputDataContainer = outputDataContainer;
        this.processDataContainer = processDataContainer;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
