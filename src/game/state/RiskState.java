package game.state;

import java.util.ArrayList;
import java.util.List;

import agent.search.non_ai.PassiveAgent;
import game.model.GameBoard;
import game.model.Node;
import game.model.Player;
import game.model.info_capsules.Attack;

public class RiskState implements State {

	private State parent;
	private List<State> children;
	private int actualCost;
	private double heuristicCost;
	private Player player;
	private GameBoard board;
	private int last_place;
	private Attack attack;
	private boolean greedy;
	
	public RiskState(GameBoard board, Player player, int actualCost, int lp, Attack attack, State state, boolean gred) {
		this.board = board;
		this.player = player;
		this.actualCost = actualCost;
		this.last_place = lp;
		this.attack = attack;
		this.parent = state;
		this.greedy = gred;
	}


	@Override
	public void setParent(State state) {
		this.parent = state;
	}

	@Override
	public State getParent() {
		return this.parent;
	}

	@Override
	public int getActualCost() {
		return actualCost;
	}

	@Override
	public double getHeuristicCost() {
		return heuristicCost;
	}

	@Override
	public double getCost() {
		return actualCost + heuristicCost;
	}

	@Override
	public void setActualCost(int cost) {
		this.actualCost = cost;
	}

	@Override
	public void setHeuristicCost(double cost) {
		this.heuristicCost = cost;
	}

	@Override
	public void generateChildrenStates() {
		this.children = new ArrayList<State>();
		PassiveAgent agent = new PassiveAgent(player.reverseTurn());
		List<Node> playerNodes = board.getPlayerNodes(this.player);
		for (int i = 0; i < playerNodes.size(); i++) {
			GameBoard chldBoard = this.board.copyBoard();
			chldBoard.place_unit(this.player, playerNodes.get(i).getId());
			List<Attack>  attacks = chldBoard.getPlayerPossibleAttacks(this.player);
			for (int j = 0; j < attacks.size(); j++) {
				GameBoard atkBoard = chldBoard.copyBoard();
				atkBoard.attack_node(this.player, attacks.get(j));
				if (!atkBoard.isGameOver()) {
					agent.observe_enviroment(atkBoard);
					atkBoard.place_unit(player.reverseTurn(), agent.place_action());
				}
				if (attacks.get(j).willAttack) {
					this.children.add(new RiskState(atkBoard, player, this.actualCost,
							playerNodes.get(i).getId(), attacks.get(j), this, this.greedy));
				} else {
					this.children.add(new RiskState(atkBoard, player, this.actualCost + 1,
							playerNodes.get(i).getId(), attacks.get(j), this, this.greedy));
				}
			}
		}
	}

	public GameBoard getBoard() {
		return board;
	}

	public int getLast_place() {
		return last_place;
	}

	public Attack getAttack() {
		return attack;
	}

	@Override
	public List<State> getChildrenStates() {
		return this.children;
	}
	
	@Override
	public int compareTo(State o) {
		Double x;
		Double y;
		if (greedy) {
			x = new Double(this.getHeuristicCost());
			y = new Double(o.getHeuristicCost());
		} else {
			x = new Double(this.getCost());
			y = new Double(o.getCost());
		}
		return x.compareTo(y); 
	}
	
	@Override
	public int hashCode() {
		return this.board.hashCode();	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RiskState) {
			return this.board.equals(((RiskState) obj).getBoard());
		} else {
			return false;
		}
	}
}
