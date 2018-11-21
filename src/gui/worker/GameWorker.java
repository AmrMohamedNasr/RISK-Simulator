package gui.worker;

import javax.swing.SwingWorker;

import agent.Agent;
import game.Game;
import game.model.GameBoard;
import gui.RiskFrame;

public class GameWorker extends SwingWorker<String, Void> {
	
	private Game game;
	private GameBoard board;
	private Agent player_1, player_2;
	private RiskFrame parent;
	
	
	
	public GameWorker(Game game, GameBoard board, Agent player_1, Agent player_2, RiskFrame parent) {
		super();
		this.game = game;
		this.board = board;
		this.player_1 = player_1;
		this.player_2 = player_2;
		this.parent = parent;
	}

	@Override
	protected String doInBackground() throws Exception {
		game.play_game(board, player_1, player_2, parent.getGameWindow());
		parent.next_state();
		parent.getEndGamePanel().show_results(game.get_game_results());
		return null;
	}

}
