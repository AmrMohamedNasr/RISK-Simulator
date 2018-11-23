package agent.heuristic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game.model.GameBoard;
import game.model.Node;
import game.model.Pair;
import game.model.Player;
import game.model.info_capsules.Attack;

public class RiskGameHeuristic implements GameHeuristic {

	@Override
	public int calculateHeuristicScore(Player player, GameBoard board) {
		List<Pair<Integer, Integer>> attackingEdges = board.getAttackingEdges();
		Set<Node> oppNodes = board.getPlayerNodesSet(player.reverseTurn());
		Set<Node> visitedOppNodes = new HashSet<>();
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
			if (plNode.getArmies() - oppNode.getArmies() > 1 && !visitedOppNodes.contains(oppNode)) {
				visitedOppNodes.add(oppNode);
				totalCost += 1;
			} else if (plNode.getArmies() - oppNode.getArmies() <= 1 && !visitedOppNodes.contains(oppNode)) {
				visitedOppNodes.add(oppNode);
				totalCost += 2;
			}
		}
		for (Node node: oppNodes) {
			if (!visitedOppNodes.contains(node)) {
				totalCost += 1;
			}
		}
		return totalCost;
	}

}
