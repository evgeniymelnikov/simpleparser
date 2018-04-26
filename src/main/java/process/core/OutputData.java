package process.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputData implements IProcessData {
	
	
	public static final List<OutputData> outputDataContainer = new ArrayList<>();
	private Map<String, String> outputDataContainerOnSpecificStep;
	
	@Override
	public boolean has(String key) {
		return outputDataContainerOnSpecificStep.containsKey(key);
	}
	
	@Override
	public String getValue(String key) {
		return outputDataContainerOnSpecificStep.get(key);
	}
	
	public OutputData(Map<String, String> outputDataContainerOnSpecificStep) {
		this.outputDataContainerOnSpecificStep = outputDataContainerOnSpecificStep;
	}
	
	
}
