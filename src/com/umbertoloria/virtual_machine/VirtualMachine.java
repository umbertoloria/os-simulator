package com.umbertoloria.virtual_machine;

import com.umbertoloria.memory.RAM;
import com.umbertoloria.cpu.CPUDriversManager;
import com.umbertoloria.program.Instruction;
import com.umbertoloria.program.Program;

public class VirtualMachine {

	private int cores_count;
	private int ram_size;

	private CPUDriversManager cdm;
	private RAM ram;

	// Inits

	public void setCoresCount(int cores_count) {
		this.cores_count = cores_count;
	}

	public void setRAMSize(int ram_size) {
		this.ram_size = ram_size;
	}

	public void init() {
		cdm = new CPUDriversManager(cores_count);
		ram = new RAM(ram_size);
	}

	// Operations

	public void execute(Program p, boolean verboose) {
		if (p.parse(verboose)) {
			Instruction instr;
			while ((instr = p.nextInstr()) != null) {
				instr.execute(cdm, verboose);
			}
		}
	}

}
