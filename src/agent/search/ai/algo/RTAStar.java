package agent.search.ai.algo;

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

public class RTAStar {
	/**
	 * Heuristic function used in search.
	 */
	private GameHeuristic heuristic;
	private int limit;
	Set<State> solSet;
	/**
	 * Constructor for the informed search method A*.
	 * 
	 * @param heuristic
	 *            Must supply a heuristic function to be used in the search.
	 */
	public RTAStar(GameHeuristic heuristic, int lim) {
		if (heuristic == null) {
			throw new RuntimeException("Must give heurstic function to A* search");
		}
		this.heuristic = heuristic;
		this.limit = lim;
		solSet = new HashSet<State>();
	}

	public boolean goalTest(RiskState state, Player player) {
		return state.getBoard().getPlayerNodesMap(player.reverseTurn()).size() == 0;
	}
	public State search(RiskState root, List<State> expanded_list, Player player) {
		PriorityQueue<State> frontier = new PriorityQueue<State>();
		Set<State> exploreHash = new HashSet<State>();
		Map<State, Double> frontier_map = new HashMap<State, Double>();
		root.setHeuristicCost(heuristic.calculateHeuristicScore(player, root.getBoard()));
		frontier.add(root);
		frontier_map.put(root, root.getCost());
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
			} else if (target != null && target.getCost() > current.getCost() && !solSet.contains(current)) {
				target = current;
			} else if (current != root && !solSet.contains(current)) {
				target = current;
			}
			if (current.getActualCost() <= limit) {
				current.generateChildrenStates();
				for (int i = 0; i < current.getChildrenStates().size(); i++) {
					State child = current.getChildrenStates().get(i);
					if (!exploreHash.contains(child) && !frontier_map.containsKey(child)) {
						child.setHeuristicCost(heuristic.calculateHeuristicScore(player, ((RiskState)child).getBoard()));
						frontier.add(child);
						frontier_map.put(child, child.getCost());
					} else if (frontier_map.containsKey(child)) {
						child.setHeuristicCost(heuristic.calculateHeuristicScore(player, ((RiskState)child).getBoard()));
						if (child.getCost() < frontier_map.get(child)) {
							frontier_map.remove(child);
							frontier.remove(child);
							frontier.add(child);
							frontier_map.put(child, child.getCost());
						}
					}
				}
			}
		}
		solSet.add(target);
		State result = null;
		if (target != null) {
			result = target;
			while(result.getParent() != null && result.getParent().getParent() != null) {
				result = result.getParent();
			}
		}
		return result;
	}
}
