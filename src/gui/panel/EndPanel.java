package gui.panel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.Player;
import game.model.info_capsules.EndGameInformation;
import gui.RiskFrame;

public class EndPanel extends JPanel implements EndGamePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel players;
	private JLabel winner;
	private JLabel metric_1;
	private JLabel metric_100;
	private JLabel metric_10000;
	private JButton returnButton;
	
	public EndPanel(RiskFrame frame) {
		players = new JLabel("");
		players.setAlignmentX(CENTER_ALIGNMENT);
		winner = new JLabel("");
		winner.setAlignmentX(CENTER_ALIGNMENT);
		metric_1 = new JLabel("");
		metric_100 = new JLabel("");
		metric_10000 = new JLabel("");
		metric_1.setAlignmentX(CENTER_ALIGNMENT);
		metric_100.setAlignmentX(CENTER_ALIGNMENT);
		metric_10000.setAlignmentX(CENTER_ALIGNMENT);
		returnButton = new JButton("Return To Menu");
		returnButton.setAlignmentX(CENTER_ALIGNMENT);
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.next_state();
			}
		});
		JPanel layout = new JPanel();
		layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
		layout.add(Box.createVerticalGlue());
		layout.add(players);
		layout.add(winner);
		layout.add(Box.createVerticalStrut(20));
		layout.add(metric_1);
		layout.add(metric_100);
		layout.add(metric_10000);
		layout.add(Box.createVerticalStrut(20));
		layout.add(returnButton);
		layout.add(Box.createVerticalGlue());
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalGlue());
		this.add(layout);
		this.add(Box.createHorizontalGlue());
	}

	@Override
	public void show_results(EndGameInformation info) {
		players.setText("Player 1 : \"" + info.player_1_agent_name 
				+ "\" VS Player 2 : \"" + info.player_2_agent_name + "\"");
		if (info.winner == Player.PLAYER_1) {
			winner.setText("Winner : Player 1");
		} else {
			winner.setText("Winner : Player 2");
		}
		if (info.winner_expanded_nodes_count == -1) {
			metric_1.setText("");
			metric_100.setText("");
			metric_10000.setText("");
		} else {
			metric_1.setText("Performance Measure ( weight : 1) = "
					+ String.valueOf(info.turns + info.winner_expanded_nodes_count));
			metric_100.setText("Performance Measure ( weight : 100) = "
					+ String.valueOf(info.turns * 100 + info.winner_expanded_nodes_count));
			metric_10000.setText("Performance Measure ( weight : 10000) = "
					+ String.valueOf(info.turns * 10000+ info.winner_expanded_nodes_count));
		}
	}

}
