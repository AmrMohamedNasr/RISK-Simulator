package agent.search.non_ai;

import agent.Agent;
import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.Attack;
import gui.RiskFrame;
import gui.panel.InputPanel;

/**
 * 
 * @author Michael Agent that reads the next move from the user.
 */
public class HumanAgent implements Agent {

	/**
	 * player uses this agent.
	 */
	private Player player;
	/**
	 * Input panel.
	 */
	private InputPanel inputP;
	/**
	 * Board.
	 */
	private GameBoard board;
	
	public HumanAgent(Player player, RiskFrame parent) {
		this.player = player;
		this.inputP = parent.getInputPanel();
	}

	@Override
	public String getAgentName() {
		return "Human Agent";
	}

	@Override
	public Player getAgentPlayer() {
		return this.player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		this.board = board;
	}

	@Override
	public int place_action() {
		this.inputP.place_turn(board, player);
		this.inputP.waitForInput();
		return this.inputP.getPlaceInput();
	}

	@Override
	public Attack attack_action() {
		this.inputP.attack_turn(board, player);
		this.inputP.waitForInput();
		return this.inputP.getAttackInput();
	}

}
