package com.germanfica.controller;

import com.germanfica.service.GameService;
import com.germanfica.util.GameMappings;
import com.germanfica.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {
    // == fields ==
    private final GameService gameService;

    // == constructors ==
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // == request methods ==
    @GetMapping(GameMappings.PLAY)
    public String play(Model model) {
        log.info("play method called");
        model.addAttribute("mainMessage", gameService.getMainMessage());
        model.addAttribute("resultMessage", gameService.getResultMessage());
        log.info("model = {}", model);
        // Show GameOver view if the player lost the game
        if(gameService.isGameOver()) {
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess) {
        log.info("processMessage method called");
        log.info("guess = {}", guess);
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;
    }
}
