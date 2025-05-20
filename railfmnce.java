import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the text:");
		String str = s.nextLine();
		System.out.println("Enter the row size:");
		int r = s.nextInt();

		char[][] mat = new char[r][str.length()];
		int i = 0, flag = 0;

		for (int j = 0; j < str.length(); j++) {
			mat[i][j] = str.charAt(j);
			if (i == r - 1) flag = 1;
			
			else if (i == 0) flag = 0;
			
			if (flag == 1) i--;
			else i++;
		}

		StringBuilder e = new StringBuilder();
		for (int k = 0; k < r; k++)
			for (int j = 0; j < str.length(); j++)
				if (mat[k][j] != ' ')
					e.append(mat[k][j]);

		StringBuilder d = new StringBuilder();
		System.out.println("Encrypted Text:" + e.toString());

		i = 0;

    //same as encryption
		for (int j = 0; j < str.length(); j++) {
			d.append(mat[i][j]);
			if (i == r - 1) flag = 1;
			
			else if (i == 0) flag = 0;
			
			if (flag == 1) i--;
			else i++;
		}
		System.out.println("Decrypted Text:" + d.toString());
	}
}
