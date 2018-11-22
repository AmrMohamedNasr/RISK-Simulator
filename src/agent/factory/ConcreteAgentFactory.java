package agent.factory;

import java.util.ArrayList;
import java.util.List;

import agent.Agent;
import agent.heuristic.GameHeuristic;
import agent.search.ai.AstarAgent;
import agent.search.ai.AstarRealTimeAgent;
import agent.search.ai.GreedyAgent;
import agent.search.non_ai.AggressiveAgent;
import agent.search.non_ai.HumanAgent;
import agent.search.non_ai.PacifistAgent;
import agent.search.non_ai.PassiveAgent;
import game.model.Player;
import gui.RiskFrame;

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
	public Agent generateAgent(int id, RiskFrame parent, Player player, GameHeuristic heuristic) {
		switch(id) {
		case 0:
			return new PassiveAgent(player);
		case 1:
			return new AggressiveAgent(player);
		case 2:
			return new HumanAgent(player, parent);
		case 3:
			return new PacifistAgent(player);
		case 4:
			return new GreedyAgent(player, heuristic);
		case 5:
			return new AstarAgent(player, heuristic);
		case 6:
			return new AstarRealTimeAgent(player, heuristic);
		default:
			throw new RuntimeException("Must Choose Agent");
		}
	}

}
