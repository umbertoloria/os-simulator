package com.umbertoloria.utils;

import java.util.Hashtable;

public class ICB {

	// TODO: use, goto, gotv, gotf (locate program-counter), print, exit
	private static ICB USE = new ICB("000000", 1, "000000", null, null);
	private static ICB SET1 = new ICB("000001", 2, "001010", null, null);
	private static ICB SET2 = new ICB("000010", 2, "011000", null, null);
	private static ICB AND1 = new ICB("000011", 2, "001110", RegistersUtils.LR, ALUUtils.AND);
	private static ICB AND2 = new ICB("000100", 2, "011100", RegistersUtils.LR, ALUUtils.AND);
	private static ICB AND3 = new ICB("000101", 2, "101010", RegistersUtils.LR, ALUUtils.AND);
	private static ICB AND4 = new ICB("000110", 2, "111000", RegistersUtils.LR, ALUUtils.AND);
	private static ICB OR1 = new ICB("000111", 2, "001110", RegistersUtils.LR, ALUUtils.OR);
	private static ICB OR2 = new ICB("001000", 2, "011100", RegistersUtils.LR, ALUUtils.OR);
	private static ICB OR3 = new ICB("001001", 2, "101010", RegistersUtils.LR, ALUUtils.OR);
	private static ICB OR4 = new ICB("001010", 2, "111000", RegistersUtils.LR, ALUUtils.OR);
	private static ICB NOT1 = new ICB("001011", 2, "001100", RegistersUtils.LR, ALUUtils.NOT);
	private static ICB NOT2 = new ICB("001100", 2, "101000", RegistersUtils.LR, ALUUtils.NOT);
	private static ICB ADD1 = new ICB("001101", 2, "001110", RegistersUtils.AR, ALUUtils.ADD);
	private static ICB ADD2 = new ICB("001110", 2, "011100", RegistersUtils.AR, ALUUtils.ADD);
	private static ICB ADD3 = new ICB("001111", 2, "101010", RegistersUtils.AR, ALUUtils.ADD);
	private static ICB ADD4 = new ICB("010000", 2, "111000", RegistersUtils.AR, ALUUtils.ADD);
	private static ICB SUB1 = new ICB("010001", 2, "001110", RegistersUtils.AR, ALUUtils.SUB);
	private static ICB SUB2 = new ICB("010010", 2, "011100", RegistersUtils.AR, ALUUtils.SUB);
	private static ICB SUB3 = new ICB("010011", 2, "101010", RegistersUtils.AR, ALUUtils.SUB);
	private static ICB SUB4 = new ICB("010100", 2, "111000", RegistersUtils.AR, ALUUtils.SUB);
	private static ICB GOTO = new ICB("010101", 1, "000001", null, ALUUtils.GOTO);
	private static ICB GOTV = new ICB("010110", 2, "100001", null, ALUUtils.GOTV);
	private static ICB GOTF = new ICB("010111", 2, "100001", null, ALUUtils.GOTF);
	private static ICB EQU1 = new ICB("011000", 2, "001110", RegistersUtils.CR, ALUUtils.EQU);
	private static ICB EQU2 = new ICB("011001", 2, "011100", RegistersUtils.CR, ALUUtils.EQU);
	private static ICB EQU3 = new ICB("011010", 2, "101010", RegistersUtils.CR, ALUUtils.EQU);
	private static ICB EQU4 = new ICB("011011", 2, "111000", RegistersUtils.CR, ALUUtils.EQU);
	private static ICB DIFF1 = new ICB("011100", 2, "001110", RegistersUtils.CR, ALUUtils.DIFF);
	private static ICB DIFF2 = new ICB("011101", 2, "011100", RegistersUtils.CR, ALUUtils.DIFF);
	private static ICB DIFF3 = new ICB("011110", 2, "101010", RegistersUtils.CR, ALUUtils.DIFF);
	private static ICB DIFF4 = new ICB("011111", 2, "111000", RegistersUtils.CR, ALUUtils.DIFF);
	private static ICB LOW1 = new ICB("100000", 2, "001110", RegistersUtils.CR, ALUUtils.LOW);
	private static ICB LOW2 = new ICB("100001", 2, "011100", RegistersUtils.CR, ALUUtils.LOW);
	private static ICB LOW3 = new ICB("100010", 2, "101010", RegistersUtils.CR, ALUUtils.LOW);
	private static ICB LOW4 = new ICB("100011", 2, "111000", RegistersUtils.CR, ALUUtils.LOW);
	private static ICB LOWEQ1 = new ICB("100100", 2, "001110", RegistersUtils.CR, ALUUtils.LOWEQ);
	private static ICB LOWEQ2 = new ICB("100101", 2, "011100", RegistersUtils.CR, ALUUtils.LOWEQ);
	private static ICB LOWEQ3 = new ICB("100110", 2, "101010", RegistersUtils.CR, ALUUtils.LOWEQ);
	private static ICB LOWEQ4 = new ICB("100111", 2, "111000", RegistersUtils.CR, ALUUtils.LOWEQ);
	private static ICB GRE1 = new ICB("101000", 2, "001110", RegistersUtils.CR, ALUUtils.GRE);
	private static ICB GRE2 = new ICB("101001", 2, "011100", RegistersUtils.CR, ALUUtils.GRE);
	private static ICB GRE3 = new ICB("101010", 2, "101010", RegistersUtils.CR, ALUUtils.GRE);
	private static ICB GRE4 = new ICB("101011", 2, "111000", RegistersUtils.CR, ALUUtils.GRE);
	private static ICB GREEQ1 = new ICB("101100", 2, "001110", RegistersUtils.CR, ALUUtils.GREEQ);
	private static ICB GREEQ2 = new ICB("101101", 2, "011100", RegistersUtils.CR, ALUUtils.GREEQ);
	private static ICB GREEQ3 = new ICB("101110", 2, "101010", RegistersUtils.CR, ALUUtils.GREEQ);
	private static ICB GREEQ4 = new ICB("101111", 2, "111000", RegistersUtils.CR, ALUUtils.GREEQ);
	private static ICB LOAD1 = new ICB("110000", 2, "001110", RegistersUtils.MR, ALUUtils.LOAD);
	private static ICB LOAD2 = new ICB("110001", 2, "011100", RegistersUtils.MR, ALUUtils.LOAD);
	private static ICB STORE1 = new ICB("110010", 2, "000110", null, ALUUtils.STORE);
	private static ICB STORE2 = new ICB("110011", 2, "010100", null, ALUUtils.STORE);
	private static ICB PRINT = new ICB("110100", 0, "000000", null, null);
	private static ICB EXIT = new ICB("110101", 0, "000000", null, null);

