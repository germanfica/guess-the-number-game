package com.germanfica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {
    // == constants ==
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // == fields ==
    @Autowired
    private Game game;
    private int guessCount = 10;

    // == init ==
    @PostConstruct
    @Override
    public String getResultMessage() {
        log.debug("getResultMessage()");
        return "I'm the result message.";
    }

    // == public methods ==
    @Override
    public String getMainMessage() {
        log.debug("getMainMessage()");
        return "I'm the main message.";
    }
}
