package gui.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import agent.Agent;
import agent.factory.AgentFactory;
import agent.factory.ConcreteAgentFactory;
import agent.heuristic.GameHeuristic;
import agent.heuristic.RiskGameHeuristic;
import file.BoardReader;
import file.FileReader;
import game.Game;
import game.RiskGame;
import game.model.GameBoard;
import game.model.Player;
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

	public StartPanel (RiskFrame parent) {
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
				File file;
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter txtfilter
				= new FileNameExtensionFilter(
						"txt files (*.txt)", "txt");
				chooser.addChoosableFileFilter(txtfilter);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(txtfilter);
				int choice = chooser.showOpenDialog(null);
				if (choice != JFileChooser.APPROVE_OPTION) {
					return;
				}
				file = chooser.getSelectedFile();
				GameBoard board = null;
				try {
					board = reader.parseFile(file);
					if (board == null) {
						return;
					}
				} catch (Exception exc) {
					return;
				}
				GameHeuristic heuristic = new RiskGameHeuristic();
				Agent player_1 = factory.generateAgent(agent_1.getSelectedIndex(), parent, Player.PLAYER_1, heuristic);
				Agent player_2 = factory.generateAgent(agent_2.getSelectedIndex(), parent, Player.PLAYER_2, heuristic);
				parent.next_state();
				game.play_game(board, player_1, player_2, parent.getGameWindow());
				parent.next_state();
				parent.getEndGamePanel().show_results(game.get_game_results());
			}
		});
	}
	
	public void reset() {
		agent_1.setSelectedIndex(0);
		agent_2.setSelectedIndex(0);
	}
}
