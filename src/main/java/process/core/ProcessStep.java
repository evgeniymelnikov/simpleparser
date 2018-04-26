package process.core;

import process.definitions.*;

public class ProcessStep extends AProcessStep {
	
	
	/**
	 * Uses Java Reflection API. Takes operation class name from from the parsed document and finds needed definition class (mathematics operation class). After brings from it names of all used interfaces (uses marker interfaces, for ex., interface x1 - meaning that for this operation needed x1, interface x2 has the same meaning, but concerning x2). Thereby system is scalable.
	 * Because the interface names include the full name of the directory, splits them and uses last part.
	 * After some operations with data (input, output, process data sources), does particular mathematics operation (chosen by up-casting rule).
	 * Cleaning operations at the end, because the steps in this case are not related.
	 *
	 * @throws Exception
	 * @see ADefinitions
	 * @see x1
	 */
	@Override
	public void perform() throws Exception {
		
		Class processStepClass = Class.forName(getStepList().get(getStepNumber()).getOperationClassName());
		Class[] interfaces = processStepClass.getInterfaces();
		for (Class i : interfaces) {
			String[] splittedName = i.getName().split("\\.");
			String xNum = splittedName[splittedName.length - 1];
			getListOfAllXNamesInString().add(xNum);
		}
		
		setInput(InputData.inputDataContainer.get(getStepNumber()));
		Object obj = processStepClass.newInstance();
		ADefinitions aDefinitions = (ADefinitions) obj;
		aDefinitions.setMap(getMapOfAllXAndValuesInDouble());
		double stepResult = aDefinitions.doOperation();
		
		newVarToPDC(getOutput().getValue("y"), stepResult);
		
		getListOfAllXNamesInString().clear();
		getMapOfAllXAndValuesInDouble().clear();
		
	}
	
}

