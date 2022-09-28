package controller;
import model.ram;
import model.program;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * It reads a csv file and creates a program object for each line in the file
 */

public class fileManager {
	private static final int PROGRAM_NAME = 0;
	private static final int PROGRAM_SIZE = 1;
	private static final int PROGRAM_TIME = 2;
	
	// Reading the file and creating a program object for each line in the file.
	public void GetFilePrograms(ram _ram) {
		program program;
		String file = "./src/documents/programasIniciales.csv";
		String line;
		
		try {
			// Reading the file and creating a program object for each line in the file.
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				
				String[] fields = line.split(",");
				
				String name = fields[PROGRAM_NAME];
				int size =  Integer.parseInt(fields[PROGRAM_SIZE]);
				int time = Integer.parseInt(fields[PROGRAM_TIME]);
				
				program = new program(name, size /64, time);
				_ram.getProgramsLINE().add(program);
		
			}
	          br.close();
	       
			
		// Catching the exception if the file is not found or if there is an IOException.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
