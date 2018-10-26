package com.umbertoloria.virtual_machine;

import com.umbertoloria.cpu.CPUDriversManager;
import com.umbertoloria.memory.RamDriver;
import com.umbertoloria.program.Instruction;
import com.umbertoloria.program.Program;
import com.umbertoloria.program.instructions.ALInstruction;
import com.umbertoloria.program.instructions.CreateInstruction;
import com.umbertoloria.program.instructions.PrintInstruction;
import com.umbertoloria.program.instructions.SetInstruction;

public class VirtualMachine {

	public static final int ARCHITECTURE = 10;

	private int cores_count;
	private int ram_size;

	private CPUDriversManager cdm;
	private RamDriver rd;

	// Inits

	public void setCoresCount(int cores_count) {
		this.cores_count = cores_count;
	}

	public void setRAMSize(int ram_size) {
		this.ram_size = ram_size;
	}

	public void init() {
		cdm = new CPUDriversManager(ARCHITECTURE, cores_count);
		rd = new RamDriver(ARCHITECTURE, ram_size);
	}

	// Operations

	public void execute(Program p, boolean verboose) {
		if (p.parse(verboose)) {
			Instruction instr;
			while ((instr = p.nextInstr()) != null) {
				if (instr instanceof ALInstruction) {
					((ALInstruction) instr).execute(cdm, verboose);
				} else if (instr instanceof CreateInstruction) {
					((CreateInstruction) instr).execute(rd, verboose);
				} else if (instr instanceof SetInstruction) {
					((SetInstruction) instr).execute(rd, verboose);
				} else if (instr instanceof PrintInstruction) {
					((PrintInstruction) instr).execute(rd, verboose);
				} else {
					System.err.println("Istruzione saltata!");
				}
			}
		}
	}

}
