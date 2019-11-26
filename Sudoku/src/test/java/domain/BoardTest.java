/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.Board;
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
        helper.initializeBoard(board, 30);
    }
    
    @Test
    public void boardContains81Cells() {
        int amountOfCells = board.getCells().size();
        Assert.assertEquals(81, amountOfCells);
    }
    
    @Test
    public void atleastSomeCellValuesAreSet() {
        long revealedValues = board.getCells().stream().filter(c -> c.getValue() != 0).count();
        assertTrue(revealedValues > 20);
    }
    
    @Test
    public void mostOfCellsAreHidden() {
        long hiddenValues = board.getCells().stream().filter(c -> c.getValue() == 0).count();
        assertTrue(hiddenValues > 45);
    }
    
}
