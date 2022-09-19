package model;

public class program {
	
	public String name;
	public int programSize;
	public int runTime;
	
	public program(String name, int programSize, int runTime) {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRunTime() {
		return runTime;
	}
	
	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}
	
	public int getProgramSize() {
		return programSize;
	}
	
	public void setProgramSize(int programSize) {
		this.programSize = programSize;
	}


}
