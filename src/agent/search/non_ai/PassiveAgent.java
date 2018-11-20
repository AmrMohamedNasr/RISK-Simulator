package agent.search.non_ai;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import agent.Agent;
import agent.search.SearchAgent;
import game.model.GameBoard;
import game.model.Node;
import game.model.Player;
import game.model.info_capsules.Attack;
/**
 * 
 * @author Michael
 * Agent that never attacks, and always places all its additional armies
 * on the vertex that has the fewest armies, breaking ties by favoring the lowest-numbered vertex.
 */
public class PassiveAgent implements Agent {
	/**
	 * player uses this agent.
	 */
	private Player player;
	/**
	 * node to place armies in.
	 */
	private Node placeNode;
	/**
	 * Attack action. 
	 */
	private Attack attack;
	
	public PassiveAgent(Player player) {
		this.player = player;
		this.attack = new Attack(false, 0, 0, 0);
	}
	@Override
	public String getAgentName() {
		return this.getClass().getName();
	}

	@Override
	public Player getAgentPlayer() {
		return player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		int min = Integer.MAX_VALUE;
		Node temp = null;
		Set<Node> nodes = board.getPlayerNodesSet(player);
		for (Node node: nodes) {
			if (min > node.getArmies() ||
					(min == node.getArmies() 
					&& (temp != null && temp.getId() > node.getId() || temp == null))) {
				temp = node;
				min = node.getArmies();
			}
		}
		placeNode = temp;
	}

	@Override
	public int place_action() {
		
		return placeNode.getId();
	}

	@Override
	public Attack attack_action() {
		
		return attack;
	}

	

}
