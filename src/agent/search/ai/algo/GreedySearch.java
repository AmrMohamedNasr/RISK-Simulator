package agent.search.ai.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import agent.heuristic.GameHeuristic;
import game.model.Player;
import game.state.RiskState;
import game.state.State;

public class GreedySearch {
	/**
	 * Heuristic function used in search.
	 */
	private GameHeuristic heuristic;

	/**
	 * Constructor for the informed search method A*.
	 * 
	 * @param heuristic
	 *            Must supply a heuristic function to be used in the search.
	 */
	public GreedySearch(GameHeuristic heuristic) {
		if (heuristic == null) {
			throw new RuntimeException("Must give heurstic function to A* search");
		}
		this.heuristic = heuristic;
	}

	public boolean goalTest(RiskState state, Player player) {
		return state.getBoard().getPlayerNodesMap(player.reverseTurn()).size() == 0;
	}
	public List<RiskState> search(RiskState root, List<State> expanded_list, Player player) {
		PriorityQueue<State> frontier = new PriorityQueue<State>();
		Set<State> exploreHash = new HashSet<State>();
		Map<State, Double> frontier_map = new HashMap<State, Double>();
		root.setHeuristicCost(heuristic.calculateHeuristicScore(player, root.getBoard()));
		frontier.add(root);
		frontier_map.put(root, root.getHeuristicCost());
		State current = null, target = null;
		expanded_list.clear();
		while (!frontier.isEmpty()) {
			current = frontier.poll();
			frontier_map.remove(current);
			expanded_list.add(current);
			exploreHash.add(current);
			if (goalTest((RiskState) current, player)) {
				target = current;
				break;
			} else if (target != null && target.getHeuristicCost() > current.getHeuristicCost()) {
				target = current;
			} else if (current != root) {
				target = current;
			}
			current.generateChildrenStates();
			for (int i = 0; i < current.getChildrenStates().size(); i++) {
				State child = current.getChildrenStates().get(i);
				if (!exploreHash.contains(child) && !frontier_map.containsKey(child)) {
					child.setHeuristicCost(heuristic.calculateHeuristicScore(player, ((RiskState)child).getBoard()));
					frontier.add(child);
					frontier_map.put(child, child.getHeuristicCost());
				} else if (frontier_map.containsKey(child)) {
					child.setHeuristicCost(heuristic.calculateHeuristicScore(player, ((RiskState)child).getBoard()));
					if (child.getHeuristicCost() < frontier_map.get(child)) {
						frontier_map.remove(child);
						frontier.remove(child);
						frontier.add(child);
						frontier_map.put(child, child.getHeuristicCost());
					}
				}
			}
		}
		
		List<RiskState> result = new ArrayList<RiskState>();
		if (target != null) {
			while(target != null) {
				result.add(0, (RiskState) target);
				target = target.getParent();
			}
		}
		return result;
	}
}
