package parse.split;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import parse.model.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitStepInOut {

    /**
     * Splits elements (x1 or x1, x2 and there value, for ex., 4ac), that are around the colon, comma, and eventually adds them to the map.
     * @param step
     * @return - the received container is used as input data (for a particular step).
     */
    public Map<String, String> xToMap(Step step){

        /*
         * Splits elements that are around the comma, colon, and eventually adds them to the map.
         */
        Iterable<String> stringIterable = Splitter.on(CharMatcher.anyOf(":,")).trimResults().omitEmptyStrings().split(step.getInput());
        List<String> list = Lists.newArrayList(stringIterable);
        Map<String,String> map = new HashMap<String,String>();

        /*
         * In the proposed xml variant: the key-value pairs are separated by commas, x1, x2 (which should be the key) are to the right of the colon.
         * Therefore, x1 has i+1, its value i.
         */
        for(int i = 0; i < list.size(); i+=2) {
            map.put(list.get(i+1),list.get(i));
        }
        return map;
    }

    /**
     * Splits elements (y and value, for ex., discriminant), that are around the colon, and eventually adds them to the map.
     * @param step
     * @return - the received container is used as output data.
     */
    public HashMap<String, String> outToMap(Step step){
        Iterable<String> stringIterable = Splitter.on(CharMatcher.anyOf(":")).trimResults().omitEmptyStrings().split(step.getOutput());
        List<String> list = Lists.newArrayList(stringIterable);
        HashMap<String,String> map = new HashMap<String,String>();
        for(int i = 0; i < list.size(); i+=2) {
            map.put(list.get(i),list.get(i+1));
        }
        return map;
    }
}
