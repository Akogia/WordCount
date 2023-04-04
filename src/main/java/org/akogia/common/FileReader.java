package org.akogia.common;

import ch.qos.logback.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {

    private static Logger log = LoggerFactory.getLogger("FileReader.class");

    public static String readFromFile(String filename) throws IOException {
        log.info("FileReader with filename: {}", filename);
        ClassLoader classLoader = FileReader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        return readFromInputStream(inputStream);
    }

    private static String readFromInputStream(InputStream inputStream)
        throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                 = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        log.info("Finished reading");
        return resultStringBuilder.toString();
    }
}
