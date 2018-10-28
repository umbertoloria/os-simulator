package com.umbertoloria.vecchi.virtual_machine;

import com.umbertoloria.vecchi.alu.ALUManager;
import com.umbertoloria.vecchi.program.Instruction;
import com.umbertoloria.vecchi.program.Program;
import com.umbertoloria.vecchi.program.instructions.ALInstruction;
import com.umbertoloria.vecchi.program.instructions.VarInstruction;
import com.umbertoloria.vecchi.ram.RAMDriver;

public class VirtualMachine {

	private static final int ARCHITECTURE = 10;

	private ALUManager cdm;
	private RAMDriver rd;

	public VirtualMachine(int ram_size) {
		cdm = new ALUManager(ARCHITECTURE);
		rd = new RAMDriver(ARCHITECTURE, ram_size);
	}

	public void execute(Program p, boolean verboose) {
		if (p.parse(verboose)) {
			Instruction instr;
			while (p.hasNext()) {
				instr = p.next();
				if (instr instanceof ALInstruction) {
					((ALInstruction) instr).execute(cdm, verboose);
				} else if (instr instanceof VarInstruction) {
					((VarInstruction) instr).execute(rd, verboose);
				} else {
					System.err.println("Istruzione saltata!");
				}
			}
		}
	}

}
