package game.model;

import java.awt.Color;

public enum Player {
	PLAYER_1, PLAYER_2;
	
	public Player reverseTurn() {
		if (this == Player.PLAYER_1) {
			return Player.PLAYER_2;
		} else {
			return Player.PLAYER_1;
		}
	}
	@Override
	public String toString() {
		if (this == PLAYER_1) {
			return "PLAYER_1";
		} else {
			return "PLAYER_2";
		}
	}
	
	public Color getColor() {
		if (this == PLAYER_1) {
			return Color.RED;
		} else {
			return Color.BLUE;
		}
	}
}
