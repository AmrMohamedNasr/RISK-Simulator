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
 * @author Michael Agent that always places all its bonus armies on the vertex
 *         with the most armies, and greedily attempts to attack so as to cause
 *         the most damage i.e: to prevent its opponent getting a continent
 *         bonus (the largest possible).
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

	/**
	 * temporary Variables for Placement and Attack.
	 */
	private int maxDamage;
	private int maxArmies;
	private Node tempPlaceNode;
	private Attack tempAttack;
	public AggressiveAgent(Player player) {
		this.player = player;
	}

	@Override
	public String getAgentName() {
		return "Aggressive Agent";
	}

	@Override
	public Player getAgentPlayer() {
		return this.player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		maxDamage = 0;
		maxArmies = 0;
		tempPlaceNode = null;
		tempAttack = new Attack(false, 0, 0, 0);
		Set<Node> nodes = board.getPlayerNodesSet(player);
		for (Node node : nodes) {
			if (node.getArmies() > maxArmies
					|| (node.getArmies() == maxArmies && node.getId() < tempPlaceNode.getId())) {
				maxArmies = node.getArmies();
				tempPlaceNode = node;
			}
		}
		List<Pair<Integer, Integer>> attackingEdges = board.getAttackingEdges();
		Set<Node> oppNodes = board.getPlayerNodesSet(player.reverseTurn());
		List<Set<Node>> continents = board.getContinents();
		for (int i = 0; i < continents.size(); i++) {
			boolean completeCont = true;
			for (Node node:continents.get(i) ) {
				if (!oppNodes.contains(node)) {
					completeCont = false;
				}
			}
			if (completeCont) {
				for (int j = 0; j < attackingEdges.size(); j++) {
					Node plNode, opNode;
					if (board.node_belongs_to(player, attackingEdges.get(i).first)) {
						plNode = board.getNodeById(player, attackingEdges.get(i).first);
						opNode = board.getNodeById(player.reverseTurn(), attackingEdges.get(i).second);
					} else {
						plNode = board.getNodeById(player, attackingEdges.get(i).second);
						opNode = board.getNodeById(player.reverseTurn(), attackingEdges.get(i).first);
					}
					if (continents.get(i).contains(opNode)) {
						setMaxAttack(plNode, opNode, board);
					}	
				}
			}
		}
		if (maxDamage == 0) {
			for (int i = 0; i < attackingEdges.size(); i++) {
				Node plNode, opNode;
				if (board.node_belongs_to(player, attackingEdges.get(i).first)) {
					plNode = board.getNodeById(player, attackingEdges.get(i).first);
					opNode = board.getNodeById(player.reverseTurn(), attackingEdges.get(i).second);
				} else {
					plNode = board.getNodeById(player, attackingEdges.get(i).second);
					opNode = board.getNodeById(player.reverseTurn(), attackingEdges.get(i).first);
				}
				setMaxAttack(plNode, opNode, board);
			}
		}
		this.attack = tempAttack;
		this.placeNode = tempPlaceNode;
	}
		
	private void setMaxAttack(Node plNode, Node opNode, GameBoard board) {
		if (plNode == tempPlaceNode) {
			if (plNode.getArmies() + board.get_turn_unit_number(player) - opNode.getArmies() > 1 && opNode.getArmies() > maxDamage
					|| (plNode.getArmies() + board.get_turn_unit_number(player) - opNode.getArmies() > 1 && opNode.getArmies() == maxDamage
							&& tempAttack.src == plNode.getId())) {
				maxDamage = opNode.getArmies();
				// assume all possible armies will go to new node conquered.
				tempAttack = new Attack(true, plNode.getId(), opNode.getId(),
						plNode.getArmies() + board.get_turn_unit_number(player) - opNode.getArmies() - 1);
			}
		} else {
			if (plNode.getArmies() - opNode.getArmies() > 1 && opNode.getArmies() > maxDamage
					|| (plNode.getArmies() - opNode.getArmies() > 1 && opNode.getArmies() == maxDamage
							&& tempAttack.src == plNode.getId())) {
				maxDamage = opNode.getArmies();
				// assume all possible armies will go to new node conquered.
				tempAttack = new Attack(true, plNode.getId(), opNode.getId(),
						plNode.getArmies() - opNode.getArmies() - 1);
			}
		}
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
