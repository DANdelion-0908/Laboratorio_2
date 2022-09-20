package controller;

import java.util.ArrayList;.
import model.ram;
import model.program;
import java.lang.Math;

public class ramControl {
	
	private ram actualRam;

	public ramControl(ram _ram, program _program){
		actualRam = _ram;
	}
	
	public int mbToBlock(int _mb) {
		_mb = (int) Math.ceil(_mb/ 64);
		return _mb;
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
	
	public void  addProgramToRam(program _Program) {
		actualRam.getProgramsEXE().add(_Program);
		actualRam.setRamStorage(actualRam.getRamStorage() - _Program.getProgramSize() );  
		actualRam.setAvailableMemory(actualRam.getAvailableMemory() - _Program.getProgramSize());
	}
	
	public void  addProgramToQueue(program _Program) {
		actualRam.getProgramsLINE().add(_Program);
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
	
	

}
