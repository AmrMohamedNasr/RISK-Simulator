package agent.search.non_ai;

import java.util.List;
import java.util.Set;

import agent.Agent;
import game.model.GameBoard;
import game.model.Node;
import game.model.Pair;
import game.model.Player;
import game.model.info_capsules.Attack;
/**
 * 
 * @author Michael
 * Agent that places its armies like the completely passive agent,
 * then conquers only one vertex (if it can), such that it loses as few armies as possible.
 */
public class PacifistAgent implements Agent {

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
	
	public PacifistAgent(Player player) {
		this.player = player;
	}
	
	@Override
	public String getAgentName() {
		return "Pacifist Agent";
	}

	@Override
	public Player getAgentPlayer() {
		return this.player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		int minArmies = Integer.MAX_VALUE;
		int minDamage = Integer.MAX_VALUE;
		Node temp = null;
		Attack minAttack = new Attack(false, 0, 0, 0);
		Set<Node> nodes = board.getPlayerNodesSet(player);
		for (Node node: nodes) {
			if (minArmies > node.getArmies() ||
					(minArmies == node.getArmies() 
					&& (temp != null && temp.getId() > node.getId() || temp == null))) {
				temp = node;
				minArmies = node.getArmies();
			}
		}
		placeNode = temp;
		
		List<Pair<Integer, Integer>> attackingEdges = board.getAttackingEdges();
		for (int i = 0; i < attackingEdges.size(); i++) {
			Node plNode, opNode;
			if (board.node_belongs_to(player, attackingEdges.get(i).first)) {
				plNode = board.getNodeById(player, attackingEdges.get(i).first);
				opNode = board.getNodeById(player.reverseTurn(), attackingEdges.get(i).second);
			} else {
				plNode = board.getNodeById(player, attackingEdges.get(i).second);
				opNode = board.getNodeById(player.reverseTurn(), attackingEdges.get(i).first);
			}
			if (temp == plNode) {
			
				if (plNode.getArmies() + board.get_turn_unit_number(player) - opNode.getArmies() > 1 && opNode.getArmies() < minDamage || 
						(plNode.getArmies() - opNode.getArmies() > 1 && opNode.getArmies() == minDamage && minAttack.src == plNode.getId() )) {
					minDamage = opNode.getArmies();
					// assume all possible armies will go to new node conquered. 
					minAttack = new Attack(true, plNode.getId(), opNode.getId(), plNode.getArmies() - opNode.getArmies() - 1);
				}
			} else {
				if (plNode.getArmies() - opNode.getArmies() > 1 && opNode.getArmies() < minDamage || 
						(plNode.getArmies() - opNode.getArmies() > 1 && opNode.getArmies() == minDamage && minAttack.src == plNode.getId() )) {
					minDamage = opNode.getArmies();
					// assume all possible armies will go to new node conquered. 
					minAttack = new Attack(true, plNode.getId(), opNode.getId(), plNode.getArmies() - opNode.getArmies() - 1);
				}
			}
		}
		this.attack = minAttack;
	}

	@Override
	public int place_action() {
		if (placeNode == null) {
			throw new RuntimeException("Must place armies in a node.");
		}
		return placeNode.getId();
	}

	@Override
	public Attack attack_action() {
		return this.attack;
	}


}
