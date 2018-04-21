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
    public Map<String, String> xToMap(Step step){

        /* This behavior doesn't fit, because it's not universal for all possible situations (can process only one x, not two x)
        String[] str = step.getInput().split(":");
        Map<String,String> map = new HashMap<String,String>();
        map.put(str[0],str[1]); */

        // guava Map Splitter does not fit, because key and value are obtained by confused places:
        // return Splitter.on(",").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(step.getInput());

        //Split elements that are around the comma, a semicolon, then add them to the list, then add to map
        Iterable<String> stringIterable = Splitter.on(CharMatcher.anyOf(":,")).trimResults().omitEmptyStrings().split(step.getInput());
        List<String> list = Lists.newArrayList(stringIterable);
        Map<String,String> map = new HashMap<String,String>();
        for(int i = 0; i < list.size(); i+=2) {
            map.put(list.get(i+1),list.get(i));
        }
        return map;
    }
}
