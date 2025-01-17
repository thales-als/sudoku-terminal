package sudoku.leaderboards;

import java.io.*;
import java.util.*;
import static sudoku.utils.PathsAndData.*;

public class GameRecord {

    private static final String FILE_PATH = LEADERBOARDS_DATA.getString();

    public static void create(Player player) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(player.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }

    public static List<Player> readAll() {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0];
                int completionTime = Integer.parseInt(parts[1]);
                players.add(new Player(name, completionTime));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return players;
    }
}