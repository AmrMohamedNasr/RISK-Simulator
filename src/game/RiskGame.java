package game;

import agent.Agent;
import agent.search.SearchAgent;
import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.EndGameInformation;
import gui.panel.GameWindow;

public class RiskGame implements Game {
	
	private EndGameInformation endGameInfo;
	private static final int duration = 1000;
	
	public RiskGame() {
		endGameInfo = null;
	}
	
	@Override
	public void play_game(GameBoard board, Agent player_1, Agent player_2, GameWindow window) {
		Player turn = Player.PLAYER_1;
		int turns = 0;
		while (!board.isGameOver()) {
			if (turn == Player.PLAYER_1) {
				player_1.observe_enviroment(board);
				board.place_unit(player_1.getAgentPlayer(), player_1.place_action());
				window.update_graph(board);
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				board.attack_node(player_1.getAgentPlayer(), player_1.attack_action());
				window.update_graph(board);
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				turns++;
			} else {
				player_2.observe_enviroment(board);
				board.place_unit(player_2.getAgentPlayer(), player_2.place_action());
				window.update_graph(board);
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				board.attack_node(player_2.getAgentPlayer(), player_2.attack_action());
				window.update_graph(board);
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		Agent winner;
		int exp_nodes;
		if (board.getPlayerNodesMap(Player.PLAYER_1).size() == 0) {
			winner = player_2;
		} else {
			winner = player_1;
		}
		if (winner instanceof SearchAgent) {
			exp_nodes = ((SearchAgent) winner).getExpandedNodesTotalNumber();
		} else {
			exp_nodes = -1;
		}
		endGameInfo = new EndGameInformation(player_1.getAgentName(),
				player_2.getAgentName(), winner.getAgentPlayer(), exp_nodes, turns);
	}

	@Override
	public EndGameInformation get_game_results() {
		return endGameInfo;
	}

}
