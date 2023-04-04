package org.akogia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCounterService {
    private static Logger log = LoggerFactory.getLogger("WordCounterService.class");
    public static void countWords(String input){
        String[] splittedString = input.split(" ");
        log.info("Number of words: {}", splittedString.length);
    }
}
