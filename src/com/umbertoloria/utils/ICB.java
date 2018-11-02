package com.umbertoloria.utils;

public class ICB {

	// TODO: use, set (write register), goto, gotv, gotf (locate program-counter), print, exit
	private static ICB USE = new ICB(BinUtils.toRawBools("000000"), 1, BinUtils.toRawBools("00000"), null, null);
	private static ICB SET1 = new ICB(BinUtils.toRawBools("000001"), 2, BinUtils.toRawBools("00111"), null, null);
	private static ICB SET2 = new ICB(BinUtils.toRawBools("000010"), 2, BinUtils.toRawBools("01110"), null, null);
	private static ICB AND1 = new ICB(BinUtils.toRawBools("000011"), 2, BinUtils.toRawBools("00111"), RegistersUtils.LR_C, ALUUtils.AND);
	private static ICB AND2 = new ICB(BinUtils.toRawBools("000100"), 2, BinUtils.toRawBools("01110"), RegistersUtils.LR_C, ALUUtils.AND);
	private static ICB AND3 = new ICB(BinUtils.toRawBools("000101"), 2, BinUtils.toRawBools("10101"), RegistersUtils.LR_C, ALUUtils.AND);
	private static ICB AND4 = new ICB(BinUtils.toRawBools("000110"), 2, BinUtils.toRawBools("11100"), RegistersUtils.LR_C, ALUUtils.AND);
	private static ICB OR1 = new ICB(BinUtils.toRawBools("000111"), 2, BinUtils.toRawBools("00111"), RegistersUtils.LR_C, ALUUtils.OR);
	private static ICB OR2 = new ICB(BinUtils.toRawBools("001000"), 2, BinUtils.toRawBools("01110"), RegistersUtils.LR_C, ALUUtils.OR);
	private static ICB OR3 = new ICB(BinUtils.toRawBools("001001"), 2, BinUtils.toRawBools("10101"), RegistersUtils.LR_C, ALUUtils.OR);
	private static ICB OR4 = new ICB(BinUtils.toRawBools("001010"), 2, BinUtils.toRawBools("11100"), RegistersUtils.LR_C, ALUUtils.OR);
	private static ICB NOT1 = new ICB(BinUtils.toRawBools("001011"), 2, BinUtils.toRawBools("00110"), RegistersUtils.LR_C, ALUUtils.NOT);
	private static ICB NOT2 = new ICB(BinUtils.toRawBools("001100"), 2, BinUtils.toRawBools("10100"), RegistersUtils.LR_C, ALUUtils.NOT);
	private static ICB ADD1 = new ICB(BinUtils.toRawBools("001101"), 2, BinUtils.toRawBools("00111"), RegistersUtils.AR_C, ALUUtils.ADD);
	private static ICB ADD2 = new ICB(BinUtils.toRawBools("001110"), 2, BinUtils.toRawBools("01110"), RegistersUtils.AR_C, ALUUtils.ADD);
	private static ICB ADD3 = new ICB(BinUtils.toRawBools("001111"), 2, BinUtils.toRawBools("10101"), RegistersUtils.AR_C, ALUUtils.ADD);
	private static ICB ADD4 = new ICB(BinUtils.toRawBools("010000"), 2, BinUtils.toRawBools("11100"), RegistersUtils.AR_C, ALUUtils.ADD);
	private static ICB SUB1 = new ICB(BinUtils.toRawBools("010001"), 2, BinUtils.toRawBools("00111"), RegistersUtils.AR_C, ALUUtils.SUB);
	private static ICB SUB2 = new ICB(BinUtils.toRawBools("010010"), 2, BinUtils.toRawBools("01110"), RegistersUtils.AR_C, ALUUtils.SUB);
	private static ICB SUB3 = new ICB(BinUtils.toRawBools("010011"), 2, BinUtils.toRawBools("10101"), RegistersUtils.AR_C, ALUUtils.SUB);
	private static ICB SUB4 = new ICB(BinUtils.toRawBools("010100"), 2, BinUtils.toRawBools("11100"), RegistersUtils.AR_C, ALUUtils.SUB);
	private static ICB GOTO = new ICB(BinUtils.toRawBools("010101"), 1, BinUtils.toRawBools("00100"), null, null);
	private static ICB GOTV = new ICB(BinUtils.toRawBools("010110"), 2, BinUtils.toRawBools("10100"), null, ALUUtils.GOTV);
	private static ICB GOTF = new ICB(BinUtils.toRawBools("010111"), 2, BinUtils.toRawBools("10100"), null, ALUUtils.GOTF);
	private static ICB EQU1 = new ICB(BinUtils.toRawBools("011000"), 2, BinUtils.toRawBools("00111"), RegistersUtils.CR_C, ALUUtils.EQU);
	private static ICB EQU2 = new ICB(BinUtils.toRawBools("011001"), 2, BinUtils.toRawBools("01110"), RegistersUtils.CR_C, ALUUtils.EQU);
	private static ICB EQU3 = new ICB(BinUtils.toRawBools("011010"), 2, BinUtils.toRawBools("10101"), RegistersUtils.CR_C, ALUUtils.EQU);
	private static ICB EQU4 = new ICB(BinUtils.toRawBools("011011"), 2, BinUtils.toRawBools("11100"), RegistersUtils.CR_C, ALUUtils.EQU);
	private static ICB DIFF1 = new ICB(BinUtils.toRawBools("011100"), 2, BinUtils.toRawBools("00111"), RegistersUtils.CR_C, ALUUtils.DIFF);
	private static ICB DIFF2 = new ICB(BinUtils.toRawBools("011101"), 2, BinUtils.toRawBools("01110"), RegistersUtils.CR_C, ALUUtils.DIFF);
	private static ICB DIFF3 = new ICB(BinUtils.toRawBools("011110"), 2, BinUtils.toRawBools("10101"), RegistersUtils.CR_C, ALUUtils.DIFF);
	private static ICB DIFF4 = new ICB(BinUtils.toRawBools("011111"), 2, BinUtils.toRawBools("11100"), RegistersUtils.CR_C, ALUUtils.DIFF);
	private static ICB LOW2 = new ICB(BinUtils.toRawBools("100001"), 2, BinUtils.toRawBools("01110"), RegistersUtils.CR_C, ALUUtils.LOW);
	private static ICB LOW1 = new ICB(BinUtils.toRawBools("100000"), 2, BinUtils.toRawBools("00111"), RegistersUtils.CR_C, ALUUtils.LOW);
	private static ICB LOW3 = new ICB(BinUtils.toRawBools("100010"), 2, BinUtils.toRawBools("10101"), RegistersUtils.CR_C, ALUUtils.LOW);
	private static ICB LOW4 = new ICB(BinUtils.toRawBools("100011"), 2, BinUtils.toRawBools("11100"), RegistersUtils.CR_C, ALUUtils.LOW);
	private static ICB LOWEQ1 = new ICB(BinUtils.toRawBools("100100"), 2, BinUtils.toRawBools("00111"), RegistersUtils.CR_C, ALUUtils.LOWEQ);
	private static ICB LOWEQ2 = new ICB(BinUtils.toRawBools("100101"), 2, BinUtils.toRawBools("01110"), RegistersUtils.CR_C, ALUUtils.LOWEQ);
	private static ICB LOWEQ3 = new ICB(BinUtils.toRawBools("100110"), 2, BinUtils.toRawBools("10101"), RegistersUtils.CR_C, ALUUtils.LOWEQ);
	private static ICB LOWEQ4 = new ICB(BinUtils.toRawBools("100111"), 2, BinUtils.toRawBools("11100"), RegistersUtils.CR_C, ALUUtils.LOWEQ);
	private static ICB GRE1 = new ICB(BinUtils.toRawBools("101000"), 2, BinUtils.toRawBools("00111"), RegistersUtils.CR_C, ALUUtils.GRE);
	private static ICB GRE2 = new ICB(BinUtils.toRawBools("101001"), 2, BinUtils.toRawBools("01110"), RegistersUtils.CR_C, ALUUtils.GRE);
	private static ICB GRE3 = new ICB(BinUtils.toRawBools("101010"), 2, BinUtils.toRawBools("10101"), RegistersUtils.CR_C, ALUUtils.GRE);
	private static ICB GRE4 = new ICB(BinUtils.toRawBools("101011"), 2, BinUtils.toRawBools("11100"), RegistersUtils.CR_C, ALUUtils.GRE);
	private static ICB GREEQ1 = new ICB(BinUtils.toRawBools("101100"), 2, BinUtils.toRawBools("00111"), RegistersUtils.CR_C, ALUUtils.GREEQ);
	private static ICB GREEQ2 = new ICB(BinUtils.toRawBools("101101"), 2, BinUtils.toRawBools("01110"), RegistersUtils.CR_C, ALUUtils.GREEQ);
	private static ICB GREEQ3 = new ICB(BinUtils.toRawBools("101110"), 2, BinUtils.toRawBools("10101"), RegistersUtils.CR_C, ALUUtils.GREEQ);
	private static ICB GREEQ4 = new ICB(BinUtils.toRawBools("101111"), 2, BinUtils.toRawBools("11100"), RegistersUtils.CR_C, ALUUtils.GREEQ);
	private static ICB LOAD1 = new ICB(BinUtils.toRawBools("110000"), 2, BinUtils.toRawBools("00111"), RegistersUtils.MR_C, ALUUtils.LOAD);
	private static ICB LOAD2 = new ICB(BinUtils.toRawBools("110001"), 2, BinUtils.toRawBools("01110"), RegistersUtils.MR_C, ALUUtils.LOAD);
	private static ICB STORE1 = new ICB(BinUtils.toRawBools("110010"), 2, BinUtils.toRawBools("00011"), null, ALUUtils.STORE);
	private static ICB STORE2 = new ICB(BinUtils.toRawBools("110011"), 2, BinUtils.toRawBools("01010"), null, ALUUtils.STORE);
	private static ICB PRINT = new ICB(BinUtils.toRawBools("110100"), 0, BinUtils.toRawBools("00000"), null, null);
	private static ICB EXIT = new ICB(BinUtils.toRawBools("110101"), 0, BinUtils.toRawBools("00000"), null, null);

	static ICB[][] organization = {
			{USE}, {SET1, SET2}, {AND1, AND2, AND3, AND4}, {OR1, OR2, OR3, OR4}, {NOT1, NOT2}, {ADD1, ADD2, ADD3, ADD4},
			{SUB1, SUB2, SUB3, SUB4}, {GOTO}, {GOTV}, {GOTF}, {EQU1, EQU2, EQU3, EQU4}, {DIFF1, DIFF2, DIFF3, DIFF4},
			{LOW1, LOW2, LOW3, LOW4}, {LOWEQ1, LOWEQ2, LOWEQ3, LOWEQ4}, {GRE1, GRE2, GRE3, GRE4},
			{GREEQ1, GREEQ2, GREEQ3, GREEQ4}, {LOAD1, LOAD2}, {STORE1, STORE2}, {PRINT}, {EXIT}
	};

	private boolean[] data;
	private int count;
	private boolean[] flags;
	private boolean[] register;
	private boolean[] aluMode;

	private ICB(boolean[] data, int count, boolean[] flags, boolean[] register, boolean[] aluMode) {
		this.data = data;
		this.count = count;
		this.flags = flags;
		this.register = register;
		this.aluMode = aluMode;
	}

	boolean[] getData() {
		return data;
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
