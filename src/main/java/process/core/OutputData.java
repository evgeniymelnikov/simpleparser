package process.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OutputData {

    private List<HashMap<String, String>> outputDataContainer = new ArrayList<>();


    public List<HashMap<String, String>> getOutputDataContainer() {
        return outputDataContainer;
    }

    public void setOutputDataContainer(List<HashMap<String, String>> outputDataContainer) {
        this.outputDataContainer = outputDataContainer;
    }
}
