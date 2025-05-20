import java.util.*;

public class Main {
    private static int[][] key = new int[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 3x3 key matrix:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                key[i][j] = sc.nextInt();

        sc.nextLine(); // consume newline
        System.out.println("Enter plaintext:");
        String input = sc.nextLine();
        String cleaned = input.replaceAll("\\s", "").toUpperCase();

        while (cleaned.length() % 3 != 0)
            cleaned += 'X'; // Padding

        String encrypted = process(cleaned, key);
        System.out.println("Encrypted text: " + encrypted);

        int[][] inverse = inverseKeyMatrix();
        String decrypted = process(encrypted, inverse);
        System.out.println("Decrypted text: " + reconstruct(input, decrypted));
    }

    // Encrypt/Decrypt
    static String process(String text, int[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 3) {
            for (int row = 0; row < 3; row++) {
                int val = 0;
                for (int col = 0; col < 3; col++)
                    val += matrix[row][col] * (text.charAt(i + col) - 'A');
                result.append((char) ((val % 26 + 26) % 26 + 'A'));
            }
        }
        return result.toString();
    }

    // Reconstruct with spaces
    static String reconstruct(String original, String decrypted) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        for (char c : original.toCharArray())
            result.append(c == ' ' ? ' ' : decrypted.charAt(index++));
        return result.toString();
    }

    // Inverse of key matrix (mod 26)
    static int[][] inverseKeyMatrix() {
        int[][] inv = new int[3][3];
        int d = determinant(key);
        int invD = modInverse((d % 26 + 26) % 26, 26);
        int[][] adj = adjugate(key);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                inv[i][j] = (invD * adj[i][j] % 26 + 26) % 26;

        return inv;
    }

    // Determinant
    static int determinant(int[][] m) {
        return m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
             - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
             + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
    }

    // Adjugate matrix
    static int[][] adjugate(int[][] m) {
        return new int[][] {
            {
                m[1][1]*m[2][2] - m[1][2]*m[2][1],
                -(m[0][1]*m[2][2] - m[0][2]*m[2][1]),
                m[0][1]*m[1][2] - m[0][2]*m[1][1]
            },
            {
                -(m[1][0]*m[2][2] - m[1][2]*m[2][0]),
                m[0][0]*m[2][2] - m[0][2]*m[2][0],
                -(m[0][0]*m[1][2] - m[0][2]*m[1][0])
            },
            {
                m[1][0]*m[2][1] - m[1][1]*m[2][0],
                -(m[0][0]*m[2][1] - m[0][1]*m[2][0]),
                m[0][0]*m[1][1] - m[0][1]*m[1][0]
            }
        };
    }

    // Modular inverse
    static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1) return x;
        return 1; // fallback
    }
}


//Ip - op:

//key:
//    {6, 24, 1},
//    {13, 16, 10},
//    {20, 17, 15}
// text: act

