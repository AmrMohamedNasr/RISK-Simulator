package game.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Node of a vertice in the game.
 * @author amrnasr
 *
 */
public class Node {
	private Integer id;
	private int armies;
	private List<Integer> edges;
	
	public Node(int id, int armies) {
		this.id = id;
		this.armies = armies;
		this.edges = new ArrayList<Integer> ();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			return this.id.equals(((Node) obj).getId());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void addEdge(int node) {
		this.edges.add(node);
	}
	
	public List<Integer> getEdges() {
		return edges;
	}
	
	public int getArmies() {
		return armies;
	}
	
	public void setArmies(int armies) {
		this.armies = armies;
	}
}
