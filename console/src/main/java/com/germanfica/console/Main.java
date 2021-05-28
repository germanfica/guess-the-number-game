package com.germanfica.console;

import com.germanfica.AppConfig;
import com.germanfica.Game;
import com.germanfica.MessageGenerator;
import com.germanfica.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess The Number Game");

        // create context (container)
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // get number generator bean from context (container)
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        // call method next() to get a random number
        int number = numberGenerator.next();

        // log generated number
        log.info("number = {}", number);

        // get game bean from context (container)
        Game game = context.getBean(Game.class);

        // get message from context (container)
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        // calling methods from MessageGenerator
        log.info("messageGenerator = {}", messageGenerator.getMainMessage());
        log.info("getResultMessage = {}", messageGenerator.getResultMessage());

        // close context (container)
        context.close();
    }
}
