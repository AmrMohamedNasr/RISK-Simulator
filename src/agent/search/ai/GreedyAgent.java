package agent.search.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import agent.heuristic.GameHeuristic;
import agent.search.SearchAgent;
import game.model.GameBoard;
import game.model.Node;
import game.model.Player;
import game.model.info_capsules.Attack;

public class GreedyAgent implements SearchAgent {

	/**
	 * heuristic function used in search.
	 */
	private GameHeuristic heuristic;
	private Player player;
	private Attack attack;
	private Node placeNode;
	private int expandedNodesNumber;

	public GreedyAgent(Player player, GameHeuristic heuristic) {
		if (heuristic == null || player == null) {
			throw new RuntimeException("Must give a right heurstic function and a player to Greedy search");
		}
		this.heuristic = heuristic;
		this.player = player;
	}

	@Override
	public String getAgentName() {
		return "GreedyAgent";
	}

	@Override
	public Player getAgentPlayer() {
		return player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		expandedNodesNumber = 0;
		int tempPlace;
		Attack tempAttack = new Attack(false, 0, 0, 0);
		PriorityQueue<GameBoard> frontier = new PriorityQueue<>();
		List<GameBoard> expandedList = new ArrayList<>();
		Map<GameBoard, Integer> frontier_map = new HashMap<>();
		frontier.add(board);
		frontier_map.put(board, board.getCost());
		while (!frontier.isEmpty()) {
			GameBoard gameBoard = frontier.poll();
			frontier_map.remove(gameBoard);
			expandedList.add(gameBoard);
			if (gameBoard.isGameOver()) {
				expandedNodesNumber = expandedList.size();
				break;
			}
			List<GameBoard> placements = gameBoard.generateBoardPlacementChildren(player);
			for (GameBoard placeBoard : placements) {
				List<GameBoard> attacks = placeBoard.generateBoardAttackChildren(player);
				for (GameBoard attackBoard : attacks) {
					if (!frontier.contains(attackBoard) && !expandedList.contains(attackBoard)) {
						attackBoard.setCost(heuristic.calculateHeuristicScore(player, attackBoard));
						frontier.add(attackBoard);
					} else if (frontier.contains(attackBoard)) {
						attackBoard.setCost(heuristic.calculateHeuristicScore(player, attackBoard));
						if (attackBoard.getCost() < frontier_map.get(attackBoard)) {
							frontier.remove(attackBoard);
							frontier_map.remove(attackBoard);
							frontier.add(attackBoard);
							frontier_map.put(attackBoard, attackBoard.getCost());
						}
					} else if (expandedList.contains(attackBoard)) {
						expandedNodesNumber = expandedList.size();
						break;
					}
				}
			}
		}
	}

	@Override
	public int place_action() {
		if (placeNode == null) {
			throw new RuntimeException("Must place armies in a node.");
		}
		return placeNode.getId();
	}

	@Override
	public Attack attack_action() {
		return attack;
	}

	@Override
	public int getExpandedNodesTotalNumber() {
		return expandedNodesNumber;
	}

}
