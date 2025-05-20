import java.util.Scanner;

public class Main {
	public static String caesarCipher(String text, int shift) {
		StringBuilder result = new StringBuilder();
		shift = shift % 26; // Normalize shift
		for (char ch : text.toCharArray()) {
			if (Character.isLetter(ch)) {
				char base = Character.isUpperCase(ch) ? 'A' : 'a';
				ch = (char) ((ch - base + shift + 26) % 26 + base); // Ensure non-negative
			}
			result.append(ch);
		}
		return result.toString();
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter text: ");
			String text = scanner.nextLine();
			int key = 5;

			String encrypted = caesarCipher(text, key);
			System.out.println("Encrypted: " + encrypted);

			String decrypted = caesarCipher(encrypted, -key);
			System.out.println("Decrypted: " + decrypted);
		}
	}
}
