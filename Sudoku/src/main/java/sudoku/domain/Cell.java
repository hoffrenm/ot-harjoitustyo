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
/**
 * Represent singular cell in Sudoku-game
 */
public class Cell {

    private int value;
    private final int row;
    private final int column;
    private final int subgrid;

    /**
     * Creates a new cell with provided parameters. Subgrid of cell is
     * calculated automatically for 9x9 board.
     *
     * @param value starting value of cell
     * @param row row coordinate of cell
     * @param column column coordinate of cell
     */
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

    /**
     * Resets cell value to zero
     */
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }

        return this.subgrid == other.subgrid;
    }
}
