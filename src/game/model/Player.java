package game.model;

public enum Player {
	PLAYER_1, PLAYER_2;
	
	public Player reverseTurn(Player player) {
		if (player == Player.PLAYER_1) {
			return Player.PLAYER_2;
		} else {
			return Player.PLAYER_1;
		}
	}
}
