package com.umbertoloria.program.instructions;

import com.umbertoloria.memory.RamDriver;
import com.umbertoloria.program.Instruction;
import com.umbertoloria.utils.ParserUtils;

public class PrintInstruction extends Instruction {

	private String varName;

	public PrintInstruction(String codedInstr) {
		super(codedInstr);
	}

	public boolean parse() {
		if (!codedInstr.startsWith("print")) {
			return false;
		}

		String partialInstr = codedInstr.substring(6).trim();

		String tmpVarName = ParserUtils.getFirstVariableToken(partialInstr);

		if (partialInstr.length() != tmpVarName.length()) {
			return false;
		}

		varName = tmpVarName;

		return true;
	}

	public void execute(RamDriver rm, boolean verboose) {
		if (verboose) {
			System.out.println("[" + toString() + "]");
		}
		System.out.println(rm.get(varName));
	}

	public String getOperation() {
		return "print";
	}

	public String toString() {
		return getOperation() + " " + varName;
	}
}
