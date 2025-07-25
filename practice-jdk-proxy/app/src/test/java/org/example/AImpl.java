package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AImpl implements AInterface{

    private static final Logger logger = LoggerFactory.getLogger(AImpl.class);

    @Override
    public String call() {
        logger.info("애플리케이션 시작");
        return "a";
    }
}
