package gui.panel;

import game.model.GameBoard;
import game.model.Stage;

public interface GameWindow {
	void draw_graph(GameBoard board);
	void update_node(int node, GameBoard board, Stage stage, int auxNode);
}
