/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.util.ArrayList;
import sudoku.logics.BoardHelper;

 /**
 * Represent board for Sudoku-game. Upholds all the cells
 * and uses BoardHelper to manage operations.
 * 
 * @author hoffrenm
 */
public class Board {
    
    private ArrayList<Cell> cells;
    private final BoardHelper helper;

    /**
     * initialise 9x9 board filled with 81 zero value cells.  
     */
    public Board() {
        this.helper = new BoardHelper();
        this.cells = new ArrayList<>(81);
        generateCells();
    }

    /**
     * Creates new board for game and reveals 
     * approximately amount of cells provided in parameter.
     * 
     * @param numOfcells amount of cells to be revealed
     */
    public void newSudoku(int numOfcells) {
        generateCells();
        helper.initializeBoard(this, numOfcells);
    }
    
    /**
     * Returns the cell located in provided coordinates.
     * 
     * @param rowIndex index of row between 0-8.
     * @param colIndex index of column between 0-8.
     * @return cell at coordinates.
     */
    public Cell getCellInGrid(int rowIndex, int colIndex) {
        return cells.stream()
                .filter(cell -> cell.getRow() == rowIndex && cell.getColumn() == colIndex)
                .findFirst()
                .get();
    }

    private void generateCells() {
        this.cells = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell cell = new Cell(0, i, j);
                cells.add(cell);
            }
        }
    }
    
    /**
     * States if game has ended. This method is not concerned whether solution
     * is correct or not.
     * 
     * @return true: if board has no zero values
     */
    public boolean isFinished() {
        return helper.hasNoEmptyValues(this);
    }

    /**
     * Sets numeric value to cell. Method does not allow 
     * setting value that is present in conflicting fields.
     * 
     * @param cell cell which value will be changed
     * @param value value to set
     * @return true: cell value was changed
     */
    public boolean setValue(Cell cell, int value) {
        boolean setToRow = helper.canBeInsertedToRow(this, cell, value);
        boolean setToCol = helper.canBeInsertedToColumn(this, cell, value);
        boolean setToSub = helper.canBeInsertedToSubgrid(this, cell, value);
        
        System.out.println("row: " + setToRow + " col: " + setToCol + " sub: " + setToSub);
        System.out.println(cell + " toInsert :" + value);
        
        if (setToRow && setToCol && setToSub) {
            cell.setValue(value);
            return true;
        }
        
        return false;
    }

    /**
     * Cells of current board. List is not ordered.
     * 
     * @return list of cells in current board.
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return "Board{" + "cells=" + cells + '}';
    }

}
