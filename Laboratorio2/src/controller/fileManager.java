package controller;
import model.ram;
import model.program;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class fileManager {
	private static final int PROGRAM_NAME = 0;
	private static final int PROGRAM_SIZE = 1;
	private static final int PROGRAM_TIME = 2;
	
	public void GetFilePrograms(ram _ram) {
		program program;
		String file = "Laboratorio2/src\\programasIniciales.csv";
		String line;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				
				String[] fields = line.split(",");
				
				String name = fields[PROGRAM_NAME];
				int size =  Integer.parseInt(fields[PROGRAM_SIZE]);
				int time = Integer.parseInt(fields[PROGRAM_TIME]);
				
				program = new program(name, size, time);
				_ram.getProgramsLINE().add(program);
		
			}
	          br.close();
	       
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