	private static Hashtable<String, ICB[]> organizationForType = new Hashtable<>();
	private static Hashtable<String, ICB> organizationForCode = new Hashtable<>();

	public static void init() {
		if (organizationForType.size() == 0) {
			organizationForType.put("use", new ICB[]{USE});
			organizationForType.put("set", new ICB[]{SET1, SET2});
			organizationForType.put("and", new ICB[]{AND1, AND2, AND3, AND4});
			organizationForType.put("or", new ICB[]{OR1, OR2, OR3, OR4});
			organizationForType.put("not", new ICB[]{NOT1, NOT2});
			organizationForType.put("add", new ICB[]{ADD1, ADD2, ADD3, ADD4});
			organizationForType.put("sub", new ICB[]{SUB1, SUB2, SUB3, SUB4});
			organizationForType.put("goto", new ICB[]{GOTO});
			organizationForType.put("gotv", new ICB[]{GOTV});
			organizationForType.put("gotf", new ICB[]{GOTF});
			organizationForType.put("equ", new ICB[]{EQU1, EQU2, EQU3, EQU4});
			organizationForType.put("diff", new ICB[]{DIFF1, DIFF2, DIFF3, DIFF4});
			organizationForType.put("low", new ICB[]{LOW1, LOW2, LOW3, LOW4});
			organizationForType.put("loweq", new ICB[]{LOWEQ1, LOWEQ2, LOWEQ3, LOWEQ4});
			organizationForType.put("gre", new ICB[]{GRE1, GRE2, GRE3, GRE4});
			organizationForType.put("greeq", new ICB[]{GREEQ1, GREEQ2, GREEQ3, GREEQ4});
			organizationForType.put("load", new ICB[]{LOAD1, LOAD2});
			organizationForType.put("store", new ICB[]{STORE1, STORE2});
			organizationForType.put("print", new ICB[]{PRINT});
			organizationForType.put("exit", new ICB[]{EXIT});
		}
		if (organizationForCode.size() == 0) {
			organizationForCode.put("000000", USE);
			organizationForCode.put("000001", SET1);
			organizationForCode.put("000010", SET2);
			organizationForCode.put("000011", AND1);
			organizationForCode.put("000100", AND2);
			organizationForCode.put("000101", AND3);
			organizationForCode.put("000110", AND4);
			organizationForCode.put("000111", OR1);
			organizationForCode.put("001000", OR2);
			organizationForCode.put("001001", OR3);
			organizationForCode.put("001010", OR4);
			organizationForCode.put("001011", NOT1);
			organizationForCode.put("001100", NOT2);
			organizationForCode.put("001101", ADD1);
			organizationForCode.put("001110", ADD2);
			organizationForCode.put("001111", ADD3);
			organizationForCode.put("010000", ADD4);
			organizationForCode.put("010001", SUB1);
			organizationForCode.put("010010", SUB2);
			organizationForCode.put("010011", SUB3);
			organizationForCode.put("010100", SUB4);
			organizationForCode.put("010101", GOTO);
			organizationForCode.put("010110", GOTV);
			organizationForCode.put("010111", GOTF);
			organizationForCode.put("011000", EQU1);
			organizationForCode.put("011001", EQU2);
			organizationForCode.put("011010", EQU3);
			organizationForCode.put("011011", EQU4);
			organizationForCode.put("011100", DIFF1);
			organizationForCode.put("011101", DIFF2);
			organizationForCode.put("011110", DIFF3);
			organizationForCode.put("011111", DIFF4);
			organizationForCode.put("100000", LOW1);
			organizationForCode.put("100001", LOW2);
			organizationForCode.put("100010", LOW3);
			organizationForCode.put("100011", LOW4);
			organizationForCode.put("100100", LOWEQ1);
			organizationForCode.put("100101", LOWEQ2);
			organizationForCode.put("100110", LOWEQ3);
			organizationForCode.put("100111", LOWEQ4);
			organizationForCode.put("101000", GRE1);
			organizationForCode.put("101001", GRE2);
			organizationForCode.put("101010", GRE3);
			organizationForCode.put("101011", GRE4);
			organizationForCode.put("101100", GREEQ1);
			organizationForCode.put("101101", GREEQ2);
			organizationForCode.put("101110", GREEQ3);
			organizationForCode.put("101111", GREEQ4);
			organizationForCode.put("110000", LOAD1);
			organizationForCode.put("110001", LOAD2);
			organizationForCode.put("110010", STORE1);
			organizationForCode.put("110011", STORE2);
			organizationForCode.put("110100", PRINT);
			organizationForCode.put("110101", EXIT);
		}
	}

