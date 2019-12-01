/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

/**
 *
 * @author hoffrenm
 */
public class Cell {
    
    private int value;
    private int row;
    private int column;
    private int subgrid;

    public Cell(int value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
        this.subgrid = calculateSubgrid(row, column);
    }
    
    private int calculateSubgrid(int row, int col) {
        int rowGrid = row / 3;
        int colGrid = col / 3;
        
        return colGrid + rowGrid * 3;
    }
    
    public void resetCellValue() {
        this.value = 0;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getSubgrid() {
        return subgrid;
    }

    @Override
    public String toString() {
        return "Cell{" + "value=" + value + ", row=" + row + ", column=" + column + ", subgrid=" + subgrid + '}';
    }
    
}
