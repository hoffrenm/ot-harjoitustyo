package sudoku.logics;

import sudoku.domain.Board;
import sudoku.domain.Cell;

/**
 *
 * @author hoffrenm
 */
public class BoardHelper {
    
    public boolean numberCanBeInserted(Board board, Cell cell, int value) {
        return true;
    }
    
    public boolean canBeInsertedToRow(Board board, Cell cell, int value) {
        return board.getCells().stream().filter(c -> c.getRow() == cell.getRow()).noneMatch(c -> c.getValue() == value);
    }
    
    public boolean canBeInsertedToColumn(Board board, Cell cell, int value) {
        return true;
    }
    
    public boolean canBeInsertedToSubgrid(Board board, Cell cell, int value) {
        return true;
    }
    
}
