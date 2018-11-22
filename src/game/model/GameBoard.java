package game.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import game.model.info_capsules.Attack;

/**
 * Game board interface.
 * 
 * @author amrnasr
 *
 */
public interface GameBoard extends Comparable<GameBoard> {
	public List<GameBoard> generateBoardPlacementChildren(Player player);

	public List<GameBoard> generateBoardAttackChildren(Player player);

	public GameBoard copyBoard();

	public Set<Node> getPlayerNodesSet(Player player);

	public List<Node> getPlayerNodes(Player player);

	public Map<Integer, Node> getPlayerNodesMap(Player player);

	public List<Attack> getPlayerPossibleAttacks(Player player);

	public boolean node_belongs_to(Player player, int node);

	public int get_turn_unit_number(Player player);

	public void place_unit(Player player, int node);

	public void attack_node(Player player, Attack attack);

	public boolean isGameOver();

	public Node getNodeById(Player player, int node);

	public List<Pair<Integer, Integer>> getAttackingEdges();

	public List<Pair<Integer, Integer>> getEdges();

	public List<Set<Node>> getContinents();

	public void setCost(int cost);

	public int getCost();
}
