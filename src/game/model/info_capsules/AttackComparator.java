package game.model.info_capsules;

import java.util.Comparator;

public class AttackComparator implements Comparator<Attack> {

	@Override
	public int compare(Attack o1, Attack o2) {
		if (o1.willAttack && !o2.willAttack) {
			return 1;
		} else if (!o1.willAttack && o2.willAttack) {
			return -1;
		} else if (o1.willAttack && o2.willAttack) {
			if (o1.src == o2.src) {
				return o1.dest - o2.dest;
			} else {
				return o1.src - o2.src;
			}
		} else {
			return 0;
		}
	}

}
