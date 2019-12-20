package sudoku.logics;

import java.util.Random;
import sudoku.domain.Board;
import sudoku.domain.Cell;

/**
 * Holds all the logic to manage board for Sudoku-game
 *
 * @author hoffrenm
 */
public class BoardHelper {

    private String[] sudokus = {"176328459538149672492765138657834921924651387381972564813296745749583216265417893",
        "278634951935271486416859237743915628681723549529486173394568712152397864867142395",
        "312654978754982316968713425586379241291465837473128659625837194147296583839541762",
        "586413729732569148941278356429831567865724913173956284298147635314695872657382491",
        "428971653596238741317546892143859267982617534675324189864792315239185476751463928"};

    private String testSudoku = "030200570000300040901047008002003087080600000056010239800020400400098005017005006";

    /**
     * Initialises a board with amount of randomly revealed values.
     *
     * @param board board that will be initialised
     * @param numberOfCells number of cells to be revealed
     */
    public void initializeBoard(Board board, int numberOfCells) {
        Random random = new Random();

        String sudoku = sudokus[random.nextInt(sudokus.length)];

        for (int i = 0; i < numberOfCells; i++) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            int index = col + row * 9;

            board.getCells().get(index).setValue(Character.getNumericValue(sudoku.charAt(index)));
        }
    }

    /**
     * Preset board for testing
     *
     * @param board board to be used
     */
    public void initializeTestSudoku(Board board) {
        for (int i = 0; i < testSudoku.length(); i++) {
            board.getCells().get(i).setValue(Character.getNumericValue(testSudoku.charAt(i)));
        }
    }

    /**
     * Determines if all of the cell values has been set.
     *
     * @param board current board
     * @return true: none of the cell values is zero
     */
    public boolean hasNoEmptyValues(Board board) {
        return board.getCells()
                .stream()
                .noneMatch(c -> c.getValue() == 0);
    }

    /**
     * Determines if value can be inserted to specific cell in row.
     *
     * @param board current board
     * @param cell cell which value is going to be changed
     * @param value value to set to the cell
     * @return true: if value is not present in same row
     */
    public boolean canBeInsertedToRow(Board board, Cell cell, int value) {
        return board.getCells()
                .stream()
                .filter(c -> c.getRow() == cell.getRow())
                .noneMatch(c -> c.getValue() == value);
    }

    /**
     * Determines if value can be inserted to specific cell in column.
     *
     * @param board current board
     * @param cell cell which value is going to be changed
     * @param value value to set to the cell
     * @return true: if value is not present in same column
     */
    public boolean canBeInsertedToColumn(Board board, Cell cell, int value) {
        return board.getCells()
                .stream()
                .filter(c -> c.getColumn() == cell.getColumn())
                .noneMatch(c -> c.getValue() == value);
    }

    /**
     * Determines if value can be inserted to specific cell in subgrid.
     *
     * @param board current board
     * @param cell cell which value is going to be changed
     * @param value value to set to the cell
     * @return true: if value is not present in same subgrid
     */
    public boolean canBeInsertedToSubgrid(Board board, Cell cell, int value) {
        return board.getCells()
                .stream().
                filter(c -> c.getSubgrid() == cell.getSubgrid())
                .noneMatch(c -> c.getValue() == value);
    }

}
