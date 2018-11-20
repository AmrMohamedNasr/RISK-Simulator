package game.model.info_capsules;
/**
 * Attack information data structure.
 * @author amrnasr
 *
 */
public class Attack {
	public boolean willAttack;
	public int src;
	public int dest;
	public int units_to_move;
	
	public Attack(boolean attack, int src, int dest, int units_to_move) {
		this.willAttack = attack;
		this.src = src;
		this.dest = dest;
		this.units_to_move = units_to_move;
	}
}
