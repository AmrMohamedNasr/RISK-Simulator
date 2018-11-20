package file;

import java.io.FileNotFoundException;

import game.model.Board;

public interface BoardReader {
	Board parseFile(String filePath) throws FileNotFoundException;
}
