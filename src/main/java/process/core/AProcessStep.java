package process.core;

import parse.model.Step;
import process.definitions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *	Abstract class for process step - implements common methods used in every process step implementation
 *
 */
public abstract class AProcessStep implements IProcessStep {

    private ArrayList<Step> stepList;

    /**
     * The number of the concrete step. Will be used in the for loop.
     */
    private int stepNumber;

    /**
     * input/process Data, input/output/process DataContainer will receive a link to the previously created objects.
     */
    private InputData inputData;
    private ProcessData processData;
    private Map<String,String> inputDataContainer;
    private List<HashMap<String, String>> outputDataContainer;
    private Map<String,String> processDataContainer;

    private String x1;
    private String x2;

    /**
     *
     * @return value of x1 from input data container (for ex, x1 = "a", this method will return "a").
     */
    public String x1ValueFromInput(){
        if(inputData.has("x1")){
            return inputData.getValue("x1");
        }
        else throw new IllegalArgumentException();

    }

    /**
     *
     * @return value of x2 from input data container (for ex, x2 = "const4", this method will return "const4").
     */
    public String x2ValueFromInput(){
        if(inputData.has("x2")){
            return inputData.getValue("x2");
        }
        else throw new IllegalArgumentException();

    }

    /**
     * PDC - process data container (stores the variables (for ex.) a, b, const4 and their values, last can be cast to double)
     * @param valueX1 - for ex., "a" or "const4".
     * @see AProcessStep#x1ValueFromInput()
     * @return values, that can be cast to double
     */
    public String x1ValueFromPDC(String valueX1){
        if(processData.has(valueX1)){
            return processData.getValue(valueX1);
        }
        else throw new IllegalArgumentException();

        }

    /**
     * @see AProcessStep#x1ValueFromPDC(String)
     * @param valueX2
     * @return
     */
    public String x2ValueFromPDC(String valueX2){
        if(processData.has(valueX2)){
            return processData.getValue(valueX2);
        }
        else throw new IllegalArgumentException();


    }

    public double valToDouble(String valFromPDC){
        return Double.parseDouble(valFromPDC);
    }

    /**
     * The purpose of this method is to put a new variable and its value into a container.
     * For example, after multiplying x1 (equal to a) and x2 (equal to c), a certain numerical value is obtained, which in the presented xml is
     * associated with ac. This will allow us to use this key (as) in the future.
     * @param key - the actual value for the step from the output data container (for ex., "y" = "ac", the param will be "ac")
     * @param value - the result of the arithmetic operation performed in step (which should be associated with the param key)
     */
    public void newVarToPDC(String key, double value){
        processDataContainer.put(key, String.valueOf(value));
    }

    /**
     * The method performs all the actions necessary to obtain the actual value of the variable, casting it to a double type and performing an arithmetic operation with it.
     * The method is based on the following methods:
     * @see AProcessStep#x1ValueFromInput()
     * @see AProcessStep#x1ValueFromPDC(String)
     * @see AProcessStep#valToDouble(String)
     *
     * The method is taking into account the up-casting rule.
     * @param arithmeticOperation - an instance of a class that requires only one variable x (a particular operation will be selected depending on the transferred instance of the class).
     * For. ex.:
     * @see Square;
     */
    public void allActionsWithX1Only(ArithmeticWithX1Only arithmeticOperation){
        x1 = x1ValueFromInput();
        double stepResult = arithmeticOperation.doOperation(valToDouble(x1ValueFromPDC(x1)));
        newVarToPDC(outputDataContainer.get(stepNumber).get("y") , stepResult);
    }

    /**
     * @see AProcessStep#allActionsWithX1Only(ArithmeticWithX1Only)
     * @param arithmeticOperation
     */
    public void allActionsWithBothX(ArithmeticWithBothX arithmeticOperation){
        x1 = x1ValueFromInput();
        x2 = x2ValueFromInput();
        double stepResult = arithmeticOperation.doOperation(valToDouble(x1ValueFromPDC(x1)), valToDouble(x2ValueFromPDC(x2)));
        newVarToPDC(outputDataContainer.get(stepNumber).get("y") , stepResult);
    }

    /**
     * Proceeding from the content of xml, a specific arithmetic operation is determined.
     * @throws Exception
     */
    @Override
    public void perform() throws Exception {

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

    /**
     * Will receive a link to the previously created objects.
     * @param stepList
     * @param inputData
     * @param processData
     * @param inputDataContainer
     * @param outputDataContainer
     * @param processDataContainer
     */
    public AProcessStep(ArrayList<Step> stepList, InputData inputData, ProcessData processData, Map<String, String> inputDataContainer, List<HashMap<String, String>> outputDataContainer, Map<String, String> processDataContainer) {
        this.stepList = stepList;
        this.inputData = inputData;
        this.processData = processData;
        this.inputDataContainer = inputDataContainer;
        this.outputDataContainer = outputDataContainer;
        this.processDataContainer = processDataContainer;
    }

     /**
     * invoked by process runner - transfer input parameters to step
     *
     * @param inputData - container of key-value pairs according to process configuration file
     */
     public void setInput(IProcessData inputData){
         this.inputData = (InputData) inputData;
        }
    /**
     *	invoked by process runner - transfer output parameters back to process
     *
     * @return container of key-value pairs according to process configuration file
     */

     public IProcessData getOutput(){
         return (IProcessData) outputDataContainer;
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

    public InputData getInputData() {
        return inputData;
    }

    public void setInputData(InputData inputData) {
        this.inputData = inputData;
    }

    public ProcessData getProcessData() {
        return processData;
    }

    public void setProcessData(ProcessData processData) {
        this.processData = processData;
    }

    public Map<String, String> getInputDataContainer() {
        return inputDataContainer;
    }

    public void setInputDataContainer(Map<String, String> inputDataContainer) {
        this.inputDataContainer = inputDataContainer;
    }

    public List<HashMap<String, String>> getOutputDataContainer() {
        return outputDataContainer;
    }

    public void setOutputDataContainer(List<HashMap<String, String>> outputDataContainer) {
        this.outputDataContainer = outputDataContainer;
    }

    public Map<String, String> getProcessDataContainer() {
        return processDataContainer;
    }

    public void setProcessDataContainer(Map<String, String> processDataContainer) {
        this.processDataContainer = processDataContainer;
    }

    public String getX1() {
        return x1;
    }

    /*public void setX1(String x1) {
        this.x1 = x1;
}*/

    public String getX2() {
        return x2;
    }

    /*public void setX2(String x2) {
        this.x2 = x2;
    }*/
}

