package process.core;

import process.definitions.*;

public class ProcessStep extends AProcessStep {


    /**
     * Uses Java Reflection API. Takes operation class name from from the parsed document and finds needed definition class (mathematics operation class).
     * After some operations with data (input, output, process data sources), does particular mathematics operation (chosen by up-casting rule).
     * Cleaning operations at the end, because the steps in this case are not related.
     *
     * @throws Exception
     * @see ADefinitions
     */
    @Override
    public void perform() throws Exception {

        setInput(InputData.inputDataContainer.get(getStepNumber()));
        Class processStepClass = Class.forName(getStepList().get(getStepNumber()).getOperationClassName());
        Object obj = processStepClass.newInstance();
        ADefinitions aDefinitions = (ADefinitions) obj;
        aDefinitions.setMap(getMapOfAllXAndValuesInDouble());
        double stepResult = aDefinitions.doOperation();

        newVarToPDC(getOutput().getValue("y"), stepResult);

        getMapOfAllXAndValuesInDouble().clear();

    }

}

