package controller;

import java.util.ArrayList;
import model.ram;
import model.program;
import java.lang.Math;

public class ramControl {
	
	private ram actualRam;

	public ramControl(ram _ram){
		actualRam = _ram;
	}
	
	public int mbToBlock(int _mb) {
		if(_mb % 64 != 0){
			double number =  (_mb/ 64);
			_mb = (int) number;
			return _mb + 1;
		}
		else {
			double number =  (_mb/ 64);
			_mb = (int) number;
			return _mb;
		}
		
	}
	
	public boolean CanAddProgramToRam(program _Program) {
		int ramSpace = actualRam.getRamStorage();
		int ProgramSpace = _Program.getProgramSize();
		String RamType = actualRam.getRamType();
		
		if (ramSpace > ProgramSpace) {
			return true;
		}
		
		else if (ramSpace <= ProgramSpace && RamType.equals("ddr")) {
			IncreaseRam(actualRam);
			return true;
		}
		
		else if (ramSpace <= ProgramSpace && RamType.equals("sdr")) {
			return false;
		}
		return false;
	}
	
	public void  addProgramToRam(ram myRam, program _Program) {
		
		myRam.getProgramsEXE().add(_Program);
		myRam.setAvailableMemory(myRam.getAvailableMemory() - _Program.getProgramSize());
	}
	
	public void  addProgramToQueue(ram myRam,program _Program) {
		myRam.getProgramsLINE().add(_Program);
	}
	
	public void IncreaseRam(ram _ram) {
		int TotalMem = _ram.getRamStorage();
		int AvailableMem = _ram.getAvailableMemory();
		
		if(TotalMem < 256) {
			_ram.setRamStorage(TotalMem + 64);
			_ram.setAvailableMemory(AvailableMem + 64);
		}
		else if (TotalMem >= 256) {
			_ram.setRamStorage(TotalMem * 2);
			_ram.setAvailableMemory(AvailableMem * 2);
		}
	}
	
	
	public void DecreaseRam(ram _ram) {
		
		int TotalMem = _ram.getRamStorage();
		int AvailableMem = _ram.getAvailableMemory();
		
		if(TotalMem <= 256 && TotalMem <= 0) {
			if (AvailableMem > TotalMem - 64) {
				_ram.setRamStorage(TotalMem - 64);
			}
		}
		
		else if(TotalMem > 256 && TotalMem <= 0) {
			if (AvailableMem > TotalMem / 2) {
				_ram.setRamStorage(TotalMem / 2);
			}
		}
	}
	
	public boolean ProgramsTimeOutram (ram myRam) {
		for(int i = 0; i < myRam.getProgramsEXE().size(); i++) {
			if (myRam.getProgramsEXE().get(i).getRunTime() == 0) {
				myRam.getProgramsEXE().remove(i);
				System.out.println("Se removio un programa");
				return true;
			}
		}
		
		return false;
	}
	
	public void reduceProgramsTime(ram myRam) {
		for(int i = 0; i < myRam.getProgramsEXE().size(); i++) {
			myRam.getProgramsEXE().get(i).setRunTime(myRam.getProgramsEXE().get(i).getRunTime() - 1) ; 
		}
	}

}
