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

	public static void main(String[] args) throws FileNotFoundException {
		FileReader reader = new FileReader();
		reader.parseFile("try.txt");
	}

	@Override
	public Board parseFile(String filePath) throws FileNotFoundException {
		Scanner in = new Scanner(new File(filePath));
		int v = readInt(in.nextLine(), "V", "");
		System.out.println(v);

		List<Node> nodes = new LinkedList<>();
		for (int i = 0; i < v; i++) {
			nodes.add(new Node(i + 1, 1));
		}

		int e = readInt(in.nextLine(), "E", "");
		System.out.println(e);
		List<Pair<Integer, Integer>> edges = new LinkedList<>();
		for (int i = 0; i < e; i++) {
			edges.add(new Pair<>());
			String s = in.nextLine();
			edges.get(i).first = readInt(s, "(", " ");
			System.out.print(edges.get(i).first + " ");
			edges.get(i).second = readInt(s, " ", ")");
			System.out.println(edges.get(i).second);

		}

		int p = readInt(in.nextLine(), "P", "");
		System.out.println(p);
		List<Set<Node>> continents = new LinkedList<>();
		List<Integer> continentBonus = new LinkedList<>();
		for (int i = 0; i < p; i++) {
			String temp[] = in.nextLine().split(" ");
			continentBonus.add(Integer.parseInt(temp[0].trim()));
			System.out.println(continentBonus.get(i));
			Set<Node> s = new HashSet<>();
			for (int j = 1; j < temp.length; j++) {
				s.add(nodes.get(Integer.parseInt(temp[j].trim()) - 1));
				System.out.print(nodes.get(Integer.parseInt(temp[j].trim()) - 1).getId() + " ");
			}
			continents.add(s);
			System.out.println();
		}

		List<Node> player_1, player_2;

		player_1 = new LinkedList<>();
		String temp[] = in.nextLine().split(" ");
		for (int i = 0; i < temp.length; i++) {
			player_1.add(nodes.get(Integer.parseInt(temp[i].trim()) - 1));
			System.out.print(nodes.get(Integer.parseInt(temp[i].trim()) - 1).getId() + " ");
		}

		System.out.println();

		player_2 = new LinkedList<>();
		temp = in.nextLine().split(" ");
		for (int i = 0; i < temp.length; i++) {
			player_2.add(nodes.get(Integer.parseInt(temp[i].trim()) - 1));
			System.out.print(nodes.get(Integer.parseInt(temp[i].trim()) - 1).getId() + " ");
		}

		in.close();
		return new Board(player_1, player_2, edges, continents, continentBonus);
	}

	private int readInt(String s, String prefix, String suffix) {
		if (s.indexOf(prefix) != -1 && !suffix.equals("") && s.indexOf(suffix) != -1) {
			return Integer.parseInt(s.substring(s.indexOf(prefix) + prefix.length(), s.indexOf(suffix)).trim());
		}
		if (s.indexOf(prefix) != -1) {
			return Integer.parseInt(s.substring(s.indexOf(prefix) + prefix.length()).trim());
		}
		return 0;
	}
}
