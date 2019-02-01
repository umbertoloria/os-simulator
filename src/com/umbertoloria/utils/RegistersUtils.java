package com.umbertoloria.utils;

public class RegistersUtils {

	public static final boolean[] AR = BinUtils.toRawBools("000");
	public static final boolean[] LR = BinUtils.toRawBools("001");
	public static final boolean[] CR = BinUtils.toRawBools("010");
	public static final boolean[] MR = BinUtils.toRawBools("011");
	public static final boolean[] OR1 = BinUtils.toRawBools("100");
	public static final boolean[] OR2 = BinUtils.toRawBools("101");

	public static boolean[] getAddressOf(String register) throws RuntimeException {
		switch (register) {
			case "AR":
				return AR;
			case "LR":
				return LR;
			case "CR":
				return CR;
			case "MR":
				return MR;
			case "OR1":
				return OR1;
			case "OR2":
				return OR2;
		}
		throw new RuntimeException("Indirizzo di registro sconosciuto");
	}

}
