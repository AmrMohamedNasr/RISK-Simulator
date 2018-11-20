package agent.factory;

import java.util.List;

import javax.swing.JFrame;

import agent.Agent;
import agent.heuristic.GameHeuristic;
import game.model.GameBoard;
import game.model.Player;

public interface AgentFactory {
	List<String> getAvailableAgentsList();
	Agent generateAgent(int id, JFrame parent, Player player, GameHeuristic heuristic);
}
