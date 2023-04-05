package org.akogia.service;

import org.akogia.common.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WordCounterService {
    private static Logger log = LoggerFactory.getLogger("WordCounterService.class");
    public static void countWords(String input) throws IOException {

         String[] splittedString = input.split(" ");
         String[] result = stopWords("stopwords.txt");
         List<String> numberOfWords = eliminateDoubleWordss(splittedString, result);
        // List<String> splittedString = Arrays.stream(input.split(" ")).toList();
        //  List<String> result = Arrays.stream(stopWords("stopwords.txt")).toList();
        // List<String> numberOfWords = eliminateDoubleWords(splittedString, result);
        log.info("Number of words: {}", numberOfWords.size());
    }

    private static String[] stopWords(String filename) throws IOException {
        String stopWords = FileReader.readFromFile(filename);
        return stopWords.split(System.lineSeparator());
    }

    private static List<String> eliminateDoubleWords(List<String> inputWords, List<String> stopWords){
        inputWords.forEach(log::info);
        List<String> list = inputWords;
        list.removeAll(stopWords);
        list.forEach(log::info);
        return inputWords;
    }

    private static List<String> eliminateDoubleWordss(String[] inputWords, String[] stopWords){
        List<String> list = new ArrayList<>(Arrays.asList(inputWords));
        list.removeAll(Arrays.asList(stopWords));
        return list;
    }
}
