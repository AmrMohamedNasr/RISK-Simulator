package game.model;

import java.util.List;

public interface GameBoardNode {
	public GameBoardNode getParent();
	
	public List<GameBoardNode> generateBoardChildren(Player player);
	
	public void setHeuristicScore(int score);
	
	public int getTotalScore();
	
	public Stage getType();
	
	public void setType(Stage stage);
}
