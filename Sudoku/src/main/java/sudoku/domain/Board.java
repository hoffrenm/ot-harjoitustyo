/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.util.ArrayList;

/**
 *
 * @author hoffrenm
 */
public class Board {
    
    private ArrayList<Cell> cells;

    public Board() {
        this.cells = new ArrayList<>(81);
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
