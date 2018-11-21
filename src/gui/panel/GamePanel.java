package gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;

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

public class GamePanel extends JPanel implements GameWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public GamePanel() {
		graph = new MultiGraph("Drawable graph");
		graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.setStrict(false);
        graph.addAttribute("ui.stylesheet",
    			"node {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: above;"
    			+ " text-size: 12;"
    			+ " }"
    			+ "node.PLAYER_1 {"
    			+ " fill-color : red;"
    			+ " }"
    			+ "node.PLAYER_2 {"
    			+ " fill-color : black;"
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
				Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
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
		graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.setStrict(false);
        graph.addAttribute("ui.stylesheet",
    			"node {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: above;"
    			+ " text-size: 12;"
    			+ " }"
    			+ "node.PLAYER_1 {"
    			+ " fill-color : red;"
    			+ " }"
    			+ "node.PLAYER_2 {"
    			+ " fill-color : black;"
    			+ " }"
    			+ "edge {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: center;"
    			+ " text-size: 12;"
    			+ "}");
	}
	
	@Override
	public void update_graph(GameBoard board) {
		clear();
		List<Node> player_nodes = board.getPlayerNodes(Player.PLAYER_1);
		for (int i = 0; i < player_nodes.size(); i++) {
			String str_id = String.valueOf(player_nodes.get(i).getId()); 
			graph.addNode(str_id);
			graph.getNode(str_id).addAttribute("ui.label", "Id : " + str_id + " | Units : "
					+ String.valueOf(player_nodes.get(i).getArmies()));
			graph.getNode(str_id).addAttribute("ui.class", "PLAYER_1");
		}
		player_nodes = board.getPlayerNodes(Player.PLAYER_2);
		for (int i = 0; i < player_nodes.size(); i++) {
			String str_id = String.valueOf(player_nodes.get(i).getId()); 
			graph.addNode(str_id);
			graph.getNode(str_id).addAttribute("ui.label", "Id : " + str_id + " | Units : "
					+ String.valueOf(player_nodes.get(i).getArmies()));
			graph.getNode(str_id).addAttribute("ui.class", "PLAYER_2");
		}
		List<Pair<Integer, Integer>> edges = board.getEdges();
		for (int i = 0; i < edges.size(); i++) {
			graph.addEdge(String.valueOf(edges.get(i).first) + "-" + String.valueOf(edges.get(i).second),
					String.valueOf(edges.get(i).first),
					String.valueOf(edges.get(i).second), false);
		
		}
		this.repaint();
	}

}
