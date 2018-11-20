package agent.factory;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import agent.Agent;
import game.model.GameBoard;

public class ConcreteAgentFactory implements AgentFactory {
	
	private static final List<String> agents = new ArrayList<String>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	{
		add("Passive Agent");
		add("Agressive Agent");
		add("Human Agent");
		add("Pacifist Agent");
		add("Greedy Agent");
		add("A* Agent");
		add("Real-Time A* Agent");
	}};
	
	@Override
	public List<String> getAvailableAgentsList() {
		return agents;
	}

	@Override
	public Agent generateAgent(int id, GameBoard board, JFrame parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
