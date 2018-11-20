package gui.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import agent.Agent;
import agent.factory.AgentFactory;
import agent.factory.ConcreteAgentFactory;
import file.BoardReader;
import file.FileReader;
import game.Game;
import game.RiskGame;
import game.model.GameBoard;
import gui.RiskFrame;

public class StartPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox<String> agent_1;
	private JComboBox<String> agent_2;
	private JButton startGame;
	private AgentFactory factory;
	private BoardReader reader;
	private Game game;

	public StartPanel(RiskFrame parent) {
		factory = new ConcreteAgentFactory();
		game = new RiskGame();
		reader = new FileReader();
		this.setLayout(new FlowLayout());
		String[] agents_options = factory.getAvailableAgentsList().toArray(new String[0]);
		agent_1 = new JComboBox<String>(agents_options);
		agent_2 = new JComboBox<String>(agents_options);
		agent_1.setSelectedIndex(0);
		agent_2.setSelectedIndex(0);
		startGame = new JButton("Start Game");
		this.add(Box.createHorizontalGlue(), this);
		this.add(agent_1);
		this.add(Box.createHorizontalGlue(), this);
		this.add(agent_2);
		this.add(Box.createHorizontalGlue(), this);
		this.add(startGame);
		this.add(Box.createHorizontalGlue(), this);
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String path;
				// TODO Auto-generated method stub
				GameBoard board = reader.parseFile(path);
				Agent player_1 = factory.generateAgent(agent_1.getSelectedIndex(), board, parent);
				Agent player_2 = factory.generateAgent(agent_2.getSelectedIndex(), board, parent);
				parent.next_state();
				game.play_game(board, player_1, player_2, parent.getGameWindow());
			}
		});
	}
}
