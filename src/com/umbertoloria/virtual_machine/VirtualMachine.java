package com.umbertoloria.virtual_machine;

import com.umbertoloria.alu.ALUManager;
import com.umbertoloria.ram.RAMDriver;
import com.umbertoloria.program.Instruction;
import com.umbertoloria.program.Program;
import com.umbertoloria.program.instructions.ALInstruction;
import com.umbertoloria.program.instructions.CreateInstruction;
import com.umbertoloria.program.instructions.PrintInstruction;
import com.umbertoloria.program.instructions.SetInstruction;

public class VirtualMachine {

	private static final int ARCHITECTURE = 10;

	private ALUManager cdm;
	private RAMDriver rd;

	public VirtualMachine (int ram_size) {
		cdm = new ALUManager(ARCHITECTURE);
		rd = new RAMDriver(ARCHITECTURE, ram_size);
	}

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
