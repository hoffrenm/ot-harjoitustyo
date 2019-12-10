/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.Board;
import sudoku.domain.Cell;
import sudoku.logics.BoardHelper;

/**
 *
 * @author hoffrenm
 */
public class BoardTest {
    
    BoardHelper helper;
    Board board;

    @Before
    public void setUp() {
        helper = new BoardHelper();
        board = new Board();
    }
    
    @Test
    public void boardContains81Cells() {
        int amountOfCells = board.getCells().size();
        assertEquals(81, amountOfCells);
    }
    
    @Test
    public void boardIsEmptyAtStart() {
        boolean empty = board.getCells()
                .stream()
                .noneMatch(c -> c.getValue() != 0);
        assertTrue(empty);
    }
    
    @Test
    public void atleastSomeCellValuesAreSet() {
        helper.initializeBoard(board, 35);
        long revealedValues = board.getCells().stream().filter(c -> c.getValue() != 0).count();
        assertTrue(revealedValues > 20);
    }
    
    @Test
    public void mostOfCellsAreHidden() {
        helper.initializeBoard(board, 35);
        long hiddenValues = board.getCells().stream().filter(c -> c.getValue() == 0).count();
        assertTrue(hiddenValues > 45);
    }
    
    @Test
    public void cellCanBeRetrievedByCoordinates() {
        Cell cell = new Cell(0, 1, 5);
        Cell found = board.getCellInGrid(1, 5);
        assertEquals(found, cell);
    }
    
    @Test
    public void validValueCanBeSet() {
        Cell cell = board.getCellInGrid(4, 8);
        boolean set = board.setValue(cell, 6);
        assertTrue(set);
        assertEquals(6, cell.getValue());
    }
    
    @Test
    public void invalidValueIsRejected() {
        Cell firstCell = board.getCellInGrid(1, 8);
        Cell secondCell = board.getCellInGrid(4, 8);
        board.setValue(firstCell, 6);
        boolean notSet = board.setValue(secondCell, 6);
        assertFalse(notSet);
        assertEquals(0, secondCell.getValue());
    }
    
    @Test
    public void fullBoardIsFinished() {
        board.getCells().forEach(cell -> cell.setValue(1));
        assertTrue(board.isFinished());
    }
    
    @Test
    public void unfinishedGameHasMissingValues() {
        board.getCells().forEach(cell -> cell.setValue(1));
        board.getCells().get(53).setValue(0);
        assertFalse(board.isFinished());
    }
}
