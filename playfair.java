import java.util.*;

public class Main {
    static char[][] square = new char[5][5];

    static void buildSquare(String key) {
        key = key.toUpperCase().replace("J", "I");
        boolean[] used = new boolean[26];
        int i = 0;
        for (char c : (key + "ABCDEFGHIKLMNOPQRSTUVWXYZ").toCharArray()) {
            if (Character.isLetter(c) && !used[c - 'A']) {
                square[i / 5][i % 5] = c;
                used[c - 'A'] = true;
                i++;
            }
        }
    }

    static int[] pos(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (square[i][j] == c) return new int[]{i, j};
        return null;
    }

    static String prepare(String s) {
        s = s.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1))
                sb.append('X');
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    static String go(String text, boolean enc) {
        StringBuilder out = new StringBuilder();
        int shift = enc ? 1 : 4;
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] pa = pos(a), pb = pos(b);
            if (pa[0] == pb[0]) {
                out.append(square[pa[0]][(pa[1] + shift) % 5]);
                out.append(square[pb[0]][(pb[1] + shift) % 5]);
            } else if (pa[1] == pb[1]) {
                out.append(square[(pa[0] + shift) % 5][pa[1]]);
                out.append(square[(pb[0] + shift) % 5][pb[1]]);
            } else {
                out.append(square[pa[0]][pb[1]]);
                out.append(square[pb[0]][pa[1]]);
            }
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Key: ");
        buildSquare(sc.nextLine());
        System.out.print("Text: ");
        String clean = prepare(sc.nextLine());
        String enc = go(clean, true);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + go(enc, false));
    }
}
