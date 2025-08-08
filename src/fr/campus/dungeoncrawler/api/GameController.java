package fr.campus.dungeoncrawler.api;

import fr.campus.dungeoncrawler.Menu;
import fr.campus.dungeoncrawler.board.Game;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // utile si React appelle l'API
public class GameController {

    private Game game;

    @GetMapping("/start")
    public void startGame() {
        Menu.startGame();
    }

    /*
    @PostMapping("/action/newturn")
    public String newturn() {
        if (game == null) return "Jeu non démarré";
        return game.newturn(); // adapte selon ta logique
    }
    */

    @GetMapping("/state")
    public Game getState() {
        return game;
    }
}
