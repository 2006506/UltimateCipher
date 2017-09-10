import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Encrypter {
	public static void main(String[] args) {
		String message = JOptionPane.showInputDialog("What is your message?");
		message = message.toLowerCase();
		String encrypted;
		
		char[] originalMessage = new char[message.length()];
		
		
		for (int i = 0; i < originalMessage.length; i++) {
			char buffer = message.charAt(i);
			originalMessage[i] = buffer;			
		}
		
		message = caesarCipher(originalMessage);
		encrypted = polybius(message);

		JOptionPane.showMessageDialog(null, "Message: " + encrypted);
		System.out.println(encrypted);
	}
	
	private static String caesarCipher(char[] message) {
		String encrypted = "";
		for (int i = 0; i < message.length; i++) {
			char nextChar;
			char buffer = message[i];
			
			nextChar = shifter(buffer);
            
			message[i] = nextChar;
			
			encrypted = encrypted + message[i];
		}
		return encrypted;
		
	}
	
	private static char shifter(char buffer) {
		int i = 0;
		int shift = 9;
		char nextChar = 'a';
		
		if (buffer == ' ') {
			return ' ';
		}
		while (i < shift) {
			if (i == 0) {
				if (buffer == 'z') {
					nextChar = 'a';
				}
				else {
					nextChar = (char)(((int) buffer) + 1);
				}
				buffer = nextChar;
				i++;
			}
			else {
				if (buffer == 'z') {
					nextChar = 'a';
				}
				else {
					nextChar = (char)(((int) nextChar) + 1);
				}
				buffer = nextChar;
				i++;
			}
			
		}
		
		return nextChar;
	}
	
	private static String polybius(String message) { 
		final int ROWS = 5;
		final int COLUMNS = 5;
		int x = 0;
		int y = 0;
		int z = 0;
		boolean isDone = false;
		char[][] polybius = new char[ROWS][COLUMNS];
		char[] originalMessage = new char[message.length()];
		char[] alphabet = new char[26];
		ArrayList<String> encryptedMessage = new ArrayList<String>();
		int endTester = message.length() - 1;
		String encrypted = "";
		String index = "";
		String index1 = "";
		String index2 = "";
		String index3 = "";

		// Shoves the message into an array
		for (int i = 0; i < originalMessage.length; i++) {
			char buffer = message.charAt(i);
			originalMessage[i] = buffer;			
		}
		
		// Creates AlphaTable
		for (char i = 'a'; i <= 'z'; i++) {
			alphabet[x] = i;
			x++;
		}

		// Create Polybius
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (alphabet[y] == 'j') {
					j--;
				}
				if (alphabet[y] != 'j') {
					polybius[i][j] = alphabet[y];
				}
					y++;
			}
		}
		
		// Encrypt The message
		for (int h = 0; h < originalMessage.length; h++) { // Change to a do while on the otter loop
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLUMNS; j++) {
					if (originalMessage[z] == 'j') {
						index3 = "31";
						encryptedMessage.add(index3);
						z++;
					}
					if (polybius[i][j] == originalMessage[z]) {
						index1 = Integer.toString(i);
						index2 = Integer.toString(j);
						index = index1 + index2;
						encryptedMessage.add(index);
						z++;
					}
				}
			}

		}
		
		for (int i = 0; i < encryptedMessage.size(); i++) {
			encrypted = encrypted + encryptedMessage.get(i);
		}
		
		return encrypted;
	}

}
