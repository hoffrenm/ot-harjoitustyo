/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logics;

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
public class BoardHelperTest {

    BoardHelper helper;
    Board board;

    @Before
    public void setUp() {
        helper = new BoardHelper();
        board = new Board();
        helper.initializeTestSudoku(board);
    }

    @Test
    public void RowMissingValueCanBeSet() {
        Cell cellToBePlaced = new Cell(0, 1, 3);
        boolean canBePlaced = helper.canBeInsertedToRow(board, cellToBePlaced, 2);
        assertTrue(canBePlaced);
    }
    
    @Test
    public void RowExistingValueIsRejected() {
        Cell cellToBePlaced = new Cell(0, 1, 2);
        boolean canBePlaced = helper.canBeInsertedToRow(board, cellToBePlaced, 4);
        assertFalse(canBePlaced);
    }
    
    @Test
    public void ColumnMissingValueCanBeSet() {
        Cell cellToBePlaced = new Cell(0, 6, 5);
        boolean canBePlaced = helper.canBeInsertedToColumn(board, cellToBePlaced, 9);
        assertTrue(canBePlaced);
    }
    
    @Test
    public void ColumnExistingValueIsRejected() {
        Cell cellToBePlaced = new Cell(0, 6, 5);
        boolean canBePlaced = helper.canBeInsertedToColumn(board, cellToBePlaced, 7);
        assertFalse(canBePlaced);
    }
    
    @Test
    public void subgridMissingValueCanBeSet() {
        Cell cellToBePlaced = new Cell(0, 8, 0);
        boolean canBePlaced = helper.canBeInsertedToSubgrid(board, cellToBePlaced, 6);
        assertTrue(canBePlaced);
    }
    
    @Test
    public void subgridExistingValueIsRejected() {
        Cell cellToBePlaced = new Cell(0, 4, 4);
        boolean canBePlaced = helper.canBeInsertedToSubgrid(board, cellToBePlaced, 3);
        assertFalse(canBePlaced);
    }

}
