package com.umbertoloria.utils;

public class InstructionUtils {

	public static final int INSTR_CODE_SIZE = 6;
	public static final int MAX_INSTRUCTION_SIZE = 64 * 3; // FIXME: 134(?)

	public static ICB getICB(boolean[] instrCode) {
		for (ICB[] instructions : ICB.organization) {
			for (ICB instruction : instructions) {
				if (BitsUtils.equals(instrCode, instruction.getData())) {
					return instruction;
				}
			}
		}
		return null;
	}

}
