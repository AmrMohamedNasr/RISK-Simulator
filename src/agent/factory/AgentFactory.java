package agent.factory;

import java.util.List;

import javax.swing.JFrame;

import agent.Agent;
import game.model.GameBoard;

public interface AgentFactory {
	List<String> getAvailableAgentsList();
	Agent generateAgent(int id, GameBoard board, JFrame parent);
}
