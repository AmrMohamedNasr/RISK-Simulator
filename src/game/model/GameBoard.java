package game.model;

import java.util.List;
import java.util.Set;

import game.model.info_capsules.Attack;
/**
 * Game board interface.
 * @author amrnasr
 *
 */
public interface GameBoard {
	public List<GameBoard> generateBoardPlacementChildren(Player player);
	
	public List<GameBoard> generateBoardAttackChildren(Player player);
	
	public GameBoard copyBoard();
	
	public Set<Node> getPlayerNodesSet(Player player);
	
	public List<Node> getPlayerNodes(Player player);
	
	public boolean node_belongs_to(Player player, int node);
	
	public int get_turn_unit_number(Player player);
	
	public void place_unit(Player player, int node);
	
	public void attack_node(Player player, Attack attack);
}
