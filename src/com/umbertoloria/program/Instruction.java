package com.umbertoloria.program;

import com.umbertoloria.program.instructions.ALInstruction;
import com.umbertoloria.program.instructions.CreateInstruction;
import com.umbertoloria.program.instructions.PrintInstruction;
import com.umbertoloria.program.instructions.SetInstruction;

public abstract class Instruction {

	public static Instruction load(String codedInstr) {
		codedInstr = codedInstr.trim();
		if (codedInstr.startsWith("and") || codedInstr.startsWith("or") || codedInstr.startsWith("add")) {
			return new ALInstruction(codedInstr);
		} else if (codedInstr.startsWith("create")) {
			return new CreateInstruction(codedInstr);
		} else if (codedInstr.startsWith("set")) {
			return new SetInstruction(codedInstr);
		} else if (codedInstr.startsWith("print")) {
			return new PrintInstruction(codedInstr);
		}
		return null; // TODO: complete function
	}

	protected String codedInstr;

	public Instruction(String codedInstr) {
		this.codedInstr = codedInstr.trim();
	}

	public abstract boolean parse();

	public abstract String getOperation();

	public abstract String toString();

}
