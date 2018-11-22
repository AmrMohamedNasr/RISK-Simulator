package agent.heuristic;

import java.util.List;

import game.model.GameBoard;
import game.model.Node;
import game.model.Pair;
import game.model.Player;

public class RiskGameHeuristic implements GameHeuristic {

	@Override
	public int calculateHeuristicScore(Player player, GameBoard board) {
		List<Pair<Integer, Integer>> attackingEdges = board.getAttackingEdges();
		int totalCost = 0;
		for (int i= 0; i < attackingEdges.size(); i++) {
			Node oppNode;
			if (board.node_belongs_to(player.reverseTurn(), attackingEdges.get(i).first)) {
				
			}
		}
		return 0;
	}

}
