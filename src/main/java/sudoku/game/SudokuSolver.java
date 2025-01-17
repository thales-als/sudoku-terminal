package sudoku.game;

public class SudokuSolver {

    public boolean solve(SudokuGrid sudokuGrid) {
        int[][] grid = sudokuGrid.getGrid();
        return solveGrid(grid, sudokuGrid);
    }

    private boolean solveGrid(int[][] grid, SudokuGrid sudokuGrid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    return tryNumbersInCell(row, col, grid, sudokuGrid);
                }
            }
        }
        return true;
    }

    private boolean tryNumbersInCell(int row, int col, int[][] grid, SudokuGrid sudokuGrid) {
        for (int num = 1; num <= 9; num++) {
            if (sudokuGrid.isValid(row, col, num)) {
                grid[row][col] = num;
                if (solveGrid(grid, sudokuGrid)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }
        return false;
    }
}
