package org.akogia.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.akogia.common.FileReader;
import org.akogia.util.MemoryAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class WordCounterServiceTest {

    private static final String LOGGER_NAME = "WordCounterService.class";
    MemoryAppender memoryAppender;

    @BeforeEach
    public void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.DEBUG);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
    }

    @Test
    void countWordswithStopWords() throws IOException {
        // Arrange
        String testString = "Mary had a little lamb";

        // Act
        WordCounterService.countWords(testString, false, new ArrayList<>());

        // Assert
        // since the stopwords will elimnate the "a", there are 4 words
        assertAll(
            ()-> assertEquals(memoryAppender.countEventsForLogger(LOGGER_NAME),1),
            ()-> assertEquals(true ,memoryAppender.contains("Number of words: 4", Level.INFO))
        );
    }

    @Test
    void countUniqueWordsWithStopWords() throws IOException {
        // Arrange
        String testString = "Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.";

        // Act
        WordCounterService.countWords(testString, false, new ArrayList<>());

        // Assert
        // Humpty-Dumpty is one word and not unique
        // "a" and "on" is on the stopwords list and therefore not counted
        assertAll(
            ()-> assertEquals(1, memoryAppender.countEventsForLogger(LOGGER_NAME)),
            ()-> assertEquals(true, memoryAppender.contains("Number of words: 7", Level.INFO)),
            ()-> assertEquals(true, memoryAppender.contains("unique 6", Level.INFO))

        );
    }

    @Test
    void averageWordLengthWithStopWords() throws IOException {
        // Arrange
        String testString = "Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.";

        // Act
        WordCounterService.countWords(testString, false, new ArrayList<>());

        // Assert
        // Humpty-Dumpty is one word and not unique
        // "a" and "on" is on the stopwords list and therefore not counted
        assertEquals(true, memoryAppender.contains("average word length: 6.7", Level.INFO));
    }

    @Test
    void setIndexForAllWords() throws IOException {
        // Arrange
        String testString = "Mary had a little lamb";

        // Act
        WordCounterService.countWords(testString, true, new ArrayList<>());

        // Assert
        assertAll(
            ()-> assertEquals(6, memoryAppender.countEventsForLogger(LOGGER_NAME)),
            ()-> assertTrue(memoryAppender.contains("Mary", Level.INFO)),
            ()-> assertTrue(memoryAppender.contains("had", Level.INFO)),
            ()-> assertTrue(memoryAppender.contains("little", Level.INFO)),
            ()-> assertTrue(memoryAppender.contains("lamb", Level.INFO)),
            ()-> assertTrue(memoryAppender.contains("Number of words: 4", Level.INFO))
        );
    }


    @Test
    void indexWithDictionary() throws IOException {
        // Arrange
        List<String> dictionary = new ArrayList<>();
        dictionary.add("big");
        dictionary.add("small");
        dictionary.add("little");
        dictionary.add("cat");
        dictionary.add("dog");
        dictionary.add("have");
        dictionary.add("has");
        dictionary.add("had");

        String testString = "Mary had a little lamb";

        // Act
        WordCounterService.countWords(testString, true, dictionary);

        // Assert
        assertAll(
            ()-> assertEquals(6, memoryAppender.countEventsForLogger(LOGGER_NAME)),
            ()-> assertTrue(memoryAppender.contains("Mary*", Level.INFO)),
            ()-> assertFalse(memoryAppender.contains("had*", Level.INFO)),
            ()-> assertFalse(memoryAppender.contains("little*", Level.INFO)),
            ()-> assertTrue(memoryAppender.contains("lamb*", Level.INFO)),
            ()-> assertTrue(memoryAppender.contains("Number of words: 4", Level.INFO)),
            ()-> assertTrue(memoryAppender.contains("unknown: 2", Level.INFO))
        );
    }
}
