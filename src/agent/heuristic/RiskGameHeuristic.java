package agent.heuristic;

import java.util.List;
import java.util.Set;

import game.model.GameBoard;
import game.model.Node;
import game.model.Pair;
import game.model.Player;

public class RiskGameHeuristic implements GameHeuristic {

	@Override
	public int calculateHeuristicScore(Player player, GameBoard board) {
		List<Pair<Integer, Integer>> attackingEdges = board.getAttackingEdges();
		Set<Node> oppNodes = board.getPlayerNodesSet(player.reverseTurn());
		List<Set<Node>> continents = board.getContinents();
		int totalCost = 0;
		for (int i= 0; i < attackingEdges.size(); i++) {
			Node oppNode, plNode;
			if (board.node_belongs_to(player.reverseTurn(), attackingEdges.get(i).first)) {
				oppNode = board.getNodeById(player.reverseTurn(), attackingEdges.get(i).first);
				plNode = board.getNodeById(player, attackingEdges.get(i).second);
			} else {
				plNode = board.getNodeById(player, attackingEdges.get(i).first);
				oppNode = board.getNodeById(player.reverseTurn(), attackingEdges.get(i).second);
			}
			totalCost += (oppNode.getArmies() - plNode.getArmies());
		}
		totalCost += oppNodes.size();
		for (int i = 0; i < continents.size(); i++) {
			boolean completeCont = true;
			for (Node node:continents.get(i) ) {
				if (!oppNodes.contains(node)) {
					completeCont = false;
				}
			}
			if (completeCont) {
				totalCost += continents.get(i).size();
			}
		}
		return totalCost;
	}

}
