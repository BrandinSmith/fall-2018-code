// This program starts Assignment01 for you by laying the foundation for the 
// simple shell program.
// Date: 9/5/18
// Author: McNett

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SimpleShell{

	public static void main(String[] args) throws IOException, InterruptedException {
		// defines template for our process -- specifies process attributes 
		ProcessBuilder pb = new ProcessBuilder("ls"); //linux command
		//ProcessBuilder pb = new ProcessBuilder("bash", "-c", "ls"); //windows 10 command
		pb.directory(new File("c:\\test"));
		Process p = pb.start();// starts process
		
		p.waitFor(); //to ensure output is ready
		
		// get process output as our input and display to screen
		Scanner input = new Scanner(p.getInputStream());
		while(input.hasNext())
			System.out.println(input.next()); 
		
		input.close();
		
	}
}