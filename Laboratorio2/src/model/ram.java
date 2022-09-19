package model;

import java.util.ArrayList;

public class ram {
	
	public String ramType;
	public int ramStorage;
	public ArrayList<program> programsEXE; 
	public ArrayList<program> programsLINE;
	

	public ram(String ramType, int ramStorage){
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
	
	public ArrayList getProgramsEXE() {
		return programsEXE;
	}
	
	public void setProgramsEXE(ArrayList programsEXE) {
		this.programsEXE = programsEXE;
	}
	public ArrayList getProgramsLINE() {
		return programsLINE;
	}
	
	public void setProgramsLINE(ArrayList programsLINE) {
		this.programsLINE = programsLINE;
	
}
	}


