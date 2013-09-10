/**
 * File: Log.java
 * Created: 8/29/2013
 * Last Changed: 9/8/2013
 * @author: Eliezer Encarnacion
 * 
 * Description: A class that will take care of all the functionality
 * 				related to logging the instances of errors given
 * 				by checkstyle.
 */

package logging;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import org.eclipse.core.internal.resources.*;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

import javax.swing.JOptionPane;

public class Log {
	
	private Scanner in;
	private PrintWriter out;
	Calendar dateAndTime;
	private SimpleDateFormat dateFormat;
	private String username;
	
	public Log() {
		
		dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		username = System.getProperty("user.name");
		
		//fileSeparator determines if the OS uses '/' or '\' for path files
		String fileSeparator = System.getProperty("file.separator");
		String logFilePath = getProjectDirectory() + fileSeparator + "log.txt";
		
		try {
			out = new PrintWriter(new FileWriter(logFilePath,true));
			in = new Scanner(logFilePath);
		}
		
		
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "I couldn't find the log file");
		}
	}
	
	public void log(String error, String fileName, String lineNum) {
		dateAndTime = Calendar.getInstance(); //get current date and time
		dateAndTime.add(Calendar.DATE, 1);
		
		String formattedDate = dateFormat.format(dateAndTime.getTime());
		out.printf("%s, %s, %s, %s, %s\n",username, formattedDate, fileName, error, lineNum);
	}
	
	public String getProjectDirectory() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		return workspace.getRoot().getLocation().toString();
	}
	
	public void closeStream() {
		out.close();
		in.close();
	}

}
