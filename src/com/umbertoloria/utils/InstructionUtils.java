package com.umbertoloria.utils;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.BitStream;

public class InstructionUtils {

	public static final int INSTRUCTION_CODE_SIZE = 6;
	private static final int CONSTANT_SIZE = 64;
	private static final int INSTRUCTION_ADDRESS_SIZE = 64;
	private static final int MEMORY_ADDRESS_SIZE = 64;
	private static final int REGISTERS_SIZE = RegistersUtils.REGISTERS_SIZE;

	private static final int[] C_T = {CONSTANT_SIZE};
	private static final int[] RC_T = {REGISTERS_SIZE, CONSTANT_SIZE};
	private static final int[] RR_T = {REGISTERS_SIZE, REGISTERS_SIZE};
	private static final int[] CC_T = {CONSTANT_SIZE, CONSTANT_SIZE};
	private static final int[] CR_T = {CONSTANT_SIZE, REGISTERS_SIZE};
	private static final int[] R_T = {REGISTERS_SIZE};
	private static final int[] I_T = {INSTRUCTION_ADDRESS_SIZE};
	private static final int[] RI_T = {REGISTERS_SIZE, INSTRUCTION_ADDRESS_SIZE};
	private static final int[] MC_T = {MEMORY_ADDRESS_SIZE, CONSTANT_SIZE};
	private static final int[] MR_T = {MEMORY_ADDRESS_SIZE, REGISTERS_SIZE};
	private static final int[] NO_T = {};

	private static IIB[][] organization = {
			{
					new IIB(new BitStream("000000"), MR_T, Bit.array("00000"), null, null)
			}, // use 0 TODO: progettare l'use
			{
					new IIB(new BitStream("000001"), RC_T, Bit.array("00111"), null, null),
					new IIB(new BitStream("000010"), RR_T, Bit.array("01110"), null, null)
			}, // set 1 TODO: write register depends
			{
					new IIB(new BitStream("000011"), CC_T, Bit.array("00111"), RegistersUtils.LR_C, ALUUtils.AND),
					new IIB(new BitStream("000100"), CR_T, Bit.array("01110"), RegistersUtils.LR_C, ALUUtils.AND),
					new IIB(new BitStream("000101"), RC_T, Bit.array("10101"), RegistersUtils.LR_C, ALUUtils.AND),
					new IIB(new BitStream("000110"), RR_T, Bit.array("11100"), RegistersUtils.LR_C, ALUUtils.AND)
			}, // and 2
			{
					new IIB(new BitStream("000111"), CC_T, Bit.array("00111"), RegistersUtils.LR_C, ALUUtils.OR),
					new IIB(new BitStream("001000"), CR_T, Bit.array("01110"), RegistersUtils.LR_C, ALUUtils.OR),
					new IIB(new BitStream("001001"), RC_T, Bit.array("10101"), RegistersUtils.LR_C, ALUUtils.OR),
					new IIB(new BitStream("001010"), RR_T, Bit.array("11100"), RegistersUtils.LR_C, ALUUtils.OR)
			}, // or 3
			{
					new IIB(new BitStream("001011"), C_T, Bit.array("00110"), RegistersUtils.LR_C, ALUUtils.NOT),
					new IIB(new BitStream("001100"), R_T, Bit.array("10100"), RegistersUtils.LR_C, ALUUtils.NOT),
			}, // not 4
			{
					new IIB(new BitStream("001101"), CC_T, Bit.array("00111"), RegistersUtils.AR_C, ALUUtils.ADD),
					new IIB(new BitStream("001110"), CR_T, Bit.array("01110"), RegistersUtils.AR_C, ALUUtils.ADD),
					new IIB(new BitStream("001111"), RC_T, Bit.array("10101"), RegistersUtils.AR_C, ALUUtils.ADD),
					new IIB(new BitStream("010000"), RR_T, Bit.array("11100"), RegistersUtils.AR_C, ALUUtils.ADD)
			}, // add 5
			{
					new IIB(new BitStream("010001"), CC_T, Bit.array("00111"), RegistersUtils.AR_C, ALUUtils.SUB),
					new IIB(new BitStream("010010"), CR_T, Bit.array("01110"), RegistersUtils.AR_C, ALUUtils.SUB),
					new IIB(new BitStream("010011"), RC_T, Bit.array("10101"), RegistersUtils.AR_C, ALUUtils.SUB),
					new IIB(new BitStream("010100"), RR_T, Bit.array("11100"), RegistersUtils.AR_C, ALUUtils.SUB)
			}, // sub 6
			{
					new IIB(new BitStream("010101"), I_T, Bit.array("00100"), RegistersUtils.PC_C, null)
			}, // goto 7
			{
					new IIB(new BitStream("010110"), RI_T, Bit.array("10100"), RegistersUtils.PC_C, ALUUtils.GOTV)
			}, // gotv 8
			{
					new IIB(new BitStream("010111"), RI_T, Bit.array("10100"), RegistersUtils.PC_C, ALUUtils.GOTF)
			}, // gotf 9
			{
					new IIB(new BitStream("011000"), CC_T, Bit.array("00111"), RegistersUtils.CR_C, ALUUtils.EQU),
					new IIB(new BitStream("011001"), CR_T, Bit.array("01110"), RegistersUtils.CR_C, ALUUtils.EQU),
					new IIB(new BitStream("011010"), RC_T, Bit.array("10101"), RegistersUtils.CR_C, ALUUtils.EQU),
					new IIB(new BitStream("011011"), RR_T, Bit.array("11100"), RegistersUtils.CR_C, ALUUtils.EQU)
			}, // equ 10
			{
					new IIB(new BitStream("011100"), CC_T, Bit.array("00111"), RegistersUtils.CR_C, ALUUtils.DIFF),
					new IIB(new BitStream("011101"), CR_T, Bit.array("01110"), RegistersUtils.CR_C, ALUUtils.DIFF),
					new IIB(new BitStream("011110"), RC_T, Bit.array("10101"), RegistersUtils.CR_C, ALUUtils.DIFF),
					new IIB(new BitStream("011111"), RR_T, Bit.array("11100"), RegistersUtils.CR_C, ALUUtils.DIFF)
			}, // diff 11
			{
					new IIB(new BitStream("100000"), CC_T, Bit.array("00111"), RegistersUtils.CR_C, ALUUtils.LOW),
					new IIB(new BitStream("100001"), CR_T, Bit.array("01110"), RegistersUtils.CR_C, ALUUtils.LOW),
					new IIB(new BitStream("100010"), RC_T, Bit.array("10101"), RegistersUtils.CR_C, ALUUtils.LOW),
					new IIB(new BitStream("100011"), RR_T, Bit.array("11100"), RegistersUtils.CR_C, ALUUtils.LOW)
			}, // low 12
			{
					new IIB(new BitStream("100100"), CC_T, Bit.array("00111"), RegistersUtils.CR_C, ALUUtils.LOWEQ),
					new IIB(new BitStream("100101"), CR_T, Bit.array("01110"), RegistersUtils.CR_C, ALUUtils.LOWEQ),
					new IIB(new BitStream("100110"), RC_T, Bit.array("10101"), RegistersUtils.CR_C, ALUUtils.LOWEQ),
					new IIB(new BitStream("100111"), RR_T, Bit.array("11100"), RegistersUtils.CR_C, ALUUtils.LOWEQ)
			}, // loweq 13
			{
					new IIB(new BitStream("101000"), CC_T, Bit.array("00111"), RegistersUtils.CR_C, ALUUtils.GRE),
					new IIB(new BitStream("101001"), CR_T, Bit.array("01110"), RegistersUtils.CR_C, ALUUtils.GRE),
					new IIB(new BitStream("101010"), RC_T, Bit.array("10101"), RegistersUtils.CR_C, ALUUtils.GRE),
					new IIB(new BitStream("101011"), RR_T, Bit.array("11100"), RegistersUtils.CR_C, ALUUtils.GRE)
			}, // gre 14
			{
					new IIB(new BitStream("101100"), CC_T, Bit.array("00111"), RegistersUtils.CR_C, ALUUtils.GREEQ),
					new IIB(new BitStream("101101"), CR_T, Bit.array("01110"), RegistersUtils.CR_C, ALUUtils.GREEQ),
					new IIB(new BitStream("101110"), RC_T, Bit.array("10101"), RegistersUtils.CR_C, ALUUtils.GREEQ),
					new IIB(new BitStream("101111"), RR_T, Bit.array("11100"), RegistersUtils.CR_C, ALUUtils.GREEQ)
			}, // greeq 15
			{
					new IIB(new BitStream("110000"), MC_T, Bit.array("00111"), RegistersUtils.MR_C, ALUUtils.LOAD),
					new IIB(new BitStream("110001"), MR_T, Bit.array("01110"), RegistersUtils.MR_C, ALUUtils.LOAD)
			}, // load 16
			{
					new IIB(new BitStream("110010"), MC_T, Bit.array("00011"), null, ALUUtils.STORE),
					new IIB(new BitStream("110011"), MR_T, Bit.array("01010"), null, ALUUtils.STORE)
			}, // store 17
			{
					// TODO: capire cosa fare con print
					new IIB(new BitStream("110100"), NO_T, Bit.array("00000"), null, null)
			}, // print 18
			{
					// TODO: capire cosa fare con exit
					new IIB(new BitStream("110101"), NO_T, Bit.array("00000"), null, null)
			} // exit 19
	};

