/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.util.ArrayList;
import sudoku.logics.BoardHelper;

/**
 *
 * @author hoffrenm
 */
public class Board {

    private ArrayList<Cell> cells;
    private BoardHelper helper;

    public Board() {
        this.helper = new BoardHelper();
        this.cells = new ArrayList<>(81);
        generateCells();
    }

    public void newSudoku(int cells) {
        generateCells();
        helper.initializeBoard(this, cells);
    }
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

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "Board{" + "cells=" + cells + '}';
    }

}
