import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
	public static String hashString(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(input.getBytes());
            
      StringBuilder hexString = new StringBuilder();
			for (byte b : digest) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} 
		catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Algorithm not supported: " + e);
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the string to hash: ");
		String inputString = scanner.nextLine();
		String sha256Hash = hashString(inputString);
		System.out.println("SHA-256 Hash: " + sha256Hash);
	}
}
