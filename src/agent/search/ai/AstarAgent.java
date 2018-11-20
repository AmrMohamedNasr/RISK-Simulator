package agent.search.ai;

import agent.heuristic.GameHeuristic;
import agent.search.SearchAgent;
import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.Attack;

public class AstarAgent implements SearchAgent {

	
	/**
	 * heuristic function used in search.
	 */
	private GameHeuristic heuristic;
	
	
	
	public AstarAgent(GameHeuristic heuristic) {
		if (heuristic == null) {
			throw new RuntimeException("Must give heurstic function to A* search");
		}
		this.heuristic = heuristic;
	}
	@Override
	public String getAgentName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getAgentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int place_action() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Attack attack_action() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExpandedNodesTotalNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
