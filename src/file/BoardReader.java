package file;

import java.io.File;

import game.model.Board;

public interface BoardReader {
	Board parseFile(File file);
}
