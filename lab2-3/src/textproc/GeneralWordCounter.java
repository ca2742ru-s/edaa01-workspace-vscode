package textproc;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor{

    Set<String> stopwords = new HashSet<String>();
    Map<String, Integer> wordCounter = new TreeMap<String, Integer>();

    public GeneralWordCounter(Set<String> stopwords){
        for(String stopword : stopwords){
            this.stopwords.add(stopword);
        }

    }

    public void process(String word){
        if(!stopwords.contains(word)){
            if(wordCounter.containsKey(word)){
                wordCounter.put(word, wordCounter.get(word) + 1);
            }
            else{
                wordCounter.put(word, 1);
            }
        }
    }

    public void report(){
        /* *
        for(String key : wordCounter.keySet()){
            if(wordCounter.get(key) >= 200){
                System.out.println(key + ": " + wordCounter.get(key));
            }
        }
            * */
        Set<Map.Entry<String, Integer>> wordSet = wordCounter.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

        wordList.sort((entry1, entry2) -> {
            int result = entry2.getValue() - entry1.getValue();
            if(result == 0){
                return entry1.getKey().compareTo(entry2.getKey());
            }
            else{
                return result;
            }
            
        });
        //List.sort(wordList, Comparator.comparing(HashMap::get()));
        
        for(int i = 0; i < 20; i++){
            System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
        }

        
    }

}
