package process.core;

import java.util.HashMap;
import java.util.Map;

public class InputData implements IProcessData{

        private Map<String,String> inputDataContainer = new HashMap<String,String>();

        @Override
        public boolean has(String key) {
            return inputDataContainer.containsKey(key);
        }

        @Override
        public String getValue(String key) {
            return inputDataContainer.get(key);
        }

    public Map<String, String> getInputDataContainer() {
        return inputDataContainer;
    }

    public void setInputDataContainer(Map<String, String> inputDataContainer) {
        this.inputDataContainer = inputDataContainer;
    }
}
