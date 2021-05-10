package com.zhn.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        // System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            logger.trace("log starting ...");
            logger.debug("log starting ...");
            logger.info("log starting ...");
            logger.warn("log starting ...");
            logger.error("log starting ...");
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}
