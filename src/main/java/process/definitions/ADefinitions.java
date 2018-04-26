package process.definitions;

import java.util.Map;

public abstract class ADefinitions {
	
	Map<String, Double> map;
	
	public double getValue(String key) {
		return map.get(key);
		
	}
	
	public abstract double doOperation();
	
	public void setMap(Map<String, Double> map) {
		this.map = map;
	}
}
