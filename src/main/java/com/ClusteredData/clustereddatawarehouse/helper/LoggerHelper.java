package com.ClusteredData.clustereddatawarehouse.helper;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerHelper {
    private static final Logger logger = Logger.getLogger(LoggerHelper.class.getName());
    private static FileHandler fileHandler; // to log to a file

    static {
        try {
            // Configure the logger with handler and formatter
            fileHandler = new FileHandler("application.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logError(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }
}
