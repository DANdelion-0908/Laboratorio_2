package model;

public class program {
	
	private String name;
	private int programSize;
	private int runTime;
	
	public program(String _name, int _programSize, int _runTime) {
		this.name = _name;
		this.programSize = _programSize;
		this.runTime = _runTime;
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
