package game;

import agent.Agent;
import game.model.GameBoard;
import game.model.info_capsules.EndGameInformation;
import gui.panel.GameWindow;

public interface Game {
	void play_game(GameBoard board, Agent player_1, Agent player_2, GameWindow window);
	EndGameInformation get_game_results();
}
