package agent.search.ai;

import java.util.List;
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
		int tempPlace;
		Attack tempAttack = new Attack(false, 0, 0, 0);
		PriorityQueue<GameBoard> frontier = new PriorityQueue<>();
		frontier.add(board);
		while (!frontier.isEmpty()) {
			GameBoard gameBoard = frontier.poll();

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
		// TODO Auto-generated method stub
		return 0;
	}

}
