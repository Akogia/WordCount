package org.akogia;

import org.akogia.controller.WordCounterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Logger log = LoggerFactory.getLogger("Main.class");
    public static void main(String[] args) throws IOException {
        WordCounterController.wordCounterControl("wordcount.txt");
        Scanner scanner = new Scanner(System.in);
        boolean looping = true;
        while(looping){
            log.info("Enter text: ");
            String input = scanner.nextLine();
            WordCounterController.countWords(input);
            if("exit".equals(input)){
                looping = false;
            }
        }
        scanner.close();
    }
}