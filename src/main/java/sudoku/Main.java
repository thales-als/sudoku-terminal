package sudoku;

import java.util.Scanner;

import sudoku.game.ConsoleUI;
import sudoku.game.SudokuGrid;

import static sudoku.utils.AudioThread.threadInit;
import static sudoku.utils.ClearConsole.clear;
import static sudoku.utils.PrintCentered.printCenteredText;
import static sudoku.utils.PathsAndData.*;
import static sudoku.leaderboards.LeaderboardsDisplay.printLeaderboardsMenu;
import static sudoku.rules.RulesDisplay.printRules;

public class Main {

    private static final String[] MENU_OPTIONS = {
        "1. Novo Jogo", 
        "2. Placar de líderes",
        "3. Regras",
        "4. Sair do jogo"
    };

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        clear();
        printCenteredText(MAIN_MENU_LOGO.getString());
        printMenuOptions();
        threadInit(MENU_ANNOUNCER.getString());

        int option;
        do {
            option = getUserInput();
            handleUserSelection(option);
        } while (option != 4);
    }

    private static void printMenuOptions() {
        for (String option : MENU_OPTIONS) {
            printCenteredText("\n" + option);
        }
        printCenteredText("\nEscolha a opção desejada: ");
    }

    private static int getUserInput() {
        while (!sc.hasNextInt()) {
            sc.next();
            printCenteredText("Entrada inválida! Tente novamente.");
        }
        return sc.nextInt();
    }

    private static void handleUserSelection(int option) {
        switch (option) {
            case 1 -> startNewGame();
            case 2 -> printLeaderboardsMenu();
            case 3 -> printRules();
            case 4 -> exitGame();
            default -> printCenteredText("Opção inválida! Tente novamente.");
        }
    }

    private static void startNewGame() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        ConsoleUI consoleUI = new ConsoleUI(sudokuGrid);
        consoleUI.startGame();
    }

    private static void exitGame() {
        System.exit(0);
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> sc.close()));
    }
}
