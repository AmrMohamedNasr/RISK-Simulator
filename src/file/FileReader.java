package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import game.model.Board;
import game.model.Node;
import game.model.Pair;

public class FileReader implements BoardReader {

	public static void main(String[] args) {
		FileReader reader = new FileReader();
		reader.parseFile(new File("try.txt"));
	}

	@Override
	public Board parseFile(File file) {
		Scanner in;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e1) {
			return null;
		}
		int v = readInt(in.nextLine(), "V", "");
		if (v < 0) {
			in.close();
			return null;
		}

		List<Node> nodes = new LinkedList<>();
		for (int i = 0; i < v; i++) {
			nodes.add(new Node(i + 1, 1));
		}

		int e = readInt(in.nextLine(), "E", "");
		if (e < 0) {
			in.close();
			return null;
		}

		List<Pair<Integer, Integer>> edges = new LinkedList<>();
		for (int i = 0; i < e; i++) {
			edges.add(new Pair<>());
			String s = in.nextLine();
			int first = readInt(s, "(", " ");
			int second = readInt(s, " ", ")");
			if (first < 0 || first > v || second < 0 || second > v) {
				in.close();
				return null;
			}
			edges.get(i).first = first;
			edges.get(i).second = second;

		}

		int p = readInt(in.nextLine(), "P", "");
		if (p < 0) {
			in.close();
			return null;
		}
		List<Set<Node>> continents = new LinkedList<>();
		List<Integer> continentBonus = new LinkedList<>();
		for (int i = 0; i < p; i++) {
			String temp[] = in.nextLine().split(" ");
			try {
				continentBonus.add(Integer.parseInt(temp[0].trim()));
				Set<Node> s = new HashSet<>();
				for (int j = 1; j < temp.length; j++) {
					int node = Integer.parseInt(temp[j].trim());
					if (node < 1 || node > v) {
						in.close();
						return null;
					}
					s.add(nodes.get(node - 1));
				}
				continents.add(s);
			} catch (NumberFormatException ex) {
				in.close();
				return null;
			}
		}

		List<Node> player_1, player_2;

		player_1 = new LinkedList<>();
		String temp[] = in.nextLine().split(" ");
		for (int i = 0; i < temp.length; i++) {
			int node;
			try {
				node = Integer.parseInt(temp[i].trim());
			} catch (NumberFormatException ex) {
				in.close();
				return null;
			}
			if (node < 1 || node > v) {
				in.close();
				return null;
			}
			player_1.add(nodes.get(node - 1));
		}

		player_2 = new LinkedList<>();
		temp = in.nextLine().split(" ");
		for (int i = 0; i < temp.length; i++) {
			int node;
			try {
				node = Integer.parseInt(temp[i].trim());
				if (node < 1 || node > v) {
					in.close();
					return null;
				}
			} catch (NumberFormatException ex) {
				in.close();
				return null;
			}
			player_2.add(nodes.get(node - 1));
		}

		in.close();

		return new Board(player_1, player_2, edges, continents, continentBonus);
	}

	private int readInt(String s, String prefix, String suffix) {
		try {
			if (s.indexOf(prefix) != -1 && !suffix.equals("") && s.indexOf(suffix) != -1) {
				return Integer.parseInt(s.substring(s.indexOf(prefix) + prefix.length(), s.indexOf(suffix)).trim());
			}
			if (s.indexOf(prefix) != -1) {
				return Integer.parseInt(s.substring(s.indexOf(prefix) + prefix.length()).trim());
			}
		} catch (NumberFormatException ex) {
			return -1;
		}
		return -1;
	}
}
