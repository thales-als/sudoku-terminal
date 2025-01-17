package sudoku.leaderboards;

public class Player {
    private String name;
    private int completionTime;

    public Player(String name, int completionTime) {
        this.name = name;
        this.completionTime = completionTime;
    }

    public String getName() {
        return name;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    @Override
    public String toString() {
        return name + ", " + completionTime;
    }
}