package com.umbertoloria.utils;

public class InstructionUtils {

	public static final int INSTRUCTION_CODE_SIZE = 6;
	private static final int CONSTANT_SIZE = 64;
	private static final int INSTRUCTION_ADDRESS_SIZE = 64;
	private static final int MEMORY_ADDRESS_SIZE = 64;
	private static final int REGISTERS_SIZE = RegistersUtils.REGISTERS_SIZE;

	private static final int[] C_TYPE = {CONSTANT_SIZE};
	private static final int[] RC_TYPE = {REGISTERS_SIZE, CONSTANT_SIZE};
	private static final int[] RR_TYPE = {REGISTERS_SIZE, REGISTERS_SIZE};
	private static final int[] CC_TYPE = {CONSTANT_SIZE, CONSTANT_SIZE};
	private static final int[] CR_TYPE = {CONSTANT_SIZE, REGISTERS_SIZE};
	private static final int[] R_TYPE = {REGISTERS_SIZE};
	private static final int[] I_TYPE = {INSTRUCTION_ADDRESS_SIZE};
	private static final int[] RI_TYPE = {REGISTERS_SIZE, INSTRUCTION_ADDRESS_SIZE};
	private static final int[] MC_TYPE = {MEMORY_ADDRESS_SIZE, CONSTANT_SIZE};
	private static final int[] MR_TYPE = {MEMORY_ADDRESS_SIZE, REGISTERS_SIZE};
	private static final int[] NO_TYPE = {};
	// new IIB[][]
	private static IIB[][] organization = {
			{
					new IIB(BinaryUtils.toRawBools("000000"), MR_TYPE, BinaryUtils.toRawBools("00000"), null, null)
			}, // use 0 TODO: progettare l'use
			{
					new IIB(BinaryUtils.toRawBools("000001"), RC_TYPE, BinaryUtils.toRawBools("00111"), null, null),
					new IIB(BinaryUtils.toRawBools("000010"), RR_TYPE, BinaryUtils.toRawBools("01110"), null, null)
			}, // set 1 TODO: write register depends
			{
					new IIB(BinaryUtils.toRawBools("000011"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.LR_CODE, ALUUtils.AND),
					new IIB(BinaryUtils.toRawBools("000100"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.LR_CODE, ALUUtils.AND),
					new IIB(BinaryUtils.toRawBools("000101"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.LR_CODE, ALUUtils.AND),
					new IIB(BinaryUtils.toRawBools("000110"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.LR_CODE, ALUUtils.AND)
			}, // and 2
			{
					new IIB(BinaryUtils.toRawBools("000111"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.LR_CODE, ALUUtils.OR),
					new IIB(BinaryUtils.toRawBools("001000"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.LR_CODE, ALUUtils.OR),
					new IIB(BinaryUtils.toRawBools("001001"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.LR_CODE, ALUUtils.OR),
					new IIB(BinaryUtils.toRawBools("001010"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.LR_CODE, ALUUtils.OR)
			}, // or 3
			{
					new IIB(BinaryUtils.toRawBools("001011"), C_TYPE, BinaryUtils.toRawBools("00110"),
							RegistersUtils.LR_CODE, ALUUtils.NOT),
					new IIB(BinaryUtils.toRawBools("001100"), R_TYPE, BinaryUtils.toRawBools("10100"),
							RegistersUtils.LR_CODE, ALUUtils.NOT),
			}, // not 4
			{
					new IIB(BinaryUtils.toRawBools("001101"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.AR_CODE, ALUUtils.ADD),
					new IIB(BinaryUtils.toRawBools("001110"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.AR_CODE, ALUUtils.ADD),
					new IIB(BinaryUtils.toRawBools("001111"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.AR_CODE, ALUUtils.ADD),
					new IIB(BinaryUtils.toRawBools("010000"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.AR_CODE, ALUUtils.ADD)
			}, // add 5
			{
					new IIB(BinaryUtils.toRawBools("010001"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.AR_CODE, ALUUtils.SUB),
					new IIB(BinaryUtils.toRawBools("010010"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.AR_CODE, ALUUtils.SUB),
					new IIB(BinaryUtils.toRawBools("010011"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.AR_CODE, ALUUtils.SUB),
					new IIB(BinaryUtils.toRawBools("010100"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.AR_CODE, ALUUtils.SUB)
			}, // sub 6
			{
					new IIB(BinaryUtils.toRawBools("010101"), I_TYPE, BinaryUtils.toRawBools("00100"),
							RegistersUtils.PC_CODE, null)
			}, // goto 7
			{
					new IIB(BinaryUtils.toRawBools("010110"), RI_TYPE, BinaryUtils.toRawBools("10100"),
							RegistersUtils.PC_CODE, ALUUtils.GOTV)
			}, // gotv 8
			{
					new IIB(BinaryUtils.toRawBools("010111"), RI_TYPE, BinaryUtils.toRawBools("10100"),
							RegistersUtils.PC_CODE, ALUUtils.GOTF)
			}, // gotf 9
			{
					new IIB(BinaryUtils.toRawBools("011000"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.CR_CODE, ALUUtils.EQA),
					new IIB(BinaryUtils.toRawBools("011001"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.CR_CODE, ALUUtils.EQA),
					new IIB(BinaryUtils.toRawBools("011010"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.CR_CODE, ALUUtils.EQA),
					new IIB(BinaryUtils.toRawBools("011011"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.CR_CODE, ALUUtils.EQA)
			}, // eqa 10
			{
					new IIB(BinaryUtils.toRawBools("011100"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.CR_CODE, ALUUtils.DIFF),
					new IIB(BinaryUtils.toRawBools("011101"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.CR_CODE, ALUUtils.DIFF),
					new IIB(BinaryUtils.toRawBools("011110"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.CR_CODE, ALUUtils.DIFF),
					new IIB(BinaryUtils.toRawBools("011111"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.CR_CODE, ALUUtils.DIFF)
			}, // diff 11
			{
					new IIB(BinaryUtils.toRawBools("100000"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.CR_CODE, ALUUtils.LOW),
					new IIB(BinaryUtils.toRawBools("100001"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.CR_CODE, ALUUtils.LOW),
					new IIB(BinaryUtils.toRawBools("100010"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.CR_CODE, ALUUtils.LOW),
					new IIB(BinaryUtils.toRawBools("100011"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.CR_CODE, ALUUtils.LOW)
			}, // low 12
			{
					new IIB(BinaryUtils.toRawBools("100100"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.CR_CODE, ALUUtils.LOWEQ),
					new IIB(BinaryUtils.toRawBools("100101"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.CR_CODE, ALUUtils.LOWEQ),
					new IIB(BinaryUtils.toRawBools("100110"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.CR_CODE, ALUUtils.LOWEQ),
					new IIB(BinaryUtils.toRawBools("100111"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.CR_CODE, ALUUtils.LOWEQ)
			}, // loweq 13
			{
					new IIB(BinaryUtils.toRawBools("101000"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.CR_CODE, ALUUtils.GRE),
					new IIB(BinaryUtils.toRawBools("101001"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.CR_CODE, ALUUtils.GRE),
					new IIB(BinaryUtils.toRawBools("101010"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.CR_CODE, ALUUtils.GRE),
					new IIB(BinaryUtils.toRawBools("101011"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.CR_CODE, ALUUtils.GRE)
			}, // gre 14
			{
					new IIB(BinaryUtils.toRawBools("101100"), CC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.CR_CODE, ALUUtils.GREEQ),
					new IIB(BinaryUtils.toRawBools("101101"), CR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.CR_CODE, ALUUtils.GREEQ),
					new IIB(BinaryUtils.toRawBools("101110"), RC_TYPE, BinaryUtils.toRawBools("10101"),
							RegistersUtils.CR_CODE, ALUUtils.GREEQ),
					new IIB(BinaryUtils.toRawBools("101111"), RR_TYPE, BinaryUtils.toRawBools("11100"),
							RegistersUtils.CR_CODE, ALUUtils.GREEQ)
			}, // greeq 15
			{
					new IIB(BinaryUtils.toRawBools("110000"), MC_TYPE, BinaryUtils.toRawBools("00111"),
							RegistersUtils.MR_CODE, ALUUtils.LOAD),
					new IIB(BinaryUtils.toRawBools("110001"), MR_TYPE, BinaryUtils.toRawBools("01110"),
							RegistersUtils.MR_CODE, ALUUtils.LOAD)
			}, // load 16
			{
					new IIB(BinaryUtils.toRawBools("110010"), MC_TYPE, BinaryUtils.toRawBools("00011"), null,
							ALUUtils.STORE),
					new IIB(BinaryUtils.toRawBools("110011"), MR_TYPE, BinaryUtils.toRawBools("01010"), null,
							ALUUtils.STORE)
			}, // store 17
			{
					// TODO: capire cosa fare con print
					new IIB(BinaryUtils.toRawBools("110100"), NO_TYPE, BinaryUtils.toRawBools("00000"), null, null)
			}, // print 18
			{
					// TODO: capire cosa fare con exit
					new IIB(BinaryUtils.toRawBools("110101"), NO_TYPE, BinaryUtils.toRawBools("00000"), null, null)
			} // exit 19
	};

	public static int[] getSizes(boolean[] instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (BitsUtils.equals(instrCode, instruction.data)) {
					return instruction.type;
				}
			}
		}
		return null;
	}

	public static boolean[] getFlags(boolean[] instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (BitsUtils.equals(instrCode, instruction.data)) {
					return instruction.flags;
				}
			}
		}
		return null;
	}

	public static boolean[] getWriteRegister(boolean[] instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (BitsUtils.equals(instrCode, instruction.data)) {
					return instruction.register;
				}
			}
		}
		return null;
	}

	public static boolean[] getAluMode (boolean[] instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (BitsUtils.equals(instrCode, instruction.data)) {
					return instruction.mode;
				}
			}
		}
		return null;
	}

	// Instruction Information Block
	static class IIB {
		boolean[] data;
		int[] type;
		boolean[] flags;
		boolean[] register;
		boolean[] mode;

		IIB(boolean[] data, int[] type, boolean[] flags, boolean[] register, boolean[] mode) {
			this.data = data;
			this.type = type;
			this.flags = flags;
			this.register = register;
			this.mode = mode;
		}

		/*void print() {
			System.out.println("Print: ");
			System.out.println("data:");
			BitsUtils.print(data);
			System.out.println("flags:");
			BitsUtils.print(flags);
			System.out.println("register:");
			BitsUtils.print(register);
			System.out.println("mode:");
			BitsUtils.print(mode);
		}*/

	}


	public static final int MAX_INSTRUCTION_SIZE = 200; // FIXME: forse è 134
































	/*public static final boolean[] USE = BinaryUtils.convertAbs(0, 5);
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

	private static final String[] instrOrder = {"use", "set", "and", "or", "not", "add", "sub", "goto", "gotv",
	"gotf", "eqa", "diff", "low", "loweq", "gre", "greeq", "load", "store", "print", "exit"};

	public static boolean[] getInstructionCode(String instr) {
		int code = getPosOf(instr, instrOrder);
		if (code >= 0) {
			return BinaryUtils.convertAbs(code, 5);
		}
		throw new RuntimeException();
	}

	private static String getInstructionName(boolean[] instr) {
		return instrOrder[BinaryUtils.toAbsInt(instr)];
	}

	// ALU Standard Instruction Names
	private static final String[] ALU_STDs = new String[]{instrOrder[2], instrOrder[3], instrOrder[5], instrOrder[6],
			instrOrder[10], instrOrder[11], instrOrder[12], instrOrder[13], instrOrder[14], instrOrder[15]};

	public static boolean isALUSTDInstructionCode(boolean[] instr) {
		return getPosOf(getInstructionName(instr), ALU_STDs) >= 0;
	}

	public static boolean isALUSTDInstructionName(String instr) {
		return getPosOf(instr, ALU_STDs) >= 0;
	}

	// GOT Condition Instruction Names
	private static final String[] tipoGotC = new String[]{instrOrder[8], instrOrder[9]};

	public static boolean isGOTCInstructionName(String instr) {
		return getPosOf(instr, tipoGotC) >= 0;
	}

	// Memory Instruction Names
	private static final String[] tipoMem = new String[]{instrOrder[16], instrOrder[17]};

	public static boolean isMEMInstructionName(String instr) {
		return getPosOf(instr, tipoMem) >= 0;
	}

	// Utils
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
	}*/

}
