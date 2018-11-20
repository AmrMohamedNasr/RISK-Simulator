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
	private boolean last_turn_attack_1;
	private boolean last_turn_attack_2;
	private List<Set<Node>> continents;
	private List<Integer> continents_bonus;
	
	public Board(List<Node> player_1, List<Node> player_2, List<Pair<Integer, Integer>> edges,
			List<Set<Node>> continents, List<Integer> continentBonus) {
		
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Node> getPlayerNodes(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Attack> getPlayerPossibleAttacks(Player player) {
		// TODO Amr-generated method stub
		return null;
	}
	@Override
	public boolean node_belongs_to(Player player, int node) {
		// TODO Auto-generated method stub
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
	
	
	
}
