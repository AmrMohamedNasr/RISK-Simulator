package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import game.model.GameBoard;
import game.model.Node;
import game.model.Pair;
import game.model.Player;
import game.model.Stage;

public class GamePanel extends JPanel implements GameWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String[] shapes = {
			"circle", "diamond", "cross", "box", "rounded-box" 	
		};
	private static final String[] colors = {
			"#F8AA17", "#34F50E", "#0ECEF5", "#AF0EF5","#4B640B", 
			"#F50EB6", "#A47864", "#619089", "#F782C0", "#F5F10E"
	};
	/**
	 * viewer.
	 */
	private Viewer viewer;
    /**
     * view of the graph.
     */
	private View view;
	/**
	 * scroll pane for the graph.
	 */
	private JScrollPane pane;
	/**
	 * Graph
	 */
	private Graph graph;
	/**
	 * Panel for log info.
	 */
	private LogPanel panel;
	/**
	 * Panel to indicate turn.
	 */
	private TurnPanel turnPanel;
	
	public GamePanel(LogPanel logPanel, TurnPanel turnPanel) {
		this.panel = logPanel;
		this.turnPanel = turnPanel;
		graph = new MultiGraph("Drawable graph");
		graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.setStrict(false);
        graph.addAttribute("ui.stylesheet",
    			"node {"
    			+ " size: 20px;"
    			+ " stroke-width: 3;"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: above;"
    			+ " text-size: 14;"
    			+ " }"
    			+ "node.PLAYER_1 {"
    			+ " fill-color : red;"
    			+ " }"
    			+ "node.PLAYER_2 {"
    			+ " fill-color : blue;"
    			+ " }"
    			+ "edge {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: center;"
    			+ " text-size: 12;"
    			+ "}");
		System.setProperty("org.graphstream.ui.renderer",
    			"org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		viewer =  new Viewer(graph,
				Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		view = viewer.addDefaultView(false);
		this.setLayout(new BorderLayout());
		pane = new JScrollPane((Component) view);
        this.add(pane, BorderLayout.CENTER);
	}
	
	/**
	 * Clears the graphs.
	 */
	private final void clear() {
		graph.clear();
		panel.clear_log();
		graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.setStrict(false);
        graph.addAttribute("ui.stylesheet",
    			"node {"
    			+ " text-mode: normal;"
    			+ " size: 20px;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: above;"
    			+ " text-size: 14;"
    			+ " stroke-width: 3;"
    			+ " }"
    			+ "node.PLAYER_1 {"
    			+ " fill-color : red;"
    			+ " }"
    			+ "node.PLAYER_2 {"
    			+ " fill-color : blue;"
    			+ " }"
    			+ "edge {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: center;"
    			+ " text-size: 12;"
    			+ "}");
	}

	private Pair<String, String> getContinent(List<Set<Node>> continents, Node node) {
		for (int i = 0; i < continents.size(); i++) {
			if (continents.get(i).contains(node)) {
				int shape_index = i % shapes.length;
				int color_index = (i / shapes.length) % colors.length;	
				return new Pair<String, String>(shapes[shape_index], colors[color_index]);
			}
		}
		return new Pair<String, String>("circle", "#000000");
	}
	
	@Override
	public void draw_graph(GameBoard board) {
		clear();
		this.turnPanel.updateTurn("Player 1 : Place Your Units.", Player.PLAYER_1);
		List<Set<Node>> p = board.getContinents();
		
		List<Node> player_nodes = board.getPlayerNodes(Player.PLAYER_1);
		for (int i = 0; i < player_nodes.size(); i++) {
			Pair<String,String> cnt_id = getContinent(p, player_nodes.get(i));
			String str_id = String.valueOf(player_nodes.get(i).getId()); 
			graph.addNode(str_id);
			if (p.size() > shapes.length) {
				graph.getNode(str_id).addAttribute("ui.style", "stroke-mode: plain;");
			}
			graph.getNode(str_id).addAttribute("ui.style", "shape : " + cnt_id.first +";");
			graph.getNode(str_id).addAttribute("ui.style", "stroke-color : " + cnt_id.second +";");
			graph.getNode(str_id).addAttribute("ui.label", "Id : " + str_id + " | Units : "
					+ String.valueOf(player_nodes.get(i).getArmies()));
			graph.getNode(str_id).addAttribute("ui.class", "PLAYER_1");
			graph.getNode(str_id).addAttribute("units",
					player_nodes.get(i).getArmies());
		}
		player_nodes = board.getPlayerNodes(Player.PLAYER_2);
		for (int i = 0; i < player_nodes.size(); i++) {
			Pair<String, String> cnt_id = getContinent(p, player_nodes.get(i));
			String str_id = String.valueOf(player_nodes.get(i).getId()); 
			graph.addNode(str_id);
			if (p.size() > shapes.length) {
				graph.getNode(str_id).addAttribute("ui.style", "stroke-mode: plain;");
			}
			graph.getNode(str_id).addAttribute("ui.style", "shape : " + cnt_id.first +";");
			graph.getNode(str_id).addAttribute("ui.style", "stroke-color : " + cnt_id.second +";");
			graph.getNode(str_id).addAttribute("ui.label", "Id : " + str_id + " | Units : "
					+ String.valueOf(player_nodes.get(i).getArmies()));
			graph.getNode(str_id).addAttribute("ui.class", "PLAYER_2");
			graph.getNode(str_id).addAttribute("units",
					player_nodes.get(i).getArmies());
		}
		List<Pair<Integer, Integer>> edges = board.getEdges();
		for (int i = 0; i < edges.size(); i++) {
			graph.addEdge(String.valueOf(edges.get(i).first) + "-" + String.valueOf(edges.get(i).second),
					String.valueOf(edges.get(i).first),
					String.valueOf(edges.get(i).second), false);
		}
	}

	@Override
	public void update_node(int node, GameBoard board, Stage stage, int auxNode) {
		String str_id = String.valueOf(node);
		Player player = Player.PLAYER_1;
		Node n = board.getNodeById(player, node);
		if (n == null) {
			player = player.reverseTurn();
			n = board.getNodeById(player, node);
			if (n == null) {
				return;
			}
		}
		int oldUnits = graph.getNode(str_id).getAttribute("units");
		graph.getNode(str_id).changeAttribute("ui.label", "Id : " + str_id + " | Units : "
				+ String.valueOf(n.getArmies()));
		graph.getNode(str_id).changeAttribute("ui.class", player.toString());
		graph.getNode(str_id).changeAttribute("units",
				n.getArmies());
		String splayer;
		String nextTurn;
		Player turnPlayer;
		Color color;
		if (player == Player.PLAYER_1) {
			splayer = "- Player 1";
			color = Color.red;
		} else {
			splayer = "- Player 2";
			color = Color.blue;
		}
		if (stage == Stage.ATTACK) {
			if (auxNode == -1) {
				panel.add_to_log(splayer + " won't attack.\n", color);
			} else {
				String aux_str = String.valueOf(auxNode);
				int armies = graph.getNode(aux_str).getAttribute("units");
				Node m = board.getNodeById(player, auxNode);
				graph.getNode(aux_str).changeAttribute("ui.label", "Id : " + aux_str + " | Units : "
					+ String.valueOf(m.getArmies()));
				graph.getNode(aux_str).changeAttribute("units", m.getArmies());
				splayer += " attacks " + str_id + "(" + String.valueOf(oldUnits) + ")"
						+ " from " + String.valueOf(auxNode) + "("
						+ String.valueOf(armies) + ") and moves "
						+ String.valueOf(n.getArmies()) + " units to it.\n";
				panel.add_to_log(splayer, color);
			}
			if (player == Player.PLAYER_1) {
				nextTurn = "Player 2 : Place Your Units.";
				turnPlayer = Player.PLAYER_2;
			} else {
				nextTurn = "Player 1 : Place Your Units.";
				turnPlayer = Player.PLAYER_1;
			}
		} else {
			splayer += " places " 
					+ String.valueOf(n.getArmies() - oldUnits) 
					+ " units in " + str_id + ".\n";
			panel.add_to_log(splayer, color);
			if (player == Player.PLAYER_1) {
				nextTurn = "Player 1 : Decide Your Attack.";
				turnPlayer = Player.PLAYER_1;
			} else {
				nextTurn = "Player 2 : Decide Your Attack.";
				turnPlayer = Player.PLAYER_2;
			}
		}
		this.turnPanel.updateTurn(nextTurn, turnPlayer);
	}

}
