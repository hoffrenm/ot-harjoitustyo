/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import sudoku.domain.Cell;

/**
 *
 * @author hoffrenm
 */
public class CellTest {

    Cell cell;

    @Before
    public void setUp() {
        this.cell = new Cell(0, 3, 7);
    }

    @Test
    public void rowIsCorrect() {
        assertEquals(3, cell.getRow());
    }

    @Test
    public void columnIsCorrect() {
        System.out.println(cell.getSubgrid());
        assertEquals(7, cell.getColumn());
    }

    @Test
    public void subgridIsCalculatedCorrectly() {
        assertEquals(5, cell.getSubgrid());
    }

    @Test
    public void startValueIsZero() {
        assertEquals(0, cell.getValue());
    }

    @Test
    public void valueCanBeChanged() {
        cell.setValue(1);
        assertEquals(1, cell.getValue());
    }

    @Test
    public void valueCanBeZeroed() {
        cell.setValue(6);
        assertEquals(6, cell.getValue());
        cell.resetCellValue();
        assertEquals(0, cell.getValue());
    }

}
