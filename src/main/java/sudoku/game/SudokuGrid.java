package sudoku.game;

import java.util.Random;

public class SudokuGrid {
    private final int[][] grid = new int[9][9];
    private static final Random random = new Random();
    private final SudokuSolver solver = new SudokuSolver();

    public int[][] getGrid() {
        return grid;
    }

    public void generateRandomBoard() {
        generateWinningBoard();
        removeValuesForUser();
    }

    private void generateWinningBoard() {
        clearGrid();
        solver.solve(this);
    }

    private void clearGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = 0;
            }
        }
    }

    private void removeValuesForUser() {
        int cellsToRemove = 61;
        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (grid[row][col] != 0) {
                grid[row][col] = 0;
                cellsToRemove--;
            }
        }
    }

    public boolean setValue(int row, int col, int value) {
        if (isValid(row, col, value)) {
            grid[row][col] = value;
            return true;
        }
        return false;
    }

    public boolean isValid(int row, int col, int value) {
        if (grid[row][col] != 0 || value < 1 || value > 9) return false;

        for (int counter = 0; counter < 9; counter++) {
            if (grid[row][counter] == value || grid[counter][col] == value) return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int indexRow = startRow; indexRow < startRow + 3; indexRow++) {
            for (int indexCol = startCol; indexCol < startCol + 3; indexCol++) {
                if (grid[indexRow][indexCol] == value) return false;
            }
        }

        return true;
    }

    public boolean isComplete() {
        for (int[] row : grid) {
            for (int value : row) {
                if (value == 0) return false;
            }
        }
        return true;
    }
}
