import java.util.*;

public class Main {

    public static String encrypt(String text, String key) {
        text = text.replaceAll("\\s+", "");
        int cols = key.length();
        int rows = (int) Math.ceil((double) text.length() / cols);
        char[][] grid = new char[rows][cols];

        // Fill the grid row-wise
        int idx = 0;
        for (int i = 0; i < rows && idx < text.length(); i++)
            for (int j = 0; j < cols && idx < text.length(); j++)
                grid[i][j] = text.charAt(idx++);

        // Read column-wise based on key order
        StringBuilder encrypted = new StringBuilder();
        for (int col : getKeyOrder(key))
            for (int i = 0; i < rows; i++)
                encrypted.append(grid[i][col] == 0 ? 'X' : grid[i][col]);

        return encrypted.toString();
    }

    public static String decrypt(String text, String key) {
        int cols = key.length();
        int rows = text.length() / cols;
        char[][] grid = new char[rows][cols];
        int[] order = getKeyOrder(key);

        // Fill the grid column-wise based on key order
        int idx = 0;
        for (int col : order)
            for (int i = 0; i < rows; i++)
                grid[i][col] = text.charAt(idx++);

        // Read row-wise
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                decrypted.append(grid[i][j]);

        return decrypted.toString().replaceAll("X+$", "");
    }

    private static int[] getKeyOrder(String key) {
        int len = key.length();
        Character[] keyChars = new Character[len];
        for (int i = 0; i < len; i++) keyChars[i] = key.charAt(i);

        Character[] sorted = keyChars.clone();
        Arrays.sort(sorted);

        int[] order = new int[len];
        boolean[] used = new boolean[len];
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                if (!used[j] && keyChars[j].equals(sorted[i])) {
                    order[i] = j;
                    used[j] = true;
                    break;
                }

        return order;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();
        System.out.print("Enter key (without spaces): ");
        String key = sc.nextLine().toUpperCase();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
