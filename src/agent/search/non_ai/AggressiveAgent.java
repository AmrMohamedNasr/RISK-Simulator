package agent.search.non_ai;

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
 * Agent that always places all its bonus armies on the vertex with the most
 * armies, and greedily attempts to attack so as to cause the most damage
 * i.e: to prevent its opponent getting a continent bonus (the largest possible).
 */
public class AggressiveAgent implements Agent {

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
	
	public AggressiveAgent(Player player) {
		this.player = player;
	}
	
	@Override
	public String getAgentName() {
		return this.getClass().getName();
	}

	@Override
	public Player getAgentPlayer() {
		return this.player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		int maxArmies = 0;
		Node tempPlaceNode = null;
		int maxDamage = 0;
		Node tempDamageNode = null;
		Set<Node> nodes = board.getPlayerNodesSet(player);
		for (Node node: nodes) {
			if (node.getArmies() > maxArmies) {
				maxArmies = node.getArmies();
				tempPlaceNode = node;
			}
			List<Integer> edges = node.getEdges();
			for (int i = 0; i < edges.size(); i++) {
				if (!board.node_belongs_to(player, edges.get(i))) {
					
				}
			}
		}
		
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
