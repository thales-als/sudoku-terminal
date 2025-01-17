package sudoku.utils;

public class ClearConsole {

    public static void clear() {
        String os = System.getProperty("os.name").toLowerCase();
        
        try {
        	
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls")
                	.inheritIO()
                	.start()
                	.waitFor();
            }
            
            else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                new ProcessBuilder("clear")
                	.inheritIO()
                	.start()
                	.waitFor();
            }
            
        } catch (Exception ex) {
            System.out.println("Não foi possível limpar o console: " + ex.getMessage());
        }
    }
}