	public static ICB getICB(boolean[] instrCode) throws RuntimeException {
		String code = BinUtils.toStr(instrCode);
		ICB res = organizationForCode.get(code);
		if (res == null) {
			throw new RuntimeException("ICB not found");
		}
		return res;
	}

	public static boolean[] getInstrCode(String instrType, boolean isOp1Const, boolean isOp2Const) throws RuntimeException {
		int offset;
		if (isOp1Const && isOp2Const) {
			// true true
			offset = 0;
		} else if (isOp1Const) {
			// true false
			offset = 1;
		} else if (isOp2Const) {
			// false true
			offset = 2;
		} else {
			// false false
			offset = 3;
		}
		ICB[] instructions = organizationForType.get(instrType);
		if (instructions == null) {
			throw new RuntimeException("Istruzione sconosciuta: " + instrType);
		}
		return instructions[offset].getInstrCode();
	}

	public static boolean[] getInstrCode(String instrType, boolean isOpConst) throws RuntimeException {
		ICB[] instructions = organizationForType.get(instrType);
		if (instructions == null) {
			throw new RuntimeException("Istruzione sconosciuta: " + instrType);
		}
		return instructions[isOpConst ? 0 : 1].getInstrCode();
	}

	private boolean[] instrCode;
	private int count;
	private boolean[] flags;
	private boolean[] register;
	private boolean[] aluMode;

	private ICB(String instrCode, int count, String flags, boolean[] register, boolean[] aluMode) {
		this.instrCode = BinUtils.toRawBools(instrCode);
		this.count = count;
		this.flags = BinUtils.toRawBools(flags);
		this.register = register;
		this.aluMode = aluMode;
	}

	public boolean[] getInstrCode() {
		return instrCode;
	}

	public int getOperandsCount() {
		return count;
	}

	public boolean[] getFlags() {
		return flags;
	}

	public boolean[] getRegister() {
		return register;
	}

	public boolean[] getAluMode() {
		return aluMode;
	}

}
