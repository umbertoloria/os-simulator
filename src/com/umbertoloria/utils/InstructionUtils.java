package com.umbertoloria.utils;

public class InstructionUtils {

	public static final boolean[] USE = BinaryUtils.convertAbs(0, 5);
	public static final boolean[] SET = BinaryUtils.convertAbs(1, 5);
	public static final boolean[] AND = BinaryUtils.convertAbs(2, 5);
	public static final boolean[] OR = BinaryUtils.convertAbs(3, 5);
	public static final boolean[] NOT = BinaryUtils.convertAbs(4, 5);
	public static final boolean[] ADD = BinaryUtils.convertAbs(5, 5);
	public static final boolean[] SUB = BinaryUtils.convertAbs(6, 5);
	public static final boolean[] GOTO = BinaryUtils.convertAbs(7, 5);
	public static final boolean[] GOTV = BinaryUtils.convertAbs(8, 5);
	public static final boolean[] GOTF = BinaryUtils.convertAbs(9, 5);
	public static final boolean[] EQA = BinaryUtils.convertAbs(10, 5);
	public static final boolean[] DIFF = BinaryUtils.convertAbs(11, 5);
	public static final boolean[] LOW = BinaryUtils.convertAbs(12, 5);
	public static final boolean[] LOWEQ = BinaryUtils.convertAbs(13, 5);
	public static final boolean[] GRE = BinaryUtils.convertAbs(14, 5);
	public static final boolean[] GREEQ = BinaryUtils.convertAbs(15, 5);
	public static final boolean[] LOAD = BinaryUtils.convertAbs(16, 5);
	public static final boolean[] STORE = BinaryUtils.convertAbs(17, 5);
	public static final boolean[] PRINT = BinaryUtils.convertAbs(18, 5);
	public static final boolean[] EXIT = BinaryUtils.convertAbs(19, 5);

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

	public static boolean isALUInstructionCode(boolean[] instr) {
		return getPosOf(getInstructionName(instr), tipoALU) >= 0;
	}

	public static boolean isALUInstructionName(String instr) {
		return getPosOf(instr, tipoALU) >= 0;
	}

	public static boolean isGOTCInstructionName(String instr) {
		return getPosOf(instr, tipoGotC) >= 0;
	}

	public static boolean isMEMInstructionName(String instr) {
		return getPosOf(instr, tipoMem) >= 0;
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

	private static String getInstructionName(boolean[] instr) {
		return instrOrder[BinaryUtils.toAbsInt(instr)];
	}

	private static int getPosOf(String ago, String[] pagliaio) {
		for (int i = 0; i < pagliaio.length; i++) {
			if (ago.equals(pagliaio[i])) {
				return i;
			}
		}
		return -1;
	}

}
