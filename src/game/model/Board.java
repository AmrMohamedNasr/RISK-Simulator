package game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game.model.info_capsules.Attack;

/**
 * Board implementation of the Risk.
 * 
 * @author amrnasr
 *
 */
public class Board implements GameBoard {
	private Map<Integer, Node> player_1_set;
	private Map<Integer, Node> player_2_set;
	private List<Pair<Integer, Integer>> edges;
	private List<Pair<Integer, Integer>> attackingEdges;
	private boolean last_turn_attack_1;
	private boolean last_turn_attack_2;
	private List<Set<Node>> continents;
	private List<Integer> continents_bonus;

	public Board(Set<Node> player_1, Set<Node> player_2, List<Pair<Integer, Integer>> edges,
			List<Set<Node>> continents, List<Integer> continentBonus) {
		this.player_1_set = new HashMap<Integer, Node>();
		this.player_2_set = new HashMap<Integer, Node>();
		for (Node n : player_1) {
			this.player_1_set.put(n.getId(), n);
		}
		for (Node n : player_2) {
			this.player_2_set.put(n.getId(), n);
		}
		this.edges = edges;
		this.attackingEdges = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < edges.size(); i++) {
			if (player_2_set.containsKey(edges.get(i).first) != player_2_set.containsKey(edges.get(i).second)) {
				this.attackingEdges.add(edges.get(i));
			}
		}
		this.continents = continents;
		this.continents_bonus = continentBonus;
		last_turn_attack_1 = false;
		last_turn_attack_2 = false;
	}

	public Board(Map<Integer, Node> player_1, Map<Integer, Node> player_2, List<Pair<Integer, Integer>> edges,
			List<Set<Node>> continents, List<Integer> continentBonus) {
		this.player_1_set = player_1;
		this.player_2_set = player_2;
		
		this.edges = edges;
		this.attackingEdges = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < edges.size(); i++) {
			if (player_2.containsKey(edges.get(i).first) != player_2.containsKey(edges.get(i).second)) {
				this.attackingEdges.add(edges.get(i));
			}
		}
		this.continents = continents;
		this.continents_bonus = continentBonus;
		last_turn_attack_1 = false;
		last_turn_attack_2 = false;
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
		Map<Integer, Node> tempPly1Set = copyMap(this.player_1_set);
		Map<Integer, Node> tempPly2Set = copyMap(this.player_2_set);
		return new Board(tempPly1Set, tempPly2Set, edges, continents, continents_bonus);
	}
	
	private Map<Integer, Node> copyMap(Map<Integer, Node> nodes) {
		Map<Integer, Node> tempNodes = new HashMap<Integer, Node>();
		for(Node node: nodes.values()) {
			Node temp = new Node(node.getId(), node.getArmies());
			temp.setEdges(node.getEdges());
			tempNodes.put(temp.getId(), temp);
		}
		return tempNodes;
	}

	@Override
	public Set<Node> getPlayerNodesSet(Player player) {
		if (player == null) {
			throw new RuntimeException("Must assign a player");
		}
		if (player == Player.PLAYER_1) {
			return new HashSet<Node>(player_1_set.values());
		} else {
			return new HashSet<Node>(player_2_set.values());
		}
	}

	@Override
	public List<Node> getPlayerNodes(Player player) {
		if (player == null) {
			throw new RuntimeException("Must assign a player");
		}
		if (player == Player.PLAYER_1) {
			return new ArrayList<Node>(player_1_set.values());
		} else {
			return new ArrayList<Node>(player_2_set.values());
		}
	}
	
	public Map<Integer, Node> getPlayerNodesMap(Player player) {
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
	public List<Attack> getPlayerPossibleAttacks(Player player) {
		List<Attack> attacks = new ArrayList<Attack>();
		Map<Integer, Node> attacker_set, attacked_set;
		if (player == Player.PLAYER_1) {
			attacker_set = player_1_set;
			attacked_set = player_2_set;
		} else {
			attacker_set = player_2_set;
			attacked_set = player_1_set;
		}
		for (int i = 0; i < this.attackingEdges.size(); i++) {
			Pair<Integer, Integer> edge = this.attackingEdges.get(i);
			int src, dest;
			if (attacker_set.containsKey(edge.first)) {
				src = edge.first;
				dest = edge.second;
			} else {
				src = edge.second;
				dest = edge.first;
			}
			Node srcN = attacker_set.get(src);
			Node destN = attacked_set.get(dest);
			for (int u = 1; u < srcN.getArmies() - destN.getArmies() - 1; u++) {
				attacks.add(new Attack(true, src, dest, u));
			}
		}
		attacks.add(new Attack(false, 0, 0, 0));
		return attacks;
	}

	public List<Attack> getPlayerUniqueAttacks(Player player) {
		List<Attack> attacks = new ArrayList<Attack>();
		Map<Integer, Node> attacker_set, attacked_set;
		if (player == Player.PLAYER_1) {
			attacker_set = player_1_set;
			attacked_set = player_2_set;
		} else {
			attacker_set = player_2_set;
			attacked_set = player_1_set;
		}
		for (int i = 0; i < this.attackingEdges.size(); i++) {
			Pair<Integer, Integer> edge = this.attackingEdges.get(i);
			int src, dest;
			if (attacker_set.containsKey(edge.first)) {
				src = edge.first;
				dest = edge.second;
			} else {
				src = edge.second;
				dest = edge.first;
			}
			Node srcN = attacker_set.get(src);
			Node destN = attacked_set.get(dest);
			if (2 < srcN.getArmies() - destN.getArmies()) {
				attacks.add(new Attack(true, src, dest, srcN.getArmies() - destN.getArmies() - 1));
			}
		}
		attacks.add(new Attack(false, 0, 0, 0));
		return attacks;
	}
	
	@Override
	public boolean node_belongs_to(Player player, int node) {
		if (player == null) {
			throw new RuntimeException("Must assign a player");
		}
		Map<Integer, Node> plNodes;
		if (player == Player.PLAYER_1) {
			plNodes = player_1_set;
		} else {
			plNodes = player_2_set;
		}
		return plNodes.containsKey(node);
	}

	@Override
	public int get_turn_unit_number(Player player) {
		int sum = 0;
		Map<Integer, Node> set = getPlayerNodesMap(player);
		if (player == Player.PLAYER_1 && last_turn_attack_1) {
			sum += 2;
		} else if (player == Player.PLAYER_2 && last_turn_attack_2) {
			sum += 2;
		}
		sum += Math.max(3, set.size() / 3);
		for (int i = 0; i < continents.size(); i++) {
			boolean all = true;
			for (Node node : continents.get(i)) {
				if (!set.containsKey(node.getId())) {
					all = false;
					break;
				}
			}
			if (all) {
				sum += continents_bonus.get(i);
			}
		}
		return sum;
	}

	@Override
	public void place_unit(Player player, int node) {
		Node n = getNodeById(player, node);
		if (n == null) {
			return;
		}
		n.setArmies(n.getArmies() + get_turn_unit_number(player));
	}

	@Override
	public void attack_node(Player player, Attack attack) {
		boolean willAttack = attack.willAttack;
		int src = attack.src;
		int dest = attack.dest;
		int units_to_move = attack.units_to_move;
		if (player == Player.PLAYER_1) {
			this.last_turn_attack_1 = willAttack && units_to_move > 0 && player_1_set.containsKey(src) != player_1_set.containsKey(dest);
		} else {
			this.last_turn_attack_2 = willAttack && units_to_move > 0 && player_1_set.containsKey(src) != player_1_set.containsKey(dest);
		}
		if (!willAttack || units_to_move < 1) {
			return;
		}

		if ((node_belongs_to(Player.PLAYER_1, src) && node_belongs_to(Player.PLAYER_1, dest))
				|| (node_belongs_to(Player.PLAYER_2, src) && node_belongs_to(Player.PLAYER_2, dest))) {
			return;
		}
		Map<Integer, Node> attacker_set = getPlayerNodesMap(player), attacked_set = getPlayerNodesMap(player.reverseTurn());
		Node source = getNodeById(player, src);
		Node destination = getNodeById(player.reverseTurn(), dest);
		if (source == null || destination == null) {
			return;
		}
		if (source.getArmies() - units_to_move - destination.getArmies() < 1) {
			return;
		}
		source.setArmies(source.getArmies() - destination.getArmies() - units_to_move);
		destination.setArmies(units_to_move);
		attacker_set.put(destination.getId(), destination);
		attacked_set.remove(destination.getId());
		for (int i = this.attackingEdges.size() - 1; i >= 0; i--) {
			if (this.attackingEdges.get(i).first == destination.getId()
					|| this.attackingEdges.get(i).second == destination.getId()) {
				this.attackingEdges.remove(i);
			}
		}
		for (int i = 0; i < destination.getEdges().size(); i++) {
			if (player_1_set.containsKey(destination.getId()) 
					!= player_1_set.containsKey(destination.getEdges().get(i))) {
				this.attackingEdges.add(new Pair<Integer, Integer>(destination.getId(),
						destination.getEdges().get(i)));
			}
		}
	}

	@Override
	public boolean isGameOver() {
		return player_1_set.isEmpty() || player_2_set.isEmpty();
	}

	@Override
	public Node getNodeById(Player player, int node) {
		Map<Integer, Node> plNodes;
		if (player == Player.PLAYER_1) {
			plNodes = player_1_set;
		} else {
			plNodes = player_2_set;
		}
		if (plNodes.containsKey(node)) {
			return plNodes.get(node);
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

	@Override
	public List<Set<Node>> getContinents() {
		return this.continents;
	}

}
