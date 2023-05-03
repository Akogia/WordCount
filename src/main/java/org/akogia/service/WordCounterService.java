package org.akogia.service;

import org.akogia.common.FileReader;
import org.akogia.common.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WordCounterService {

    private static final String STOPWORDS = "stopwords.txt";


    public static void countWords(String input, boolean index, List<String> dictionary)throws IOException {
        String[] splittedString = input.split(" ");
        String[] result = stopWords(STOPWORDS);
        List<String> numberOfWords = eliminateNonUniqueWords(splittedString, result);
        List<String> uniqueWords = uniqueWords(numberOfWords);
        Printer.printResults(numberOfWords, uniqueWords, index, dictionary);
    }

    private static String[] stopWords(String filename) throws IOException {
        String stopWords = FileReader.readFromFile(filename);
        return stopWords.split(System.lineSeparator());
    }

    private static List<String> eliminateNonUniqueWords(String[] inputWords, String[] stopWords){
        List<String> list = new ArrayList<>(Arrays.asList(inputWords));
        list.removeAll(Arrays.asList(stopWords));
        return list;
    }

    private static List<String> uniqueWords(List<String> inputsWords){
        return inputsWords.stream().distinct().toList();
    }


}
