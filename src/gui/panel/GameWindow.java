package gui.panel;

import game.model.GameBoard;

public interface GameWindow {
	void draw_graph(GameBoard board);
	void update_node(int node, GameBoard board);
}
