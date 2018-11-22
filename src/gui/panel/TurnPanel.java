package gui.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game.model.Player;

public class TurnPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel turnLabel;
	
	public TurnPanel() {
		turnLabel = new JLabel();
		this.add(turnLabel);
	}
	
	public void updateTurn(String s, Player player) {
		turnLabel.setForeground(player.getColor());
		turnLabel.setText(s);
	}
}
