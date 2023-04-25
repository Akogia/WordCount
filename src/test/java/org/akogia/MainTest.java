package org.akogia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import java.io.IOException;
import java.util.Scanner;
import org.akogia.util.MemoryAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class MainTest {

    private static final String LOGGER_NAME = "Main.class";
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

    //@Test
    //void loadFileIntoMain() throws IOException {
    //    // Arrange
    //    String[] args = new String[]{"WordCount","wordcount.txt"};
    //    Scanner mockScanner = mock(Scanner.class);
//
    //    when(mockScanner.nextLine()).thenReturn(" ");
//
    //    // Act
    //    Main.main(args);
//
    //    verify(mockScanner).nextLine();
    //    assertEquals(memoryAppender.search("Number of words: 4", Level.INFO).size(),1);
    //}
}
