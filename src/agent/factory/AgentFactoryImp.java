package agent.factory;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import agent.Agent;
import agent.heuristic.GameHeuristic;
import agent.search.ai.AstarAgent;
import agent.search.ai.AstarRealTimeAgent;
import agent.search.non_ai.AggressiveAgent;
import agent.search.non_ai.HumanAgent;
import agent.search.non_ai.PacifistAgent;
import agent.search.non_ai.PassiveAgent;
import game.model.GameBoard;
import game.model.Player;

public class AgentFactoryImp implements AgentFactory {

	@Override
	public List<String> getAvailableAgentsList() {
		List<String> agents = Arrays.asList("HumanAgent", "PassiveAgent", "AggressiveAgent",
				"PacifistAgent", "AstarAgent", "Real-TimeAstarAgent");
		return agents;
	}

	@Override
	public Agent generateAgent(int id, JFrame parent, Player player, GameHeuristic heuristic) {
		switch(id) {
		case 0:
			return new HumanAgent(player, parent);
		case 1:
			return new PassiveAgent(player);
		case 2:
			return new AggressiveAgent(player);
		case 3:
			return new PacifistAgent(player);
		case 4:
			return new AstarAgent(player, heuristic);
		case 5:
			return new AstarRealTimeAgent(player, heuristic);
		default:
			throw new RuntimeException("Must Choose Agent");
		}
	}

}
