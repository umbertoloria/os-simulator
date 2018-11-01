package com.umbertoloria.utils;

import com.umbertoloria.bittings.BitStream;

public class RegistersUtils {

	// TODO: completare con 8 registri! così si tratta solo di avere 3 bits hahaha
	public static final int REGISTERS_SIZE = 3;
	public static final BitStream PC_C = new BitStream("000");
	public static final BitStream AR_C = new BitStream("001");
	public static final BitStream LR_C = new BitStream("010");
	public static final BitStream MR_C = new BitStream("011");
	public static final BitStream CR_C = new BitStream("100");
	public static final BitStream OR1_C = new BitStream("101");
	public static final BitStream OR2_C = new BitStream("110");

	public static BitStream getRegisterCode(String token) throws RuntimeException {
		switch (token) {
			case "PC":
				return RegistersUtils.PC_C;
			case "AR":
				return RegistersUtils.AR_C;
			case "LR":
				return RegistersUtils.LR_C;
			case "MR":
				return RegistersUtils.MR_C;
			case "CR":
				return RegistersUtils.CR_C;
			case "OR1":
				return RegistersUtils.OR1_C;
			case "OR2":
				return RegistersUtils.OR2_C;
		}
		throw new RuntimeException();
	}

	public static String getRegisterName(BitStream code) {
		if (code.equals(PC_C)) {
			return "PC";
		} else if (code.equals(AR_C)) {
			return "AR";
		} else if (code.equals(LR_C)) {
			return "LR";
		} else if (code.equals(MR_C)) {
			return "MR";
		} else if (code.equals(CR_C)) {
			return "CR";
		} else if (code.equals(OR1_C)) {
			return "OR1";
		} else if (code.equals(OR2_C)) {
			return "OR2";
		}
		throw new RuntimeException();
	}

	public static boolean isRegisterCode(BitStream a) {
		return a.endsWith(PC_C) || a.endsWith(AR_C) || a.endsWith(LR_C) || a.endsWith(MR_C) ||
				a.endsWith(CR_C) || a.endsWith(OR1_C) || a.endsWith(OR2_C);
	}

}
