package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BImpl implements BInterface{

    public static final Logger logger = LoggerFactory.getLogger(BImpl.class);

    @Override
    public String call() {
        logger.info("b 호출");
        return "b";
    }
}
