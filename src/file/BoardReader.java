package file;

import game.model.Board;

public interface BoardReader {
	Board parseFile(String filePath);
}
