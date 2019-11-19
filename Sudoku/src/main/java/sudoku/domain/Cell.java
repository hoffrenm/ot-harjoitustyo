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

    public Cell(int value, int row, int column, int subgrid) {
        this.value = value;
        this.row = row;
        this.column = column;
        this.subgrid = subgrid;
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

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSubgrid() {
        return subgrid;
    }

    public void setSubgrid(int subgrid) {
        this.subgrid = subgrid;
    }

    @Override
    public String toString() {
        return "Cell{" + "value=" + value + ", row=" + row + ", column=" + column + ", subgrid=" + subgrid + '}';
    }
    
}
