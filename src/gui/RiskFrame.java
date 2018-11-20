package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

import gui.panel.EndGamePanel;
import gui.panel.EndPanel;
import gui.panel.GamePanel;
import gui.panel.GameWindow;
import gui.panel.StartPanel;

public class RiskFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EndPanel endPanel;
	private GamePanel gamePanel;
	private StartPanel startPanel;
	private int current_state;
	
	public RiskFrame() {
		current_state = 0;
		startPanel = new StartPanel(this);
		gamePanel = new GamePanel();
		endPanel = new EndPanel();
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.add(startPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void next_state() {
		current_state = (current_state + 1) % 3;
		if (current_state == 0) {
			this.add(startPanel, BorderLayout.CENTER);
		} else if (current_state == 1) {
			this.add(gamePanel, BorderLayout.CENTER);
		} else if (current_state == 2) {
			this.add(endPanel, BorderLayout.CENTER);
		}
	}
	
	public GameWindow getGameWindow() {
		return gamePanel;
	}
	
	public EndGamePanel getEndGamePanel() {
		return endPanel;
	}
}