	public static IIB getAllData(BitStream instrCode) {//boolean[] instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (instrCode.equals(instruction.data)) {
					return instruction;
				}
			}
		}
		return null;
	}

	/*public static int[] getOperandsDimensions(BitStream instrCode) {//boolean[] instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (instrCode.equals(instruction.data)) {
					return instruction.dimensions;
				}
			}
		}
		return null;
	}

	public static BitStream getFlags(BitStream instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (instrCode.equals(instruction.data)) {
					return instruction.flags;
				}
			}
		}
		return null;
	}

	public static BitStream getWriteRegister(BitStream instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (instrCode.equals(instruction.data)) {
					return instruction.register;
				}
			}
		}
		return null;
	}

	public static BitStream getAluMode(BitStream instrCode) {
		for (IIB[] instructions : organization) {
			for (IIB instruction : instructions) {
				if (instrCode.equals(instruction.data)) {
					return instruction.aluMode;
				}
			}
		}
		return null;
	}*/

	// Instruction Information Block
	public static class IIB {
		BitStream data;
		int[] dimensions;
		Bit[] flags;
		BitStream register;
		BitStream aluMode;

		private IIB(BitStream data, int[] dimensions, Bit[] flags, BitStream register, BitStream aluMode) {
			this.data = data;
			this.dimensions = dimensions;
			this.flags = flags;
			this.register = register;
			this.aluMode = aluMode;
		}

		public BitStream getData() {
			return data; // TODO: clone?
		}

		public int[] getOperandsDimensions() {
			return dimensions;
		}

		public Bit[] getFlags() {
			return flags; // TODO: clone?
		}

		public BitStream getRegister() {
			return register; // TODO: clone?
		}

		public BitStream getAluMode() {
			return aluMode; // TODO: clone?
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
	public static final boolean[] EQU = BinaryUtils.convertAbs(10, 5);
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
	"gotf", "equ", "diff", "low", "loweq", "gre", "greeq", "load", "store", "print", "exit"};

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
