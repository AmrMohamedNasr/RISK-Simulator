package agent.search.ai;

import java.util.ArrayList;
import java.util.List;
import agent.heuristic.GameHeuristic;
import agent.search.SearchAgent;
import agent.search.ai.algo.GreedySearch;
import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.Attack;
import game.state.RiskState;
import game.state.State;

public class GreedyAgent implements SearchAgent {

	/**
	 * heuristic function used in search.
	 */
	private GreedySearch greedySearch;
	private Player player;
	private List<RiskState> solutionPath;
	int solIndex;
	private int exp_nodes;
	
	public GreedyAgent(Player player, GameHeuristic heuristic) {
		if (heuristic == null) {
			throw new RuntimeException("Must give heurstic function to A* search");
		}
		this.player = player;
		this.greedySearch = new GreedySearch(heuristic);
		this.exp_nodes = 0;
		this.solIndex = 0;
		this.solutionPath = null;
	}

	@Override
	public String getAgentName() {
		return "Greedy Agent";
	}

	@Override
	public Player getAgentPlayer() {
		return this.player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		if (this.solIndex == 0 || this.solIndex + 1 == this.solutionPath.size()) {
			List<State> states = new ArrayList<State>();
			this.solutionPath = this.greedySearch.search(new RiskState(
					board, player, 0, 0, null, null, true),
				states, player);
			this.exp_nodes += states.size();
			this.solIndex = 0;
		}
		this.solIndex++;
	}

	@Override
	public int place_action() {
		return this.solutionPath.get(solIndex).getLast_place();
	}

	@Override
	public Attack attack_action() {
		return this.solutionPath.get(solIndex).getAttack();
	}

	@Override
	public int getExpandedNodesTotalNumber() {
		return this.exp_nodes;
	}


}
