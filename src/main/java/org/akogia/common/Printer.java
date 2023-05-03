package org.akogia.common;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Printer {

    private static Logger log = LoggerFactory.getLogger("Printer.class");


    public static void printResults(List<String> inputWords, List<String> uniqueWords, boolean index, List<String> dictionary) {
        log.info("Number of words: {}, unique {}; average word length: {} characters", inputWords.size(), uniqueWords.size(), averageWordLength(inputWords));
        if(index){
            List<String> unknownWords = compareDictionary(inputWords,dictionary);
            log.info("Index (unknown: {}):",unkownWords(unknownWords));
            inputWords.forEach(log::info);
        }
    }

    private static List<String> compareDictionary(List<String> inputWords, List<String> dictionary){
        for(int i=0; i<inputWords.size();i++){
            if(!findInDictionary(inputWords.get(i), dictionary)){
                inputWords.set(i, inputWords.get(i) + "*");
            }
        }
        return inputWords;
    }

    private static boolean findInDictionary(String inputWord, List<String> dictionary){
        return dictionary.stream().anyMatch(s -> s.equals(inputWord));
    }

    private static int unkownWords(List<String> inputWords) {
        int counter = 0;
        for(String word: inputWords){
            if(word.contains("*")){
                counter++;
            }
        }
        return counter;
    }

    private static double averageWordLength(List<String> inputsWords){
        double totalLength = 0;
        int counter = 0;
        for(String word: inputsWords){
            totalLength += word.length();
            counter++;
        }
        return totalLength/counter;
    }
}
