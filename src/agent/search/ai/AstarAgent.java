package agent.search.ai;

import java.util.ArrayList;
import java.util.List;

import agent.heuristic.GameHeuristic;
import agent.search.SearchAgent;
import agent.search.ai.algo.AStar;
import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.Attack;
import game.state.RiskState;
import game.state.State;

public class AstarAgent implements SearchAgent {

	/**
	 * heuristic function used in search.
	 */
	private AStar astar;
	private Player player;
	private List<RiskState> solPath;
	private int solIndex;
	private int exp_nodes;
	
	public AstarAgent(Player player, GameHeuristic heuristic) {
		if (heuristic == null) {
			throw new RuntimeException("Must give heurstic function to A* search");
		}
		this.player = player;
		this.astar = new AStar(heuristic);
		this.exp_nodes = 0;
		this.solIndex = 0;
		this.solPath = null;
	}

	@Override
	public String getAgentName() {
		return "A* Agent";
	}

	@Override
	public Player getAgentPlayer() {
		return this.player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		if (this.solIndex == 0 || this.solIndex + 1 == this.solPath.size()) {
			List<State> states = new ArrayList<State>();
			this.solPath = this.astar.search(new RiskState(
					board, player, 0, 0, null, null, false),
				states, player);
			this.exp_nodes += states.size();
			this.solIndex = 0;
		} else if (!this.solPath.get(this.solIndex).getBoard().equals(board)) {
			List<State> states = new ArrayList<State>();
			this.solPath = this.astar.search(new RiskState(
					board, player, 0, 0, null, null, false),
				states, player);
			this.exp_nodes += states.size();
			this.solIndex = 0;
		}
		this.solIndex++;
	}

	@Override
	public int place_action() {
		return this.solPath.get(solIndex).getLast_place();
	}

	@Override
	public Attack attack_action() {
		return this.solPath.get(solIndex).getAttack();
	}

	@Override
	public int getExpandedNodesTotalNumber() {
		return this.exp_nodes;
	}

}
