package gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
import gui.worker.GameWorker;

public class StartPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox<String> agent_1;
	private JComboBox<String> agent_2;
	private JLabel label;
	private JLabel riskLabel;
	private JButton startGame;
	private AgentFactory factory;
	private BoardReader reader;
	private Game game;

	public StartPanel (RiskFrame parent) {
		factory = new ConcreteAgentFactory();
		game = new RiskGame();
		reader = new FileReader();
		JPanel listers = new JPanel();
		listers.setLayout(new BoxLayout(listers, BoxLayout.X_AXIS));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		String[] agents_options = factory.getAvailableAgentsList().toArray(new String[0]);
		agent_1 = new JComboBox<String>(agents_options);
		agent_2 = new JComboBox<String>(agents_options);
		label = new JLabel(" VS ");
		label.setForeground(Color.WHITE);
		Font font = label.getFont();
		riskLabel = new JLabel("RISK");
		riskLabel.setFont(new Font(font.getFontName(), font.getStyle(), 128));
		riskLabel.setForeground(Color.black);
		riskLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font font2 = new Font(font.getFontName(), font.getStyle(), 16);
		Color transparent = new Color(0, 0, 0, 0);
		this.setOpaque(false);
		label.setFont(new Font(font.getFontName(), font.getStyle(), 32));
		agent_1.setSelectedIndex(0);
		agent_2.setSelectedIndex(0);
		agent_1.setBackground(Color.WHITE);
		agent_2.setBackground(Color.WHITE);
		agent_1.setMaximumSize(new Dimension(150, 80));
		agent_2.setMaximumSize(new Dimension(150, 80));
		agent_1.setFont(font2);
		agent_2.setFont(font2);
		startGame = new JButton("Start Game");
		startGame.setFont(new Font(font.getFontName(), font.getStyle(), 24));
		startGame.setMaximumSize(new Dimension(300, 300));
		listers.add(Box.createHorizontalGlue());
		listers.add(agent_1);
		listers.add(Box.createHorizontalStrut(20));
		listers.add(label);
		listers.add(Box.createHorizontalStrut(20));
		listers.add(agent_2);
		listers.add(Box.createHorizontalGlue());
		listers.setBackground(transparent);
		this.add(Box.createVerticalStrut(40));
		this.add(riskLabel);
		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalStrut(40));
		this.add(listers);
		this.add(Box.createVerticalStrut(50));
		startGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(startGame);
		this.add(Box.createVerticalGlue());
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
				GameWorker worker = new GameWorker(game, board, player_1, player_2, parent);
				worker.execute();
			}
		});
	}
	
	public void reset() {
		agent_1.setSelectedIndex(0);
		agent_2.setSelectedIndex(0);
	}
}
