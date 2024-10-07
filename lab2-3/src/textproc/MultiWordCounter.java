package textproc;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor{

    Map<String, Integer> wordCounterMap = new TreeMap<String, Integer>();

    public MultiWordCounter(String[] landskap){
        for(String l : landskap){
            wordCounterMap.put(l, 0);
        }

    }

    public void process(String w){
        if(wordCounterMap.containsKey(w)){
            wordCounterMap.put(w, wordCounterMap.get(w) + 1);
        }
    }

    public void report(){
        for(String key : wordCounterMap.keySet()){
            System.out.println(key + ": " + wordCounterMap.get(key));
        }
    }

}
