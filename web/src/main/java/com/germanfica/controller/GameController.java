package com.germanfica.controller;

import com.germanfica.service.GameService;
import com.germanfica.util.AttributeNames;
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
    @GetMapping(GameMappings.HOME)
    public String home(Model model) {
        log.info("home method called");
        model.addAttribute(AttributeNames.CURRENT_URL, GameMappings.HOME); // get current url
        model.addAttribute(AttributeNames.HOME_URL, GameMappings.HOME);
        model.addAttribute(AttributeNames.PLAY_URL, GameMappings.PLAY);
        model.addAttribute(AttributeNames.ABOUT_URL, GameMappings.ABOUT);
        return ViewNames.HOME;
    }

    @GetMapping(GameMappings.PLAY)
    public String play(Model model) {
        log.info("play method called");
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        model.addAttribute(AttributeNames.CURRENT_URL, GameMappings.PLAY); // get current url
        model.addAttribute(AttributeNames.HOME_URL, GameMappings.HOME);
        model.addAttribute(AttributeNames.PLAY_URL, GameMappings.PLAY);
        model.addAttribute(AttributeNames.RESTART_URL, GameMappings.RESTART);
        model.addAttribute(AttributeNames.ABOUT_URL, GameMappings.ABOUT);
        log.info("model = {}", model);
        // Show GameOver view if the player lost the game
        if(gameService.isGameOver()) {
            return ViewNames.GAME_OVER; // Show Game Over view
        }
        return ViewNames.PLAY; // Show Play view
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess) {
        log.info("processMessage method called");
        log.info("guess = {}", guess);
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY; // Redirects to play view
    }

    @GetMapping(GameMappings.RESTART)
    public String restart() {
        gameService.reset();
        return GameMappings.REDIRECT_PLAY; // Redirects to play view
    }
}
