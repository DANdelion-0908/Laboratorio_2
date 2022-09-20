package model;

import java.util.ArrayList;

public class ram {
	
	private String ramType;
	private int ramStorage;
	private int AvailableMemory;
	private ArrayList<program> programsEXE; 
	private ArrayList<program> programsLINE;
	

	public ram(String _ramType, int _ramStorage, int _AvailableMemory){
		this.AvailableMemory = _AvailableMemory;
		this.ramType = _ramType;
		this.ramStorage = _ramStorage;
		
		programsEXE = new ArrayList<program>();
		programsLINE = new ArrayList<program>();

}
	
	public String getRamType() {
		return ramType;
	}
	
	public void setRamType(String ramType) {
		this.ramType = ramType;
	}
	
	public int getRamStorage() {
		return ramStorage;
	}
	
	public void setRamStorage(int ramStorage) {
		this.ramStorage = ramStorage;
	}
	
	public ArrayList<program> getProgramsEXE() {
		return programsEXE;
	}
	
	public void setProgramsEXE(ArrayList<program> programsEXE) {
		this.programsEXE = programsEXE;
	}
	public ArrayList<program> getProgramsLINE() {
		return programsLINE;
	}
	
	public void setProgramsLINE(ArrayList<program> programsLINE) {
		this.programsLINE = programsLINE;
	}

	public int getAvailableMemory() {
		return AvailableMemory;
	}

	public void setAvailableMemory(int availableMemory) {
		AvailableMemory = availableMemory;
	}
	
	
}


