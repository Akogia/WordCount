package org.akogia;

import java.util.ArrayList;
import java.util.List;
import org.akogia.common.FileReader;
import org.akogia.controller.WordCounterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static boolean INDEX = false;

    public static boolean FILE = true;
    public static List<String> DICTIONARY = new ArrayList<>();

    private static Logger log = LoggerFactory.getLogger("Main.class");
    public static void main(String[] args) throws IOException {
        // Hier wird der Wert statisch übergeben, kann genauso über args[] übergeben werden
        // Check for arguments and optional
        checkArguments(args);
        WordCounterController.wordCounterControl(args, FILE, INDEX, DICTIONARY);
        Scanner scanner = new Scanner(System.in);
        boolean looping = true;
        while(looping){
            log.info("Enter text: ");
            String input = scanner.nextLine();
            WordCounterController.countWords(input,INDEX, DICTIONARY);
            if("exit".equals(input)){
                looping = false;
            }
        }
        scanner.close();
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