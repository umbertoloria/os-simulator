package com.umbertoloria.program.instructions;

import com.umbertoloria.cpu.CPUDriversManager;
import com.umbertoloria.program.Instruction;
import com.umbertoloria.utils.ParserUtils;

public class ALInstruction extends Instruction {

	private static final int AND = 1;
	private static final int OR = 2;
	private static final int ADD = 3;

	private int type;
	private int a, b;

	public ALInstruction(String codedInstr) {
		super(codedInstr);
	}

	public boolean parse() {
		// TODO: sistemare il parsing
		if (!codedInstr.startsWith("and") && !codedInstr.startsWith("or") && !codedInstr.startsWith("add")) {
			return false;
		}

		int tmpType = 0;
		String[] tokens = new String[2];
		int[] operands = new int[2];

		String partialInstr = codedInstr;

		if (partialInstr.startsWith("and")) {
			tmpType = AND;
			partialInstr = partialInstr.substring(4).trim();
		} else if (partialInstr.startsWith("or")) {
			tmpType = OR;
			partialInstr = partialInstr.substring(3).trim();
		} else if (partialInstr.startsWith("add")) {
			tmpType = ADD;
			partialInstr = partialInstr.substring(4).trim();
		}

		tokens[0] = ParserUtils.getFirstNumericToken(partialInstr);
		operands[0] = Integer.parseInt(tokens[0]); // try
		partialInstr = partialInstr.substring(tokens[0].length() + 1).trim(); // one space at least

		tokens[1] = partialInstr;
		operands[1] = Integer.parseInt(tokens[1]); // try

		type = tmpType;
		a = operands[0];
		b = operands[1];

		return true;
	}

	public void execute(CPUDriversManager cdm, boolean verboose) {
		if (verboose) {
			System.out.print("[" + toString() + "] -> ");
		}
		int res = 0; // TODO: remove = 0
		if (type == AND) {
			res = cdm.and(a, b);
		} else if (type == OR) {
			res = cdm.or(a, b);
		} else if (type == ADD) {
			res = cdm.add(a, b);
		}
		System.out.println(res); // TODO: gestire il risultato
	}

	public String getOperation() {
		if (type == AND) {
			return "and";
		} else if (type == OR) {
			return "or";
		} else if (type == ADD) {
			return "add";
		}
		return null;
	}

	public String toString() {
		return getOperation() + " " + a + " " + b;
	}
}
