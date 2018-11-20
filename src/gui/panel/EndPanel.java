package gui.panel;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.Player;
import game.model.info_capsules.EndGameInformation;

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
	
	public EndPanel() {
		players = new JLabel("");
		winner = new JLabel("");
		metric_1 = new JLabel("");
		metric_100 = new JLabel("");
		metric_10000 = new JLabel("");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalGlue());
		this.add(players);
		this.add(Box.createVerticalGlue());
		this.add(winner);
		this.add(Box.createVerticalGlue());
		this.add(metric_1);
		this.add(Box.createVerticalGlue());
		this.add(metric_100);
		this.add(Box.createVerticalGlue());
		this.add(metric_10000);
		this.add(Box.createVerticalGlue());
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
