package org.akogia.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.akogia.common.FileReader;
import org.akogia.service.WordCounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WordCounterController {

    private static boolean INDEX = false;

    private static boolean FILE = false;
    private static List<String> DICTIONARY = new ArrayList<>();

    private static Logger log = LoggerFactory.getLogger("WordCounterController.class");


    public static void wordCounterControl(String[] args) throws IOException {
        log.info("wordCounterControl - File Read");

        if(FILE){
            String readFromFile = FileReader.readFromFile(args[1]);
            readFromFile = readFromFile.replaceAll("\n", "");
            WordCounterService.countWords(readFromFile, INDEX, DICTIONARY);
        }
    }


    public static void countWords(String input) throws IOException {
        log.info("countWords");
        WordCounterService.countWords(input, INDEX, DICTIONARY);
    }

    public static void checkArguments(String[] args) throws IOException {
        // first argument [1] is file
        if(args[1].contains(".txt")){
            FILE = true;
        }
        if(compareStrings("-index",args)){
            INDEX = true;
        }

        for(String dict:args) {
            if (dict.contains("-dictionary")) {
                String[] filename = dict.split("=");
                String dictFile = FileReader.readFromFile(filename[1]);
                DICTIONARY = Arrays.stream(dictFile.split("\n")).toList();
            }
        }
    }

    private static boolean compareStrings(String target, String[] input){
        for(String word:input){
            if(word.contains(target)){
                return true;
            }
        }
        return false;
    }

}
