package process.core;

import java.util.HashMap;
import java.util.Map;

public class ProcessData implements IProcessData{
    //переданные в xml переменные a, b, c, const;
    // будет дополняться переменными, которые будут возникать в ходе выполнения шагов (к  пр., square, discriminant)
    public Map<String,String> processDataContainer = new HashMap<String,String>();

    @Override
    public boolean has(String key) {
        return processDataContainer.containsKey(key);
    }

    @Override
    public String getValue(String key) {
        return processDataContainer.get(key);
    }
}
