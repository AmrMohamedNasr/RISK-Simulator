package agent;

import game.model.GameBoard;
import game.model.Player;
import game.model.info_capsules.Attack;

public interface Agent {
	String getAgentName();
	Player getAgentPlayer();
	void observe_enviroment(GameBoard board);
	int place_action();
	Attack attack_action();
}
