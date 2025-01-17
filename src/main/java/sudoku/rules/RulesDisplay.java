package sudoku.rules;

import java.util.Scanner;

import sudoku.Main;

import static sudoku.utils.PrintCentered.*;
import static sudoku.utils.PathsAndData.*;
import static sudoku.utils.ClearConsole.clear;

public class RulesDisplay {

    private static final String[] MENU_OPTIONS = {
        "1. Voltar para o Menu Principal",
        "2. Sair do jogo"
    };

    private static final Scanner sc = new Scanner(System.in);

    public static void printRules() {
        clear();
        printCenteredText(RULES_LOGO.getString());
        printRulesDescription();
        printMenuOptions();

        int option;
        do {
            option = getUserInput();
            handleUserInput(option);
        } while (option != 2);
    }

    private static void printRulesDescription() {
        printCenteredText("""
            \nAs linhas horizontais e verticais da grade quadriculada
            devem ser preenchidas com números de 1 a 9,
            assim como cada um dos nove blocos quadrados.
            O desafio é completar todo o sistema sem repetir algarismos nas linhas,
            colunas ou quadrados.
        """);
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

    private static void handleUserInput(int option) {
        switch (option) {
            case 1 -> Main.main(null);
            case 2 -> exitGame();
            default -> printCenteredText("Opção inválida! Tente novamente.");
        }
    }

    private static void exitGame() {
        System.exit(0);
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> sc.close()));
    }
}
