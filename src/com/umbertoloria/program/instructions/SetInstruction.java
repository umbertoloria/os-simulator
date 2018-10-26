package com.umbertoloria.program.instructions;

import com.umbertoloria.ram.RAMDriver;
import com.umbertoloria.program.Instruction;
import com.umbertoloria.utils.ParserUtils;

public class SetInstruction extends Instruction {

	private String varName;
	private int value;

	public SetInstruction(String codedInstr) {
		super(codedInstr);
	}

	public boolean parse() {
		if (!codedInstr.startsWith("set")) {
			return false;
		}

		String partialInstr = codedInstr.substring(4).trim();

		String tmpVarName = ParserUtils.getFirstVariableToken(partialInstr);

		if (partialInstr.length() == tmpVarName.length()) {
			return false;
		}

		String tmpValueStr = partialInstr.substring(tmpVarName.length() + 1).trim(); // one space at least
		int tmpValue = 0;
		try {
			tmpValue = Integer.parseInt(tmpValueStr);
		} catch (NumberFormatException e) {
			return false;
		}

		varName = tmpVarName;
		value = tmpValue;

		return true;
	}

	public void execute(RAMDriver rm, boolean verboose) {
		if (verboose) {
			System.out.println("[" + toString() + "]");
		}
		rm.set(varName, value);
	}

	public String getOperation() {
		return "set";
	}

	public String toString() {
		return getOperation() + " " + varName + " " + value;
	}
}
