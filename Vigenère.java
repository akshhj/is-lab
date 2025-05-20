import java.util.*;

public class Main {
    static String process(String text, String key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            char t = text.charAt(i);
            char k = key.charAt(i % key.length());
            int shift = encrypt ? k - 'A' : 26 - (k - 'A');
            result.append((char) ((t - 'A' + shift) % 26 + 'A'));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Keyword: ");
        String key = sc.next().toUpperCase();
        System.out.print("Plaintext: ");
        String text = sc.next().toUpperCase();
        String encrypted = process(text, key, true);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + process(encrypted, key, false));
    }
}
