package model;

import java.util.ArrayList;

public class ram {
	
// Declaring the variables that will be used in the class.
	private String ramType;
	private int ramStorage;
	private int AvailableMemory;
	private ArrayList<program> programsEXE; 
	private ArrayList<program> programsLINE;

// A constructor that is used to create a new object of the class ram.
	public ram(String _ramType, int _ramStorage, int _AvailableMemory){
		this.AvailableMemory = _AvailableMemory;
		this.ramType = _ramType;
		this.ramStorage = _ramStorage;
		
		programsEXE = new ArrayList<program>();
		programsLINE = new ArrayList<program>();

}
	
	/**
	 * This function returns the ramType of the computer
	 * 
	 * @return The ramType variable is being returned.
	 */

	public String getRamType() {
		return ramType;
	}
	
	/**
	 * This function sets the ramType of the computer
	 * 
	 * @param ramType The type of RAM used in the computer.
	 */

	public void setRamType(String ramType) {
		this.ramType = ramType;
	}
	
	/**
	 * This function returns the value of the ramStorage variable
	 * 
	 * @return The ramStorage variable is being returned.
	 */

	public int getRamStorage() {
		return ramStorage;
	}
	
	/**
	 * This function sets the ramStorage variable to the value of the parameter ramStorage
	 * 
	 * @param ramStorage The amount of RAM storage the computer has.
	 */

	public void setRamStorage(int ramStorage) {
		this.ramStorage = ramStorage;
	}
	
	/**
	 * This function returns the programsEXE ArrayList
	 * 
	 * @return The programsEXE arraylist.
	 */

	public ArrayList<program> getProgramsEXE() {
		return programsEXE;
	}
	
	/**
	 * This function sets the programsEXE variable to the value of the parameter programsEXE
	 * 
	 * @param programsEXE An ArrayList of program objects.
	 */

	public void setProgramsEXE(ArrayList<program> programsEXE) {
		this.programsEXE = programsEXE;
	}

	/**
	 * This function returns the ArrayList of programs that are in the LINE category
	 * 
	 * @return The programsLINE arraylist is being returned.
	 */

	public ArrayList<program> getProgramsLINE() {
		return programsLINE;
	}
	
	/**
	 * This function sets the value of the programsLINE variable to the value of the programsLINE variable
	 * passed in as a parameter
	 * 
	 * @param programsLINE ArrayList of program objects
	 */

	public void setProgramsLINE(ArrayList<program> programsLINE) {
		this.programsLINE = programsLINE;
	}

	/**
	 * This function returns the amount of available memory
	 * 
	 * @return The available memory.
	 */

	public int getAvailableMemory() {
		return AvailableMemory;
	}

	/**
	 * This function sets the available memory of the computer
	 * 
	 * @param availableMemory The amount of memory available on the node.
	 */
	public void setAvailableMemory(int availableMemory) {
		AvailableMemory = availableMemory;
	}
	
}


