package org.akogia.service;

import org.akogia.common.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WordCounterService {
    private static Logger log = LoggerFactory.getLogger("WordCounterService.class");

    private static final String STOPWORDS = "stopwords.txt";


    public static void countWords(String input, boolean index, List<String> dictionary)throws IOException {
        String[] splittedString = input.split(" ");
        String[] result = stopWords(STOPWORDS);
        List<String> numberOfWords = eliminateDoubleWords(splittedString, result);
        List<String> uniqueWords = uniqueWords(numberOfWords);
        printResults(numberOfWords, uniqueWords, index, dictionary);
    }


    private static void printResults(List<String> inputWords, List<String> uniqueWords, boolean index, List<String> dictionary) {
        log.info("Number of words: {}, unique {}; average word length: {} characters", inputWords.size(), uniqueWords.size(), averageWordLength(inputWords));
        if(index){
            List<String> unknownWords = compareDictionary(inputWords,dictionary);
            log.info("Index (unknown: {}):",unkownWords(unknownWords));
            inputWords.forEach(System.out::println);
        }
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

    private static String[] stopWords(String filename) throws IOException {
        String stopWords = FileReader.readFromFile(filename);
        return stopWords.split(System.lineSeparator());
    }

    private static List<String> eliminateDoubleWords(String[] inputWords, String[] stopWords){
        List<String> list = new ArrayList<>(Arrays.asList(inputWords));
        list.removeAll(Arrays.asList(stopWords));
        return list;
    }

    private static List<String> uniqueWords(List<String> inputsWords){
        return inputsWords.stream().distinct().toList();
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
