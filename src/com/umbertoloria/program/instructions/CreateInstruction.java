package com.umbertoloria.program.instructions;

import com.umbertoloria.ram.RAMDriver;
import com.umbertoloria.program.Instruction;
import com.umbertoloria.utils.ParserUtils;

public class CreateInstruction extends Instruction {

	private String varName;
	private int length;

	public CreateInstruction(String codedInstr) {
		super(codedInstr);
	}

	public boolean parse() {
		if (!codedInstr.startsWith("create")) {
			return false;
		}

		String partialInstr = codedInstr.substring(7).trim();

		String tmpVarName = ParserUtils.getFirstVariableToken(partialInstr);

		if (partialInstr.length() == tmpVarName.length()) {
			return false;
		}

		String tmpLengthStr = partialInstr.substring(tmpVarName.length() + 1).trim(); // one space at least
		int tmpLength = 0;
		try {
			tmpLength = Integer.parseInt(tmpLengthStr);
		} catch (NumberFormatException e) {
			return false;
		}

		if (tmpLength < 1) { // TODO: test this
			return false;
		}

		varName = tmpVarName;
		length = tmpLength;

		return true;
	}

	public void execute(RAMDriver rm, boolean verboose) {
		if (verboose) {
			System.out.println("[" + toString() + "]");
		}
		// TODO: return true of false
		if (length == 1) {
			rm.create(varName);
		} else {
			rm.create(varName, length);
		}
	}

	public String getOperation() {
		return "create";
	}

	public String toString() {
		return getOperation() + " " + varName + " " + length;
	}
}
