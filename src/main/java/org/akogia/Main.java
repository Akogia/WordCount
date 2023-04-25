package org.akogia;

import org.akogia.controller.WordCounterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

import java.util.Scanner;

public class Main {


    private static Logger log = LoggerFactory.getLogger("Main.class");
    public static void main(String[] args) throws IOException {
        // Hier wird der Wert statisch übergeben, kann genauso über args[] übergeben werden
        // Input arguments are: $FileDirName$ wordcount.txt -index -dictionary=dict.txt
        // Check for arguments and optional
        WordCounterController.checkArguments(args);;
        WordCounterController.wordCounterControl(args);
        Scanner scanner = new Scanner(System.in);
        boolean looping = true;
        while(looping){
            log.info("Enter text: ");
            String input = scanner.nextLine();
            if(input.isEmpty()){
                break;
            }
            WordCounterController.countWords(input);
        }
        scanner.close();
    }


}