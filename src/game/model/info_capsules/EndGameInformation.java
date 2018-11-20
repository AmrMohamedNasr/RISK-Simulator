package game.model.info_capsules;

import game.model.Player;

public class EndGameInformation {
	
	public String player_1_agent_name;
	public String player_2_agent_name;
	public Player winner;
	public int winner_expanded_nodes_count;
	public int turns;
	
	public EndGameInformation(String p1_an, String p2_an, Player winner, int w_expanded_nodes, int turns) {
		this.player_1_agent_name = p1_an;
		this.player_2_agent_name = p2_an;
		this.winner = winner;
		this.winner_expanded_nodes_count = w_expanded_nodes;
		this.turns = turns;
	}
}
