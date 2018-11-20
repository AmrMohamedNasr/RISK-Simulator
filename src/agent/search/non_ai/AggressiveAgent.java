package agent.search.non_ai;

import agent.search.SearchAgent;
import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.Attack;
/**
 * 
 * @author Michael
 * Agent that always places all its bonus armies on the vertex with the most
 * armies, and greedily attempts to attack so as to cause the most damage
 * i.e: to prevent its opponent getting a continent bonus (the largest possible).
 */
public class AggressiveAgent implements SearchAgent {

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
