package com.umbertoloria.program;

import com.umbertoloria.cpu.CPUDriversManager;
import com.umbertoloria.utils.ParserUtils;

public class Instruction {

	private String codedInstr;
	private String operation;
	private int a, b;

	public Instruction(String codedInstr) {
		this.codedInstr = codedInstr.trim();
	}

	public boolean parse() {

		String[] tokens = new String[3];
		int[] operands = new int[2];

		String partialInstr = codedInstr;

		tokens[0] = ParserUtils.getFirstAlphabeticToken(partialInstr);
		partialInstr = partialInstr.substring(tokens[0].length() + 1).trim(); // one space at least

		tokens[1] = ParserUtils.getFirstNumericToken(partialInstr);
		operands[0] = Integer.parseInt(tokens[1]); // try
		partialInstr = partialInstr.substring(tokens[1].length() + 1).trim(); // one space at least

		tokens[2] = partialInstr;
		operands[1] = Integer.parseInt(tokens[2]);

		// TODO: checking everythings

		operation = tokens[0];
		a = operands[0];
		b = operands[1];

		return true;
	}

	public void execute(CPUDriversManager cdm, boolean verboose) {
		int res = 0; // TODO: remove = 0
		if (operation.equals("and")) {
			res = cdm.and(a, b);
		} else if (operation.equals("or")) {
			res = cdm.or(a, b);
		} else if (operation.equals("add")) {
			res = cdm.add(a, b);
		}
		System.out.println(res); // TODO: gestire il risultato
	}

	@Override
	public String toString() {
		return codedInstr;
	}

}
