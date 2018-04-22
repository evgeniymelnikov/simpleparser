package process.core;

import java.util.HashMap;
import java.util.Map;

public class ProcessData implements IProcessData{

    private Map<String,String> processDataContainer = new HashMap<String,String>();

    @Override
    public boolean has(String key) {
        return processDataContainer.containsKey(key);
    }

    @Override
    public String getValue(String key) {
        return processDataContainer.get(key);
    }

    public Map<String, String> getProcessDataContainer() {
        return processDataContainer;
    }

    public void setProcessDataContainer(Map<String, String> processDataContainer) {
        this.processDataContainer = processDataContainer;
    }
}
