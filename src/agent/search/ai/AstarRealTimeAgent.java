package agent.search.ai;

import java.util.ArrayList;
import java.util.List;

import agent.heuristic.GameHeuristic;
import agent.search.SearchAgent;
import agent.search.ai.algo.RTAStar;
import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.Attack;
import game.state.RiskState;
import game.state.State;

public class AstarRealTimeAgent implements SearchAgent {


	/**
	 * heuristic function used in search.
	 */
	private RTAStar rtastar;
	private Player player;
	private Attack move;
	private int move_p;
	private int exp_nodes;
	private static final int limit = 4;
	
	public AstarRealTimeAgent(Player player, GameHeuristic heuristic) {
		if (heuristic == null) {
			throw new RuntimeException("Must give heurstic function to A* search");
		}
		this.player = player;
		this.rtastar = new RTAStar(heuristic, limit);
		this.exp_nodes = 0;
	}

	@Override
	public String getAgentName() {
		return "RT-A* Agent";
	}

	@Override
	public Player getAgentPlayer() {
		return this.player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		List<State> states = new ArrayList<State>();
		RiskState res = (RiskState) this.rtastar.search(new RiskState(
				board, player, 0, 0, null, null, false),
			states, player);
		this.exp_nodes += states.size();
		this.move_p = res.getLast_place();
		this.move = res.getAttack();
	}

	@Override
	public int place_action() {
		return this.move_p;
	}

	@Override
	public Attack attack_action() {
		return this.move;
	}

	@Override
	public int getExpandedNodesTotalNumber() {
		return this.exp_nodes;
	}

}
