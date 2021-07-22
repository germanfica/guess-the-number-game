package com.germanfica.service;

import com.germanfica.Game;
import com.germanfica.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class GameServiceImpl implements GameService {
    // == fields ==
    final Game game;
    final MessageGenerator message;

    // == constructors ==
    @Autowired
    public GameServiceImpl(Game game, MessageGenerator message) {
        this.game = game;
        this.message = message;
    }

    // == init =
    @PostConstruct
    public void init() {
        log.info("mainMessage = {}", message.getMainMessage());
        log.info("number = {}", game.getNumber());
    }

    // == public methods ==
    @Override
    public boolean isGameOver() { return game.isGameWon() || game.isGameLost(); }

    @Override
    public String getMainMessage() {
        return message.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return message.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }
}
