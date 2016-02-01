package com.apelon.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {

    private static final Logger logger = LogManager.getLogger(AppLogger.class);

    public static Logger get() {
        return logger;
    }
}

