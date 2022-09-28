package model;

public class program {
	
	private String name;
	private int programSize;
	private int runTime;
	
	// A constructor.
	public program(String _name, int _programSize, int _runTime) {
		this.name = _name;
		this.programSize = _programSize;
		this.runTime = _runTime;
	}
	
	/**
	 * This function returns the name of the person
	 * 
	 * @return The name of the person.
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * This function sets the name of the object to the name passed in as a parameter
	 * 
	 * @param name The name of the parameter.
	 */

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This function returns the runTime of the movie
	 * 
	 * @return The runTime variable is being returned.
	 */

	public int getRunTime() {
		return runTime;
	}
	
	/**
	 * This function sets the run time of the movie
	 * 
	 * @param runTime The time in seconds that the program will run for.
	 */

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}
	
	/**
	 * This function returns the size of the program
	 * 
	 * @return The program size.
	 */

	public int getProgramSize() {
		return programSize;
	}
	
	/**
	 * This function sets the program size
	 * 
	 * @param programSize The size of the program.
	 */
	
	public void setProgramSize(int programSize) {
		this.programSize = programSize;
	}


}
