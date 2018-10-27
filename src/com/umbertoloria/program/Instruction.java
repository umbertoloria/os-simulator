package com.umbertoloria.program;

import com.umbertoloria.program.instructions.ALInstruction;
import com.umbertoloria.program.instructions.VarInstruction;

public abstract class Instruction {

	public static Instruction load(String codedInstr) {
		codedInstr = codedInstr.trim();
		if (codedInstr.startsWith("and") || codedInstr.startsWith("or") || codedInstr.startsWith("add")) {
			return new ALInstruction(codedInstr);
		} else if (codedInstr.startsWith("create") || codedInstr.startsWith("set") || codedInstr.startsWith("print")) {
			return new VarInstruction(codedInstr);
		}
		return null;
	}

	protected String codedInstr;

	public Instruction(String codedInstr) {
		this.codedInstr = codedInstr.trim();
	}

	public abstract boolean parse();

	public abstract String getOperation();

	public abstract String toString();

}
