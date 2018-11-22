package gui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.model.GameBoard;
import game.model.Node;
import game.model.Player;
import game.model.info_capsules.Attack;

public class InputPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel attackAction;
	private JPanel placeAction;
	private int placeValue;
	private Attack attackValue;
	private JSlider unitsSlider;
	private JComboBox<String> attackList;
	private JComboBox<String> placeList;
	private JLabel placeLabel;
	private JLabel attackLabel;
	private JLabel unitsToMoveLabel;
	private JLabel sliderValue;
	private JButton placeButton;
	private JButton attackButton;
	private Semaphore wait_for_input;
	private List<Integer> maxValues;

	public InputPanel() {
		wait_for_input = new Semaphore(0);
		maxValues = new ArrayList<Integer> ();
		attackAction = new JPanel();
		placeAction = new JPanel();
		unitsSlider = new JSlider();
		attackList = new JComboBox<String>();
		placeList = new JComboBox<String>();
		placeLabel = new JLabel("Place 0 units in vertex : ");
		attackLabel = new JLabel("Use attack : ");
		unitsToMoveLabel = new JLabel("Units To Move : ");
		placeButton = new JButton("Place Units");
		attackButton = new JButton("Attack");
		sliderValue = new JLabel("0");
		attackAction.setLayout(new BoxLayout(attackAction, BoxLayout.X_AXIS));
		attackAction.add(attackLabel);
		attackAction.add(attackList);
		attackAction.add(Box.createHorizontalStrut(20));
		attackAction.add(unitsToMoveLabel);
		JPanel sliderPane = new JPanel();
		sliderPane.setLayout(new BoxLayout(sliderPane, BoxLayout.Y_AXIS));
		sliderPane.add(unitsSlider, BorderLayout.CENTER);
		sliderValue.setAlignmentX(CENTER_ALIGNMENT);
		sliderPane.add(sliderValue, BorderLayout.SOUTH);
		attackAction.add(sliderPane);
		attackAction.add(Box.createHorizontalStrut(20));
		attackAction.add(attackButton);
		placeAction.setLayout(new BoxLayout(placeAction, BoxLayout.X_AXIS));
		placeAction.add(placeLabel);
		placeAction.add(placeList);
		placeAction.add(Box.createHorizontalStrut(20));
		unitsSlider.setPaintTicks(true);
	    unitsSlider.setSnapToTicks(true);
	    unitsSlider.setPaintLabels(true);
	    unitsSlider.setMinorTickSpacing(1);
	    unitsSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				sliderValue.setText(String.valueOf(unitsSlider.getValue()));
			}
		});
		attackList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int i = attackList.getSelectedIndex();
				unitsSlider.setMinimum(1);
				unitsSlider.setMaximum(maxValues.get(i));
				unitsSlider.setValue(maxValues.get(i));
				sliderValue.setText(String.valueOf(unitsSlider.getValue()));
			}
		});
		attackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int units_to_move = unitsSlider.getValue();
				boolean willAttack = attackList.getSelectedIndex() != 0;
				int src = 0, dest = 0;
				if (willAttack) {
					String units = (String) attackList.getSelectedItem();
					String[] opts = units.split(" --> ");
					src = Integer.valueOf(opts[0]);
					dest = Integer.valueOf(opts[1]);
				}
				attackValue = new Attack(willAttack, src, dest, units_to_move);
				hide_panels();
				wait_for_input.release();
			}
		});
		placeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				try {
					i = Integer.valueOf((String) placeList.getSelectedItem());
				} catch (Exception ex) {
					return;
				}
				placeValue = i;
				hide_panels();
				wait_for_input.release();
			}
		});
		placeAction.add(placeButton);
		attackAction.setVisible(false);
		placeAction.setVisible(false);
		this.setLayout(new FlowLayout());
		this.add(attackAction);
		this.add(placeAction);
	}
	
	public void hide_panels() {
		attackAction.setVisible(false);
		placeAction.setVisible(false);
	}
	
	public void attack_turn(GameBoard board, Player player) {
		List<Attack> attacks = board.getPlayerUniqueAttacks(player);
		List<String> possible_attacks = new ArrayList<String>();
		maxValues.clear();
		maxValues.add(0);
		possible_attacks.add("No Attack");
		for (int i = 0; i < attacks.size(); i++) {
			Attack at = attacks.get(i);
			if (at.willAttack) {
				possible_attacks.add(String.valueOf(at.src) + " --> " + String.valueOf(at.dest));
				maxValues.add(at.units_to_move);
			}
		}
		attackList.setModel(new DefaultComboBoxModel<String>(possible_attacks.toArray(new String[0])));
		attackList.setSelectedIndex(0);
		unitsSlider.setValue(0);
		unitsSlider.setMaximum(0);
		unitsSlider.setMinimum(0);
		sliderValue.setText(String.valueOf(unitsSlider.getValue()));
		attackAction.setVisible(true);
	}
	
	public void place_turn(GameBoard board, Player player) {
		Map<Integer, Node> playerNodes = board.getPlayerNodesMap(player);
		List<String> nodes = new ArrayList<String>();
		List<Integer> nodesN = new ArrayList<Integer>(playerNodes.keySet());
		Collections.sort(nodesN);
		for (int i : nodesN) {
			nodes.add(String.valueOf(i));
		}
		placeLabel.setText("Place " + String.valueOf(board.get_turn_unit_number(player)) + " units in vertex : ");
		placeList.setModel(new DefaultComboBoxModel<String>(nodes.toArray(new String[0])));
		placeList.setSelectedIndex(0);
		placeAction.setVisible(true);
	}
	
	public int getPlaceInput() {
		return placeValue;
	}
	
	public Attack getAttackInput() {
		return attackValue;
	}
	
	public void waitForInput() {
		try {
			this.wait_for_input.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
