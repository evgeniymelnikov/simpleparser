package process.definitions;

import java.util.Map;

public abstract class ADefinitions {

    Map<String, Double> map;

    public double getValue(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else throw new IllegalArgumentException();

    }

    public abstract double doOperation();

    public void setMap(Map<String, Double> map) {
        this.map = map;
    }
}
