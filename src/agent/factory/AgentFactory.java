package agent.factory;

import java.util.List;

import agent.Agent;
import agent.heuristic.GameHeuristic;
import game.model.Player;
import gui.RiskFrame;

public interface AgentFactory {
	List<String> getAvailableAgentsList();
	Agent generateAgent(int id, RiskFrame parent, Player player, GameHeuristic heuristic);
}
