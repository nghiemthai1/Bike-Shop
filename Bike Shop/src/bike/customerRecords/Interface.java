package bike.customerRecords;


import javax.swing.JOptionPane;

/**
 * A user interface for the University's Person Application.
 * Presents all prompts, errors, and messages to the user
 * in the form of dialog boxes.
 * 
 * @author Gena Gizzi
 * @author Eugene Koval
 */
public class Interface {
	
	/**
	 * Presents an error with a given message.
	 * Selecting 'OK' dismisses this dialog box.
	 * 
	 * @param message the message to be presented.
	 */
	public static void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error!", 
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Prompts the user for an input. An input may be typed in the box 
	 * provided and selecting 'OK'. Clicking 'Cancel' terminates the program.
	 * 
	 * @param prompt the message to be displayed to the user.
	 * @return the user's input
	 */
	public static String getInput(String prompt) {
		String input = JOptionPane.showInputDialog(null, 
				prompt, "Required Field"
						+ "", JOptionPane.QUESTION_MESSAGE);
		if (input == null) {
			System.exit(0);
		}
		return input;
	}
	
	/**
	 * Presents a given message to the user.
	 * Selecting 'OK' dismisses this dialog box.
	 * 
	 * @param message the message to be presented.
	 */
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Hint",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	
	
	
}
