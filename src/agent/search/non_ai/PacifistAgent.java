package agent.search.non_ai;

import agent.Agent;
import agent.search.SearchAgent;
import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.Attack;
/**
 * 
 * @author Michael
 * Agent that places its armies like the completely passive agent,
 * then conquers only one vertex (if it can), such that it loses as few armies as possible.
 */
public class PacifistAgent implements Agent {

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


}
