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
        for (int i = 1; i < 9; i *= 2) {
            board.getCells().add(new Cell(i, 1, i, i));
        }
    }

    @Test
    public void RowMissingValueCanBeSet() {
        System.out.println(board.toString());
        Cell cellToBePlaced = new Cell(0, 1, 3, 3);
        boolean canBePlaced = helper.canBeInsertedToRow(board, cellToBePlaced, 3);
        assertTrue(canBePlaced);
    }
    
    @Test
    public void RowExistingValueIsRejected() {
        Cell cellToBePlaced = new Cell(0, 1, 3, 3);
        boolean canBePlaced = helper.canBeInsertedToRow(board, cellToBePlaced, 8);
        assertFalse(canBePlaced);
    }

}
