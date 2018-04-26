package process.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InputData implements IProcessData {
	
	public static final List<InputData> inputDataContainer = new ArrayList<>();
	private Map<String, String> inputDataContainerOnSpecificStep;
	
	@Override
	public boolean has(String key) {
		return inputDataContainerOnSpecificStep.containsKey(key);
	}
	
	@Override
	public String getValue(String key) {
		return inputDataContainerOnSpecificStep.get(key);
	}
	
	public InputData(Map<String, String> inputDataContainerOnSpecificStep) {
		this.inputDataContainerOnSpecificStep = inputDataContainerOnSpecificStep;
	}
}
