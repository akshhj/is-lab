import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Main {
	public static String encrypt(String plainText, String key) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		byte[] encryptedText = cipher.doFinal(plainText.getBytes());
		return Base64.getEncoder().encodeToString(encryptedText);
	}
	public static String decrypt(String encryptedText, String key) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] decodedText = Base64.getDecoder().decode(encryptedText);
		byte[] decryptedText = cipher.doFinal(decodedText);
		return new String(decryptedText); 
	} 
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the text to encrypt: ");
			String plainText = scanner.nextLine();

			System.out.print("Enter an 8-character key: ");
			String key = scanner.nextLine();
			if (key.length() != 8) {
				System.out.println("Error: Key must be exactly 8 characters long.");
				scanner.close();
				return;
			}
			String encryptedText = encrypt(plainText, key);
			System.out.println("Encrypted Text: " + encryptedText);
			String decryptedText = decrypt(encryptedText, key);
			System.out.println("Decrypted Text: " + decryptedText);
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
