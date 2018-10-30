package com.umbertoloria.utils;

public class InstructionUtils {

	private static final String[] instrOrder = new String[]{"use", "set", "and", "or", "not", "add", "sub", "goto",
			"gotv", "gotf", "eqa", "diff", "low", "loweq", "gre", "greeq", "load", "store", "print", "exit"};
	private static final String[] tipoALU = new String[]{instrOrder[2], instrOrder[3], instrOrder[5], instrOrder[6],
			instrOrder[10], instrOrder[11], instrOrder[12], instrOrder[13], instrOrder[14], instrOrder[15]};
	private static final String[] tipoGotC = new String[]{instrOrder[8], instrOrder[9]};
	private static final String[] tipoMem = new String[]{instrOrder[16], instrOrder[17]};

	public static final int MAX_INSTRUCTION_SIZE = 135;

	public static boolean[] getInstructionCode(String instr) {
		int code = getPosOf(instr, instrOrder);
		if (code >= 0) {
			return BinaryUtils.convertAbs(code, 5);
		}
		throw new RuntimeException();
	}

	public static String getInstructionName(boolean[] instr) {
		return instrOrder[BinaryUtils.toAbsInt(instr)];
	}

	public static boolean isALUInstruction(boolean[] instr) {
		return getPosOf(getInstructionName(instr), tipoALU) >= 0;
	}

	public static boolean isGOTCInstruction(boolean[] instr) {
		return getPosOf(getInstructionName(instr), tipoGotC) >= 0;
	}
	// sdoppiarli

	public static boolean isMEMInstruction(boolean[] instr) {
		return getPosOf(getInstructionName(instr), tipoMem) >= 0;
	}





	/*public int getNumberInstruction(String instr) {
		return getPosOf(instr, instrOrder);
	}*/

	private static int getPosOf(String ago, String[] pagliaio) {
		for (int i = 0; i < pagliaio.length; i++) {
			if (ago.equals(pagliaio[i])) {
				return i;
			}
		}
		return -1;
	}

	public static void print(boolean[] x, int length) {
		int i;
		for (i = 0; i < 5; i++) {
			System.out.print(x[i] ? '1' : '0');
		}
		System.out.print(" ");
		if (x.length == 17) {
			for (i = 5; i < 7; i++) {
				System.out.print(x[i] ? '1' : '0');
			}
			System.out.print(" ");
			for (i = 7; i < 12; i++) {
				System.out.print(x[i] ? '1' : '0');
			}
			System.out.print(" ");
			for (i = 12; i < 17; i++) {
				System.out.print(x[i] ? '1' : '0');
			}
		} else {
			for (i = 5; i < length; i++) {
				System.out.print(x[i] ? '1' : '0');
			}
		}
		System.out.println();
	}

}
