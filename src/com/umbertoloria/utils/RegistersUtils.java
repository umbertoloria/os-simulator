package com.umbertoloria.utils;

public class RegistersUtils {

	// TODO: completare con 8 registri! così si tratta solo di avere 3 bits hahaha
	public static final int REGISTERS_SIZE = 3;
	public static final boolean[] PC_CODE = BinaryUtils.toRawBools("000");
	public static final boolean[] AR_CODE = BinaryUtils.toRawBools("001");
	public static final boolean[] LR_CODE = BinaryUtils.toRawBools("010");
	public static final boolean[] MR_CODE = BinaryUtils.toRawBools("011");
	public static final boolean[] CR_CODE = BinaryUtils.toRawBools("100");
	public static final boolean[] OR1_CODE = BinaryUtils.toRawBools("101");
	public static final boolean[] OR2_CODE = BinaryUtils.toRawBools("110");

	public static boolean[] getRegisterCode(String token) throws RuntimeException {
		switch (token) {
			case "PC":
				return RegistersUtils.PC_CODE;
			case "AR":
				return RegistersUtils.AR_CODE;
			case "LR":
				return RegistersUtils.LR_CODE;
			case "MR":
				return RegistersUtils.MR_CODE;
			case "CR":
				return RegistersUtils.CR_CODE;
			case "OR1":
				return RegistersUtils.OR1_CODE;
			case "OR2":
				return RegistersUtils.OR2_CODE;
		}
		throw new RuntimeException();
	}

	public static String getRegisterName(boolean[] code) {
		if (BitsUtils.equals(code, PC_CODE)) {
			return "PC";
		} else if (BitsUtils.equals(code, AR_CODE)) {
			return "AR";
		} else if (BitsUtils.equals(code, LR_CODE)) {
			return "LR";
		} else if (BitsUtils.equals(code, MR_CODE)) {
			return "MR";
		} else if (BitsUtils.equals(code, CR_CODE)) {
			return "CR";
		} else if (BitsUtils.equals(code, OR1_CODE)) {
			return "OR1";
		} else if (BitsUtils.equals(code, OR2_CODE)) {
			return "OR2";
		}
		throw new RuntimeException();
	}

	public static boolean isRegisterCode(boolean[] a) {
		return BitsUtils.endsWith(a, PC_CODE) || BitsUtils.endsWith(a, AR_CODE) || BitsUtils.endsWith(a, LR_CODE) ||
				BitsUtils.endsWith(a, MR_CODE) || BitsUtils.endsWith(a, CR_CODE) || BitsUtils.endsWith(a, OR1_CODE) ||
				BitsUtils.endsWith(a, OR2_CODE);
	}

}
