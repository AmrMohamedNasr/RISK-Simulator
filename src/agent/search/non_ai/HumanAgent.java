package agent.search.non_ai;

import java.awt.Dialog;

import javax.swing.JFrame;

import agent.Agent;
import agent.search.SearchAgent;
import game.model.GameBoard;
import game.model.Node;
import game.model.Player;
import game.model.info_capsules.Attack;

/**
 * 
 * @author Michael
 * Agent that reads the next move from the user.
 */
public class HumanAgent implements Agent {

	
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
	 * JFrame.
	 */
	private JFrame parent;
	
	public HumanAgent(Player player, JFrame parent) {
		this.player = player;
		this.parent = parent;
	}
	
	@Override
	public String getAgentName() {
		return "HumanAgent";
	}

	@Override
	public Player getAgentPlayer() {
		return this.player;
	}

	@Override
	public void observe_enviroment(GameBoard board) {
		// TODO Auto-generated method stub
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
