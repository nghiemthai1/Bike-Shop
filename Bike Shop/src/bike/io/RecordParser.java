package bike.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class RecordParser  {
	
	public static List<String> parseData(File file) {
		List<String> fileData = new LinkedList<String>();
		try(BufferedReader reader =
				new BufferedReader(
				new FileReader(file))) {
			String lineData = "";
			while ((lineData = reader.readLine()) != null) {
				fileData.add(lineData);
			}
			for (Iterator<String> it = fileData.iterator(); it.hasNext(); ) {
				String data = it.next();
				if (data.contains(",,") || data.contains("#")) {
					it.remove();
				}
			}
		}
		catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, 
					file.getName() + " was not found.", "File Not Found", 
					JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return fileData;
	}
}
