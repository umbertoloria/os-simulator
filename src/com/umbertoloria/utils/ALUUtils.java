package com.umbertoloria.utils;

public class ALUUtils {

	public static final boolean[] AND = BinUtils.toRawBools("0000");
	public static final boolean[] OR = BinUtils.toRawBools("0001");
	public static final boolean[] NOT = BinUtils.toRawBools("0010");
	public static final boolean[] ADD = BinUtils.toRawBools("0011");
	public static final boolean[] SUB = BinUtils.toRawBools("0100");
	public static final boolean[] GOTV = BinUtils.toRawBools("0101");
	public static final boolean[] GOTF = BinUtils.toRawBools("0110");
	public static final boolean[] EQU = BinUtils.toRawBools("0111");
	public static final boolean[] DIFF = BinUtils.toRawBools("1000");
	public static final boolean[] LOW = BinUtils.toRawBools("1001");
	public static final boolean[] LOWEQ = BinUtils.toRawBools("1010");
	public static final boolean[] GRE = BinUtils.toRawBools("1011");
	public static final boolean[] GREEQ = BinUtils.toRawBools("1100");
	public static final boolean[] LOAD = ADD;
	public static final boolean[] STORE = ADD;

}
