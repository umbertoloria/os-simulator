package com.umbertoloria.program.instructions;

import com.umbertoloria.program.Instruction;
import com.umbertoloria.ram.RAMDriver;
import com.umbertoloria.utils.ParserUtils;

public class VarInstruction extends Instruction {

	private static final int CREATE = 1;
	private static final int SET = 2;
	private static final int PRINT = 3;

	private int type;
	private String varName;
	private int number;

	public VarInstruction(String codedInstr) {
		super(codedInstr);
	}

	public boolean parse() {
		int tmpType = 0;
		String partialInstr = codedInstr;

		if (partialInstr.startsWith("create")) {
			tmpType = CREATE;
			partialInstr = partialInstr.substring(7).trim();
		} else if (partialInstr.startsWith("set")) {
			tmpType = SET;
			partialInstr = partialInstr.substring(4).trim();
		} else if (partialInstr.startsWith("print")) {
			tmpType = PRINT;
			partialInstr = partialInstr.substring(6).trim();
		}

		String tmpVarName;
		try {
			tmpVarName = ParserUtils.getFirstVariableToken(partialInstr);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}

		partialInstr = partialInstr.substring(tmpVarName.length()).trim(); // one space at least
		if (partialInstr.length() > 0) { // deve avere per forza un int, altrimenti niente

			int tmpNumber;
			try {
				tmpNumber = ParserUtils.getFirstNumericToken(partialInstr);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				return false;
			}

			if (tmpType == CREATE && tmpNumber < 1) { // TODO: test this
				System.err.println("Minimo 1 elementi in una variabile");
				return false;
			}

			number = tmpNumber;

		}

		type = tmpType;
		varName = tmpVarName;

		return true;
	}

	public void execute(RAMDriver rm, boolean verboose) {
		if (type == CREATE) {
			if (verboose) {
				System.out.println("[" + toString() + "]");
			}
			// TODO: return true of false
			if (number == 1) {
				rm.create(varName);
			} else {
				rm.create(varName, number);
			}
		} else if (type == SET) {
			if (verboose) {
				System.out.println("[" + toString() + "]");
			}
			rm.set(varName, number);
		} else if (type == PRINT) {
			if (verboose) {
				System.out.println("[" + toString() + "]");
			}
			System.out.println(rm.get(varName));
		}
	}

	public String getOperation() {
		if (type == CREATE) {
			return "create";
		} else if (type == SET) {
			return "set";
		} else if (type == PRINT) {
			return "print";
		}
		return null;
	}

	public String toString() {
		if (type == PRINT) {
			return getOperation() + " " + varName;
		}
		return getOperation() + " " + varName + " " + number;
	}
}
