package org.akogia.controller;

import org.akogia.common.FileReader;
import org.akogia.service.WordCounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WordCounterController {

    private static Logger log = LoggerFactory.getLogger("WordCounterController.class");

    public static void wordCounterControl(String filename) throws IOException {
        log.info("wordCounterControl");
        String file = FileReader.readFromFile(filename);
        WordCounterService.countWords(file);
    }

    public static void countWords(String input) throws IOException {
        log.info("countWords");
        WordCounterService.countWords(input);
    }

}
