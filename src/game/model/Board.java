package game.model;

import java.util.List;
import java.util.Set;

import game.model.info_capsules.Attack;

/**
 * Board implementation of the Risk.
 * @author amrnasr
 *
 */
public class Board implements GameBoard {
	private List<Node> player_1;
	private Set<Node> player_1_set;
	private List<Node> player_2;
	private Set<Node> player_2_set;
	private List<Pair<Integer, Integer>> edges;
	private List<Pair<Integer, Integer>> attackingEdges;
	private boolean last_turn_attack_1;
	private boolean last_turn_attack_2;
	private List<Set<Node>> continents;
	private List<Integer> continents_bonus;
	
	public Board(List<Node> player_1, List<Node> player_2, List<Pair<Integer, Integer>> edges,
			List<Set<Node>> continents, List<Integer> continentBonus) {
		this.player_1 = player_1;
		this.player_2 = player_2;
		this.edges = edges;
		this.continents = continents;
		this.continents_bonus = continentBonus;
	}
	
	@Override
	public List<GameBoard> generateBoardPlacementChildren(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<GameBoard> generateBoardAttackChildren(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GameBoard copyBoard() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Node> getPlayerNodesSet(Player player) {
		if (player == null) {
			throw new RuntimeException("Must assign a player");
		}
		if (player == Player.PLAYER_1) { 
			return player_1_set;
		} else {
			return player_2_set;
		}
	}
	@Override
	public List<Node> getPlayerNodes(Player player) {
		if (player == null) {
			throw new RuntimeException("Must assign a player");
		}
		if (player == Player.PLAYER_1) { 
			return player_1;
		} else {
			return player_2;
		}
	}
	@Override
	public List<Attack> getPlayerPossibleAttacks(Player player) {
		// TODO Amr-generated method stub
		return null;
	}
	@Override
	public boolean node_belongs_to(Player player, int node) {
		if (player == null) {
			throw new RuntimeException("Must assign a player");
		}
		Set<Node> plNodes;
		if (player == Player.PLAYER_1) { 
			plNodes = player_1_set;
		} else {
			plNodes = player_2_set;
		}
		for (Node nodeT: plNodes) {
			if (nodeT.getId() == node) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int get_turn_unit_number(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void place_unit(Player player, int node) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void attack_node(Player player, Attack attack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isGameOver() {
		if (player_1.isEmpty())
			return true;
		if (player_2.isEmpty())
			return true;
		return false;
	}

	

	@Override
	public Node getNodeById(Player player, int node) {
		Set<Node> plNodes;
		if (player == Player.PLAYER_1) { 
			plNodes = player_1_set;
		} else {
			plNodes = player_2_set;
		}
		for (Node nodeT: plNodes) {
			if (nodeT.getId() == node) {
				return nodeT;
			}
		}
		return null;
	}

	@Override
	public List<Pair<Integer, Integer>> getAttackingEdges() {
		return this.attackingEdges;
	}

	@Override
	public List<Pair<Integer, Integer>> getEdges() {
		return this.edges;
	}
	
	
	
	
	
}
