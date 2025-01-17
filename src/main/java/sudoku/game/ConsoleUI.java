package sudoku.game;

import java.util.Scanner;
import sudoku.leaderboards.Player;

import static sudoku.utils.PathsAndData.DEFEAT_SE;
import static sudoku.utils.PathsAndData.VICTORY_SE;
import static sudoku.Main.*;
import static sudoku.leaderboards.GameRecord.*;
import static sudoku.utils.AudioThread.*;
import static sudoku.utils.PrintCentered.*;
import static sudoku.utils.ClearConsole.*;

public class ConsoleUI {
    private final SudokuGrid sudokuGrid;
    private final Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    public ConsoleUI(SudokuGrid sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }

    public void startGame() {
        sudokuGrid.generateRandomBoard();
        long startTime = System.currentTimeMillis();
        
        clear();

        while (isRunning) {
        	printGrid();

            if (sudokuGrid.isComplete()) {
                handleGameCompletion(startTime);
                break;
            }

            promptForPlayerMove();
        }
    }

    private void promptForPlayerMove() {
        printCenteredText("Digite 'sair' para sair da partida.");
        printCenteredText("Digite sua jogada no formato (A1 5), ou 'resolver' para completar:");
        String input = scanner.nextLine();

        if ("sair".equalsIgnoreCase(input)) {
            main(null);
        } else if ("resolver".equalsIgnoreCase(input)) {
            attemptToSolveSudoku();
        } else {
            processPlayerMove(input);
        }
    }

    private void handleGameCompletion(long startTime) {
        playWinnerTheme();
        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - startTime) / 1000;

        printCenteredText("Parabéns, você completou o Sudoku em " + timeTaken + " segundos!");
        String playerName = promptForPlayerName();
        saveGameRecord(playerName, (int) timeTaken);
        askToPlayAgain();
    }

    private String promptForPlayerName() {
        printCenteredText("Digite seu nome: ");
        return scanner.nextLine();
    }

    private void saveGameRecord(String playerName, int timeTaken) {
        Player player = new Player(playerName, timeTaken);
        create(player);
    }

    private void askToPlayAgain() {
        printCenteredText("Deseja jogar novamente? (s/n)");
        String response = scanner.nextLine();

        if ("s".equalsIgnoreCase(response)) {
            sudokuGrid.generateRandomBoard();
            startGame();
        } else {
            main(null);
        }
    }

    private void attemptToSolveSudoku() {
    	SudokuSolver solver = new SudokuSolver();
        boolean solvable = solver.solve(sudokuGrid);

        clear();
        
        if (!solvable) {
            handleDefeat();
        }
    }

    private void handleDefeat() {
        playLoserTheme();
        printGrid();
        printCenteredText("Este Sudoku não tem solução.");
        askToPlayAgain();
    }

    private void processPlayerMove(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 2) {
        	clear();
            printCenteredText("Formato inválido. Tente novamente.");
            return;
        }

        try {
            String cell = parts[0].toUpperCase();
            int value = Integer.parseInt(parts[1]);

            int row = cell.charAt(0) - 'A';
            int col = Integer.parseInt(cell.substring(1)) - 1;
            
            clear();

            if (!sudokuGrid.setValue(row, col, value)) {
            	clear();
                printCenteredText("Jogada inválida. Tente novamente.");
            }
        } catch (Exception e) {
        	clear();
            printCenteredText("Entrada inválida. Tente novamente.");
        }
    }

    private void playWinnerTheme() {
    	threadInit(VICTORY_SE.getString());
    }

    private void playLoserTheme() {
        threadInit(DEFEAT_SE.getString());
    }

    private void printGrid() {
        int[][] grid = sudokuGrid.getGrid();

        printCenteredText("  1 2 3   4 5 6   7 8 9");
        printCenteredText("  +-------+-------+-------+");

        for (int counter = 0; counter < 9; counter++) {
            printGridRow(counter, grid);
        }
    }

    private void printGridRow(int index, int[][] grid) {
        StringBuilder line = new StringBuilder((char) ('A' + index) + " | ");
        for (int counter = 0; counter < 9; counter++) {
            line.append((grid[index][counter] == 0 ? "." : grid[index][counter]) + " ");
            if ((counter + 1) % 3 == 0) line.append("| ");
        }
        printCenteredText(line.toString());

        if ((index + 1) % 3 == 0) {
            printCenteredText("  +-------+-------+-------+");
        }
    }
}
