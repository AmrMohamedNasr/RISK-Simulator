package agent.heuristic;

import game.model.GameBoard;
import game.model.Player;

public interface GameHeuristic {
	int calculateHeuristicScore(Player player, GameBoard board);
}
