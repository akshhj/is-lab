import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class Main {
	public static String hash(String input) {
		try {
// Create a MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
// Perform the hash computation
			byte[] digest = md.digest(input.getBytes());
// Convert byte array into hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte b : digest) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 algorithm not found!", e);
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a string to hash with MD5: ");
		String input = scanner.nextLine();
		String hashedOutput = hash(input);
		System.out.println("MD5 hash:");
		System.out.println(hashedOutput);
		scanner.close();
	}
}
