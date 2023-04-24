package org.akogia.controller;

import java.util.List;
import org.akogia.common.FileReader;
import org.akogia.service.WordCounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WordCounterController {

    private static Logger log = LoggerFactory.getLogger("WordCounterController.class");


    public static void wordCounterControl(String[] args, boolean file, boolean index, List<String> dictionary) throws IOException {
        log.info("wordCounterControl - File Read");

        if(file){
            String readFromFile = FileReader.readFromFile(args[1]);
            readFromFile = readFromFile.replaceAll("\n", "");
            WordCounterService.countWords(readFromFile, index, dictionary);
        }
    }


    public static void countWords(String input, boolean index, List<String> dictionary) throws IOException {
        log.info("countWords");
        WordCounterService.countWords(input, index, dictionary);
    }

}
